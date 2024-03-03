package pro.sky.teamoneproject.commands.buttonsforshelters;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.utils.LocationUtils;

import static pro.sky.teamoneproject.constant.ConstantsForShelter.*;

@Component
public class LocationMapCommand extends Command {
    @Autowired
    private TelegramBot telegramBot;

    public LocationMapCommand() {
        super(LOCATION_MAP);
    }

    @Override
    public void action(Update update) {
        long chatId = update.message().chat().id();

        //TODO: Брать координаты с БД
        float latitude = 48.431534f;
        float longitude = 135.100598f;

        SendLocation sendLocation = new SendLocation(chatId, latitude, longitude);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Yandex").url(LocationUtils.getYandexUrl(latitude, longitude)));
        sendLocation.replyMarkup(keyboardMarkup);

        // Не выдает организацию, мб нужно добавлять в БД ссылки на геопозицию
//        keyboardMarkup.addRow(new InlineKeyboardButton("2gis").url(LocationUtils.get2Gis(latitude, longitude)));
//        sendLocation.replyMarkup(keyboardMarkup);

        telegramBot.execute(sendLocation);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
