package pro.sky.teamoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.entity.ShelterClient;

import java.util.Optional;
@Repository
public interface PetAdaptationRepository extends JpaRepository<PetAdaptation, Long> {
    Optional<PetAdaptation> findById(Long id);
}
