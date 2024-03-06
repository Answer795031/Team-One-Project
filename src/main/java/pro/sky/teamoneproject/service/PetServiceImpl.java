package pro.sky.teamoneproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.exception.PetNotFoundException;
import pro.sky.teamoneproject.listener.TelegramBotListener;
import pro.sky.teamoneproject.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private TelegramBotListener telegramBotListener;

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet addPet(String name, String gender, int age, String species,boolean invalid,
                      String rulesForGettingToKnowAnimals,
                      String listofDocuments, String listOfRecommendationForMovePet,
                      String informationAboutTheLivingConditionPet, String consultationDoghandlerForFirstCommunication,
                      String contactWitsOtherDoghanlers, String reasonWhyShelterCanReject) {
        Pet pet = new Pet(name, gender, age, species,invalid,
                rulesForGettingToKnowAnimals, listofDocuments, listOfRecommendationForMovePet,
                informationAboutTheLivingConditionPet, consultationDoghandlerForFirstCommunication,
                contactWitsOtherDoghanlers, reasonWhyShelterCanReject);
        petRepository.save(pet);
        telegramBotListener.updatePetsCommand();

        return pet;
    }

    @Override
    public Pet getPet(Long id) {
        if (petRepository.findById(id).isEmpty()) {
            throw new PetNotFoundException("Питомец не найден!");
        }

        return petRepository.findById(id).get();
    }

    @Override
    public Pet updatePet(Long id, String name, String gender, int age, String species, boolean invalid, String rulesForGettingToKnowAnimals,
                         String listofDocuments, String listOfRecommendationForMovePet,
                         String informationAboutTheLivingConditionPet, String consultationDoghandlerForFirstCommunication,
                         String contactWitsOtherDoghanlers, String reasonWhyShelterCanReject) {
        if (petRepository.findById(id).isEmpty()) {
            return petRepository.save(new Pet(name, gender, age, species,invalid, rulesForGettingToKnowAnimals, listofDocuments, listOfRecommendationForMovePet,
                    informationAboutTheLivingConditionPet, consultationDoghandlerForFirstCommunication,
                    contactWitsOtherDoghanlers, reasonWhyShelterCanReject));
        }
        Pet existingPet = petRepository.findById(id).get();
        existingPet.setName(name);
        existingPet.setGender(gender);
        existingPet.setAge(age);
        existingPet.setSpecies(species);
        petRepository.save(existingPet);

        telegramBotListener.updatePetsCommand();

        return existingPet;
    }

    @Override
    public Pet removePet(Long id) {
        if (petRepository.findById(id).isEmpty()) {
            return null;
        }
        petRepository.deleteById(id);
        telegramBotListener.updatePetsCommand();

        return petRepository.findById(id).get();
    }
}
