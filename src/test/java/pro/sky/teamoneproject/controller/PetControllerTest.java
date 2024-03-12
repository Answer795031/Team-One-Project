package pro.sky.teamoneproject.controller;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.service.PetService;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootTest
class PetControllerTest {

    @Autowired
    private PetController petController;

    @Mock
    private PetService petService;

    @Test
    void testAddPet() {

        // Задаем значения для параметров
        String name = "Buddy";
        String gender = "Male";
        int age = 3;
        String species = "Dog";
        boolean invalid = false;
        String rulesForGettingToKnowAnimalsUnderTakeFromShelter = "Be patient and gentle"; //Будьте терпеливы и нежны
        String rulesForGettingToKnowAnimals = "rules1";
        String listofDocuments = "docs1";
        String listOfRecommendationForMovePet = "rec1";
        String informationAboutTheLivingConditionPet = "info1";
        String consultationDoghandlerForFirstCommunication = "consult1";
        String contactWitsOtherDoghanlers = "contact1";
        String reasonWhyShelterCanReject = "reason1";

        // Метод addPet вызывает метод addPet из сервиса и возвращает результат
        when(petService.addPet(name, gender, age, species, invalid, rulesForGettingToKnowAnimalsUnderTakeFromShelter, listofDocuments, listOfRecommendationForMovePet,informationAboutTheLivingConditionPet,
                consultationDoghandlerForFirstCommunication, contactWitsOtherDoghanlers, reasonWhyShelterCanReject)).thenReturn(new Pet(1L, name, gender, age, species, invalid, rulesForGettingToKnowAnimalsUnderTakeFromShelter));

        // Вызываем метод контроллера addPet и проверяем результат
        Pet addedPet = petController.addPet(name, gender, age, species, invalid, rulesForGettingToKnowAnimalsUnderTakeFromShelter, listofDocuments, listOfRecommendationForMovePet,informationAboutTheLivingConditionPet,
                consultationDoghandlerForFirstCommunication, contactWitsOtherDoghanlers, reasonWhyShelterCanReject);

        assertEquals(name, addedPet.getName());
        assertEquals(gender, addedPet.getGender());
        assertEquals(age, addedPet.getAge());
        assertEquals(species, addedPet.getSpecies());
        assertEquals(invalid, addedPet.isInvalid());
        assertEquals(rulesForGettingToKnowAnimalsUnderTakeFromShelter, addedPet.getRulesForGettingToKnowAnimals());
    }

    @Test
    void testGetPet() {
        // Задаем идентификатор животного для тестирования
        Long petId = 123L;

        // Создаем mock объект для возвращаемого значения метода getPet из сервиса
        Pet expectedPet = new Pet(petId, "Fluffy", "Female", 2, "Cat", false, "Be playful and affectionate");

        // Устанавливаем поведение для метода getPet, возвращающее ожидаемое животное
        when(petService.getPet(petId)).thenReturn(expectedPet);

        // Вызываем метод контроллера getPet с заданным идентификатором и проверяем результат
        Pet obtainedPet = petController.getPet(petId);

        // Проверяем, что полученное животное соответствует ожидаемому
        assertEquals(expectedPet, obtainedPet);
    }

    @Test
    void testUpdatePet() {
        // Задаем значения для обновления животного
        Long petId = 456L;
        String newName = "Max";
        String newGender = "Male";
        int newAge = 4;
        String newSpecies = "Dog";
        boolean newInvalid = true;
        String newRules = "Be calm and patient";

        // Создаем mock объект для возвращаемого значения метода updatePet из сервиса
        Pet updatedPet = new Pet(petId, newName, newGender, newAge, newSpecies, newInvalid, newRules);

        // Устанавливаем поведение для метода updatePet, возвращающее обновленное животное
        when(petService.updatePet(petId, newName, newGender, newAge, newSpecies, newInvalid, newRules, null, null, null, null, null, null))
                .thenReturn(updatedPet);

        // Вызываем метод контроллера updatePet с заданными значениями для обновления и идентификатором животного
        Pet updated = petController.updatePet(petId, newName, newGender, newAge, newSpecies, newInvalid, newRules,
                null, null, null, null, null, null);

        // Проверяем, что полученное обновленное животное соответствует ожидаемому
        assertEquals(updatedPet, updated);
    }

    @Test
    void testRemovePet() {
        // Задаем идентификатор животного для удаления
        Long petId = 789L;

        // Создаем mock объект для возвращаемого значения метода removePet из сервиса
        Pet removedPet = new Pet(petId, "Rex", "Male", 3, "Dog", true, "Be friendly and loyal");

        // Устанавливаем поведение для метода removePet, возвращающее удаленное животное
        when(petService.removePet(petId)).thenReturn(removedPet);

        // Вызываем метод контроллера removePet с заданным идентификатором и проверяем результат
        Pet removed = petController.removePet(petId);

        // Проверяем, что полученное удаленное животное соответствует ожидаемому
        assertEquals(removedPet, removed);
    }

}
