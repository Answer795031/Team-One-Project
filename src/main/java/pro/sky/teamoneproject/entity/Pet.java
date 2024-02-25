package pro.sky.teamoneproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String gender;
    private int age;
    private String species;

    public Pet(String name, String gender, int age, String species) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.species = species;
    }

    public Pet() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && Objects.equals(id, pet.id) && Objects.equals(name, pet.name) && Objects.equals(gender, pet.gender) && Objects.equals(species, pet.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age, species);
    }
}
