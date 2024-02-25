package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.model.Update;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command command1)) return false;

        return Objects.equals(command, command1.command);
    }

    @Override
    public int hashCode() {
        return command != null ? command.hashCode() : 0;
    }

    /**
     * Действие команды
     * @param update полученное обновление от бота (сообщения, callback)
     */
    public abstract void action(Update update);
}
