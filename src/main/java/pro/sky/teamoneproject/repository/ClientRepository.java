package pro.sky.teamoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.teamoneproject.entity.ShelterClient;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ShelterClient, Long> {
    Optional<ShelterClient> findByChatId(Long chatId);
}
