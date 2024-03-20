package pro.sky.teamoneproject.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PetAdaptation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pathToFilePhoto;
    private String ration;
    private String healthAndParticular;
    private String changeParticular;
    private LocalDateTime reportDateTime;
    @ManyToOne
    private ShelterClient shelterClient;

    public PetAdaptation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathToFilePhoto() {
        return pathToFilePhoto;
    }

    public void setPathToFilePhoto(String pathToFilePhoto) {
        this.pathToFilePhoto = pathToFilePhoto;
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getHealthAndParticular() {
        return healthAndParticular;
    }

    public void setHealthAndParticular(String healthAndParticular) {
        this.healthAndParticular = healthAndParticular;
    }

    public String getChangeParticular() {
        return changeParticular;
    }

    public void setChangeParticular(String changeParticular) {
        this.changeParticular = changeParticular;
    }

    public LocalDateTime getReportDateTime() {
        return reportDateTime;
    }

    public void resetReportDateTime() {
        this.reportDateTime = LocalDateTime.now();
    }

    public ShelterClient getShelterClient() {
        return shelterClient;
    }

    public void setShelterClient(ShelterClient shelterClient) {
        this.shelterClient = shelterClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetAdaptation that = (PetAdaptation) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(pathToFilePhoto, that.pathToFilePhoto))
            return false;
        if (!Objects.equals(ration, that.ration)) return false;
        if (!Objects.equals(healthAndParticular, that.healthAndParticular))
            return false;
        return Objects.equals(changeParticular, that.changeParticular);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pathToFilePhoto != null ? pathToFilePhoto.hashCode() : 0);
        result = 31 * result + (ration != null ? ration.hashCode() : 0);
        result = 31 * result + (healthAndParticular != null ? healthAndParticular.hashCode() : 0);
        result = 31 * result + (changeParticular != null ? changeParticular.hashCode() : 0);
        return result;
    }
}
