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
public class BackToPetMenuCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public BackToPetMenuCommand() {
        super(BACK_TO_PET_MENU);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId, "Пожалуйста, ознакомьтесь со всеми пунктами!");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {RULES_OF_MEETING_WITH_PET},
                {LIST_OF_DOCUMENTS},
                {TRANSPORTATION_RECOMMENDATIONS},
                {HOME_IMPROVEMENT_TIPS},
                {FIRST_MEETING_WITH_PET},
                {DOG_HANDLERS_RECOMMENDATIONS},
                {RULES_OF_SERVICE},
                {CALL_VOLUNTEER},
                {BACK_TO_SELECT_PETS}
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }
}
