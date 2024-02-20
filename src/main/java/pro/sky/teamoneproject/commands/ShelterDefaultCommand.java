package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.teamoneproject.repository.ClientRepository;

public class ShelterDefaultCommand extends Command {
    public ShelterDefaultCommand(TelegramBot telegramBot, ClientRepository clientRepository) {
        super(telegramBot, clientRepository);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        String messageText = update.message().text();
        SendMessage sendMessage = new SendMessage(chatId, "Для " + messageText + ", доступны следующие команды");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {"Узнать информацию о приюте"}, //TODO: Вынести в константы
                {"Как взять животное?"}, //TODO: Вынести в константы
                {"Прислать отчет о питомце"}, //TODO: Вынести в константы
                {"Позвать волонтера"} //TODO: Вынести в константы
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }
}
