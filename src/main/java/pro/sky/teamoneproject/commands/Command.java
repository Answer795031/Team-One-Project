package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.model.Update;

public abstract class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Действие команды
     * @param update полученное обновление от бота (сообщения, callback)
     */
    public abstract void action(Update update);
}
