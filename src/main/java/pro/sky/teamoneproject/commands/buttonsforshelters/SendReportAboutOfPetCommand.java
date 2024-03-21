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
import static pro.sky.teamoneproject.constant.ConstantsForShelter.SEND_REPORT_ABOUT_OF_PET;
import static pro.sky.teamoneproject.constant.SendReportSteps.SEND_PHOTO;
import static pro.sky.teamoneproject.constant.ShelterClientMode.SEND_PET_INFO;

@Component
public class SendReportAboutOfPetCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ShelterClientRepository shelterClientRepository;

    public SendReportAboutOfPetCommand() {
        super(SEND_REPORT_ABOUT_OF_PET);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        ShelterClient shelterClient = shelterClientRepository.findByChatId(chatId).get();
        shelterClient.setSelectedMode(SEND_PET_INFO);
        shelterClient.setSendReportSteps(SEND_PHOTO);
        shelterClientRepository.save(shelterClient);

        SendMessage sendMessage = new SendMessage(chatId, "Вышлите фото питомца");
        sendMessage.replyMarkup(new ReplyKeyboardMarkup(new String[][]{{BACK_TO_MAIN_MENU}}, true, false, false));
        telegramBot.execute(sendMessage);
    }
}
