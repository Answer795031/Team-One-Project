package pro.sky.teamoneproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.service.ShelterService;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @Operation(
            tags = "Shelters",
            summary = "Добавление записи приюта в БД"
    )
    @PostMapping("/add")
    public Shelter add(@RequestBody Shelter shelter) {
        return shelterService.add(shelter.getName(), shelter.getDescription());
    }

    @Operation(
            tags = "Shelters",
            summary = "Получение записи приюта из БД"
    )
    @GetMapping("/get/{id}")
    public Shelter get(@PathVariable long id) {
        return shelterService.get(id);
    }

    @Operation(
            tags = "Shelters",
            summary = "Изменение записи приюта в БД"
    )
    @PutMapping("/update/{id}")
    public Shelter update(@PathVariable long id, @RequestBody Shelter shelter) {
        return shelterService.update(id, shelter.getName(), shelter.getDescription());
    }


    @Operation(
            tags = "Shelters",
            summary = "Удаление записи приюта из БД"
    )
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable long id) {
        shelterService.remove(id);
    }
}
