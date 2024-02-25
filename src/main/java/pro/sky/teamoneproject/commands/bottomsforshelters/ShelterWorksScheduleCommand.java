package pro.sky.teamoneproject.commands.bottomsforshelters;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.ShelterWorksSchedule;
import static pro.sky.teamoneproject.constant.ConstantsForShelter.back;

@Component
public class ShelterWorksScheduleCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public ShelterWorksScheduleCommand() {
        super(ShelterWorksSchedule);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId, "Какая то инфа c с расписанием");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                { back }
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
