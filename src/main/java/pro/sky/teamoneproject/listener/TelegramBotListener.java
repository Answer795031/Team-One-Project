package pro.sky.teamoneproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
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
import pro.sky.teamoneproject.controller.ShelterController;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.exception.AlreadyRegisteredException;
import pro.sky.teamoneproject.model.telegrambot.request.InlineKeyboardButtonBuilder;
import pro.sky.teamoneproject.repository.ShelterRepository;
import pro.sky.teamoneproject.service.ShelterServiceImpl;

import java.util.*;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TelegramBotListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, InlineKeyboardButtonBuilder> inlineButtons = new HashMap<>();

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ShelterRepository shelterRepository;
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

        if (commands.containsKey(receiveMessage)) {
            commands.get(receiveMessage).action(update);
        }
        else {
            // TODO: Вынести в отдельные классы
            switch (receiveMessage) {
                case "Как взять животное?":
                case "Прислать отчет о питомце":
                    telegramBot.execute(new SendMessage(chatId, update.message().text()));
                    break;
            }
        }
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
     * Регистрация команд и кнопок в соответствующие кэши
     */
    private void registrationCommandsAndCallbacks() {
        // Проходим по всем зарегистрированным бинам
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            // Исключаем текущий класс, т.к. происходит зацикливание FIXME: Исправить зацикливание
            if (beanName.equalsIgnoreCase(this.getClass().getSimpleName())
                    || beanName.equalsIgnoreCase(ShelterServiceImpl.class.getSimpleName())
                    || beanName.equalsIgnoreCase(ShelterController.class.getSimpleName())) {
                continue;
            }

            // Получаем экземпляр класса бина
            Object bean = applicationContext.getBean(beanName);

            // Исключаем классы без аннотации Component
            if (!bean.getClass().isAnnotationPresent(Component.class)) {
                continue;
            }

            if (bean instanceof Command) { // Регистрируем команды
                Command command = (Command)bean;
                if (command.getCommand() == null) {
                    logger.error(String.format("Команда бина \"%s\"(%s) равна null!", bean.getClass().getName(), beanName));
                    continue;
                }

                if (!commands.containsKey(command.getCommand())) {
                    registrationCommand(command);
                    logger.info(String.format("Бин \"%s\"(%s) зарегистрирован как команда \"%s\"", bean.getClass().getName(), beanName, command.getCommand()));
                }
            } else if (bean instanceof InlineKeyboardButtonBuilder) { // Регистрируем кнопки
                InlineKeyboardButtonBuilder inlineKeyboardButtonBuilder = (InlineKeyboardButtonBuilder)bean;

                if (inlineButtons.containsKey(inlineKeyboardButtonBuilder.getCallbackData())) {
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

        registrationCommandsAndCallbacks();

        commands.put(BACK, commands.get(INFO_ABOUT_OF_SHELTER)); //TODO: Продумать как переписать

        telegramBot.setUpdatesListener(this);
    }
}
