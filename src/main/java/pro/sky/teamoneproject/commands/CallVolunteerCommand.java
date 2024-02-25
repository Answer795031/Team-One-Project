package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.inlinebuttons.HelpByNicknameInlineButtonBuilder;
import pro.sky.teamoneproject.inlinebuttons.HelpByPhoneInlineButtonBuilder;
import pro.sky.teamoneproject.inlinebuttons.HelpFromBotInlineButtonBuilder;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class CallVolunteerCommand extends Command{
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private HelpByNicknameInlineButtonBuilder helpByNicknameInlineButtonBuilder;
    @Autowired
    private HelpByPhoneInlineButtonBuilder helpByPhoneInlineButtonBuilder;
    @Autowired
    private HelpFromBotInlineButtonBuilder helpFromBotInlineButtonBuilder;

    public CallVolunteerCommand() {
        super(CallVolunteer);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage message = new SendMessage(chatId, "Позвать волонтера можно следующими способами");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        keyboardMarkup.addRow(helpByNicknameInlineButtonBuilder.buildInlineKeyboardButton());
        keyboardMarkup.addRow(helpByPhoneInlineButtonBuilder.buildInlineKeyboardButton());
        keyboardMarkup.addRow(helpFromBotInlineButtonBuilder.buildInlineKeyboardButton());

        message.replyMarkup(keyboardMarkup);

        telegramBot.execute(message);
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
