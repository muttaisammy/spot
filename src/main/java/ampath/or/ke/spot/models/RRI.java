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

    @Column(name = "second_visits_for_new_art_clients_booked_surge")
    private int second_visits_for_new_art_clients_booked_surge;

    @Column(name = "second_visits_for_new_art_clients_honoured_surge")
    private int second_visits_for_new_art_clients_honoured_surge;

    @Column(name = "daily_scheduled_visits")
    private int daily_scheduled_visits;

    @Column(name = "daily_no_booked_kept_appointment_ka")
    private int daily_no_booked_kept_appointment_ka;

    @Column(name = "daily_no_booked_came_early_ce")
    private int daily_no_booked_came_early_ce;

    @Column(name = "daily_no_booked_missed_appointment_ma")
    private int daily_no_booked_missed_appointment_ma;

    @Column(name = "clients_disengagement_targeted_to_bring_back_surge")
    private int clients_disengagement_targeted_to_bring_back_surge;

    @Column(name = "clients_disengagement_no_brought_back_surge")
    private int clients_disengagement_no_brought_back_surge;

    @Column(name = "iit_target_to_bring_back")
    private int iit_target_to_bring_back;

    @Column(name = "iit_no_brought_back")
    private int iit_no_brought_back;

    @Column(name = "viral_load_done")
    private int viral_load_done;

    @Column(name = "daily_pmtct_stat_new_anc_visits")
    private int daily_pmtct_stat_new_anc_visits;

    @Column(name = "daily_pmtct_stat_kps_at_anc1")
    private int daily_pmtct_stat_kps_at_anc1;

    @Column(name = "daily_pmtct_stat_tested_at_anc1")
    private int daily_pmtct_stat_tested_at_anc1;

    @Column(name = "daily_pmtct_stat_and_syphillies_at_anc1_hiv_pos")
    private int daily_pmtct_stat_and_syphillies_at_anc1_hiv_pos;

    @Column(name = "daily_tested_for_syphilis_at_1st_anc")
    private int daily_tested_for_syphilis_at_1st_anc;

    @Column(name = "daily_pmtct_vl_eligible_for_vl_test")
    private int daily_pmtct_vl_eligible_for_vl_test;

    @Column(name = "eid_eid_initial_tests_less_than_8_weeks")
    private int eid_eid_initial_tests_less_than_8_weeks;

    @Column(name = "eid_eid_initial_tests_8_weeks_to_12_months")
    private int eid_eid_initial_tests_8_weeks_to_12_months;

    @Column(name = "total_eid")
    private int total_eid;

    @Column(name = "eid_eid_pos")
    private int eid_eid_pos;

    @Column(name = "daily_pmtct_iit_no_to_bring_back_target")
    private int daily_pmtct_iit_no_to_bring_back_target;

    @Column(name = "daily_pmtct_iit_no_brought_back_among_iit")
    private int daily_pmtct_iit_no_brought_back_among_iit;

    @Column(name = "daily_pmtct_determine_stock_at_hand")
    private int daily_pmtct_determine_stock_at_hand;

    @Column(name = "daily_pmtct_duo_kits_at_hand")
    private int daily_pmtct_duo_kits_at_hand;

    @Column(name = "daily_pmtct_dbs_filter_papers")
    private int daily_pmtct_dbs_filter_papers;

    @Column(name = "cxca_cxca_screened")
    private int cxca_cxca_screened;

    @Column(name = "cxca_cxca_screened_pos")
    private int cxca_cxca_screened_pos;

    @Column(name = "cxca_cxca_received_treatment")
    private int cxca_cxca_received_treatment;

    @Column(name = "cxca_cxca_suspected")
    private int cxca_cxca_suspected;

    @Column(name = "daily_tpt_tx_curr_never_on_tpt")
    private int daily_tpt_tx_curr_never_on_tpt;

    @Column(name = "daily_tpt_no_screened_neg_for_tb")
    private int daily_tpt_no_screened_neg_for_tb;

    @Column(name = "daily_tpt_started_tpt_tx_new")
    private int daily_tpt_started_tpt_tx_new;

    @Column(name = "daily_tpt_started_tpt_tx_curr")
    private int daily_tpt_started_tpt_tx_curr;

    @Column(name = "daily_tpt_started_tpt")
    private int daily_tpt_started_tpt;

    @Column(name = "daily_1st_95_index_testing_children_tested")
    private int daily_1st_95_index_testing_children_tested;

    @Column(name = "daily_2nd_and_3rd_95_cacx_targeted_wra")
    private int daily_2nd_and_3rd_95_cacx_targeted_wra;

    @Column(name = "daily_2nd_and_3rd_95_eligible_for_vl_test")
    private int daily_2nd_and_3rd_95_eligible_for_vl_test;

    @Column(name = "daily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible")
    private int daily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible;

    @Column(name = "daily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated")
    private int daily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated;

    @Column(name = "daily_2nd_and_3rd_95_no_newly_iit")
    private int daily_2nd_and_3rd_95_no_newly_iit;

    @Column(name = "daily_2nd_and_3rd_95_tb_ccc_no_screened")
    private int daily_2nd_and_3rd_95_tb_ccc_no_screened;

    @Column(name = "daily_2nd_and_3rd_95_tb_ccc_workload")
    private int daily_2nd_and_3rd_95_tb_ccc_workload;

    @Column(name = "daily_2nd_and_3rd_95_tb_opd_no_screened")
    private int daily_2nd_and_3rd_95_tb_opd_no_screened;

    @Column(name = "daily_2nd_and_3rd_95_tb_opd_workload")
    private int daily_2nd_and_3rd_95_tb_opd_workload;

    @Column(name = "daily_other_hiv_tests")
    private int daily_other_hiv_tests;

    @Column(name = "daily_pmtct_stat_known_status_kp_plus_tested")
    private int daily_pmtct_stat_known_status_kp_plus_tested;

    @Column(name = "daily_pmtct_stat_pmtct_stat_d")
    private int daily_pmtct_stat_pmtct_stat_d;

    @Column(name = "daily_tpt_active_on_tpt_cummulative")
    private int daily_tpt_active_on_tpt_cummulative;

    @Column(name = "daily_tpt_completed_tpt")
    private int daily_tpt_completed_tpt;

    @Column(name = "daily_tpt_completed_tpt_tx_new")
    private int daily_tpt_completed_tpt_tx_new;

    @Column(name = "daily_tpt_completed_tpt_tx_curr")
    private int daily_tpt_completed_tpt_tx_curr;

    @Column(name = "daily_pmtct_missed_hiv_testing_target")
    private int daily_pmtct_missed_hiv_testing_target;

    @Column(name = "daily_pmtct_reached_and_ccounted")
    private int daily_pmtct_reached_and_ccounted;

    @Column(name = "daily_pmtct_iit_new_iit_clients")
    private int daily_pmtct_iit_new_iit_clients;

    @Column(name = "daily_pmtct_tpt_uptake_eligible_for_tpt")
    private int daily_pmtct_tpt_uptake_eligible_for_tpt;

    @Column(name = "daily_pmtct_tpt_uptake_started_on_tpt")
    private int daily_pmtct_tpt_uptake_started_on_tpt;

    @Column(name = "daily_prep_new_agyw")
    private int daily_prep_new_agyw;

    @Column(name = "week")
    private int week;

    @Column(name = "month")
    private int month;

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

    public int getSecond_visits_for_new_art_clients_booked_surge() {
        return second_visits_for_new_art_clients_booked_surge;
    }

    public void setSecond_visits_for_new_art_clients_booked_surge(int second_visits_for_new_art_clients_booked_surge) {
        this.second_visits_for_new_art_clients_booked_surge = second_visits_for_new_art_clients_booked_surge;
    }

    public int getSecond_visits_for_new_art_clients_honoured_surge() {
        return second_visits_for_new_art_clients_honoured_surge;
    }

    public void setSecond_visits_for_new_art_clients_honoured_surge(int second_visits_for_new_art_clients_honoured_surge) {
        this.second_visits_for_new_art_clients_honoured_surge = second_visits_for_new_art_clients_honoured_surge;
    }

    public int getDaily_scheduled_visits() {
        return daily_scheduled_visits;
    }

    public void setDaily_scheduled_visits(int daily_scheduled_visits) {
        this.daily_scheduled_visits = daily_scheduled_visits;
    }

    public int getDaily_no_booked_kept_appointment_ka() {
        return daily_no_booked_kept_appointment_ka;
    }

    public void setDaily_no_booked_kept_appointment_ka(int daily_no_booked_kept_appointment_ka) {
        this.daily_no_booked_kept_appointment_ka = daily_no_booked_kept_appointment_ka;
    }

    public int getDaily_no_booked_came_early_ce() {
        return daily_no_booked_came_early_ce;
    }

    public void setDaily_no_booked_came_early_ce(int daily_no_booked_came_early_ce) {
        this.daily_no_booked_came_early_ce = daily_no_booked_came_early_ce;
    }

    public int getDaily_no_booked_missed_appointment_ma() {
        return daily_no_booked_missed_appointment_ma;
    }

    public void setDaily_no_booked_missed_appointment_ma(int daily_no_booked_missed_appointment_ma) {
        this.daily_no_booked_missed_appointment_ma = daily_no_booked_missed_appointment_ma;
    }

    public int getClients_disengagement_targeted_to_bring_back_surge() {
        return clients_disengagement_targeted_to_bring_back_surge;
    }

    public void setClients_disengagement_targeted_to_bring_back_surge(int clients_disengagement_targeted_to_bring_back_surge) {
        this.clients_disengagement_targeted_to_bring_back_surge = clients_disengagement_targeted_to_bring_back_surge;
    }

    public int getClients_disengagement_no_brought_back_surge() {
        return clients_disengagement_no_brought_back_surge;
    }

    public void setClients_disengagement_no_brought_back_surge(int clients_disengagement_no_brought_back_surge) {
        this.clients_disengagement_no_brought_back_surge = clients_disengagement_no_brought_back_surge;
    }

    public int getIit_target_to_bring_back() {
        return iit_target_to_bring_back;
    }

    public void setIit_target_to_bring_back(int iit_target_to_bring_back) {
        this.iit_target_to_bring_back = iit_target_to_bring_back;
    }

    public int getIit_no_brought_back() {
        return iit_no_brought_back;
    }

    public void setIit_no_brought_back(int iit_no_brought_back) {
        this.iit_no_brought_back = iit_no_brought_back;
    }

    public int getViral_load_done() {
        return viral_load_done;
    }

    public void setViral_load_done(int viral_load_done) {
        this.viral_load_done = viral_load_done;
    }

    public int getDaily_pmtct_stat_new_anc_visits() {
        return daily_pmtct_stat_new_anc_visits;
    }

    public void setDaily_pmtct_stat_new_anc_visits(int daily_pmtct_stat_new_anc_visits) {
        this.daily_pmtct_stat_new_anc_visits = daily_pmtct_stat_new_anc_visits;
    }

    public int getDaily_pmtct_stat_kps_at_anc1() {
        return daily_pmtct_stat_kps_at_anc1;
    }

    public void setDaily_pmtct_stat_kps_at_anc1(int daily_pmtct_stat_kps_at_anc1) {
        this.daily_pmtct_stat_kps_at_anc1 = daily_pmtct_stat_kps_at_anc1;
    }

    public int getDaily_pmtct_stat_tested_at_anc1() {
        return daily_pmtct_stat_tested_at_anc1;
    }

    public void setDaily_pmtct_stat_tested_at_anc1(int daily_pmtct_stat_tested_at_anc1) {
        this.daily_pmtct_stat_tested_at_anc1 = daily_pmtct_stat_tested_at_anc1;
    }

    public int getDaily_pmtct_stat_and_syphillies_at_anc1_hiv_pos() {
        return daily_pmtct_stat_and_syphillies_at_anc1_hiv_pos;
    }

    public void setDaily_pmtct_stat_and_syphillies_at_anc1_hiv_pos(int daily_pmtct_stat_and_syphillies_at_anc1_hiv_pos) {
        this.daily_pmtct_stat_and_syphillies_at_anc1_hiv_pos = daily_pmtct_stat_and_syphillies_at_anc1_hiv_pos;
    }

    public int getDaily_tested_for_syphilis_at_1st_anc() {
        return daily_tested_for_syphilis_at_1st_anc;
    }

    public void setDaily_tested_for_syphilis_at_1st_anc(int daily_tested_for_syphilis_at_1st_anc) {
        this.daily_tested_for_syphilis_at_1st_anc = daily_tested_for_syphilis_at_1st_anc;
    }

    public int getDaily_pmtct_vl_eligible_for_vl_test() {
        return daily_pmtct_vl_eligible_for_vl_test;
    }

    public void setDaily_pmtct_vl_eligible_for_vl_test(int daily_pmtct_vl_eligible_for_vl_test) {
        this.daily_pmtct_vl_eligible_for_vl_test = daily_pmtct_vl_eligible_for_vl_test;
    }

    public int getEid_eid_initial_tests_less_than_8_weeks() {
        return eid_eid_initial_tests_less_than_8_weeks;
    }

    public void setEid_eid_initial_tests_less_than_8_weeks(int eid_eid_initial_tests_less_than_8_weeks) {
        this.eid_eid_initial_tests_less_than_8_weeks = eid_eid_initial_tests_less_than_8_weeks;
    }

    public int getEid_eid_initial_tests_8_weeks_to_12_months() {
        return eid_eid_initial_tests_8_weeks_to_12_months;
    }

    public void setEid_eid_initial_tests_8_weeks_to_12_months(int eid_eid_initial_tests_8_weeks_to_12_months) {
        this.eid_eid_initial_tests_8_weeks_to_12_months = eid_eid_initial_tests_8_weeks_to_12_months;
    }

    public int getTotal_eid() {
        return total_eid;
    }

    public void setTotal_eid(int total_eid) {
        this.total_eid = total_eid;
    }

    public int getEid_eid_pos() {
        return eid_eid_pos;
    }

    public void setEid_eid_pos(int eid_eid_pos) {
        this.eid_eid_pos = eid_eid_pos;
    }

    public int getDaily_pmtct_iit_no_to_bring_back_target() {
        return daily_pmtct_iit_no_to_bring_back_target;
    }

    public void setDaily_pmtct_iit_no_to_bring_back_target(int daily_pmtct_iit_no_to_bring_back_target) {
        this.daily_pmtct_iit_no_to_bring_back_target = daily_pmtct_iit_no_to_bring_back_target;
    }

    public int getDaily_pmtct_iit_no_brought_back_among_iit() {
        return daily_pmtct_iit_no_brought_back_among_iit;
    }

    public void setDaily_pmtct_iit_no_brought_back_among_iit(int daily_pmtct_iit_no_brought_back_among_iit) {
        this.daily_pmtct_iit_no_brought_back_among_iit = daily_pmtct_iit_no_brought_back_among_iit;
    }

    public int getDaily_pmtct_determine_stock_at_hand() {
        return daily_pmtct_determine_stock_at_hand;
    }

    public void setDaily_pmtct_determine_stock_at_hand(int daily_pmtct_determine_stock_at_hand) {
        this.daily_pmtct_determine_stock_at_hand = daily_pmtct_determine_stock_at_hand;
    }

    public int getDaily_pmtct_duo_kits_at_hand() {
        return daily_pmtct_duo_kits_at_hand;
    }

    public void setDaily_pmtct_duo_kits_at_hand(int daily_pmtct_duo_kits_at_hand) {
        this.daily_pmtct_duo_kits_at_hand = daily_pmtct_duo_kits_at_hand;
    }

    public int getDaily_pmtct_dbs_filter_papers() {
        return daily_pmtct_dbs_filter_papers;
    }

    public void setDaily_pmtct_dbs_filter_papers(int daily_pmtct_dbs_filter_papers) {
        this.daily_pmtct_dbs_filter_papers = daily_pmtct_dbs_filter_papers;
    }

    public int getCxca_cxca_screened() {
        return cxca_cxca_screened;
    }

    public void setCxca_cxca_screened(int cxca_cxca_screened) {
        this.cxca_cxca_screened = cxca_cxca_screened;
    }

    public int getCxca_cxca_screened_pos() {
        return cxca_cxca_screened_pos;
    }

    public void setCxca_cxca_screened_pos(int cxca_cxca_screened_pos) {
        this.cxca_cxca_screened_pos = cxca_cxca_screened_pos;
    }

    public int getCxca_cxca_received_treatment() {
        return cxca_cxca_received_treatment;
    }

    public void setCxca_cxca_received_treatment(int cxca_cxca_received_treatment) {
        this.cxca_cxca_received_treatment = cxca_cxca_received_treatment;
    }

    public int getCxca_cxca_suspected() {
        return cxca_cxca_suspected;
    }

    public void setCxca_cxca_suspected(int cxca_cxca_suspected) {
        this.cxca_cxca_suspected = cxca_cxca_suspected;
    }

    public int getDaily_tpt_tx_curr_never_on_tpt() {
        return daily_tpt_tx_curr_never_on_tpt;
    }

    public void setDaily_tpt_tx_curr_never_on_tpt(int daily_tpt_tx_curr_never_on_tpt) {
        this.daily_tpt_tx_curr_never_on_tpt = daily_tpt_tx_curr_never_on_tpt;
    }

    public int getDaily_tpt_no_screened_neg_for_tb() {
        return daily_tpt_no_screened_neg_for_tb;
    }

    public void setDaily_tpt_no_screened_neg_for_tb(int daily_tpt_no_screened_neg_for_tb) {
        this.daily_tpt_no_screened_neg_for_tb = daily_tpt_no_screened_neg_for_tb;
    }

    public int getDaily_tpt_started_tpt_tx_new() {
        return daily_tpt_started_tpt_tx_new;
    }

    public void setDaily_tpt_started_tpt_tx_new(int daily_tpt_started_tpt_tx_new) {
        this.daily_tpt_started_tpt_tx_new = daily_tpt_started_tpt_tx_new;
    }

    public int getDaily_tpt_started_tpt_tx_curr() {
        return daily_tpt_started_tpt_tx_curr;
    }

    public void setDaily_tpt_started_tpt_tx_curr(int daily_tpt_started_tpt_tx_curr) {
        this.daily_tpt_started_tpt_tx_curr = daily_tpt_started_tpt_tx_curr;
    }

    public int getDaily_tpt_started_tpt() {
        return daily_tpt_started_tpt;
    }

    public void setDaily_tpt_started_tpt(int daily_tpt_started_tpt) {
        this.daily_tpt_started_tpt = daily_tpt_started_tpt;
    }

    public int getDaily_1st_95_index_testing_children_tested() {
        return daily_1st_95_index_testing_children_tested;
    }

    public void setDaily_1st_95_index_testing_children_tested(int daily_1st_95_index_testing_children_tested) {
        this.daily_1st_95_index_testing_children_tested = daily_1st_95_index_testing_children_tested;
    }

    public int getDaily_2nd_and_3rd_95_cacx_targeted_wra() {
        return daily_2nd_and_3rd_95_cacx_targeted_wra;
    }

    public void setDaily_2nd_and_3rd_95_cacx_targeted_wra(int daily_2nd_and_3rd_95_cacx_targeted_wra) {
        this.daily_2nd_and_3rd_95_cacx_targeted_wra = daily_2nd_and_3rd_95_cacx_targeted_wra;
    }

    public int getDaily_2nd_and_3rd_95_eligible_for_vl_test() {
        return daily_2nd_and_3rd_95_eligible_for_vl_test;
    }

    public void setDaily_2nd_and_3rd_95_eligible_for_vl_test(int daily_2nd_and_3rd_95_eligible_for_vl_test) {
        this.daily_2nd_and_3rd_95_eligible_for_vl_test = daily_2nd_and_3rd_95_eligible_for_vl_test;
    }

    public int getDaily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible() {
        return daily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible;
    }

    public void setDaily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible(int daily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible) {
        this.daily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible = daily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible;
    }

    public int getDaily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated() {
        return daily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated;
    }

    public void setDaily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated(int daily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated) {
        this.daily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated = daily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated;
    }

    public int getDaily_2nd_and_3rd_95_no_newly_iit() {
        return daily_2nd_and_3rd_95_no_newly_iit;
    }

    public void setDaily_2nd_and_3rd_95_no_newly_iit(int daily_2nd_and_3rd_95_no_newly_iit) {
        this.daily_2nd_and_3rd_95_no_newly_iit = daily_2nd_and_3rd_95_no_newly_iit;
    }

    public int getDaily_2nd_and_3rd_95_tb_ccc_no_screened() {
        return daily_2nd_and_3rd_95_tb_ccc_no_screened;
    }

    public void setDaily_2nd_and_3rd_95_tb_ccc_no_screened(int daily_2nd_and_3rd_95_tb_ccc_no_screened) {
        this.daily_2nd_and_3rd_95_tb_ccc_no_screened = daily_2nd_and_3rd_95_tb_ccc_no_screened;
    }

    public int getDaily_2nd_and_3rd_95_tb_ccc_workload() {
        return daily_2nd_and_3rd_95_tb_ccc_workload;
    }

    public void setDaily_2nd_and_3rd_95_tb_ccc_workload(int daily_2nd_and_3rd_95_tb_ccc_workload) {
        this.daily_2nd_and_3rd_95_tb_ccc_workload = daily_2nd_and_3rd_95_tb_ccc_workload;
    }

    public int getDaily_2nd_and_3rd_95_tb_opd_no_screened() {
        return daily_2nd_and_3rd_95_tb_opd_no_screened;
    }

    public void setDaily_2nd_and_3rd_95_tb_opd_no_screened(int daily_2nd_and_3rd_95_tb_opd_no_screened) {
        this.daily_2nd_and_3rd_95_tb_opd_no_screened = daily_2nd_and_3rd_95_tb_opd_no_screened;
    }

    public int getDaily_2nd_and_3rd_95_tb_opd_workload() {
        return daily_2nd_and_3rd_95_tb_opd_workload;
    }

    public void setDaily_2nd_and_3rd_95_tb_opd_workload(int daily_2nd_and_3rd_95_tb_opd_workload) {
        this.daily_2nd_and_3rd_95_tb_opd_workload = daily_2nd_and_3rd_95_tb_opd_workload;
    }

    public int getDaily_other_hiv_tests() {
        return daily_other_hiv_tests;
    }

    public void setDaily_other_hiv_tests(int daily_other_hiv_tests) {
        this.daily_other_hiv_tests = daily_other_hiv_tests;
    }

    public int getDaily_pmtct_stat_known_status_kp_plus_tested() {
        return daily_pmtct_stat_known_status_kp_plus_tested;
    }

    public void setDaily_pmtct_stat_known_status_kp_plus_tested(int daily_pmtct_stat_known_status_kp_plus_tested) {
        this.daily_pmtct_stat_known_status_kp_plus_tested = daily_pmtct_stat_known_status_kp_plus_tested;
    }

    public int getDaily_pmtct_stat_pmtct_stat_d() {
        return daily_pmtct_stat_pmtct_stat_d;
    }

    public void setDaily_pmtct_stat_pmtct_stat_d(int daily_pmtct_stat_pmtct_stat_d) {
        this.daily_pmtct_stat_pmtct_stat_d = daily_pmtct_stat_pmtct_stat_d;
    }

    public int getDaily_tpt_active_on_tpt_cummulative() {
        return daily_tpt_active_on_tpt_cummulative;
    }

    public void setDaily_tpt_active_on_tpt_cummulative(int daily_tpt_active_on_tpt_cummulative) {
        this.daily_tpt_active_on_tpt_cummulative = daily_tpt_active_on_tpt_cummulative;
    }

    public int getDaily_tpt_completed_tpt() {
        return daily_tpt_completed_tpt;
    }

    public void setDaily_tpt_completed_tpt(int daily_tpt_completed_tpt) {
        this.daily_tpt_completed_tpt = daily_tpt_completed_tpt;
    }

    public int getDaily_tpt_completed_tpt_tx_new() {
        return daily_tpt_completed_tpt_tx_new;
    }

    public void setDaily_tpt_completed_tpt_tx_new(int daily_tpt_completed_tpt_tx_new) {
        this.daily_tpt_completed_tpt_tx_new = daily_tpt_completed_tpt_tx_new;
    }

    public int getDaily_tpt_completed_tpt_tx_curr() {
        return daily_tpt_completed_tpt_tx_curr;
    }

    public void setDaily_tpt_completed_tpt_tx_curr(int daily_tpt_completed_tpt_tx_curr) {
        this.daily_tpt_completed_tpt_tx_curr = daily_tpt_completed_tpt_tx_curr;
    }

    public int getDaily_pmtct_missed_hiv_testing_target() {
        return daily_pmtct_missed_hiv_testing_target;
    }

    public void setDaily_pmtct_missed_hiv_testing_target(int daily_pmtct_missed_hiv_testing_target) {
        this.daily_pmtct_missed_hiv_testing_target = daily_pmtct_missed_hiv_testing_target;
    }

    public int getDaily_pmtct_reached_and_ccounted() {
        return daily_pmtct_reached_and_ccounted;
    }

    public void setDaily_pmtct_reached_and_ccounted(int daily_pmtct_reached_and_ccounted) {
        this.daily_pmtct_reached_and_ccounted = daily_pmtct_reached_and_ccounted;
    }

    public int getDaily_pmtct_iit_new_iit_clients() {
        return daily_pmtct_iit_new_iit_clients;
    }

    public void setDaily_pmtct_iit_new_iit_clients(int daily_pmtct_iit_new_iit_clients) {
        this.daily_pmtct_iit_new_iit_clients = daily_pmtct_iit_new_iit_clients;
    }

    public int getDaily_pmtct_tpt_uptake_eligible_for_tpt() {
        return daily_pmtct_tpt_uptake_eligible_for_tpt;
    }

    public void setDaily_pmtct_tpt_uptake_eligible_for_tpt(int daily_pmtct_tpt_uptake_eligible_for_tpt) {
        this.daily_pmtct_tpt_uptake_eligible_for_tpt = daily_pmtct_tpt_uptake_eligible_for_tpt;
    }

    public int getDaily_pmtct_tpt_uptake_started_on_tpt() {
        return daily_pmtct_tpt_uptake_started_on_tpt;
    }

    public void setDaily_pmtct_tpt_uptake_started_on_tpt(int daily_pmtct_tpt_uptake_started_on_tpt) {
        this.daily_pmtct_tpt_uptake_started_on_tpt = daily_pmtct_tpt_uptake_started_on_tpt;
    }

    public int getDaily_prep_new_agyw() {
        return daily_prep_new_agyw;
    }

    public void setDaily_prep_new_agyw(int daily_prep_new_agyw) {
        this.daily_prep_new_agyw = daily_prep_new_agyw;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
