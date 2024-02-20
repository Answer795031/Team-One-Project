package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.ClientRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;

import java.util.List;

public class StartCommand extends Command {
    private final ShelterRepository shelterRepository;

    public StartCommand(TelegramBot telegramBot, ClientRepository clientRepository, ShelterRepository shelterRepository) {
        super(telegramBot, clientRepository);
        this.shelterRepository = shelterRepository;
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        String username = update.message().chat().username();

        if (clientRepository.findByChatId(chatId).isEmpty()) {
            ShelterClient shelterClient = new ShelterClient();
            shelterClient.setUsername(username);
            shelterClient.setChatId(chatId);
            clientRepository.save(shelterClient);
        }

        SendMessage sendMessage = new SendMessage(chatId, "*** Приветственное сообщение ***"); //TODO: Придумать приветственное сообщение
        sendMessage.replyMarkup(getShelterButtons());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок выбора приюта под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    public ReplyKeyboardMarkup getShelterButtons()  {
        List<Shelter> shelters = shelterRepository.getAll();

        String[][] shelterButtons = new String[shelters.size()][1];
        for (int i = 0; i < shelters.size(); i++) {
            shelterButtons[i][0] = shelters.get(i).getName();
        }

        return new ReplyKeyboardMarkup(shelterButtons, true, false, false);
    }
}
