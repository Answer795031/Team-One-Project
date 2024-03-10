package pro.sky.teamoneproject.commands.returnbuttons;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.ShelterClientRepository;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;
import static pro.sky.teamoneproject.constant.ShelterClientMode.DEFAULT;
import static pro.sky.teamoneproject.constant.SendReportSteps.NONE;

@Component
public class BackToMainMenuCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ShelterClientRepository shelterClientRepository;

    public BackToMainMenuCommand() {
        super(BACK_TO_MAIN_MENU);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();

        ShelterClient shelterClient = shelterClientRepository.findByChatId(chatId).get();
        shelterClient.setSelectedMode(DEFAULT);
        shelterClient.setSendReportSteps(NONE);
        shelterClientRepository.save(shelterClient);

        SendMessage sendMessage = new SendMessage(chatId, "Доступны следующие команды");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        String[][] keyboard = new String[][] {
                {INFO_ABOUT_OF_SHELTER},
                {HOW_YOU_CAN_TAKE_PET},
                {GET_SHELTER_CLIENT_NUMBER},
                {SEND_REPORT_ABOUT_OF_PET},
                {CALL_VOLUNTEER},
                {BACK_TO_SELECT_SHELTER}
        };

        return new ReplyKeyboardMarkup(keyboard, true, false, false);
    }
}
