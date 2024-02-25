package pro.sky.teamoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.teamoneproject.entity.Shelter;

import java.util.List;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    @Query("SELECT s from Shelter s")
    List<Shelter> getAll();
}
