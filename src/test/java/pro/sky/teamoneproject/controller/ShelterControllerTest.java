package pro.sky.teamoneproject.controller;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.service.ShelterService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class ShelterControllerTest {

    @Autowired
    private ShelterController shelterController;

    @Mock
    private ShelterService shelterService;

    @Test
    void testAddShelter() {
        // Задаем значения для параметров
        String name = "Best Friends Animal Society";
        String description = "Animal rescue organization";

        // Метод add вызывает метод add из сервиса и возвращает результат
        long shelterId = 1L;
        when(shelterService.add(name, description)).thenReturn(new Shelter(shelterId, name, description));

        // Вызываем метод контроллера add и проверяем результат
        Shelter addedShelter = shelterController.add(name, description);

        assertEquals(name, addedShelter.getName());
        assertEquals(description, addedShelter.getDescription());
    }

    @Test
    void testGetShelter() {
        // Задаем значение id для тестирования
        long shelterId = 1L;

        // Создаем mock объект для возвращаемого значения метода get из сервиса
        Shelter expectedShelter = new Shelter(shelterId, "Save Our Shelter", "Non-profit animal shelter");

        // Устанавливаем поведение для метода get, возвращающее ожидаемый объект Shelter
        when(shelterService.get(shelterId)).thenReturn(expectedShelter);

        // Вызываем метод контроллера get с заданным id и проверяем результат
        Shelter obtainedShelter = shelterController.get(shelterId);

        // Проверяем, что полученный объект Shelter соответствует ожидаемому
        assertEquals(expectedShelter, obtainedShelter);
    }

    @Test
    void testRemoveShelter() {
        // Задаем id объекта Shelter для удаления
        long shelterId = 2L;

        // Вызываем метод контроллера remove с заданным id
        shelterController.remove(shelterId);

        // Проверяем, что метод remove был вызван один раз для данного id
        verify(shelterService, times(1)).remove(shelterId);
    }

    @Test
    void testUpdateShelter() {
        // Задаем значения для обновления объекта Shelter
        long shelterId = 3L;
        String newName = "Homeless Pets Foundation";
        String newDescription = "Rescue and adoption center";

        // Создаем mock объект для возвращаемого значения метода update из сервиса
        Shelter updatedShelter = new Shelter(shelterId, newName, newDescription);

        // Устанавливаем поведение для метода update, возвращающее обновленный объект Shelter
        when(shelterService.update(shelterId, newName, newDescription)).thenReturn(updatedShelter);

        // Вызываем метод контроллера update с заданными значениями для обновления и id
        Shelter updated = shelterController.update(shelterId, newName, newDescription);

        // Проверяем, что полученный объект Shelter соответствует ожидаемому
        assertEquals(updatedShelter, updated);
    }
}