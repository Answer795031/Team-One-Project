package pro.sky.teamoneproject.commands.buttonsforshelters;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.ShelterClientRepository;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.BACK_TO_MAIN_MENU;
import static pro.sky.teamoneproject.constant.ConstantsForShelter.GET_SHELTER_CLIENT_NUMBER;
import static pro.sky.teamoneproject.constant.ShelterClientMode.GET_PHONE_NUMBER;

@Component
public class GetShelterClientNumberCommand extends Command {
    @Autowired
    private ShelterClientRepository shelterClientRepository;
    @Autowired
    private TelegramBot telegramBot;
    public GetShelterClientNumberCommand() {
        super(GET_SHELTER_CLIENT_NUMBER);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();

        ShelterClient shelterClient = shelterClientRepository.findByChatId(chatId).get();
        shelterClient.setSelectedMode(GET_PHONE_NUMBER);
        shelterClientRepository.save(shelterClient);

        SendMessage sendMessage = new SendMessage(chatId, "Введите номер телефона в формате +71234567890");
        sendMessage.replyMarkup(new ReplyKeyboardMarkup(new String[][]{{BACK_TO_MAIN_MENU}}, true, false, false));
        telegramBot.execute(sendMessage);
    }
}
