package pro.sky.teamoneproject.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private ShelterClient shelterClient;
    private LocalDateTime lastNotificationDateTime;

    public Notification() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShelterClient getShelterClient() {
        return shelterClient;
    }

    public void setShelterClient(ShelterClient shelterClient) {
        this.shelterClient = shelterClient;
    }

    public LocalDateTime getLastNotificationDateTime() {
        return lastNotificationDateTime;
    }

    public void resetLastNotificationDateTime() {
        this.lastNotificationDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    public boolean isSendNotification() {
        return lastNotificationDateTime
                .plusMinutes(3)
                .isAfter(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification that)) return false;

        if (id != that.id) return false;
        if (!Objects.equals(shelterClient, that.shelterClient))
            return false;
        return Objects.equals(lastNotificationDateTime, that.lastNotificationDateTime);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (shelterClient != null ? shelterClient.hashCode() : 0);
        result = 31 * result + (lastNotificationDateTime != null ? lastNotificationDateTime.hashCode() : 0);
        return result;
    }
}
