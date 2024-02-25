package pro.sky.teamoneproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ShelterClient {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private Long chatId;
    @OneToOne
    private Shelter selectedShelter;
    private LocalDateTime lastTimeAppeal;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShelterClient shelterClient)) return false;

        if (!Objects.equals(id, shelterClient.id)) return false;
        if (!Objects.equals(username, shelterClient.username)) return false;
        return Objects.equals(chatId, shelterClient.chatId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        return result;
    }
}
