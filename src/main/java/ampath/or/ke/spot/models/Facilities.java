package ampath.or.ke.spot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facilities")
public class Facilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

     @Column(name = "uuid")
    private String uuid;

    @Column(name = "country")
    private String country;

    @Column(name = "Partner")
    private String Partner;

    @Column(name = "location_id")
    private int location_id;

    @Column(name = "facilityname")
    private String facilityname;

    @Column(name = "mflcode")
    private String mflcode;

    @Column(name = "owner")
    private String owner;

    @Column(name = "county")
    private String county;

    @Column(name = "subcounty")
    private String subcounty;

    @Column(name = "ward")
    private String ward;

    @Column(name = "org_unit")
    private String org_unit;

    @Column(name = "emr")
    private String emr;

    @Column(name = "status")
    private int status;

    @Column(name = "txcurr")
    private int txcurr;

    @Column(name = "amrs_id")
    private String amrsid;

    @Column(name = "afyastat_id")
    private String afyastatid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }


    public String getPartner() {
        return Partner;
    }

    public void setPartner(String partner) {
        Partner = partner;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubcounty() {
        return subcounty;
    }

    public void setSubcounty(String subcounty) {
        this.subcounty = subcounty;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }


    public String getEmr() {
        return emr;
    }

    public void setEmr(String emr) {
        this.emr = emr;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
     public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrg_unit() {
        return org_unit;
    }

    public void setOrg_unit(String org_unit) {
        this.org_unit = org_unit;
    }

    public String getMflcode() {
        return mflcode;
    }

    public void setMflcode(String mflcode) {
        this.mflcode = mflcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTxcurr() {
        return txcurr;
    }

    public void setTxcurr(int txcurr) {
        this.txcurr = txcurr;
    }

    public String getAmrsid() {
        return amrsid;
    }

    public void setAmrsid(String amrsid) {
        this.amrsid = amrsid;
    }

    public String getAfyastatid() {
        return afyastatid;
    }

    public void setAfyastatid(String afyastatid) {
        this.afyastatid = afyastatid;
    }
}
