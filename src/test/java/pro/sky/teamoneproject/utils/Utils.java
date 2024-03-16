package pro.sky.teamoneproject.utils;

import org.json.JSONObject;
import pro.sky.teamoneproject.entity.Pet;
import pro.sky.teamoneproject.entity.Shelter;

public class Utils {
    public static JSONObject getActualPet() throws Exception {
        JSONObject actualPet = new JSONObject();

        actualPet.put("name", "Baaaarsik");
        actualPet.put("gender", "Male");
        actualPet.put("age", 12);
        actualPet.put("species", "Cat");
        actualPet.put("invalid", false);
        actualPet.put("rulesForGettingToKnowAnimals", "None 1");
        actualPet.put("listofDocuments", "Passport");
        actualPet.put("listOfRecommendationForMovePet", "None 2");
        actualPet.put("informationAboutTheLivingConditionPet", "None 3");
        actualPet.put("consultationDoghandlerForFirstCommunication", "None 4");
        actualPet.put("contactWitsOtherDoghanlers", "None 5");
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
        pet.setListofDocuments("Passport");
        pet.setListOfRecommendationForMovePet("None 2");
        pet.setInformationAboutTheLivingConditionPet("None 3");
        pet.setConsultationDoghandlerForFirstCommunication("None 4");
        pet.setContactWitsOtherDoghanlers("None 5");
        pet.setReasonWhyShelterCanReject("None 6");

        return pet;
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
}
