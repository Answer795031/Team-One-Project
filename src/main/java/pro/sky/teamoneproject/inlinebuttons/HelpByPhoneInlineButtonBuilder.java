package pro.sky.teamoneproject.inlinebuttons;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.model.telegrambot.request.InlineKeyboardButtonBuilder;

@Component
public class HelpByPhoneInlineButtonBuilder extends InlineKeyboardButtonBuilder {
    @Autowired
    private TelegramBot telegramBot;

    public HelpByPhoneInlineButtonBuilder() {
        super("По номеру телефона");
    }

    @Override
    public void onClick(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        long chatId = callbackQuery.message().chat().id();
        SendContact sendContact = new SendContact(chatId, "7-800-555-35-35", "Проще позвонить чем просто написать");
        telegramBot.execute(sendContact);
    }
}
