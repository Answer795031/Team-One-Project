package pro.sky.teamoneproject.service;

import org.springframework.stereotype.Service;
import pro.sky.teamoneproject.entity.RuleForPet;
import pro.sky.teamoneproject.repository.RulesForPetRepository;

@Service
public class RulesForPetServiceImpl implements RulesForPetService{

    private final RulesForPetRepository rulesForPetRepository;

    public RulesForPetServiceImpl(RulesForPetRepository rulesForPetRepository) {
        this.rulesForPetRepository = rulesForPetRepository;
    }

    @Override
    public RuleForPet addRule(String text) {
        RuleForPet rule = new RuleForPet(text);
        rulesForPetRepository.save(rule);
        return rule;
    }

    @Override
    public RuleForPet getRule(Long id) {
        return rulesForPetRepository.findById(id).get();
    }
}
