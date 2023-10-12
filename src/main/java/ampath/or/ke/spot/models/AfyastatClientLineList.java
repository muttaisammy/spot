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
@Table(name = "hts")
public class AfyastatClientLineList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "person_id")
    private int personId;

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

    @Column(name = "provider")
    private String provider;
    @Column(name = "final_test_result")
    private String finalTestResult;

    @Column(name = "entry_point")
    private String entryPoint;

    @Column(name = "sdp")
    private String sdp;





    /**
     * Convenience method: calculates the person's age on test date based on the birthdate.
     * @return int value of the client's age
     */
    public int getAge(){
        if (testDate == null || birthDate == null) {
            return 0;
        }
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(birthDate);

        Calendar testday = Calendar.getInstance();
        testday.setTime(testDate);
        int age = testday.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        // Adjust age when today's date is before the person's birthday
        int testdaysMonth = testday.get(Calendar.MONTH);
        int birthdayMonth = birthday.get(Calendar.MONTH);
        int testdaysDay = testday.get(Calendar.DAY_OF_MONTH);
        int birthdayDay = birthday.get(Calendar.DAY_OF_MONTH);

        if (testdaysMonth < birthdayMonth) {
            age--;
        } else if (testdaysMonth == birthdayMonth && testdaysDay < birthdayDay) {
            age--;
        }
        return age;
    }
    public String getTestMonth(){
        if (testDate == null) {
            return null;
        }
        Calendar testday = Calendar.getInstance();
        testday.setTime(testDate);
        int testDayMonth = testday.get(Calendar.MONTH);
        return GlobalVars.CurrMonth(testDayMonth+1);
    }

    public Integer getTestYear(){
        if (testDate == null) {
            return null;
        }
        Calendar testDay = Calendar.getInstance();
        testDay.setTime(testDate);
        return testDay.get(Calendar.YEAR);
    }
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

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
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

    public int getLocation_id() {
        return locationId;
    }

    public void setLocation_id(int location_id) {
        this.locationId = location_id;
    }

    public Date getDataEntryDate() {
        return dataEntryDate;
    }

    public void setDataEntryDate(Date dataEntryDate) {
        this.dataEntryDate = dataEntryDate;
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
}
