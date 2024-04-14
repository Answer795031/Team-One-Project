package pro.sky.teamoneproject.commands.buttominfoaboutshelter;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
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
        SendMessage sendMessage = new SendMessage(chatId, "" +
                "Мы рады видеть наших гостей ежедневно с 10.00 до 16.00 часов по адресу: ул. Никому не известная, д. 10 А\n" +
                "\n" +
                "НАШ ПРИЮТ ПЕРЕПОЛНЕН, ПРИЁМА ЖИВОТНЫХ НЕТ!\n" +
                "\n" +
                "Гости до 18 лет В СОПРОВОЖДЕНИИ РОДИТЕЛЕЙ!\n" +
                "\n" +
                "ТЕЛЕФОН ПРИЮТА: 57-60-66\n" +
                "\n" +
                "Руководитель Приюта Девушка на ресепшене\n" +
                "с 9.00 ч. до 17.00 ч. по будням 8-912-873-97-65.\n" +
                "\n" +
                "КОРМИТЬ животных без разрешения сотрудников Приюта ЗАПРЕЩЕНО!\n" +
                "\n" +
                "Прогулки с собаками ежедневно с 12 до 15 часов.\n" +
                "\n");
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
