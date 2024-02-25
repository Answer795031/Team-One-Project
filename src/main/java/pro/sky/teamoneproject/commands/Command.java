package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import pro.sky.teamoneproject.repository.ClientRepository;

public abstract class Command {
    protected TelegramBot telegramBot;
    protected ClientRepository clientRepository;

    public Command(TelegramBot telegramBot, ClientRepository clientRepository) {
        this.telegramBot = telegramBot;
        this.clientRepository = clientRepository;
    }

    /**
     * Действие команды
     * @param update полученные обновления от бота
     */
    public abstract void action(Update update);
}
