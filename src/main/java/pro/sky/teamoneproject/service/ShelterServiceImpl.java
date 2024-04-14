package pro.sky.teamoneproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.exception.ShelterNotFoundException;
import pro.sky.teamoneproject.listener.TelegramBotListener;
import pro.sky.teamoneproject.repository.ShelterRepository;

import java.util.Optional;

@Service
public class ShelterServiceImpl implements ShelterService {
    @Autowired
    private ShelterRepository repository;
    @Autowired
    private TelegramBotListener telegramBotListener;

    public Shelter add(String name, String description) {
        Shelter shelter = new Shelter(name, description);

        repository.save(shelter);
        telegramBotListener.updateSheltersCommand();

        return shelter;
    }

    @Override
    public Shelter get(long id) {
        Optional<Shelter> shelter = repository.findById(id);

        if (shelter.isEmpty()) {
            throw new ShelterNotFoundException("Приют по id не найден!");
        }

        return shelter.get();
    }

    @Override
    public void remove(long id) {
        repository.deleteById(id);
        telegramBotListener.updateSheltersCommand();
    }

    @Override
    public Shelter update(long id, String name, String description) {
        Shelter shelter = get(id);

        shelter.setId(id);
        shelter.setName(name);
        shelter.setDescription(description);
        telegramBotListener.updateSheltersCommand();

        return shelter;
    }
}
