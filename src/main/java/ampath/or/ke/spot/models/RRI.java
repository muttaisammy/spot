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

    @Column(name = "tx_new_surge")
    private int tx_new_surge;

    @Column(name = "prep_new_surge")
    private int prep_new_surge;

    @Column(name = "prep_new_pbfw")
    private int prep_new_pbfw;

    @Column(name = "opd_workload")
    private int opd_workload;

    @Column(name = "no_of_clients_screened_hp")
    private int no_of_clients_screened_hp;

    @Column(name = "daily_gbv_cases_identified_sgbv")
    private int daily_gbv_cases_identified_sgbv;

    @Column(name = "daily_gbv_cases_identified_physical_or_emotional")
    private int daily_gbv_cases_identified_physical_or_emotional;

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

    public int getTx_new_surge() {
        return tx_new_surge;
    }

    public void setTx_new_surge(int tx_new_surge) {
        this.tx_new_surge = tx_new_surge;
    }

    public int getPrep_new_surge() {
        return prep_new_surge;
    }

    public void setPrep_new_surge(int prep_new_surge) {
        this.prep_new_surge = prep_new_surge;
    }

    public int getPrep_new_pbfw() {
        return prep_new_pbfw;
    }

    public void setPrep_new_pbfw(int prep_new_pbfw) {
        this.prep_new_pbfw = prep_new_pbfw;
    }

    public int getOpd_workload() {
        return opd_workload;
    }

    public void setOpd_workload(int opd_workload) {
        this.opd_workload = opd_workload;
    }

    public int getNo_of_clients_screened_hp() {
        return no_of_clients_screened_hp;
    }

    public void setNo_of_clients_screened_hp(int no_of_clients_screened_hp) {
        this.no_of_clients_screened_hp = no_of_clients_screened_hp;
    }

    public int getDaily_gbv_cases_identified_sgbv() {
        return daily_gbv_cases_identified_sgbv;
    }

    public void setDaily_gbv_cases_identified_sgbv(int daily_gbv_cases_identified_sgbv) {
        this.daily_gbv_cases_identified_sgbv = daily_gbv_cases_identified_sgbv;
    }

    public int getDaily_gbv_cases_identified_physical_or_emotional() {
        return daily_gbv_cases_identified_physical_or_emotional;
    }

    public void setDaily_gbv_cases_identified_physical_or_emotional(
            int daily_gbv_cases_identified_physical_or_emotional) {
        this.daily_gbv_cases_identified_physical_or_emotional = daily_gbv_cases_identified_physical_or_emotional;
    }
}
