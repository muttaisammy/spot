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
@Table(name = "moh_dataset")
public class MOHDataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;

    @Column(name = "mflcode")
    private String mflcode;

    @Column(name = "facility_name")
    private String facilityName;

    @Column(name = "orgunit")
    private String orgunit;

    @Column(name = "dataelement")
    private String dataelement;

    @Column(name = "categorycombo")
    private String categorycombo;

    @Column(name = "period")
    private String period;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "dataset")
    private String dataset;

    @Column(name = "value")
    private int value;

    @Column(name = "category")
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgunit() {
        return orgunit;
    }

    public void setOrgunit(String orgunit) {
        this.orgunit = orgunit;
    }

    public String getDataelement() {
        return dataelement;
    }

    public void setDataelement(String dataelement) {
        this.dataelement = dataelement;
    }

    public String getCategorycombo() {
        return categorycombo;
    }

    public void setCategorycombo(String categorycombo) {
        this.categorycombo = categorycombo;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMflcode() {
        return mflcode;
    }

    public void setMflcode(String mflcode) {
        this.mflcode = mflcode;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
