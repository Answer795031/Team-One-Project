package pro.sky.teamoneproject.commands.returnbuttons;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.commands.bottomfortakespet.HowYouCanTakePet;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class BackToSelectPetMenuCommand extends Command {
    @Autowired
    private HowYouCanTakePet howYouCanTakePet;

    public BackToSelectPetMenuCommand() {
        super(BACK_TO_SELECT_PETS);
    }

    @Override
    public void action(Update update) {
        howYouCanTakePet.action(update);
    }
}
