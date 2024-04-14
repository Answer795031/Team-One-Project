package pro.sky.teamoneproject.service;

import pro.sky.teamoneproject.entity.Pet;

public interface PetService {
    
    Pet addPet(String name,
               String gender,
               int age,
               String species,
               boolean invalid,
               String rulesForGettingToKnowAnimals,
               String listOfDocuments,
               String listOfRecommendationForMovePet,
               String informationAboutTheLivingConditionPet,
               String consultationDogHandlerForFirstCommunication,
               String contactWitsOtherDogHandlers,
               String reasonWhyShelterCanReject);


    Pet updatePet(Long id,
                  String name,
                  String gender,
                  int age,
                  String species,
                  boolean invalid,
                  String rulesForGettingToKnowAnimals,
                  String listOfDocuments,
                  String listOfRecommendationForMovePet,
                  String informationAboutTheLivingConditionPet,
                  String consultationDogHandlerForFirstCommunication,
                  String contactWitsOtherDogHandlers,
                  String reasonWhyShelterCanReject);

    Pet removePet(Long id);

    Pet getPet(Long id);
}
