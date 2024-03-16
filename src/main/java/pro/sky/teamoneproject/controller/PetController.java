package pro.sky.teamoneproject.controller;

import io.swagger.v3.oas.annotations.Operation;
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
            summary = "Add a new pet"
    )
    @PostMapping(value = "/add")
    public Pet addPet(@RequestBody Pet pet) {
        return petService.addPet(pet.getName(),
                pet.getGender(),
                pet.getAge(),
                pet.getSpecies(),
                pet.isInvalid(),
                pet.getRulesForGettingToKnowAnimals(),
                pet.getListofDocuments(),
                pet.getListOfRecommendationForMovePet(),
                pet.getInformationAboutTheLivingConditionPet(),
                pet.getConsultationDoghandlerForFirstCommunication(),
                pet.getContactWitsOtherDoghanlers(),
                pet.getReasonWhyShelterCanReject());
    }

    @GetMapping("/get/{id}")
    public Pet getPet(@PathVariable Long id) {
        return petService.getPet(id);
    }

    @PutMapping(value = "/update/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        return petService.updatePet(id,
                pet.getName(),
                pet.getGender(),
                pet.getAge(),
                pet.getSpecies(),
                pet.isInvalid(),
                pet.getRulesForGettingToKnowAnimals(),
                pet.getListofDocuments(),
                pet.getListOfRecommendationForMovePet(),
                pet.getInformationAboutTheLivingConditionPet(),
                pet.getConsultationDoghandlerForFirstCommunication(),
                pet.getContactWitsOtherDoghanlers(),
                pet.getReasonWhyShelterCanReject());
    }

    @DeleteMapping(value = "/remove/{id}")
    public Pet removePet(@PathVariable Long id) {
        return petService.removePet(id);
    }
}
