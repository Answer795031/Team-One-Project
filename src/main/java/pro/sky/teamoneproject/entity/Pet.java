package pro.sky.teamoneproject.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private int age;
    private String species;
    private boolean invalid;
    private String rulesForGettingToKnowAnimals;
    private String listOfDocuments;
    private String listOfRecommendationForMovePet;
    private String informationAboutTheLivingConditionPet; //щенок взрослый инвалид
    private String consultationDogHandlerForFirstCommunication;
    private String contactWitsOtherDogHandlers;
    private String reasonWhyShelterCanReject;

    public Pet() {
    }

    public Pet(String name,
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

        this.name = name;
        this.gender = gender;
        this.age = age;
        this.species = species;
        this.invalid = invalid;
        this.rulesForGettingToKnowAnimals = rulesForGettingToKnowAnimals;
        this.listOfDocuments = listOfDocuments;
        this.listOfRecommendationForMovePet = listOfRecommendationForMovePet;
        this.informationAboutTheLivingConditionPet = informationAboutTheLivingConditionPet;
        this.consultationDogHandlerForFirstCommunication = consultationDogHandlerForFirstCommunication;
        this.contactWitsOtherDogHandlers = contactWitsOtherDogHandlers;
        this.reasonWhyShelterCanReject = reasonWhyShelterCanReject;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public String getRulesForGettingToKnowAnimals() {
        return rulesForGettingToKnowAnimals;
    }

    public String getListOfDocuments() {
        return listOfDocuments;
    }

    public String getListOfRecommendationForMovePet() {
        return listOfRecommendationForMovePet;
    }

    public String getInformationAboutTheLivingConditionPet() {
        return informationAboutTheLivingConditionPet;
    }

    public String getConsultationDogHandlerForFirstCommunication() {
        return consultationDogHandlerForFirstCommunication;
    }

    public String getContactWitsOtherDogHandlers() {
        return contactWitsOtherDogHandlers;
    }

    public String getReasonWhyShelterCanReject() {
        return reasonWhyShelterCanReject;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public void setRulesForGettingToKnowAnimals(String rulesForGettingToKnowAnimals) {
        this.rulesForGettingToKnowAnimals = rulesForGettingToKnowAnimals;
    }

    public void setListOfDocuments(String listOfDocuments) {
        this.listOfDocuments = listOfDocuments;
    }

    public void setListOfRecommendationForMovePet(String listOfRecommendationForMovePet) {
        this.listOfRecommendationForMovePet = listOfRecommendationForMovePet;
    }

    public void setInformationAboutTheLivingConditionPet(String informationAboutTheLivingConditionPet) {
        this.informationAboutTheLivingConditionPet = informationAboutTheLivingConditionPet;
    }

    public void setConsultationDogHandlerForFirstCommunication(String consultationDogHandlerForFirstCommunication) {
        this.consultationDogHandlerForFirstCommunication = consultationDogHandlerForFirstCommunication;
    }

    public void setContactWitsOtherDogHandlers(String contactWitsOtherDogHandlers) {
        this.contactWitsOtherDogHandlers = contactWitsOtherDogHandlers;
    }

    public void setReasonWhyShelterCanReject(String reasonWhyShelterCanReject) {
        this.reasonWhyShelterCanReject = reasonWhyShelterCanReject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && invalid == pet.invalid && Objects.equals(id, pet.id) && Objects.equals(name, pet.name) && Objects.equals(gender, pet.gender) && Objects.equals(species, pet.species) && Objects.equals(rulesForGettingToKnowAnimals, pet.rulesForGettingToKnowAnimals) && Objects.equals(listOfDocuments, pet.listOfDocuments) && Objects.equals(listOfRecommendationForMovePet, pet.listOfRecommendationForMovePet) && Objects.equals(informationAboutTheLivingConditionPet, pet.informationAboutTheLivingConditionPet) && Objects.equals(consultationDogHandlerForFirstCommunication, pet.consultationDogHandlerForFirstCommunication) && Objects.equals(contactWitsOtherDogHandlers, pet.contactWitsOtherDogHandlers) && Objects.equals(reasonWhyShelterCanReject, pet.reasonWhyShelterCanReject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age, species, invalid, rulesForGettingToKnowAnimals, listOfDocuments, listOfRecommendationForMovePet, informationAboutTheLivingConditionPet, consultationDogHandlerForFirstCommunication, contactWitsOtherDogHandlers, reasonWhyShelterCanReject);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                ", invalid=" + invalid +
                ", rulesForGettingToKnowAnimals='" + rulesForGettingToKnowAnimals + '\'' +
                ", listOfDocuments='" + listOfDocuments + '\'' +
                ", listOfRecommendationForMovePet='" + listOfRecommendationForMovePet + '\'' +
                ", informationAboutTheLivingConditionPet='" + informationAboutTheLivingConditionPet + '\'' +
                ", consultationDogHandlerForFirstCommunication='" + consultationDogHandlerForFirstCommunication + '\'' +
                ", contactWitsOtherDogHandlers='" + contactWitsOtherDogHandlers + '\'' +
                ", reasonWhyShelterCanReject='" + reasonWhyShelterCanReject + '\'' +
                '}';
    }
}
