package pro.sky.teamoneproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.service.ShelterService;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    private ShelterService shelterService;

    @Operation(
            tags = "Shelter",
            summary = "Add a new shelter"
    )
    @PostMapping("/add")
    public Shelter add(@RequestBody Shelter shelter) {
        return shelterService.add(shelter.getName(), shelter.getDescription());
    }

    @Operation(
            tags = "Shelter",
            summary = "Get shelter from database"
    )
    @GetMapping("/get/{id}")
    public Shelter get(@PathVariable long id) {
        return shelterService.get(id);
    }

    @Operation(
            tags = "Shelter",
            summary = "Remove shelter"
    )
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable long id) {
        shelterService.remove(id);
    }

    @Operation(
            tags = "Shelter",
            summary = "Edit shelter"
    )
    @PutMapping("/update/{id}")
    public Shelter update(@PathVariable long id, @RequestBody Shelter shelter) {
        return shelterService.update(id, shelter.getName(), shelter.getDescription());
    }
}
