package pro.sky.teamoneproject.serviceTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.exception.PetNotFoundException;
import pro.sky.teamoneproject.listener.TelegramBotListener;
import pro.sky.teamoneproject.repository.PetRepository;
import pro.sky.teamoneproject.service.PetServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetServiceTest {

    @Autowired
    private PetServiceImpl petService;

    @MockBean
    private PetRepository petRepository;

    @MockBean
    private TelegramBotListener telegramBotListener;

    @Test
    public void testAddPet() {
        // Создаем данные для теста
        String name = "test";
        String gender = "male";
        int age = 1;
        String species = "dog";
        boolean invalid = false;
        String rulesForGettingToKnowAnimals = "rules1";
        String listofDocuments = "docs1";
        String listOfRecommendationForMovePet = "rec1";
        String informationAboutTheLivingConditionPet = "info1";
        String consultationDoghandlerForFirstCommunication = "consult1";
        String contactWitsOtherDoghanlers = "contact1";
        String reasonWhyShelterCanReject = "reason1";

        // Mock-объект для save() petRepository
        when(petRepository.save(Mockito.any(Pet.class))).thenAnswer(i -> i.getArgument(0));

        // Вызываем метод, который хотим протестировать
        Pet pet = petService.addPet(name, gender, age, species, invalid, rulesForGettingToKnowAnimals, listofDocuments, listOfRecommendationForMovePet, informationAboutTheLivingConditionPet, consultationDoghandlerForFirstCommunication, contactWitsOtherDoghanlers, reasonWhyShelterCanReject);

        // Проверка результатов
        assertEquals(name, pet.getName());
        assertEquals(gender, pet.getGender());
        assertEquals(age, pet.getAge());
        assertEquals(species, pet.getSpecies());

        assertThat(pet).isNotNull();
        assertThat(pet.getName()).isEqualTo("test");
        assertThat(pet.getGender()).isEqualTo("male");
        assertThat(pet.getAge()).isEqualTo(1);
        assertThat(pet.getSpecies()).isEqualTo("dog");
        assertThat(pet.isInvalid()).isFalse();
        assertThat(pet.getRulesForGettingToKnowAnimals()).isEqualTo("rules1");
        assertThat(pet.getListofDocuments()).isEqualTo("docs1");
        assertThat(pet.getListOfRecommendationForMovePet()).isEqualTo("rec1");
        assertThat(pet.getInformationAboutTheLivingConditionPet()).isEqualTo("info1");
        assertThat(pet.getConsultationDoghandlerForFirstCommunication()).isEqualTo("consult1");
        assertThat(pet.getContactWitsOtherDoghanlers()).isEqualTo("contact1");
        assertThat(pet.getReasonWhyShelterCanReject()).isEqualTo("reason1");
    }


    @Test
    public void testGetPet_Success() {
        Long id = 1L;
        Pet mockPet = new Pet();
        mockPet.setName("test");
        mockPet.setGender("male");
        mockPet.setAge(1);
        mockPet.setSpecies("dog");
        // Добавьте остальные поля по необходимости

        // Мы задаем поведение mock-объекта
        when(petRepository.findById(id)).thenReturn(Optional.of(mockPet));

        Pet pet = petService.getPet(id);

        // Проверяем, что результат соответствует ожидаемым значениям.
        assertEquals(mockPet, pet);
    }

    @Test
    public void testGetPet_PetNotFoundException() {
        Long id = 1L;

        // Мы задаем поведение mock-объекта для ситуации, когда питомец не найден
        when(petRepository.findById(id)).thenReturn(Optional.empty());

        // Проверяем, что будет выброшено исключение PetNotFoundException
        assertThrows(PetNotFoundException.class, () -> petService.getPet(id));
    }



    // тесты для сценариев, когда питомец существует и не существует в репозитории
    @Test
    public void testGetExistingPet() {
        // Arrange
        Pet pet = new Pet("Max", "male", 3, "dog", false, "rules1", "docs1", "rec1", "info1", "consult1", "contact1", "reason1");
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        // Act
        Pet result = petService.getPet(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Max");
        // Другие проверки
    }

    @Test
    public void testGetNonExistingPet() {
        // Arrange
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PetNotFoundException.class, () -> petService.getPet(1L));
    }

    // тесты для обновления существующего и несуществующего питомца
    @Test
    public void testUpdateExistingPet() {
        // Arrange
        Pet existingPet = new Pet("Max", "male", 3, "dog", false, "rules1", "docs1", "rec1", "info1", "consult1", "contact1", "reason1");
        when(petRepository.findById(1L)).thenReturn(Optional.of(existingPet));
        when(petRepository.save(Mockito.any(Pet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Pet updatedPet = petService.updatePet(1L, "Buddy", "male", 4, "cat", true, "rules2", "docs2", "rec2", "info2", "consult2", "contact2", "reason2");

        // Assert
        assertThat(updatedPet).isNotNull();
        assertThat(updatedPet.getName()).isEqualTo("Buddy");
        // Другие проверки
    }

    // тесты для удаления существующего и несуществующего питомца
    @Test
    public void testRemoveExistingPet() {
        // Arrange
        Pet pet = new Pet("Max", "male", 3, "dog", false, "rules1", "docs1", "rec1", "info1", "consult1", "contact1", "reason1");
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        // Act
        Pet removedPet = petService.removePet(1L);

        // Assert
        assertThat(removedPet).isNotNull();
        assertThat(removedPet.getName()).isEqualTo("Max");
        // Другие проверки
    }

    @Test
    public void testRemoveNonExistingPet() {
        // Arrange
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Pet removedPet = petService.removePet(1L);

        // Assert
        assertThat(removedPet).isNull();
    }


}

