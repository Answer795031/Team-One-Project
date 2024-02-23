package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendContact;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.teamoneproject.repository.ClientRepository;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

public class CallVolunteerCommand extends Command{
    public CallVolunteerCommand(TelegramBot telegramBot, ClientRepository clientRepository) {
        super(telegramBot, clientRepository);
    }
    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        String messageText = update.message().text();
        SendMessage sendMessage = new SendMessage(chatId, "Позвать волонтера можно следующими способами");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton fromPhone = new InlineKeyboardButton("По номеру телефона"); //TODO: Назвать нормально переменную
        fromPhone.callbackData("phone-helper"); //TODO: Назвать нормально, вынести в константы

        InlineKeyboardButton fromNikName = new InlineKeyboardButton("По никнейму телеграм"); //TODO: Назвать нормально переменную
        fromNikName.callbackData("nikname-helper"); //TODO: Назвать нормально, вынести в константы

        InlineKeyboardButton fromBot = new InlineKeyboardButton("Через бота"); //TODO: Назвать нормально переменную
        fromBot.callbackData("from-bot-helper"); //TODO: Назвать нормально, вынести в константы

        keyboardMarkup.addRow(fromPhone);
        keyboardMarkup.addRow(fromNikName);
        keyboardMarkup.addRow(fromBot);

        sendMessage.replyMarkup(keyboardMarkup);
        if (update.callbackQuery() != null) {
            processCallbackQuery(update);
        }
        telegramBot.execute(sendMessage);

    }
    private void processCallbackQuery(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        long chatId = callbackQuery.message().chat().id();

        if (callbackQuery.data().equals("phone-helper")) {
            SendContact sendContact = new SendContact(chatId, "7-800-555-35-35", "Проще позвонить чем просто написать");
            telegramBot.execute(sendContact);
        } else if (callbackQuery.data().equals("nikname-helper")) {
            SendMessage sendMessage = new SendMessage(chatId,
                    """
                            @saver_cat
                            @Tretkir99
                            @AlexeySamohvalov
                            @MTarasov13
                            """);
            telegramBot.execute(sendMessage);
        } else if (callbackQuery.data().equals("from-bot-helper")) {
            //TODO: Реализовать
        }

        AnswerCallbackQuery answer = new AnswerCallbackQuery(callbackQuery.id());
        telegramBot.execute(answer);
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
