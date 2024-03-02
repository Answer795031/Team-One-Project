package pro.sky.teamoneproject.commands.bottomfortakespet;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.repository.PetRepository;

import java.util.List;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;
@Component
public class HowYouCanTakePet extends Command {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    PetRepository petRepository;

    public HowYouCanTakePet() {
        super(HOW_YOU_CAN_TAKE_PET);
    }
    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId, "Выберите того питомца по которому вы бы хотели узнать информацию");
        sendMessage.replyMarkup(getReplyKeyboard());
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    private ReplyKeyboardMarkup getReplyKeyboard() {
        List<Pet> pets = petRepository.getAll();
        Integer page = 0;
        String[][] petButtoms = new String[pets.size()][1];
        for (int i = 0; i < pets.size(); i++) {
            petButtoms[i][0] = pets.get(i).getName();
//            if (i == 8) {
//                petButtoms[9][2] = "->";
//                page++;
//            }
////            else if (i == pets.size()-1 && i < 9){
////                petButtoms[pets.size()][1] = "->";
////            }
        }

        return new ReplyKeyboardMarkup(petButtoms, true, false, false);
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
