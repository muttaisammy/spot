package ampath.or.ke.spot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hivsummaries")
public class HIVSummaries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "mflcode")
    private String mflcode;

    @Column(name = "Month")
    private String month;

    @Column(name = "year")
    private int year;

    @Column(name = "tx_new")
    private int tx_new;

    @Column(name = "tx_curr")
    private int tx_curr;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTx_new() {
        return tx_new;
    }

    public void setTx_new(int tx_new) {
        this.tx_new = tx_new;
    }

    public int getTx_curr() {
        return tx_curr;
    }

    public void setTx_curr(int tx_curr) {
        this.tx_curr = tx_curr;
    }
}
