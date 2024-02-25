package pro.sky.teamoneproject.controller;

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

    @PostMapping(value = "post/{name}/{gender}/{age}/{species}")
    public Pet addPet(@PathVariable String name,
                      @PathVariable String gender,
                      @PathVariable int age,
                      @PathVariable String species) {
        return petService.addPet(name, gender, age, species);
    }

    @GetMapping("{id}/get")
    public Pet getPet(@PathVariable Long id) {
        return petService.getPet(id);
    }

    @PutMapping(value = "{id}/update/{name}/{gender}/{age}/{species}")
    public Pet updatePet(@PathVariable Long id,
                         @PathVariable String name,
                         @PathVariable String gender,
                         @PathVariable int age,
                         @PathVariable String species) {
        return petService.updatePet(id, name, gender, age, species);
    }

    @DeleteMapping(value = "{id}/remove")
    public Pet removePet(@PathVariable Long id) {
        return petService.removePet(id);
    }
}
