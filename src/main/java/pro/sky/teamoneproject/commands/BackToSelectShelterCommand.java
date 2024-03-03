package pro.sky.teamoneproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.ShelterClientRepository;

import java.util.Optional;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.BACK_TO_SELECT_SHELTER;
import static pro.sky.teamoneproject.constant.ConstantsForShelter.SELECT_SHELTER_MESSAGE;

@Component
public class BackToSelectShelterCommand extends Command {
    @Autowired
    protected TelegramBot telegramBot;
    @Autowired
    protected ShelterClientRepository shelterClientRepository;
    @Autowired
    private StartCommand startCommand;

    public BackToSelectShelterCommand() {
        super(BACK_TO_SELECT_SHELTER);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        Optional<ShelterClient> shelterClientOpt = shelterClientRepository.findByChatId(chatId);

        if (shelterClientOpt.isPresent()) {
            ShelterClient shelterClient = shelterClientOpt.get();
            shelterClient.setLastTimeAppeal(null);
            shelterClientRepository.save(shelterClient);
        }

        SendMessage sendMessage = new SendMessage(chatId, SELECT_SHELTER_MESSAGE);
        sendMessage.replyMarkup(startCommand.getShelterButtons());
        telegramBot.execute(sendMessage);
    }
}
