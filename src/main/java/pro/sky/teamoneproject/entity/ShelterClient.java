package pro.sky.teamoneproject.entity;

import jakarta.persistence.*;
import pro.sky.teamoneproject.constant.SendReportSteps;
import pro.sky.teamoneproject.constant.ShelterClientMode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static pro.sky.teamoneproject.constant.ShelterClientMode.DEFAULT;
import static pro.sky.teamoneproject.constant.SendReportSteps.*;

@Entity
public class ShelterClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Long chatId;
    @ManyToOne
    private Shelter selectedShelter;
    private LocalDateTime lastTimeAppeal;
    @OneToMany(fetch = FetchType.LAZY)
    public List<Pet> pets;
    private int petViewPageNumber;
    private String phoneNumber;
    private ShelterClientMode selectedMode;
    private SendReportSteps sendReportSteps;

    public ShelterClient() {}

    public ShelterClient(Long id, String username, Long chatId) {
        this.id = id;
        this.username = username;
        this.chatId = chatId;
        this.selectedMode = DEFAULT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Shelter getSelectedShelter() {
        return selectedShelter;
    }

    public void setSelectedShelter(Shelter selectedShelter) {
        this.selectedShelter = selectedShelter;
    }

    public LocalDateTime getLastTimeAppeal() {
        return lastTimeAppeal;
    }

    public void setLastTimeAppeal(LocalDateTime lastTimeAppeal) {
        this.lastTimeAppeal = lastTimeAppeal;
    }

    public int getPetViewPageNumber() {
        return petViewPageNumber;
    }

    public void setPetViewPageNumber(int petViewPageNumber) {
        this.petViewPageNumber = petViewPageNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ShelterClientMode getSelectedMode() {
        if (selectedMode == null) {
            selectedMode = DEFAULT;
        }

        return selectedMode;
    }

    public void setSelectedMode(ShelterClientMode selectedMode) { this.selectedMode = selectedMode; }

    public SendReportSteps getSendReportSteps() {
        if (sendReportSteps == null) {
            sendReportSteps = NONE;
        }

        return sendReportSteps;
    }

    public void setSendReportSteps(SendReportSteps sendReportSteps) {
        this.sendReportSteps = sendReportSteps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShelterClient that)) return false;

        if (petViewPageNumber != that.petViewPageNumber) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(username, that.username)) return false;
        if (!Objects.equals(chatId, that.chatId)) return false;
        if (!Objects.equals(selectedShelter, that.selectedShelter))
            return false;
        if (!Objects.equals(lastTimeAppeal, that.lastTimeAppeal))
            return false;
        if (!Objects.equals(pets, that.pets)) return false;
        if (!Objects.equals(phoneNumber, that.phoneNumber)) return false;
        if (selectedMode != that.selectedMode) return false;
        return sendReportSteps == that.sendReportSteps;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        result = 31 * result + (selectedShelter != null ? selectedShelter.hashCode() : 0);
        result = 31 * result + (lastTimeAppeal != null ? lastTimeAppeal.hashCode() : 0);
        result = 31 * result + (pets != null ? pets.hashCode() : 0);
        result = 31 * result + petViewPageNumber;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (selectedMode != null ? selectedMode.hashCode() : 0);
        result = 31 * result + (sendReportSteps != null ? sendReportSteps.hashCode() : 0);
        return result;
    }
}
