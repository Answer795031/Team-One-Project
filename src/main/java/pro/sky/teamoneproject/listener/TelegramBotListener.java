package pro.sky.teamoneproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.commands.ShelterDefaultCommand;
import pro.sky.teamoneproject.commands.buttonsforpets.InfoAboutOfPetDefaultCommand;
import pro.sky.teamoneproject.constant.SendReportSteps;
import pro.sky.teamoneproject.controller.PetController;
import pro.sky.teamoneproject.controller.ShelterController;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.exception.AlreadyRegisteredException;
import pro.sky.teamoneproject.model.telegrambot.request.InlineKeyboardButtonBuilder;
import pro.sky.teamoneproject.repository.PetRepository;
import pro.sky.teamoneproject.repository.ShelterClientRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;
import pro.sky.teamoneproject.service.PetServiceImpl;
import pro.sky.teamoneproject.service.ShelterServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.BACK_TO_MAIN_MENU;
import static pro.sky.teamoneproject.constant.ConstantsForShelter.GET_SHELTER_CLIENT_NUMBER;
import static pro.sky.teamoneproject.constant.SendReportSteps.*;
import static pro.sky.teamoneproject.constant.ShelterClientMode.*;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TelegramBotListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, InlineKeyboardButtonBuilder> inlineButtons = new HashMap<>();

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ShelterClientRepository shelterClientRepository;
    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private GenericApplicationContext applicationContext;

    /**
     * Обработчик полученных обновлений (сообщения, callback)
     * @param updates полученные обновления (сообщения, callback)
     * @return статус обработанного обновления
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            try {
                if (update.message() != null) {
                    processMessage(update);
                } else if (update.callbackQuery() != null) {
                    processCallbackQuery(update);
                }
            } catch (NullPointerException e) {
                logger.error(e.getMessage());
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Обработчик полученных сообщений
     * @param update полученное обновление от бота (сообщения, callback)
     */
    private void processMessage(Update update) {
        long chatId = update.message().chat().id();
        String receiveMessage = update.message().text();

        if (!isRegisteredUser(chatId)) {
            commands.get("/start").action(update);
            return;
        }

        if (receiveMessage != null) {
            if (!(commands.get(receiveMessage) instanceof ShelterDefaultCommand)
                    && !isValidUser(chatId)) {
                commands.get("/start").action(update);
                return;
            }

            if (commands.containsKey(receiveMessage)) {
                commands.get(receiveMessage).action(update);
                return;
            }

            if (processReceiveShelterClientNumber(update)) {
                return;
            }
        }

        processReceivePetReport(update);
    }

    private boolean processReceivePetReport(Update update) {
        long chatId = update.message().chat().id();
        Message receiveMessage = update.message();
        ShelterClient shelterClient = shelterClientRepository.findByChatId(chatId).get();

        if (shelterClient.getSelectedMode() == SEND_PET_INFO) {
            SendReportSteps reportStep = shelterClient.getSendReportSteps();

            if (reportStep == SEND_PHOTO) {
                if (receiveMessage.photo() == null) {
                    SendMessage sendMessage = new SendMessage(chatId, "Вышлите фото питомца");
                    sendMessage.replyMarkup(new ReplyKeyboardMarkup(new String[][]{{BACK_TO_MAIN_MENU}}, true, false, false));
                    telegramBot.execute(sendMessage);
                } else {
                    try {
                        PhotoSize[] receivedPhotos = receiveMessage.photo();
                        String fileId = receivedPhotos[receivedPhotos.length - 1].fileId();
                        File downloadedFile = downloadFile(fileId, String.format("./PetReports/%s.jpg", fileId));
                        logger.info("Файл сохранен по пути {}", downloadedFile.getAbsolutePath());

                        SendMessage sendMessage = new SendMessage(chatId, "Вышлите рацион питомца");
                        sendMessage.replyMarkup(new ReplyKeyboardMarkup(new String[][]{{BACK_TO_MAIN_MENU}}, true, false, false));
                        telegramBot.execute(sendMessage);

                        shelterClient.setSendReportSteps(SEND_DIET);
                        shelterClientRepository.save(shelterClient);
                    } catch (IOException e) {
                        SendMessage sendMessage = new SendMessage(chatId, "Произошла ошибка, вышлите фото питомца");
                        sendMessage.replyMarkup(new ReplyKeyboardMarkup(new String[][]{{BACK_TO_MAIN_MENU}}, true, false, false));
                        telegramBot.execute(sendMessage);
                    }
                }
            } else if (reportStep == SEND_DIET) {
                //TODO: Сохранение рациона питомца
                telegramBot.execute(new SendMessage(chatId, "*** Добавить функционал сохранения рациона питомца ***"));

                SendMessage sendMessage = new SendMessage(chatId, "Вышлите информацию об общем самочувствие и привыкание к новому месту");
                sendMessage.replyMarkup(new ReplyKeyboardMarkup(new String[][]{{BACK_TO_MAIN_MENU}}, true, false, false));
                telegramBot.execute(sendMessage);

                shelterClient.setSendReportSteps(SEND_HEALTH_AND_ADAPTATION);
                shelterClientRepository.save(shelterClient);
            } else if (reportStep == SEND_HEALTH_AND_ADAPTATION) {
                //TODO: Сохранение информации об общем самочувствии и привыкании к новому месту
                telegramBot.execute(new SendMessage(chatId, "*** Добавить функционал сохранения информации об общем самочувствии и привыкании к новому месту ***"));

                SendMessage sendMessage = new SendMessage(chatId, "Вышлите изменения в поведении: отказ от старых привычек, приобретение новых.");
                sendMessage.replyMarkup(new ReplyKeyboardMarkup(new String[][]{{BACK_TO_MAIN_MENU}}, true, false, false));
                telegramBot.execute(sendMessage);

                shelterClient.setSendReportSteps(SEND_CHANGES_IN_BEHAVIOR);
                shelterClientRepository.save(shelterClient);
            } else if (reportStep == SEND_CHANGES_IN_BEHAVIOR) {
                // TODO: Сохранение изменения в поведении: отказ от старых привычек, приобретение новых.
                telegramBot.execute(new SendMessage(chatId, "*** Добавить функционал сохранения изменения в поведении: отказ от старых привычек, приобретение новых. ***"));

                SendMessage sendMessage = new SendMessage(chatId, "Отчет сохранен");
                telegramBot.execute(sendMessage);

                commands.get(BACK_TO_MAIN_MENU).action(update);
            }

            return true;
        }

        return false;
    }

    public File downloadFile(String fileId, String outputFilePath) throws IOException {
        File outputFile = new File(outputFilePath);
        if (!outputFile.getParentFile().exists()) {
            //noinspection ResultOfMethodCallIgnored
            outputFile.getParentFile().mkdirs();
        }

        GetFile getFile = new GetFile(fileId);
        GetFileResponse response = telegramBot.execute(getFile);

        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(telegramBot.getFileContent(response.file()));
        fos.close();

        return outputFile;
    }

    private boolean processReceiveShelterClientNumber(Update update) {
        long chatId = update.message().chat().id();
        String receiveMessage = update.message().text();
        ShelterClient shelterClient = shelterClientRepository.findByChatId(chatId).get();

        if (shelterClient.getSelectedMode() == GET_PHONE_NUMBER){
            if (isValidPhoneNumber(receiveMessage)){
                shelterClient.setPhoneNumber(receiveMessage);
                SendMessage sendMessage = new SendMessage(chatId, "Ваш номер: "+receiveMessage);
                telegramBot.execute(sendMessage);
                shelterClient.setSelectedMode(DEFAULT);
                shelterClientRepository.save(shelterClient);
            } else {
                commands.get(GET_SHELTER_CLIENT_NUMBER).action(update);
            }

            return true;
        }

        return false;
    }

    private boolean isValidPhoneNumber(String receiveMessage) {
        Pattern pattern = Pattern.compile("([+][0-9]{11})");
        Matcher matcher = pattern.matcher(receiveMessage);

        return matcher.matches();
    }

    /**
     * Обработчик полученных callback от кнопок сообщений
     * @param update полученное обновление от бота (сообщения, callback)
     */
    private void processCallbackQuery(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        String callbackData = callbackQuery.data().toLowerCase();

        if (inlineButtons.containsKey(callbackData)) {
            inlineButtons.get(callbackData).onClick(update);
        }

        AnswerCallbackQuery answer = new AnswerCallbackQuery(callbackQuery.id());
        telegramBot.execute(answer);
    }

    /**
     * Проверка зарегистрирован ли пользователь
     * @param chatId id пользователя
     * @return true - проверка прошла успешно, false - проверка не прошла
     */
    private boolean isRegisteredUser(long chatId) {
        return shelterClientRepository.findByChatId(chatId).isPresent();
    }

    /**
     * Проверка валидации выбора приюта
     * @param chatId id пользователя
     * @return true - проверка прошла успешно, false - проверка не прошла
     */
    private boolean isValidUser(long chatId) {
        ShelterClient shelter = shelterClientRepository.findByChatId(chatId).get();

        if (shelter.getLastTimeAppeal() == null) {
            return true;
        }

        return LocalDateTime.now().isBefore(shelter.getLastTimeAppeal().plusDays(1));
    }

    /**
     * Регистрация команды в кэш
     * @param command - команда
     */
    private void registrationCommand(Command command) {
        if (commands.containsKey(command.getCommand())) {
            throw new AlreadyRegisteredException(String.format("Команда \"%s\" уже зарегистрирована!", command.getCommand()));
        }

        commands.put(command.getCommand(), command);
    }

    /**
     * Регистрация кнопки в кэш
     * @param inlineKeyboardButtonBuilder - кнопка
     */
    private void registrationInlineButton(InlineKeyboardButtonBuilder inlineKeyboardButtonBuilder) {
        if (inlineButtons.containsKey(inlineKeyboardButtonBuilder.getCallbackData())) {
            throw new AlreadyRegisteredException(String.format("Кнопка \"%s\" уже зарегистрирована!", inlineKeyboardButtonBuilder.getCallbackData()));
        }

        inlineButtons.put(inlineKeyboardButtonBuilder.getCallbackData(), inlineKeyboardButtonBuilder);
    }

    /**
     * Выгрузка приютов с БД и регистрация их как бины
     */
    private void registrationShelterBeans() {
        if (applicationContext.containsBeanDefinition("shelterDefaultCommand")) {
            applicationContext.removeBeanDefinition("shelterDefaultCommand");
        }

        for (Shelter shelter : shelterRepository.getAll()) {
            if (commands.containsKey(shelter.getName())) {
                continue;
            }

            applicationContext.registerBean(shelter.getName(), ShelterDefaultCommand.class);
            ((Command)applicationContext.getBean(shelter.getName())).setCommand(shelter.getName());
        }
    }

    /**
     * Выгрузка питомцев с БД и регистрация их как бины
     */
    private void registrationPetsBeans() {
        if (applicationContext.containsBeanDefinition("infoAboutOfPetDefaultCommand")) {
            applicationContext.removeBeanDefinition("infoAboutOfPetDefaultCommand");
        }

        for (Pet pet : petRepository.getAll()) {
            if (commands.containsKey(pet.getName())) {
                continue;
            }

            applicationContext.registerBean(pet.getName(), InfoAboutOfPetDefaultCommand.class);
            ((Command)applicationContext.getBean(pet.getName())).setCommand(pet.getName());
        }
    }

    /**
     * Обновление списка приютов.
     */
    public void updateSheltersCommand() {
        commands.values().removeIf(command -> {
            boolean isRemove = command instanceof ShelterDefaultCommand;

            if (isRemove) {
                applicationContext.removeBeanDefinition(command.getCommand());
            }

            return isRemove;
        });

        registrationShelterBeans();
        registrationCommandsAndCallbacks();
    }

    /**
     * Обновление списка питомцев.
     */
    public void updatePetsCommand() {
        commands.values().removeIf(command -> {
            boolean isRemove = command instanceof InfoAboutOfPetDefaultCommand;

            if (isRemove) {
                applicationContext.removeBeanDefinition(command.getCommand());
            }

            return isRemove;
        });

        registrationPetsBeans();
        registrationCommandsAndCallbacks();
    }

    /**
     * Регистрация команд и кнопок в соответствующие кэши
     */
    private void registrationCommandsAndCallbacks() {
        // Проходим по всем зарегистрированным бинам
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            // Исключаем текущий класс, т.к. происходит зацикливание FIXME: Исправить зацикливание
            if (beanName.equalsIgnoreCase(this.getClass().getSimpleName())
                    || beanName.equalsIgnoreCase(ShelterServiceImpl.class.getSimpleName())
                    || beanName.equalsIgnoreCase(ShelterController.class.getSimpleName())
                    || beanName.equalsIgnoreCase(PetServiceImpl.class.getSimpleName())
                    || beanName.equalsIgnoreCase(PetController.class.getSimpleName())) {
                continue;
            }

            // Получаем экземпляр класса бина
            Object bean = applicationContext.getBean(beanName);

            // Исключаем классы без аннотации Component
            if (!bean.getClass().isAnnotationPresent(Component.class)) {
                continue;
            }

            if (bean instanceof Command command) { // Регистрируем команды
                if (command.getCommand() == null) {
                    logger.error(String.format("Команда бина \"%s\"(%s) равна null!", bean.getClass().getName(), beanName));
                    continue;
                }

                if (!commands.containsKey(command.getCommand())) {
                    registrationCommand(command);
                    logger.info(String.format("Бин \"%s\"(%s) зарегистрирован как команда \"%s\"", bean.getClass().getName(), beanName, command.getCommand()));
                }
            } else if (bean instanceof InlineKeyboardButtonBuilder inlineKeyboardButtonBuilder) { // Регистрируем кнопки

                if (!inlineButtons.containsKey(inlineKeyboardButtonBuilder.getCallbackData())) {
                    registrationInlineButton(inlineKeyboardButtonBuilder);
                    logger.info(String.format("Бин \"%s\"(%s) зарегистрирован как кнопка с келлбеком \"%s\"", bean.getClass().getName(), beanName, inlineKeyboardButtonBuilder.getCallbackData()));
                }
            } else { // Выводим незарегистрированные бины
                logger.warn(String.format("Бин \"%s\"(%s) не зарегистрирован", bean.getClass().getName(), beanName));
            }
        }
    }

    /**
     * Инициализация бота
     */
    @PostConstruct
    private void init() {
        registrationShelterBeans();
        registrationPetsBeans();

        registrationCommandsAndCallbacks();
        telegramBot.setUpdatesListener(this);
    }
}
