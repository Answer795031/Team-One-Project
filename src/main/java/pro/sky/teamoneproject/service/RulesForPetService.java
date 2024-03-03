package pro.sky.teamoneproject.service;

import pro.sky.teamoneproject.entity.RuleForPet;

public interface RulesForPetService {

    RuleForPet addRule(String text);

    RuleForPet getRule(Long id);
}
