package pro.sky.teamoneproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.repository.ShelterRepository;
import pro.sky.teamoneproject.service.ShelterService;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShelterController.class)
public class ShelterControllerTestMvc {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShelterService shelterService;
    @Autowired
    private ShelterController shelterController;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(shelterController).isNotNull();
    }

    @Test
    public void addStudentTest() throws Exception {

        String name = "name";
        String description = "description";
        Long id = 1L;

        Shelter shelter = new Shelter(name, description);
        shelter.setId(id);

        when(shelterService.add(name, description)).thenReturn(shelter);

        String request = objectMapper.writeValueAsString(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/shelter/add", name, description)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(description));
    }
}