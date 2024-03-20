package pro.sky.teamoneproject.controllerTest;

import com.pengrad.telegrambot.TelegramBot;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.teamoneproject.controller.PetController;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.listener.TelegramBotListener;
import pro.sky.teamoneproject.repository.PetAdaptationRepository;
import pro.sky.teamoneproject.repository.PetRepository;
import pro.sky.teamoneproject.repository.ShelterClientRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;
import pro.sky.teamoneproject.service.PetServiceImpl;
import pro.sky.teamoneproject.service.ShelterServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.teamoneproject.utils.Utils.getActualPet;
import static pro.sky.teamoneproject.utils.Utils.getPet;

@WebMvcTest
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetAdaptationRepository petAdaptationRepository;

    @MockBean
    private PetRepository petRepository;

    @MockBean
    private ShelterClientRepository shelterClientRepository;

    @MockBean
    private ShelterRepository shelterRepository;

    @MockBean
    private TelegramBotListener telegramBotListener;

    @MockBean
    private TelegramBot telegramBot;

    @SpyBean
    private PetServiceImpl petService;

    @SpyBean
    private ShelterServiceImpl shelterService;

    @InjectMocks
    private PetController petController;

    @Test
    void addPetTest() throws Exception {
        JSONObject actualPet = getActualPet();
        Pet pet = getPet();

        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/pet/add")
                .content(actualPet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(pet.getName()))
                .andExpect(jsonPath("$.gender").value(pet.getGender()))
                .andExpect(jsonPath("$.age").value(pet.getAge()))
                .andExpect(jsonPath("$.species").value(pet.getSpecies()))
                .andExpect(jsonPath("$.invalid").value(pet.isInvalid()))
                .andExpect(jsonPath("$.rulesForGettingToKnowAnimals").value(pet.getRulesForGettingToKnowAnimals()))
                .andExpect(jsonPath("$.listOfDocuments").value(pet.getListOfDocuments()))
                .andExpect(jsonPath("$.listOfRecommendationForMovePet").value(pet.getListOfRecommendationForMovePet()))
                .andExpect(jsonPath("$.informationAboutTheLivingConditionPet").value(pet.getInformationAboutTheLivingConditionPet()))
                .andExpect(jsonPath("$.consultationDogHandlerForFirstCommunication").value(pet.getConsultationDogHandlerForFirstCommunication()))
                .andExpect(jsonPath("$.contactWitsOtherDogHandlers").value(pet.getContactWitsOtherDogHandlers()))
                .andExpect(jsonPath("$.reasonWhyShelterCanReject").value(pet.getReasonWhyShelterCanReject()));
    }

    @Test
    void getPetTest() throws Exception {
        JSONObject actualPet = getActualPet();
        Pet pet = getPet();

        when(petRepository.findById(any(Long.class))).thenReturn(Optional.of(pet));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/pet/get/" + 123)
                .content(actualPet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(pet.getName()))
                .andExpect(jsonPath("$.gender").value(pet.getGender()))
                .andExpect(jsonPath("$.age").value(pet.getAge()))
                .andExpect(jsonPath("$.species").value(pet.getSpecies()))
                .andExpect(jsonPath("$.invalid").value(pet.isInvalid()))
                .andExpect(jsonPath("$.rulesForGettingToKnowAnimals").value(pet.getRulesForGettingToKnowAnimals()))
                .andExpect(jsonPath("$.listOfDocuments").value(pet.getListOfDocuments()))
                .andExpect(jsonPath("$.listOfRecommendationForMovePet").value(pet.getListOfRecommendationForMovePet()))
                .andExpect(jsonPath("$.informationAboutTheLivingConditionPet").value(pet.getInformationAboutTheLivingConditionPet()))
                .andExpect(jsonPath("$.consultationDogHandlerForFirstCommunication").value(pet.getConsultationDogHandlerForFirstCommunication()))
                .andExpect(jsonPath("$.contactWitsOtherDogHandlers").value(pet.getContactWitsOtherDogHandlers()))
                .andExpect(jsonPath("$.reasonWhyShelterCanReject").value(pet.getReasonWhyShelterCanReject()));
    }

    @Test
    void updatePetTest() throws Exception {
        JSONObject actualPet = getActualPet();
        Pet pet = getPet();

        when(petRepository.findById(any(Long.class))).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/pet/update/" + 123)
                .content(actualPet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(pet.getName()))
                .andExpect(jsonPath("$.gender").value(pet.getGender()))
                .andExpect(jsonPath("$.age").value(pet.getAge()))
                .andExpect(jsonPath("$.species").value(pet.getSpecies()))
                .andExpect(jsonPath("$.invalid").value(pet.isInvalid()))
                .andExpect(jsonPath("$.rulesForGettingToKnowAnimals").value(pet.getRulesForGettingToKnowAnimals()))
                .andExpect(jsonPath("$.listOfDocuments").value(pet.getListOfDocuments()))
                .andExpect(jsonPath("$.listOfRecommendationForMovePet").value(pet.getListOfRecommendationForMovePet()))
                .andExpect(jsonPath("$.informationAboutTheLivingConditionPet").value(pet.getInformationAboutTheLivingConditionPet()))
                .andExpect(jsonPath("$.consultationDogHandlerForFirstCommunication").value(pet.getConsultationDogHandlerForFirstCommunication()))
                .andExpect(jsonPath("$.contactWitsOtherDogHandlers").value(pet.getContactWitsOtherDogHandlers()))
                .andExpect(jsonPath("$.reasonWhyShelterCanReject").value(pet.getReasonWhyShelterCanReject()));
    }

    @Test
    void removePetTest() throws Exception {
        Pet pet = getPet();

        when(petRepository.findById(any(Long.class))).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/pet/remove/" + 123)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}