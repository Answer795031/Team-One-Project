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
            summary = "Добавление записи приюта в БД",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "name",
                            description = "Название"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "description",
                            description = "Описание"
                    )
            }
    )
    @PostMapping("/add")
    public Shelter add(String name, String description) {
        return shelterService.add(name, description);
    }

    @Operation(
            tags = "Shelters",
            summary = "Получение записи приюта из БД",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "id",
                            description = "ID"
                    )
            }
    )
    @GetMapping("/get")
    public Shelter get(long id) {
        return shelterService.get(id);
    }

    @Operation(
            tags = "Shelters",
            summary = "Изменение записи приюта в БД",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "id",
                            description = "ID"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "name",
                            description = "Название"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "description",
                            description = "Описание"
                    )
            }
    )
    @PutMapping("/update")
    public Shelter update(long id, String name, String description) {
        return shelterService.update(id, name, description);
    }


    @Operation(
            tags = "Shelters",
            summary = "Удаление записи приюта из БД",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "id",
                            description = "ID"
                    )
            }
    )
    @DeleteMapping("/remove")
    public void remove(long id) {
        shelterService.remove(id);
    }
}
