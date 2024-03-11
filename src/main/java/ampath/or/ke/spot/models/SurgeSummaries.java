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
@Table(name = "surge_summaries")
public class SurgeSummaries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "mflcode")
    private int mflcode;

    @Column(name = "year_week")
    private int yearWeek;

    @Column(name = "visit_this_week")
    private int visitThisWeek;
    @Column(name = "on_schedule")
    private int onSchedule;

    @Column(name = "early_appointment")
    private int earlyAppointment;

    @Column(name = "late_appointment")
    private int lateAppointment;

    @Column(name = "scheduled_this_week")
    private int scheduled_this_week;

    @Column(name = "unscheduled_this_week")
    private int unscheduled_this_week;



}
