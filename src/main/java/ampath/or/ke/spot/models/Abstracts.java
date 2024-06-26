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
@Table(name = "abstracts")
public class Abstracts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "mainauthor")
    private String mainauthor;

    @Column(name = "coauthor")
    private String coauthor;

    @Column(name = "tracker_no")
    private String trackerNo;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "introduction" ,columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "methodology" ,columnDefinition = "TEXT")
    private String methodology;

    @Column(name = "result" ,columnDefinition = "TEXT")
    private String result;

    @Column(name = "conclusion" ,columnDefinition = "TEXT")
    private String conclusion;

    @Column(name = "created_on")
    private Date dateCreated;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMainauthor() {
        return mainauthor;
    }

    public void setMainauthor(String mainauthor) {
        this.mainauthor = mainauthor;
    }

    public String getCoauthor() {
        return coauthor;
    }

    public void setCoauthor(String coauthor) {
        this.coauthor = coauthor;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getTrackerNo() {
        return trackerNo;
    }

    public void setTrackerNo(String trackerNo) {
        this.trackerNo = trackerNo;
    }
}
