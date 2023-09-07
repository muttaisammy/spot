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
@Table(name = "rri")
public class RRI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "mflcode")
    private String mflcode;

    @Column(name = "category")
    private String category;

    @Column(name = "hst_tst")
    private int hst_tst;

    @Column(name = "hst_pos")
    private int hst_pos;

    @Column(name = "hst_link")
    private int hst_link;

    @Column(name = "period")
    private int period;

    @Column(name="created_by")
    private int created_by;

    @Column(name="created_on")
    private Date created_on;

    @Column(name="modified_by", nullable=true)
    private int modified_by;

    @Column(name="modified_on", nullable=true)
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

    public String getMflcode() {
        return mflcode;
    }

    public void setMflcode(String mflcode) {
        this.mflcode = mflcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getHst_tst() {
        return hst_tst;
    }

    public void setHst_tst(int hst_tst) {
        this.hst_tst = hst_tst;
    }

    public int getHst_pos() {
        return hst_pos;
    }

    public void setHst_pos(int hst_pos) {
        this.hst_pos = hst_pos;
    }

    public int getHst_link() {
        return hst_link;
    }

    public void setHst_link(int hst_link) {
        this.hst_link = hst_link;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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
}
