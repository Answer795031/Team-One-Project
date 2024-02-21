package pro.sky.teamoneproject.commands.bottomsforshelters;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.repository.ClientRepository;

public class InfoAboutOfShelterCommand extends Command {
    public InfoAboutOfShelterCommand(TelegramBot telegramBot, ClientRepository clientRepository) {
        super(telegramBot, clientRepository);
    }
    public final String ShelterWorksSchedule = "Расписание работы приюта";
    public final String AdressOfShelter = "Адрес приюта";
    public final String LocationMap = "Схема проезда";
    public final String Propusk  = "Выдать пропуск";//TODO ПРИДУМАЙ НОРМ НАЗВАНИЕ
    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        String messageText = update.message().text();
        SendMessage sendMessage = new SendMessage(chatId, "Для того чтобы " + messageText.toLowerCase() + ", доступны следующие команды");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                { ShelterWorksSchedule },
                { AdressOfShelter },
                { LocationMap },
                { Propusk }
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }
}
