package pro.sky.teamoneproject.commands.returnbuttons;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class BackToMainMenuCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public BackToMainMenuCommand() {
        super(BACK_TO_MAIN_MENU);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        String messageText = update.message().text();
        SendMessage sendMessage = new SendMessage(chatId, "Доступны следующие команды");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {INFO_ABOUT_OF_SHELTER}, //TODO: Вынести в константы
                {HOW_YOU_CAN_TAKE_PET}, //TODO: Вынести в константы
                {SEND_REPORT_ABOUT_OF_PET}, //TODO: Вынести в константы
                {CALL_VOLUNTEER}
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }
}
