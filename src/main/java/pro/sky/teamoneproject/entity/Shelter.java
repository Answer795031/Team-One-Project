package pro.sky.teamoneproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Shelter {
    @Id
    @GeneratedValue
    private Long id;
}
