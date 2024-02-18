package pro.sky.teamoneproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TelegramBot telegramBot;

    /**
     * Обработчик полученных обновлений (сообщения, callback)
     * @param updates полученные обновления (сообщения, callback)
     * @return статус обработанного обновления
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(this::processMessage);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Обработчик полученных сообщений
     * @param update полученные обновления
     */
    private void processMessage(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage;

        switch (update.message().text()) {
            case "/start":
                sendMessage = new SendMessage(chatId, "*** Приветственное сообщение ***"); //TODO: Придумать приветственное сообщение
                sendMessage.replyMarkup(getReplyKeyboard());
                telegramBot.execute(sendMessage);
                break;
            case "Узнать информацию о приюте":
            case "Как взять животное?":
            case "Прислать отчет о питомце":
            case "Позвать волонтера":
                telegramBot.execute(new SendMessage(chatId, update.message().text()));
                break;
        }
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {"Узнать информацию о приюте"},
                {"Как взять животное?"},
                {"Прислать отчет о питомце"},
                {"Позвать волонтера"}
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }

    /**
     * Инициализация бота
     */
    @PostConstruct
    private void init() {
        telegramBot.setUpdatesListener(this);
    }
}
