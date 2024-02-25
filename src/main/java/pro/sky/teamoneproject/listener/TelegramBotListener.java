package pro.sky.teamoneproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendContact;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.teamoneproject.commands.*;
import pro.sky.teamoneproject.commands.bottomsforshelters.*;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.repository.ClientRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

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
        commands.put(InfoAboutOfShelter, new InfoAboutOfShelterCommand(telegramBot, clientRepository));
            commands.put(ShelterWorksSchedule, new ShelterWorksScheduleCommand(telegramBot, clientRepository));
            commands.put(AdressOfShelter, new AdressOfShelterCommand(telegramBot, clientRepository));
            commands.put(LocationMap, new LocationMapCommand(telegramBot, clientRepository));
            commands.put(Propusk, new PropuskCommand(telegramBot, clientRepository));
                commands.put(back, new InfoAboutOfShelterCommand(telegramBot, clientRepository));
             commands.put(CallVolunteer, new CallVolunteerCommand(telegramBot, clientRepository));
 }
}
