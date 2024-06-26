package ampath.or.ke.spot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
    private String ccc;

    @Column(name = "pname")
    private String pname;

    @Column(name = "county")
    private String county;

    @Column(name = "sub_county")
    private String subCounty;

    @Column(name = "fname")
    private String fname;
    
    @Column(name = "mflcode")
    private String mflcode;
    
    @Column(name = "order_no")
    private int orderno;

    @Column(name = "amrs_location")
    private String amrsLocation;

    @Column(name = "recency_number")
    private int recencyNumber;

    @Column(name = "sex")
    private String sex;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "age")
    private String age;

    @Column(name = "pmtct")
    private String pmtct;

    @Column(name = "sample_type")
    private String sampleType;

    @Column(name = "collection_date")
    private Date collectionDate;

    @Column(name = "received_status")
    private String receivedStatus;

    @Column(name = "curr_regimen")
    private String currRegimen;

    @Column(name = "art_initiation_date")
    private Date artInitiationDate;

    @Column(name = "justification")
    private String justification;

    @Column(name = "date_received")
    private Date dateReceived;

    @Column(name = "date_entered")
    private Date dateEntered;

    @Column(name = "date_of_testing")
    private Date dateOfTesting;

    @Column(name = "date_of_approval")
    private Date dateOfApproval;

    @Column(name = "date_of_dispach")
    private Date dateOfDispach;

    @Column(name = "viral_load")
    private String viralLoad;

    @Column(name = "entry")
    private String entry;

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

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMflcode() {
        return mflcode;
    }

    public void setMflcode(String mflcode) {
        this.mflcode = mflcode;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getAmrsLocation() {
        return amrsLocation;
    }

    public void setAmrsLocation(String amrsLocation) {
        this.amrsLocation = amrsLocation;
    }

    public int getRecencyNumber() {
        return recencyNumber;
    }

    public void setRecencyNumber(int recencyNumber) {
        this.recencyNumber = recencyNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPmtct() {
        return pmtct;
    }

    public void setPmtct(String pmtct) {
        this.pmtct = pmtct;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(String receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public String getCurrRegimen() {
        return currRegimen;
    }

    public void setCurrRegimen(String currRegimen) {
        this.currRegimen = currRegimen;
    }

    public Date getArtInitiationDate() {
        return artInitiationDate;
    }

    public void setArtInitiationDate(Date artInitiationDate) {
        this.artInitiationDate = artInitiationDate;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public Date getDateOfTesting() {
        return dateOfTesting;
    }

    public void setDateOfTesting(Date dateOfTesting) {
        this.dateOfTesting = dateOfTesting;
    }

    public Date getDateOfApproval() {
        return dateOfApproval;
    }

    public void setDateOfApproval(Date dateOfApproval) {
        this.dateOfApproval = dateOfApproval;
    }

    public Date getDateOfDispach() {
        return dateOfDispach;
    }

    public void setDateOfDispach(Date dateOfDispach) {
        this.dateOfDispach = dateOfDispach;
    }

    public String getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(String viralLoad) {
        this.viralLoad = viralLoad;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
