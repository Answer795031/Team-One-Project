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
            summary = "Добавление записи питомца в БД",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "name",
                            description = "Имя"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "gender",
                            schema = @Schema(allowableValues = {"Male", "Female "}),
                            description = "Пол"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "age",
                            description = "Возраст"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "species",
                            schema = @Schema(allowableValues = {"Dog", "Cat"}),
                            description = "Вид"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "invalid",
                            schema = @Schema(description = "Инвалид",type = "boolean"),
                            description = "Наличие инвалидности"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "rulesForGettingToKnowAnimalsUnderTakeFromShelter",
                            description = "Правила знакомства с животным до того, как забрать его из приюта"
                    )
            }
    )
    @PostMapping(value = "/add")
    public Pet addPet(String name,
                      String gender,
                      int age,
                      String species,
                      boolean invalid,
                      String rulesForGettingToKnowAnimalsUnderTakeFromShelter,
                      String listOfDocuments,
                      String listOfRecommendationForMovePet,
                      String informationAboutTheLivingConditionPet,
                      String consultationDogHandlerForFirstCommunication,
                      String contactWitsOtherDogHandlers,
                      String reasonWhyShelterCanReject)  {

        return petService
                .addPet(
                        name,
                        gender,
                        age,
                        species,
                        invalid,
                        rulesForGettingToKnowAnimalsUnderTakeFromShelter,
                        listOfDocuments,
                        listOfRecommendationForMovePet,
                        informationAboutTheLivingConditionPet,
                        consultationDogHandlerForFirstCommunication,
                        contactWitsOtherDogHandlers,
                        reasonWhyShelterCanReject);
    }

    @Operation(
            tags = "Pets",
            summary = "Получение записи питомца из БД",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "id",
                            description = "ID"
                    )
            }
    )
    @GetMapping("/get")
    public Pet getPet(Long id) {
        return petService.getPet(id);
    }

    @Operation(
            tags = "Pets",
            summary = "Изменение записи питомца в БД",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "id",
                            description = "ID"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "name",
                            description = "Имя"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "gender",
                            schema = @Schema(allowableValues = {"Male", "Female "}),
                            description = "Пол"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "age",
                            description = "Возраст"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "species",
                            schema = @Schema(allowableValues = {"Dog", "Cat"}),
                            description = "Вид"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "invalid",
                            schema = @Schema(description = "Инвалид",type = "boolean"),
                            description = "Наличие инвалидности"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "rulesForGettingToKnowAnimalsUnderTakeFromShelter",
                            description = "Правила знакомства с животным до того, как забрать его из приюта"
                    )
            }
    )
    @PutMapping(value = "/update")
    public Pet updatePet(Long id,
                         String name,
                         String gender,
                         int age,
                         String species,
                         boolean invalid,
                         String rulesForGettingToKnowAnimals,
                         String listOfDocuments,
                         String listOfRecommendationForMovePet,
                         String informationAboutTheLivingConditionPet,
                         String consultationDogHandlerForFirstCommunication,
                         String contactWitsOtherDogHandlers,
                         String reasonWhyShelterCanReject) {

        return petService
                .updatePet(
                        id,
                        name,
                        gender,
                        age,
                        species,
                        invalid,
                        rulesForGettingToKnowAnimals,
                        listOfDocuments,
                        listOfRecommendationForMovePet,
                        informationAboutTheLivingConditionPet,
                        consultationDogHandlerForFirstCommunication,
                        contactWitsOtherDogHandlers,
                        reasonWhyShelterCanReject);
    }

    @Operation(
            tags = "Pets",
            summary = "Удаление записи питомца из БД",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "id",
                            description = "ID"
                    )
            }
    )
    @DeleteMapping(value = "/remove")
    public Pet removePet(Long id) {
        return petService.removePet(id);
    }
}
