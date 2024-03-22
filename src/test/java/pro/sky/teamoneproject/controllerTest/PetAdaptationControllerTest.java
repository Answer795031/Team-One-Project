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
import pro.sky.teamoneproject.controller.PetAdaptationController;
import pro.sky.teamoneproject.controller.PetController;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.listener.TelegramBotListener;
import pro.sky.teamoneproject.repository.PetAdaptationRepository;
import pro.sky.teamoneproject.repository.PetRepository;
import pro.sky.teamoneproject.repository.ShelterClientRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;
import pro.sky.teamoneproject.service.PetAdaptationServiceImpl;
import pro.sky.teamoneproject.service.PetServiceImpl;
import pro.sky.teamoneproject.service.ShelterServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.teamoneproject.utils.Utils.*;

@WebMvcTest
class PetAdaptationControllerTest {

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
    private PetAdaptationServiceImpl petAdaptationService;

    @SpyBean
    private ShelterServiceImpl shelterService;

    @InjectMocks
    private PetController petController;

    @InjectMocks
    private PetAdaptationController petAdaptationController;

    @Test
    void getPetAdaptationTest() throws Exception {
        JSONObject actualPetAdaptation = getActualPetAdaptation();
        PetAdaptation petAdaptation = getPetAdaptation();

        when(petAdaptationRepository.findById(any(Long.class))).thenReturn(Optional.of(petAdaptation));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/reports/get/" + 123)
                .content(actualPetAdaptation.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pathToFilePhoto").value(petAdaptation.getPathToFilePhoto()))
                .andExpect(jsonPath("$.ration").value(petAdaptation.getRation()))
                .andExpect(jsonPath("$.healthAndParticular").value(petAdaptation.getHealthAndParticular()))
                .andExpect(jsonPath("$.changeParticular").value(petAdaptation.getChangeParticular()))
                .andExpect(jsonPath("$.reportDateTime").value(petAdaptation.getReportDateTime()))
                .andExpect(jsonPath("$.shelterClient").value(petAdaptation.getShelterClient()))
                .andExpect(jsonPath("$.checkReport").value(petAdaptation.isCheckReport()));
    }

    @Test
    void updatePetTest() throws Exception {
        JSONObject actualPetAdaptation = getActualPetAdaptation();
        PetAdaptation petAdaptation = getPetAdaptation();

        boolean status = true;

        when(petAdaptationRepository.findById(any(Long.class))).thenReturn(Optional.of(petAdaptation));
        when(petAdaptationRepository.save(any(PetAdaptation.class))).thenReturn(petAdaptation);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/reports/update/123?status=" + status)
                .content(actualPetAdaptation.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pathToFilePhoto").value(petAdaptation.getPathToFilePhoto()))
                .andExpect(jsonPath("$.ration").value(petAdaptation.getRation()))
                .andExpect(jsonPath("$.healthAndParticular").value(petAdaptation.getHealthAndParticular()))
                .andExpect(jsonPath("$.changeParticular").value(petAdaptation.getChangeParticular()))
                .andExpect(jsonPath("$.reportDateTime").value(petAdaptation.getReportDateTime()))
                .andExpect(jsonPath("$.shelterClient").value(petAdaptation.getShelterClient()))
                .andExpect(jsonPath("$.checkReport").value(status));
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