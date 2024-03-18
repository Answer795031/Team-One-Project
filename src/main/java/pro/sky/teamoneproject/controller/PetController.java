package pro.sky.teamoneproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.*;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.service.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(
            tags = "Pets",
            summary = "Добавление записи питомца в БД"
    )
    @PostMapping(value = "/add")
    public Pet addPet(@RequestBody Pet pet) {
        return petService.addPet(
                pet.getName(),
                pet.getGender(),
                pet.getAge(),
                pet.getSpecies(),
                pet.getInvalid(),
                pet.getRulesForGettingToKnowAnimals(),
                pet.getListOfDocuments(),
                pet.getListOfRecommendationForMovePet(),
                pet.getInformationAboutTheLivingConditionPet(),
                pet.getConsultationDogHandlerForFirstCommunication(),
                pet.getContactWitsOtherDogHandlers(),
                pet.getReasonWhyShelterCanReject());
    }

    @Operation(
            tags = "Pets",
            summary = "Получение записи питомца из БД"
    )
    @GetMapping("/get/{id}")
    public Pet getPet(@PathVariable Long id) {
        return petService.getPet(id);
    }

    @Operation(
            tags = "Pets",
            summary = "Изменение записи питомца в БД"
    )
    @PutMapping(value = "/update/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        return petService.updatePet(id,
                pet.getName(),
                pet.getGender(),
                pet.getAge(),
                pet.getSpecies(),
                pet.getInvalid(),
                pet.getRulesForGettingToKnowAnimals(),
                pet.getListOfDocuments(),
                pet.getListOfRecommendationForMovePet(),
                pet.getInformationAboutTheLivingConditionPet(),
                pet.getConsultationDogHandlerForFirstCommunication(),
                pet.getContactWitsOtherDogHandlers(),
                pet.getReasonWhyShelterCanReject());
    }

    @Operation(
            tags = "Pets",
            summary = "Удаление записи питомца из БД"
    )
    @DeleteMapping(value = "/remove/{id}")
    public Pet removePet(@PathVariable Long id) {
        return petService.removePet(id);
    }
}
