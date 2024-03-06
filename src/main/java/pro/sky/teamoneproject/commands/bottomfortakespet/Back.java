package pro.sky.teamoneproject.commands.bottomfortakespet;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.ShelterClientRepository;

@Component
public class Back extends Command {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ShelterClientRepository shelterClientRepository;
    @Autowired
    private HowYouCanTakePet petButton;

    public Back() {
        super("⬅");
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();

        ShelterClient shelterClient = shelterClientRepository.findByChatId(chatId).orElseThrow();

        int newPage = shelterClient.getPetViewPageNumber() - 1;
        if (newPage < 0) {
            newPage = 0;
        }

        shelterClient.setPetViewPageNumber(newPage);
        shelterClientRepository.save(shelterClient);

        SendMessage sendMessage = new SendMessage(chatId, "Выбрана страница " + (newPage + 1));
        sendMessage.replyMarkup(petButton.getReplyKeyboard(chatId));
        telegramBot.execute(sendMessage);
    }
}
