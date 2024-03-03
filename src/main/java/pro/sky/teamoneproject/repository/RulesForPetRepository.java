package pro.sky.teamoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.teamoneproject.entity.RuleForPet;

@Repository
public interface RulesForPetRepository extends JpaRepository<RuleForPet, Long> {
}
