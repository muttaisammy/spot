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
@Table(name = "databasesinfo")
 public class DatabasesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "facility_name")
    private String facilityname;

    @Column(name = "url")
    private String url;

    @Column(name = "mflcode")
    private String mflcode;

    @Column(name="status")
    private String status;

    @Column(name="reuploaded")
    private String reuploaded;

    @Column(name = "lastencounter")
    private Date lastencounter;

    @Column(name = "dbsize")
    private String dbsize;

    @Column(name = "dbname")
    private String dbname;

    @Column(name = "created_by")
    private int created_by;

    @Column(name = "created_on")
    private Date created_on;

    @Column(name = "modified_by", nullable = true)
    private int modified_by;

    @Column(name = "modified_on", nullable = true)
    private Date modified_on;

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

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMflcode() {
        return mflcode;
    }

    public void setMflcode(String mflcode) {
        this.mflcode = mflcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastencounter() {
        return lastencounter;
    }

    public void setLastencounter(Date lastencounter) {
        this.lastencounter = lastencounter;
    }

    public String getDbsize() {
        return dbsize;
    }

    public void setDbsize(String dbsize) {
        this.dbsize = dbsize;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
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

    public String getReuploaded() {
        return reuploaded;
    }

    public void setReuploaded(String reuploaded) {
        this.reuploaded = reuploaded;
    }
}
