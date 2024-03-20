package pro.sky.teamoneproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.exception.PetNotFoundException;
import pro.sky.teamoneproject.listener.TelegramBotListener;
import pro.sky.teamoneproject.repository.PetAdaptationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PetAdaptationServiceImpl implements PetAdaptationService{
    @Autowired
    private PetAdaptationRepository petAdaptationRepository;
//    @Autowired
//    private TelegramBotListener telegramBotListener;

    @Override
    public PetAdaptation updatePetAdaptation(Long id, boolean checkReport) {
        if (petAdaptationRepository.findById(id).isEmpty()) {
            return null;
        }
        PetAdaptation existingPetAdaptation = petAdaptationRepository.findById(id).get();
        existingPetAdaptation.setCheckReport(checkReport);
        petAdaptationRepository.save(existingPetAdaptation);

        return existingPetAdaptation;
    }
    @Override
    public PetAdaptation getPetAdaptation(Long id) {
        if (petAdaptationRepository.findById(id).isEmpty()) {
            throw new PetNotFoundException("Отчеты не найдены");
        }

        return petAdaptationRepository.findById(id).get();
    }
    @Override
    public PetAdaptation removePetAdaptation(Long id) {
        if (petAdaptationRepository.findById(id).isEmpty()) {
            return null;
        }
        petAdaptationRepository.deleteById(id);

        return petAdaptationRepository.findById(id).get();
    }
}
