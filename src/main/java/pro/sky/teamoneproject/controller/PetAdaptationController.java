package pro.sky.teamoneproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.service.PetAdaptationService;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class PetAdaptationController {

    private final PetAdaptationService petAdaptationService;

    public PetAdaptationController(PetAdaptationService petAdaptationService) {
        this.petAdaptationService = petAdaptationService;
    }

    @Operation(
            tags = "Reports",
            summary = "Получение отчета из БД"
    )
    @GetMapping("/get/{id}")
    public PetAdaptation getPetAdaptation(@PathVariable Long id) {
        return petAdaptationService.getPetAdaptation(id);
    }

    @Operation(
            tags = "Reports",
            summary = "Изменение статуса отчета в БД"
    )
    @PutMapping(value = "/update/{id}")
    public PetAdaptation updatePetAdaptation(@PathVariable Long id, @RequestParam("Report ID") boolean check) {
        return petAdaptationService.updatePetAdaptation(id, check);
    }

    @Operation(
            tags = "Reports",
            summary = "Удаление отчета из БД"
    )
    @DeleteMapping(value = "/remove/{id}")
    public PetAdaptation removePet(@PathVariable Long id) {
        return petAdaptationService.removePetAdaptation(id);
    }
}
