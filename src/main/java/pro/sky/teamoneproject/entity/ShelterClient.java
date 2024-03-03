package pro.sky.teamoneproject.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    public ShelterClient() {}

    public ShelterClient(Long id, String username, Long chatId) {
        this.id = id;
        this.username = username;
        this.chatId = chatId;
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
        return Objects.equals(pets, that.pets);
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
        return result;
    }
}
