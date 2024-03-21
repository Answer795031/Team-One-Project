package pro.sky.teamoneproject.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.exception.ShelterNotFoundException;
import pro.sky.teamoneproject.listener.TelegramBotListener;
import pro.sky.teamoneproject.repository.ShelterRepository;
import pro.sky.teamoneproject.service.ShelterServiceImpl;

@SpringBootTest
public class ShelterServiceTest {

    @Mock
    private ShelterRepository repository;

    @Mock
    private TelegramBotListener telegramBotListener;

    @InjectMocks
    private ShelterServiceImpl service;

    @Captor
    private ArgumentCaptor<Shelter> shelterCaptor;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAdd() {
        // Подготовка входных данных
        String name = "Test Shelter";
        String description = "Test Description";
        Shelter savedShelter = new Shelter();
        savedShelter.setName(name);
        savedShelter.setDescription(description);

        // Имитация метода сохранения репозитория
        when(repository.save(any(Shelter.class))).thenReturn(savedShelter);

        // Имитация метода telegramBotListener
        // Поскольку он возвращает void, нам не нужно указывать поведение

        // Подготовка ожидаемого результата
        Shelter result = service.add(name, description);

        // Начало теста
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(description, result.getDescription());

        // Проверка того, что метод сохранения репозитория был вызван с правильным объектом Shelter.
        verify(repository).save(shelterCaptor.capture());
        Shelter capturedShelter = shelterCaptor.getValue();
        assertEquals(name, capturedShelter.getName());
        assertEquals(description, capturedShelter.getDescription());

        // Проверка того, что метод TelegramBotListener был вызван
        verify(telegramBotListener).updateSheltersCommand();
    }

    @Test
    public void testGetExistingShelter() {
        // Подготовка входных данных
        long id = 1L;
        Shelter existingShelter = new Shelter();
        existingShelter.setName("Existing Shelter");
        existingShelter.setDescription("Existing Description");

        // Имитация метода
        when(repository.findById(anyLong())).thenReturn(Optional.of(existingShelter));

        // Подготовка ожидаемого результата
        Shelter result = service.get(id);

        // Начало теста
        assertNotNull(result);
        assertEquals(existingShelter, result);
    }

    @Test
    public void testGetNonExistingShelter() {
        // Подготовка входных данных
        long id = 1L;

        // Имитация метода findById репозитория для возврата пустого необязательного значения.
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Начало теста
        assertThrows(ShelterNotFoundException.class, () -> {
            service.get(id);
        });
    }

    @Test
    public void testRemove() {
        // Подготовка входных данных
        long id = 1L;

        // Имитация метода telegramBotListener
        // Поскольку он возвращает void, нам не нужно указывать поведение

        service.remove(id);

        // Проверка того, что метод deleteById репозитория был вызван с правильным идентификатором
        verify(repository).deleteById(id);

        // Проверяем, что метод telegramBotListener был вызван
        verify(telegramBotListener).updateSheltersCommand();
    }

    @Test
    void update_ShouldUpdateShelter_WhenValidIdProvided() {
        // Подготовка входных данных
        long id = 1L;
        Shelter shelter = new Shelter();
        shelter.setId(id);
        shelter.setName("Old Name");
        shelter.setDescription("Old Description");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(shelter));

        String newName = "New Name";
        String newDescription = "New Description";

        // Подготовка ожидаемого результата
        Shelter updatedShelter = service.update(id, newName, newDescription);

        // Начало теста
        Assertions.assertEquals(id, updatedShelter.getId());
        Assertions.assertEquals(newName, updatedShelter.getName());
        Assertions.assertEquals(newDescription, updatedShelter.getDescription());

        // Проверка
        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(telegramBotListener, Mockito.times(1)).updateSheltersCommand();
    }

    @Test
    void update_ShouldThrowShelterNotFoundException_WhenInvalidIdProvided() {
        // Подготовка входных данных
        long id = 1L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        String newName = "New Name";
        String newDescription = "New Description";

        // Подготовка ожидаемого результата & Начало теста
        Assertions.assertThrows(ShelterNotFoundException.class, () -> {
            service.update(id, newName, newDescription);
        });

        // Проверка
        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verifyNoInteractions(telegramBotListener);
    }

}
    /*
    @Test
    public void testUpdate() {
        // Подготовка входных данных
        long id = 1L;
        String newName = "Updated Shelter";
        String newDescription = "Updated Description";
        Shelter existingShelter = new Shelter();
        existingShelter.setId(id);
        existingShelter.setName("Old Shelter");
        existingShelter.setDescription("Old Description");

        // Имитация метода получения репозитория
        when(repository.findById(id)).thenReturn(Optional.of(existingShelter));

        // Имитация метода сохранения репозитория
        when(repository.save(any(Shelter.class))).thenReturn(existingShelter);

        // Имитация метода telegramBotListener
        // Поскольку этот метод не возвращает никакого значения, мы можем просто проверить, что он был вызван
        doNothing().when(telegramBotListener).updateSheltersCommand();

        // Подготовка ожидаемого результата
        Shelter updatedShelter = service.update(id, newName, newDescription);

        // Начало теста
        assertEquals(id, updatedShelter.getId());
        assertEquals(newName, updatedShelter.getName());
        assertEquals(newDescription, updatedShelter.getDescription());

        assertNotNull(updatedShelter);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Shelter.class));
        verify(telegramBotListener, times(1)).updateSheltersCommand();
///////
        // Проверка того, что метод сохранения репозитория был вызван с обновленным приютом
        verify(repository).save(shelterCaptor.capture());
        Shelter capturedShelter = shelterCaptor.getValue();
        assertEquals(id, capturedShelter.getId());
        assertEquals(newName, capturedShelter.getName());
        assertEquals(newDescription, capturedShelter.getDescription());

        // Проверяем, что был вызван метод updateSheltersCommand telegramBotListener's
        verify(telegramBotListener).updateSheltersCommand();
    }
 */
