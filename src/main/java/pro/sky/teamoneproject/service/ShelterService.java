package pro.sky.teamoneproject.service;

import pro.sky.teamoneproject.entity.Shelter;

public interface ShelterService {
    Shelter add(String name, String description);
    Shelter get(long id);
    void remove(long id);
    Shelter update(long id, String name, String description);
}
