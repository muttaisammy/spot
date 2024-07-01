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
@Table(name = "conference_register")
public class ConferenceRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "pref")
    private String pref;

    @Column(name = "full_name")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "county")
    private String county;

    @Column(name = "designation")
    private String designation;

    @Column(name = "abstract_title")
    private String abstractTitle;

    @Column(name = "abstract_presentation")
    private String abstractPresentation;

    @Column(name = "dot")
    private String dot;

    @Column(name="created_on")
    private Date created_on;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAbstractTitle() {
        return abstractTitle;
    }

    public void setAbstractTitle(String abstractTitle) {
        this.abstractTitle = abstractTitle;
    }

    public String getAbstractPresentation() {
        return abstractPresentation;
    }

    public void setAbstractPresentation(String abstractPresentation) {
        this.abstractPresentation = abstractPresentation;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }
}
