package pro.sky.teamoneproject.commands.buttonsforshelters;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class ShelterWorksScheduleCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public ShelterWorksScheduleCommand() {
        super(SHELTER_WORKS_SCHEDULE);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId, "Какая то инфа c с расписанием");
        telegramBot.execute(sendMessage);
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
