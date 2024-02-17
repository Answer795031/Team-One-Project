package pro.sky.teamoneproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TelegramBot telegramBot;

    /**
     * Обработчик полученных обновлений (сообщения, callback)
     * @param updates полученные обновления (сообщения, callback)
     * @return статус обработанного обновления
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @PostConstruct
    private void init() {
        telegramBot.setUpdatesListener(this);
    }
}
