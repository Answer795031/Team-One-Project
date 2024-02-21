package pro.sky.teamoneproject.commands.bottomsforshelters;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.repository.ClientRepository;

public class ShelterWorksScheduleCommand extends Command {
    public ShelterWorksScheduleCommand(TelegramBot telegramBot, ClientRepository clientRepository) {
        super(telegramBot, clientRepository);
    }
    final String back = "Вернуться к меню приюта";

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
//        String messageText = update.message().text();
        SendMessage sendMessage = new SendMessage(chatId, "Какая то инфа");
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
}
