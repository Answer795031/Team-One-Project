package pro.sky.teamoneproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendContact;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.commands.ShelterDefaultCommand;
import pro.sky.teamoneproject.commands.StartCommand;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.repository.ClientRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TelegramBotListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map<String, Command> commands = new HashMap<>();

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ShelterRepository shelterRepository;

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
     * @param update полученные обновления
     */
    private void processMessage(Update update) {
        long chatId = update.message().chat().id();
        String receiveMessage = update.message().text();

        if (commands.containsKey(receiveMessage)) {
            commands.get(receiveMessage).action(update);
        }
//        else {
//            // TODO: Вынести в отдельные классы
//            switch (receiveMessage) {
//                case "Узнать информацию о приюте":
//                case "Как взять животное?":
//                case "Прислать отчет о питомце":
//                    telegramBot.execute(new SendMessage(chatId, update.message().text()));
//                    break;
//                case "Позвать волонтера":
//                    SendMessage message = new SendMessage(chatId, "Позвать волонтера можно следующими способами");
//                    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
//
//                    InlineKeyboardButton fromPhone = new InlineKeyboardButton("По номеру телефона"); //TODO: Назвать нормально переменную
//                    fromPhone.callbackData("phone-helper"); //TODO: Назвать нормально, вынести в константы
//
//                    InlineKeyboardButton fromNikName = new InlineKeyboardButton("По никнейму телеграм"); //TODO: Назвать нормально переменную
//                    fromNikName.callbackData("nikname-helper"); //TODO: Назвать нормально, вынести в константы
//
//                    InlineKeyboardButton fromBot = new InlineKeyboardButton("Через бота"); //TODO: Назвать нормально переменную
//                    fromBot.callbackData("from-bot-helper"); //TODO: Назвать нормально, вынести в константы
//
//                    keyboardMarkup.addRow(fromPhone);
//                    keyboardMarkup.addRow(fromNikName);
//                    keyboardMarkup.addRow(fromBot);
//
//                    message.replyMarkup(keyboardMarkup);
//
//                    telegramBot.execute(message);
//                    break;
//            }
//        }
    }

    /**
     * Обработчик полученных callback от кнопок сообщений
     * @param update полученные обновления
     */
    private void processCallbackQuery(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        long chatId = callbackQuery.message().chat().id();

        if (callbackQuery.data().equals("phone-helper")) {
            SendContact sendContact = new SendContact(chatId, "7-800-555-35-35", "Проще позвонить чем просто написать");
            telegramBot.execute(sendContact);
        } else if (callbackQuery.data().equals("nikname-helper")) {
            SendMessage sendMessage = new SendMessage(chatId,
                    """
                            @saver_cat
                            @Tretkir99
                            @AlexeySamohvalov
                            @MTarasov13
                            """);
            telegramBot.execute(sendMessage);
        } else if (callbackQuery.data().equals("from-bot-helper")) {
            //TODO: Реализовать
        }

        AnswerCallbackQuery answer = new AnswerCallbackQuery(callbackQuery.id());
        telegramBot.execute(answer);
    }

    private Map<String, Command> getShelters() {
        Map<String, Command> shelters = new HashMap<>();
        for (Shelter shelter : shelterRepository.getAll()) {
            shelters.put(shelter.getName(), new ShelterDefaultCommand(telegramBot, clientRepository));
        }
        return shelters;
    }

    /**
     * Инициализация бота
     */
    @PostConstruct
    private void init() {
        telegramBot.setUpdatesListener(this);

        commands.put("/start", new StartCommand(telegramBot, clientRepository, shelterRepository));
        commands.putAll(getShelters());
        commands.put("Узнать информацию о приюте", new InfoAboutOfShelterCommand(telegramBot, clientRepository));
            commands.put("Расписание работы приюта", new ShelterWorksScheduleCommand(telegramBot, clientRepository));
                commands.put("Вернуться к меню приюта", new InfoAboutOfShelterCommand(telegramBot, clientRepository));
 }
}
