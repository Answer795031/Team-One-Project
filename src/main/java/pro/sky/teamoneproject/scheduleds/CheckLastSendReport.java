package pro.sky.teamoneproject.scheduleds;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.teamoneproject.entity.Notification;
import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.repository.NotificationRepository;
import pro.sky.teamoneproject.repository.PetAdaptationRepository;

import java.time.LocalDateTime;

@Service
public class CheckLastSendReport {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private PetAdaptationRepository petAdaptationRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @Async
    @Scheduled(cron = "0 */1 * * * *")
    public void checkLastReportAndSandWarningMessage() {
        for (Notification notification : notificationRepository.findAll()) {
            PetAdaptation petAdaptation = petAdaptationRepository.findByShelterClientId(notification.getShelterClient().getId()).get();
            if (!notification.isSendNotification()) {
                if (petAdaptation.getReportDateTime().plusDays(2).isBefore(LocalDateTime.now())) {
                    telegramBot.execute(new SendMessage(notification.getShelterClient().getChatId(),
                            "Дорогой усыновитель, мы заметили, что ты не отправил вчера отчет. " +
                                    "Пожалуйста, подойди ответственнее к этому занятию. В противном случае волонтеры приюта будут обязаны самолично проверять условия содержания животного"
                    ));
                    notification.resetLastNotificationDateTime();
                    notificationRepository.save(notification);
                } else if (petAdaptation.getReportDateTime().plusDays(1).plusHours(2).isBefore(LocalDateTime.now())) {
                    telegramBot.execute(new SendMessage(notification.getShelterClient().getChatId(), "Дорогой усыновитель, не забудьте отправить сегодня отчет"));
                    notification.resetLastNotificationDateTime();
                    notificationRepository.save(notification);
                }
            }
        }
    }
}
