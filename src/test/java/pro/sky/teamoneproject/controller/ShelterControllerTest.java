package pro.sky.teamoneproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.repository.ShelterRepository;
import pro.sky.teamoneproject.service.ShelterService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShelterControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private ShelterController shelterController;
    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(shelterController).isNotNull();
    }

    @Test
    public void testAddShelter() throws URISyntaxException {
        Shelter shelter = new Shelter("Name", "Description");

//        URI addUri = new URI("http://localhost:" + port + "/shelter/add");
//        URI getUri = new URI("http://localhost:" + port + "/shelter/get");

        Shelter post = this.testRestTemplate
                .postForEntity("http://localhost:" + port + "/shelter/add?name=" + shelter.getName() + "&description=" + shelter.getDescription(), shelter, Shelter.class).getBody();
        Shelter resp = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/shelter/get?id=" + shelter.getId(), Shelter.class).getBody();

        assertEquals(post, resp);
    }
}