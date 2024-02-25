package pro.sky.teamoneproject.service;

import pro.sky.teamoneproject.entity.Pet;

public interface PetService {
    
    Pet addPet(String name, String gender, int age, String species);

    Pet updatePet(Long id, String name, String gender, int age, String species);

    Pet removePet(Long id);

    Pet getPet(Long id);
}
