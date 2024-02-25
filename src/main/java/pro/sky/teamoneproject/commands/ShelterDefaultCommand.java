package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

public class ShelterDefaultCommand extends Command {
    @Autowired
    protected TelegramBot telegramBot;

    public ShelterDefaultCommand() {
        super(null);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        String messageText = update.message().text();
        SendMessage sendMessage = new SendMessage(chatId, "Для приюта \"" + messageText + "\", доступны следующие команды");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {InfoAboutOfShelter}, //TODO: Вынести в константы
                {HowYouCanTakePet}, //TODO: Вынести в константы
                {SendReportAboutOfPet}, //TODO: Вынести в константы
                {CallVolunteer  } //TODO: Вынести в константы
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }
}
