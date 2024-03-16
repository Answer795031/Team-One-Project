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
import pro.sky.teamoneproject.controller.ShelterController;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.listener.TelegramBotListener;
import pro.sky.teamoneproject.repository.PetRepository;
import pro.sky.teamoneproject.repository.ShelterRepository;
import pro.sky.teamoneproject.service.PetServiceImpl;
import pro.sky.teamoneproject.service.ShelterServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pro.sky.teamoneproject.utils.Utils.*;

@WebMvcTest
class ShelterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterRepository shelterRepository;

    @MockBean
    private PetRepository petRepository;

    @MockBean
    private TelegramBotListener telegramBotListener;

    @MockBean
    private TelegramBot telegramBot;

    @SpyBean
    private ShelterServiceImpl shelterService;

    @SpyBean
    private PetServiceImpl petService;

    @InjectMocks
    private ShelterController shelterController;

    @Test
    void addTest() throws Exception {
        JSONObject actualShelter = getActualShelter();
        Shelter shelter = getShelter();

        when(shelterRepository.save(any(Shelter.class))).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/shelter/add")
                        .content(actualShelter.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value(shelter.getName()))
                .andExpect(jsonPath("$.description").value(shelter.getDescription()));
    }

    @Test
    void getTest() throws Exception {
        JSONObject actualShelter = getActualShelter();
        Shelter shelter = getShelter();

        when(shelterRepository.findById(any(Long.class))).thenReturn(Optional.of(shelter));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelter/get/" + 123)
                        .content(actualShelter.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value(shelter.getName()))
                .andExpect(jsonPath("$.description").value(shelter.getDescription()));
    }

    @Test
    void removeTest() throws Exception {
        Shelter shelter = getShelter();

        when(shelterRepository.findById(any(Long.class))).thenReturn(Optional.of(shelter));
        when(shelterRepository.save(any(Shelter.class))).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/shelter/remove/" + 123)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateTest() throws Exception {
        JSONObject actualShelter = getActualShelter();
        Shelter shelter = getShelter();

        when(shelterRepository.findById(any(Long.class))).thenReturn(Optional.of(shelter));
        when(shelterRepository.save(any(Shelter.class))).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/shelter/update/" + 123)
                        .content(actualShelter.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value(shelter.getName()))
                .andExpect(jsonPath("$.description").value(shelter.getDescription()));
    }
}