package pro.sky.teamoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.teamoneproject.entity.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
