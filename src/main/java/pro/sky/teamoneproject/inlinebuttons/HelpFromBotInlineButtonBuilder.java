package pro.sky.teamoneproject.inlinebuttons;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.model.telegrambot.request.InlineKeyboardButtonBuilder;

@Component
public class HelpFromBotInlineButtonBuilder extends InlineKeyboardButtonBuilder {
    @Autowired
    private TelegramBot telegramBot;

    public HelpFromBotInlineButtonBuilder() {
        super("Через бота");
    }

    @Override
    public void onClick(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        long chatId = callbackQuery.message().chat().id();

        telegramBot.execute(new SendMessage(chatId, "Функционал еще не реализован")); //TODO: Реализовать
    }
}
