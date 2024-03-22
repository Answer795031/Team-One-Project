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
public class BackToExtraPetMenuCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public BackToExtraPetMenuCommand() {
        super(BACK_TO_EXTRA_PET_MENU);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        String messageText = update.message().text();
        SendMessage sendMessage = new SendMessage(chatId, "Выберите интересующий Вас раздел");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {HOME_IMPROVEMENT_TIPS_YOUNG_PET},
                {HOME_IMPROVEMENT_TIPS_OLD_PET},
                {HOME_IMPROVEMENT_TIPS_INVALID_PET},
                {BACK_TO_PET_MENU}
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }
}
