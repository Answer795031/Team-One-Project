package pro.sky.teamoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.teamoneproject.entity.Notification;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByShelterClientId(long shelterClientId);
}
