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
                    )
            }
    )
    @PostMapping(value = "/add")
    public Pet addPet(String name,
                      String gender,
                      int age,
                      String species) {
        return petService.addPet(name, gender, age, species);
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
                         String species) {
        return petService.updatePet(id, name, gender, age, species);
    }

    @DeleteMapping(value = "/remove/{id}")
    public Pet removePet(@PathVariable Long id) {
        return petService.removePet(id);
    }
}
