package ampath.or.ke.spot.models;

import ampath.or.ke.spot.utils.GlobalVars;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Calendar;


/**
 * AfyastatClientLineList is representation of HIV test record. It captures Client's test record as would be done in a register.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "afyastat_client_linelist")
public class AfyastatClientLineList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "person_id")
    private int personId;

    @Column(name = "cht_uuid")
    private String chtUuid;

    @Column(name = "encounter_id")
    private int encounterId;

    @Column(name = "encounter_Type")
    private String encounterType;

    @Column(name = "provider_id")
    private int providerId;

    @Column(name = "patient_name")
    private String patinetName;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "test_date")
    private Date testDate;

    @Column(name = "data_entry_date")
    private Date dataEntryDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "location_id")
    private int locationId;

    @Column(name = "mfl")
    private int mfl;

    @Column(name = "emr")
    private String emr;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "provider")
    private String provider;

    @Column(name = "final_test_result")
    private String finalTestResult;

    @Column(name = "entry_point")
    private String entryPoint;

    @Column(name = "sdp")
    private String sdp;


    @Column(name = "created_by")
    private int created_by;

    @Column(name = "created_on")
    private Date created_on;

    @Column(name = "modified_by", nullable = true)
    private int modified_by;

    @Column(name = "modified_on", nullable = true)
    private Date modified_on;

    @Column(name = "age")
    private int age;

    @Column(name = "period_id")
    private int period;

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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(int encounterId) {
        this.encounterId = encounterId;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Date getDataEntryDate() {
        return dataEntryDate;
    }

    public void setDataEntryDate(Date dataEntryDate) {
        this.dataEntryDate = dataEntryDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getMfl() {
        return mfl;
    }

    public void setMfl(int mfl) {
        this.mfl = mfl;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getFinalTestResult() {
        return finalTestResult;
    }

    public void setFinalTestResult(String finalTestResult) {
        this.finalTestResult = finalTestResult;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public int getModified_by() {
        return modified_by;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public Date getModified_on() {
        return modified_on;
    }

    public void setModified_on(Date modified_on) {
        this.modified_on = modified_on;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getEmr() {
        return emr;
    }

    public void setEmr(String emr) {
        this.emr = emr;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getPatinetName() {
        return patinetName;
    }

    public void setPatinetName(String patinetName) {
        this.patinetName = patinetName;
    }

    public String getChtUuid() {
        return chtUuid;
    }

    public void setChtUuid(String chtUuid) {
        this.chtUuid = chtUuid;
    }

    public String getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(String encounterType) {
        this.encounterType = encounterType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
