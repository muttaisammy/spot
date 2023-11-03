package ampath.or.ke.spot.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;


@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lab_results")
public class LabResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "ccc_no")
    private String ccc_no;

    @Column(name = "pname")
    private String pname;

    @Column(name = "county")
    private String county;

    @Column(name = "sub_county")
    private String sub_county;

    @Column(name = "fname")
    private String fname;
    
    @Column(name = "mflcode")
    private String mflcode;
    
    @Column(name = "order_no")
    private int order_no;

    @Column(name = "amrs_location")
    private String amr_location;

    @Column(name = "rec_no")
    private int rec_no;

    @Column(name = "sex")
    private String sex;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "age")
    private int age;

    @Column(name = "pmtct")
    private String pmtct;

    @Column(name = "sample_type")
    private String sample_type;

    @Column(name = "collection_date")
    private Date collection_date;

    @Column(name = "received_status")
    private String received_status;

    @Column(name = "curr_regimen")
    private String curr_regimen;

    @Column(name = "art_init_date")
    private Date art_init_date;

    @Column(name = "justification")
    private String justification;

    @Column(name = "date_received")
    private Date date_received;

    @Column(name = "date_entered")
    private Date date_entered;

    @Column(name = "date_of_testing")
    private Date date_of_testing;

    @Column(name = "date_of_approval")
    private Date date_of_approval;

    @Column(name = "date_of_dispach")
    private Date date_of_dispach;

    @Column(name = "viral_load")
    private String viral_load;

    @Column(name = "entry")
    private String entry;

    public void setId(int id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setCcc_no(String ccc_no) {
        this.ccc_no = ccc_no;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setSub_county(String sub_county) {
        this.sub_county = sub_county;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setMflcode(String mflcode) {
        this.mflcode = mflcode;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public void setAmr_location(String amr_location) {
        this.amr_location = amr_location;
    }

    public void setRec_no(int rec_no) {
        this.rec_no = rec_no;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPmtct(String pmtct) {
        this.pmtct = pmtct;
    }

    public void setSample_type(String sample_type) {
        this.sample_type = sample_type;
    }

    public void setCollection_date(Date collection_date) {
        this.collection_date = collection_date;
    }

    public void setReceived_status(String received_status) {
        this.received_status = received_status;
    }

    public void setCurr_regimen(String curr_regimen) {
        this.curr_regimen = curr_regimen;
    }

    public void setArt_init_date(Date art_init_date) {
        this.art_init_date = art_init_date;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public void setDate_received(Date date_received) {
        this.date_received = date_received;
    }

    public void setDate_entered(Date date_entered) {
        this.date_entered = date_entered;
    }

    public void setDate_of_testing(Date date_of_testing) {
        this.date_of_testing = date_of_testing;
    }

    public void setDate_of_approval(Date date_of_approval) {
        this.date_of_approval = date_of_approval;
    }

    public void setDate_of_dispach(Date date_of_dispach) {
        this.date_of_dispach = date_of_dispach;
    }

    public void setViral_load(String viral_load) {
        this.viral_load = viral_load;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
