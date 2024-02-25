package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.ClientRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;

import java.time.LocalDateTime;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class ShelterDefaultCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ClientRepository clientRepository;
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

        ShelterClient client = clientRepository.findByChatId(chatId).orElseThrow();
        client.setSelectedShelter(shelter);
        client.setLastTimeAppeal(LocalDateTime.now());
        clientRepository.save(client);

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
                {InfoAboutOfShelter}, //TODO: Вынести в константы
                {HowYouCanTakePet}, //TODO: Вынести в константы
                {SendReportAboutOfPet}, //TODO: Вынести в константы
                {CallVolunteer}
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
