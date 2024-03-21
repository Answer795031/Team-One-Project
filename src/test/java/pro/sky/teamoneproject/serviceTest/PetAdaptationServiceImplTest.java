package pro.sky.teamoneproject.serviceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.teamoneproject.TeamOneProjectApplication;
import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.entity.ShelterClient;
import pro.sky.teamoneproject.repository.PetAdaptationRepository;
import pro.sky.teamoneproject.repository.ShelterClientRepository;
import pro.sky.teamoneproject.service.PetAdaptationService;
import pro.sky.teamoneproject.utils.Utils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PetAdaptationServiceImplTest {
    @Autowired
    private PetAdaptationService petAdaptationService;
    @MockBean
    private PetAdaptationRepository petAdaptationRepository;
    @MockBean
    private ShelterClientRepository shelterClientRepository;


//    @Test
//    public void testAddPet() {
//        PetAdaptation petAdaptation = new PetAdaptation();
//        Long id = 5L;
//
//        petAdaptation.setId(id);
//        petAdaptation.setPathToFilePhoto("pathToFilePhoto");
//        petAdaptation.setRation("ration");
//        petAdaptation.setHealthAndParticular("healthAndParticular");
//        petAdaptation.setChangeParticular("changeParticular");
//        petAdaptation.setCheckReport(false);
//        petAdaptation.setShelterClient(new ShelterClient(3L, "MTarasov13", 390313270L));
//
//
//    }

    @Test
    public void testGetPetAdaptation() {
        // Создаем данные для теста
        Long id = 1L;
        ShelterClient shelterClient = Utils.getShelterClient();
        LocalDateTime reportDateTime = LocalDateTime.now();

        PetAdaptation mockPetAdaptation = new PetAdaptation();
//                "pathToFilePhoto",
//                "ration",
//                "healthAndParticular",
//                "changeParticular",
//                reportDateTime,
//                shelterClient,
//                false
//        );
        mockPetAdaptation.setPathToFilePhoto("pathToFilePhoto");
        mockPetAdaptation.setRation("ration");
        mockPetAdaptation.setHealthAndParticular("healthAndParticular");
        mockPetAdaptation.setChangeParticular("changeParticular");
        mockPetAdaptation.setCheckReport(false);
        mockPetAdaptation.setShelterClient(shelterClient);

//         Mock-объект для save() petAdaptationRepository
        when(petAdaptationRepository.findById(id)).thenReturn(Optional.of(mockPetAdaptation));
        PetAdaptation petAdaptation = petAdaptationService.getPetAdaptation(id);
//
//        // Проверяем, что результат соответствует ожидаемым значениям.
        assertNotNull(petAdaptation);
        assertEquals(mockPetAdaptation, petAdaptation);
    }


}