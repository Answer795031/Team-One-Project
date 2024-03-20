package pro.sky.teamoneproject.service;

import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.entity.ShelterClient;

import java.time.LocalDateTime;
import java.util.List;

public interface PetAdaptationService {
    PetAdaptation addPetAdaptation(String pathToFilePhoto,
                                   String ration,
                                   String healthAndParticular,
                                   String changeParticular,
                                   LocalDateTime reportDateTime,
                                   ShelterClient shelterClient);
    PetAdaptation updatePetAdaptation(Long id, boolean checkReport);

    List<PetAdaptation> getAdaptationPet(Long id);

    PetAdaptation removePetAdaptation(Long id);

    PetAdaptation getPetAdaptation(Long id);
}
