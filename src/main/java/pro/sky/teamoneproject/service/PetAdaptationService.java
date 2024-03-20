package pro.sky.teamoneproject.service;

import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.entity.ShelterClient;

import java.time.LocalDateTime;
import java.util.List;

public interface PetAdaptationService {
    PetAdaptation updatePetAdaptation(Long id, boolean checkReport);

    PetAdaptation removePetAdaptation(Long id);

    PetAdaptation getPetAdaptation(Long id);
}
