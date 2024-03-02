package pro.sky.teamoneproject.service;

import pro.sky.teamoneproject.entity.Pet;

public interface PetService {
    
    Pet addPet(String name, String gender, int age, String species,String rulesForGettingToKnowAnimals,
               String listofDocuments, String listOfRecommendationForMovePet,
               String informationAboutTheLivingConditionPet, String consultationDoghandlerForFirstCommunication,
               String contactWitsOtherDoghanlers, String reasonWhyShelterCanReject);


    Pet updatePet(Long id, String name, String gender, int age, String species, String rulesForGettingToKnowAnimals,
                  String listofDocuments, String listOfRecommendationForMovePet,
                  String informationAboutTheLivingConditionPet, String consultationDoghandlerForFirstCommunication,
                  String contactWitsOtherDoghanlers, String reasonWhyShelterCanReject);

    Pet removePet(Long id);

    Pet getPet(Long id);
}
