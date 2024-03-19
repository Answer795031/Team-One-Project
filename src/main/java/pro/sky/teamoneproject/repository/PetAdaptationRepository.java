package pro.sky.teamoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.teamoneproject.entity.PetAdaptation;

import java.util.Optional;
@Repository
public interface PetAdaptationRepository extends JpaRepository<PetAdaptation, Long> {
    @Query("SELECT pa FROM PetAdaptation pa ORDER BY pa.reportDateTime DESC LIMIT 1")
    Optional<PetAdaptation> findByShelterClientId(Long shelterClientId);
}
