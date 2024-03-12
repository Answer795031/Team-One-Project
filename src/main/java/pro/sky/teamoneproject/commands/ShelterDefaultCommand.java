package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.ShelterClientRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;

import java.time.LocalDateTime;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class ShelterDefaultCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ShelterClientRepository shelterClientRepository;
    @Autowired
    private ShelterRepository shelterRepository;

    public ShelterDefaultCommand() {
        super(null);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        String messageText = update.message().text();

        Shelter shelter = shelterRepository.findByName(messageText).orElseThrow();

        ShelterClient client = shelterClientRepository.findByChatId(chatId).orElseThrow();
        client.setSelectedShelter(shelter);
        client.setLastTimeAppeal(LocalDateTime.now());
        shelterClientRepository.save(client);

        SendMessage sendMessage = new SendMessage(chatId, shelter.getDescription() + "\nДля приюта \"" + messageText + "\", доступны следующие команды");
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
