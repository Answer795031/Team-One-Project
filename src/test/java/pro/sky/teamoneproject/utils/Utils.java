package pro.sky.teamoneproject.utils;

import org.json.JSONObject;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.entity.PetAdaptation;
import pro.sky.teamoneproject.entity.Shelter;
import pro.sky.teamoneproject.entity.ShelterClient;

import java.time.LocalDateTime;

import static pro.sky.teamoneproject.constant.ShelterClientMode.DEFAULT;

public class Utils {
    public static JSONObject getActualPet() throws Exception {
        JSONObject actualPet = new JSONObject();

        actualPet.put("name", "Baaaarsik");
        actualPet.put("gender", "Male");
        actualPet.put("age", 12);
        actualPet.put("species", "Cat");
        actualPet.put("invalid", false);
        actualPet.put("rulesForGettingToKnowAnimals", "None 1");
        actualPet.put("listOfDocuments", "Passport");
        actualPet.put("listOfRecommendationForMovePet", "None 2");
        actualPet.put("informationAboutTheLivingConditionPet", "None 3");
        actualPet.put("consultationDogHandlerForFirstCommunication", "None 4");
        actualPet.put("contactWitsOtherDogHandlers", "None 5");
        actualPet.put("reasonWhyShelterCanReject", "None 6");

        return actualPet;
    }

    public static Pet getPet() {
        Pet pet = new Pet();

        pet.setName("Baaaarsik");
        pet.setGender("Male");
        pet.setAge(12);
        pet.setSpecies("Cat");
        pet.setInvalid(false);
        pet.setRulesForGettingToKnowAnimals("None 1");
        pet.setListOfDocuments("Passport");
        pet.setListOfRecommendationForMovePet("None 2");
        pet.setInformationAboutTheLivingConditionPet("None 3");
        pet.setConsultationDogHandlerForFirstCommunication("None 4");
        pet.setContactWitsOtherDogHandlers("None 5");
        pet.setReasonWhyShelterCanReject("None 6");

        return pet;
    }

    public static JSONObject getActualPetAdaptation() throws Exception {
        JSONObject actualPetAdaptation = new JSONObject();

        actualPetAdaptation.put("pathToFilePhoto", "pathToFilePhoto");
        actualPetAdaptation.put("ration", "ration");
        actualPetAdaptation.put("healthAndParticular", "healthAndParticular");
        actualPetAdaptation.put("changeParticular", "changeParticular");
        actualPetAdaptation.put("reportDateTime", LocalDateTime.now());
        actualPetAdaptation.put("shelterClient", getShelterClient());
        actualPetAdaptation.put("checkReport", false);

        return actualPetAdaptation;
    }

    public static PetAdaptation getPetAdaptation() {
        PetAdaptation petAdaptation = new PetAdaptation();

        petAdaptation.setPathToFilePhoto("pathToFilePhoto");
        petAdaptation.setRation("ration");
        petAdaptation.setHealthAndParticular("healthAndParticular");
        petAdaptation.setChangeParticular("changeParticular");
        petAdaptation.getReportDateTime();
        petAdaptation.setShelterClient(getShelterClient());
        petAdaptation.setCheckReport(false);

        return petAdaptation;
    }

    public static JSONObject getActualShelter() throws Exception {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", "Shelter Test name");
        jsonObject.put("description", "Shelter Test desc");

        return jsonObject;
    }

    public static Shelter getShelter() {
        Shelter shelter = new Shelter();

        shelter.setName("Shelter Test name");
        shelter.setDescription("Shelter Test desc");

        return shelter;
    }
    public static ShelterClient getShelterClient() {
        ShelterClient shelterClient = new ShelterClient();
        shelterClient.setUsername("Tretyakov99");
        shelterClient.setChatId(987987987L);
        shelterClient.setSelectedMode(DEFAULT);

        return shelterClient;
    }
}
