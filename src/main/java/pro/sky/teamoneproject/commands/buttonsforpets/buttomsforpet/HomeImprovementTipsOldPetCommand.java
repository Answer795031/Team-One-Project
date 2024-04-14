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
public class HomeImprovementTipsOldPetCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public HomeImprovementTipsOldPetCommand() {
        super(HOME_IMPROVEMENT_TIPS_OLD_PET);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId, "Рекомендации по обустройству дома для взрослого питомца:\n" +
                "1.Уютное место для сна и отдыха: Предоставьте вашему питомцу удобное место для сна и отдыха, такое как мягкая " +
                "постель или специальная кошачья/собачья кровать. Обратите внимание, чтобы место для сна было защищено" +
                " от сквозняков и располагалось в тихом уголке дома.\n" +
                "\n" +
                "2.Игровое пространство: Выделите определенную зону для игр вашего питомца, где он может безопасно" +
                " развлекаться. Разместите игрушки, когтеточки (для кошек), мячи и другие развивающие элементы, " +
                "чтобы стимулировать его активность и интеллект.\n" +
                "\n" +
                "3.Регулярное упражнение: Обеспечьте вашему питомцу достаточно места для физической активности. " +
                "Если у вас есть собака, регулярные прогулки и возможность для бега будут очень важны. Для кошек " +
                "предусмотрите доступ к игровым и разгрузочным зонам, а также к лазалкам.");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {BACK_TO_EXTRA_PET_MENU}
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
