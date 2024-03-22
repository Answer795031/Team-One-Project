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
    private boolean checkReport;

    public PetAdaptation() {
    }

    public PetAdaptation(String pathToFilePhoto, String ration, String healthAndParticular, String changeParticular, LocalDateTime reportDateTime, ShelterClient shelterClient, boolean checkReport) {
        this.pathToFilePhoto = pathToFilePhoto;
        this.ration = ration;
        this.healthAndParticular = healthAndParticular;
        this.changeParticular = changeParticular;
        this.reportDateTime = reportDateTime;
        this.shelterClient = shelterClient;
        this.checkReport = checkReport;
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

    public boolean isCheckReport() {
        return checkReport;
    }

    public void setCheckReport(boolean checkReport) {
        this.checkReport = checkReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetAdaptation that = (PetAdaptation) o;

        if (checkReport != that.checkReport) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(pathToFilePhoto, that.pathToFilePhoto))
            return false;
        if (!Objects.equals(ration, that.ration)) return false;
        if (!Objects.equals(healthAndParticular, that.healthAndParticular))
            return false;
        if (!Objects.equals(changeParticular, that.changeParticular))
            return false;
        if (!Objects.equals(reportDateTime, that.reportDateTime))
            return false;
        return Objects.equals(shelterClient, that.shelterClient);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pathToFilePhoto != null ? pathToFilePhoto.hashCode() : 0);
        result = 31 * result + (ration != null ? ration.hashCode() : 0);
        result = 31 * result + (healthAndParticular != null ? healthAndParticular.hashCode() : 0);
        result = 31 * result + (changeParticular != null ? changeParticular.hashCode() : 0);
        result = 31 * result + (reportDateTime != null ? reportDateTime.hashCode() : 0);
        result = 31 * result + (shelterClient != null ? shelterClient.hashCode() : 0);
        result = 31 * result + (checkReport ? 1 : 0);
        return result;
    }
}
