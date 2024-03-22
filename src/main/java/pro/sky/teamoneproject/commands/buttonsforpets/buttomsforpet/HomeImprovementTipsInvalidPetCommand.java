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
public class HomeImprovementTipsInvalidPetCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public HomeImprovementTipsInvalidPetCommand() {
        super(HOME_IMPROVEMENT_TIPS_INVALID_PET);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId,
                "Когда речь идет о питомце с ограниченными возможностями, " +
                "важно создать специальные условия для его комфортного и безопасного пребывания в доме. Вот несколько рекомендаций:\n" +
                "\n" +
                "1.Адаптивное пространство: Создайте пространство, которое легко доступно и безопасно для вашего питомца. " +
                "Подумайте о его ограниченных возможностях и адаптируйте окружающую среду: сделайте пути проезда шире и более " +
                "доступными, уберите препятствия и установите специальные подъемники или рампы, если это необходимо.\n" +
                "\n" +
                "2.Удобные условия для сна и отдыха: Предоставьте вашему питомцу мягкое и безопасное место для отдыха, которое" +
                " легко доступно. Обратите внимание на материалы, из которых изготовлены постельное белье и мягкие поверхности, чтобы" +
                " они были безопасными для вашего питомца.\n" +
                "\n" +
                "3Специальные игрушки и развлечения: Подберите игрушки и развлечения, которые соответствуют специфическим " +
                "потребностям вашего питомца. Например, для слепых животных можно использовать игрушки с звуковыми сигналами, " +
                "а для питомцев с ограниченным движением - игрушки, которые легко захватить и манипулировать.\n" +
                "\n" +
                "4.Регулярный медицинский уход: Обеспечьте вашему питомцу регулярный медицинский уход и контрольное " +
                "обследование ветеринаром. Следите за его состоянием здоровья и обеспечьте необходимое лечение и уход " +
                "в соответствии с его потребностями.\n" +
                "\n" +
                "Важно также обращаться за советом к ветеринарному специалисту или ветреабилитологу, чтобы получить " +
                "индивидуальные рекомендации по уходу за питомцем с ограниченными возможностями.");
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
