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
public class HomeImprovementTipsYoungPetCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public HomeImprovementTipsYoungPetCommand() {
        super(HOME_IMPROVEMENT_TIPS_YOUNG_PET);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId, "Рекомендации по обустройству дома для молодого питомца:" +
                "1.Безопасное пространство: Обеспечьте место, где ваш питомец может чувствовать себя безопасно и комфортно. " +
                "Убедитесь, что нет опасных предметов, к которым он может достать, и что нет опасных зон, например, укройте " +
                "провода и опасные растения.\n" +
                "\n" +
                "2.Удобные места для отдыха и игры:" +
                " Предоставьте вашему питомцу места для отдыха и игры, такие как игрушки, когтеточки, постель или" +
                " специальное место для отдыха. Это поможет занять его внимание и предотвратить скучные поведенческие" +
                " проблемы.\n" +
                "\n" +
                "3.Обучение и социализация: Постарайтесь начать обучение и социализацию своего питомца сразу после его" +
                " появления в доме. Это поможет ему лучше адаптироваться к новой обстановке и стать хорошо воспитанным " +
                "членом семьи.");
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
