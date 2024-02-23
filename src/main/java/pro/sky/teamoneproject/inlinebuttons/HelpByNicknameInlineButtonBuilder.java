package pro.sky.teamoneproject.inlinebuttons;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.model.telegrambot.request.InlineKeyboardButtonBuilder;

@Component
public class HelpByNicknameInlineButtonBuilder extends InlineKeyboardButtonBuilder {
    @Autowired
    private TelegramBot telegramBot;

    public HelpByNicknameInlineButtonBuilder() {
        super("По никнейму телеграм");
    }

    @Override
    public void onClick(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        long chatId = callbackQuery.message().chat().id();

        SendMessage sendMessage = new SendMessage(chatId,
                """
                        @saver_cat
                        @Tretkir99
                        @AlexeySamohvalov
                        @MTarasov13
                        """);
        telegramBot.execute(sendMessage);
    }
}
