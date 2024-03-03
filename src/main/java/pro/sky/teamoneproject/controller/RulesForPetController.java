package pro.sky.teamoneproject.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.entity.RuleForPet;
import pro.sky.teamoneproject.service.RulesForPetService;

@RestController
@RequestMapping("/rules")
public class RulesForPetController {

    private final RulesForPetService rulesForPetService;

    public RulesForPetController(RulesForPetService rulesForPetService) {
        this.rulesForPetService = rulesForPetService;
    }

    @PostMapping(value = "/add")
    public RuleForPet addRule(String text) {
        return rulesForPetService.addRule(text);
    }

    @GetMapping("/get/{id}")
    public RuleForPet getRule(@PathVariable Long id) {
        return rulesForPetService.getRule(id);
    }
}
