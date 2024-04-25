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
@Table(name = "surge")
public class SurgeReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "mflcode")
    private int mflcode;

    @Column(name = "person_id")
    private int personId;

    @Column(name = "location_id")
    private int locationId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "ccno")
    private String ccno;

    @Column(name = "upi")
    private String upi;

    @Column(name = "encounter_date")
    private Date encounterDate;

    @Column(name = "year_week")
    private int yearWeek;

    @Column(name = "age")
    private Float age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "visit_this_week")
    private int visitThisWeek;

    @Column(name = "on_schedule")
    private int onSchedule;

    @Column(name = "early_appointment")
    private int earlyAppointment;

    @Column(name = "late_appointment")
    private int lateAppointment;

    @Column(name = "scheduled_this_week")
    private int scheduled_this_week;

    @Column(name = "unscheduled_this_week")
    private int unscheduled_this_week;

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

    public int getMflcode() {
        return mflcode;
    }

    public void setMflcode(int mflcode) {
        this.mflcode = mflcode;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getCcno() {
        return ccno;
    }

    public void setCcno(String ccno) {
        this.ccno = ccno;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public Date getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(Date encounterDate) {
        this.encounterDate = encounterDate;
    }

    public int getYearWeek() {
        return yearWeek;
    }

    public void setYearWeek(int yearWeek) {
        this.yearWeek = yearWeek;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getVisitThisWeek() {
        return visitThisWeek;
    }

    public void setVisitThisWeek(int visitThisWeek) {
        this.visitThisWeek = visitThisWeek;
    }

    public int getOnSchedule() {
        return onSchedule;
    }

    public void setOnSchedule(int onSchedule) {
        this.onSchedule = onSchedule;
    }

    public int getEarlyAppointment() {
        return earlyAppointment;
    }

    public void setEarlyAppointment(int earlyAppointment) {
        this.earlyAppointment = earlyAppointment;
    }

    public int getLateAppointment() {
        return lateAppointment;
    }

    public void setLateAppointment(int lateAppointment) {
        this.lateAppointment = lateAppointment;
    }

    public int getScheduled_this_week() {
        return scheduled_this_week;
    }

    public void setScheduled_this_week(int scheduled_this_week) {
        this.scheduled_this_week = scheduled_this_week;
    }

    public int getUnscheduled_this_week() {
        return unscheduled_this_week;
    }

    public void setUnscheduled_this_week(int unscheduled_this_week) {
        this.unscheduled_this_week = unscheduled_this_week;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
