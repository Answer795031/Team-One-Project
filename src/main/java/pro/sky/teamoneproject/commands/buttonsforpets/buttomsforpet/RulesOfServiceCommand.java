package pro.sky.teamoneproject.commands.buttonsforpets.buttomsforpet;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class RulesOfServiceCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public RulesOfServiceCommand() {
        super(RULES_OF_SERVICE);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId, "Вам могут отказать в сервисе по следующим пунктам:\n1...\n2...\n3...");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {BACK_TO_PET_MENU}
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
