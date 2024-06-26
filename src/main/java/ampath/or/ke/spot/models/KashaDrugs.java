package ampath.or.ke.spot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kasha_drugs")
public class KashaDrugs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "personId")
    private String personId;

    @Column(name = "encounterId")
    private String encounterId;

    @Column(name = "encounterDate")
    private String encounterDate;

    @Column(name = "clinicalDate")
    private String clinicalDate;

    @Column(name = "medicalPickUpTCA")
    private String medicalPickUpTCA;

    @Column(name = "medicationType")
    private String medicationType;


    @Column(name = "drug")
    private String drug;

    @Column(name = "Qty")
    private String Qty;

    @Column(name = "ActualQty")
    private String ActualQty;

    @Column(name = "predictionScores")
    private float predictionScores;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private int created_by;

    @Column(name = "created_on")
    private Date dateCreated;

    @Column(name = "modified_by", nullable = true)
    private int modified_by;

    @Column(name = "modified_on", nullable = true)
    private Date modifiedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }


    public String getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(String encounterDate) {
        this.encounterDate = encounterDate;
    }

    public String getClinicalDate() {
        return clinicalDate;
    }

    public void setClinicalDate(String clinicalDate) {
        this.clinicalDate = clinicalDate;
    }

    public String getMedicalPickUpTCA() {
        return medicalPickUpTCA;
    }

    public void setMedicalPickUpTCA(String medicalPickUpTCA) {
        this.medicalPickUpTCA = medicalPickUpTCA;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getModified_by() {
        return modified_by;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(String medicationType) {
        this.medicationType = medicationType;
    }

    public String getActualQty() {
        return ActualQty;
    }

    public void setActualQty(String actualQty) {
        ActualQty = actualQty;
    }

    public float getPredictionScores() {
        return predictionScores;
    }

    public void setPredictionScores(float predictionScores) {
        this.predictionScores = predictionScores;
    }
}
