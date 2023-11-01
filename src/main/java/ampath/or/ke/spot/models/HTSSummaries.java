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
@Table(name = "hts_summaries")
public class HTSSummaries {
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

    @Column(name = "total_tested")
    private int total_tested;

    @Column(name = "positive")
    private int pos;

    @Column(name = "negative")
    private int negative;

    @Column(name = "inconclusive")
    private int inconclusive;

    @Column(name = "other")
    private int other;

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

    public int getTotal_tested() {
        return total_tested;
    }

    public void setTotal_tested(int total_tested) {
        this.total_tested = total_tested;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public int getInconclusive() {
        return inconclusive;
    }

    public void setInconclusive(int inconclusive) {
        this.inconclusive = inconclusive;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
}
