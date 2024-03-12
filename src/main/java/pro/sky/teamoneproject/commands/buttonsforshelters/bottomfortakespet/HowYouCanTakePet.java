package pro.sky.teamoneproject.commands.buttonsforshelters.bottomfortakespet;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.ShelterClientRepository;
import pro.sky.teamoneproject.repository.PetRepository;

import java.util.List;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class HowYouCanTakePet extends Command {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ShelterClientRepository shelterClientRepository;

    public HowYouCanTakePet() {
        super(HOW_YOU_CAN_TAKE_PET);
    }
    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();
        SendMessage sendMessage = new SendMessage(chatId, "Выберите того питомца по которому вы бы хотели узнать информацию");
        sendMessage.replyMarkup(getReplyKeyboard(chatId));
        telegramBot.execute(sendMessage);
    }

    /**
     * Метод для создания кнопок под вводом сообщение
     * @return таблица кнопок [строка][ячейка строки]
     */
    public ReplyKeyboardMarkup getReplyKeyboard(long chatId) {
        ShelterClient shelterClient = shelterClientRepository.findByChatId(chatId).orElseThrow();
        List<Pet> pets = petRepository.getAll();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(new String[0][0], true, false, false);

        int page = shelterClient.getPetViewPageNumber();
        int columnCount = 2;
        int petOnePageCount = columnCount * 2;
        int remainingPets = pets.size() - petOnePageCount * page;
        int remainingPetsByPage = Math.min(petOnePageCount, remainingPets);

        String[][] petButtons;

        int rowCount = (int)Math.round((double)Math.min(petOnePageCount, pets.size() - petOnePageCount * page) / 2);

        try {
            petButtons = new String[rowCount][columnCount];

            for (int i = 0; i < remainingPetsByPage; i++) {
                petButtons[i / columnCount][i % columnCount] = pets.get(i + petOnePageCount * page).getName();
            }

            for (String[] petButton : petButtons) {
                if (petButton[1] != null) {
                    keyboardMarkup.addRow(petButton);
                } else {
                    keyboardMarkup.addRow(petButton[0]);
                }
            }

        } catch (NegativeArraySizeException ignore) {}

        keyboardMarkup.addRow(getNavigateButtons(page, remainingPets, petOnePageCount));

        return keyboardMarkup;
    }

    private String[] getNavigateButtons(int page, int remainingPets, int petOnePageCount) {
        String[] pageButtons;
        if (page > 0) {
            if (remainingPets > petOnePageCount) {
                pageButtons = new String[3];
                pageButtons[0] = "⬅";
                pageButtons[1] = BACK_TO_MAIN_MENU;
                pageButtons[2] = "➡";
            } else {
                pageButtons = new String[2];
                pageButtons[0] = "⬅";
                pageButtons[1] = BACK_TO_MAIN_MENU;
            }
        } else {
            if (remainingPets > petOnePageCount) {
                pageButtons = new String[2];
                pageButtons[0] = BACK_TO_MAIN_MENU;
                pageButtons[1] = "➡";
            } else {
                pageButtons = new String[1];
                pageButtons[0] = BACK_TO_MAIN_MENU;
            }
        }

        return pageButtons;
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
