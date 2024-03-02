package pro.sky.teamoneproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
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
            summary = "Add a new pet",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "name",
                            description = "Name"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "gender",
                            schema = @Schema(allowableValues = {"Male", "Female "}),
                            description = "Gender"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "age",
                            description = "Age"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "species",
                            schema = @Schema(allowableValues = {"Dog", "Cat"}),
                            description = "Species"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "rulesForGettingToKnowAnimalsUnderTakeFromShelter",
                            description = "правила знакомства с животным до того, как забрать его из приюта"
                    )
            }
    )
    @PostMapping(value = "/add")
    public Pet addPet(String name,
                      String gender,
                      int age,
                      String species,
                      String rulesForGettingToKnowAnimalsUnderTakeFromShelter,
                      String listofDocuments,
                      String listOfRecommendationForMovePet,
                      String informationAboutTheLivingConditionPet,
                      String consultationDoghandlerForFirstCommunication,
                      String contactWitsOtherDoghanlers,
                      String reasonWhyShelterCanReject)  {
        return petService.addPet(name, gender, age, species,
                rulesForGettingToKnowAnimalsUnderTakeFromShelter, listofDocuments, listOfRecommendationForMovePet,
                informationAboutTheLivingConditionPet, consultationDoghandlerForFirstCommunication,
                contactWitsOtherDoghanlers, reasonWhyShelterCanReject);
    }

    @GetMapping("/get/{id}")
    public Pet getPet(@PathVariable Long id) {
        return petService.getPet(id);
    }

    @PutMapping(value = "/update/{id}")
    public Pet updatePet(@PathVariable Long id,
                         String name,
                         String gender,
                         int age,
                         String species,
                         String rulesForGettingToKnowAnimals,
                         String listofDocuments,
                         String listOfRecommendationForMovePet,
                         String informationAboutTheLivingConditionPet,
                         String consultationDoghandlerForFirstCommunication,
                         String contactWitsOtherDoghanlers,
                         String reasonWhyShelterCanReject) {
        return petService.updatePet(id, name, gender, age, species,rulesForGettingToKnowAnimals,
                listofDocuments,
                listOfRecommendationForMovePet,
                informationAboutTheLivingConditionPet,
                consultationDoghandlerForFirstCommunication,
                contactWitsOtherDoghanlers,
                reasonWhyShelterCanReject);
    }

    @DeleteMapping(value = "/remove/{id}")
    public Pet removePet(@PathVariable Long id) {
        return petService.removePet(id);
    }
}
