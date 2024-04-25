package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.AfyastatErrors;
import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.SMTPServer;
import ampath.or.ke.spot.models.SurgeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;


public interface SurgeRepository extends JpaRepository<SurgeReport, Long> {
    List<SurgeReport> findAll();
    SurgeReport findFirstByOrderByIdDesc();
    List<SurgeReport> findByPersonIdAndYearWeek(int pid,int yw);
    List<SurgeReport> findByMflcode(int mflcode);

   @Query(value = "SELECT mflcode,year_week,\n" +
            "sum(on_schedule) on_schedule,\n" +
            "sum(scheduled_this_week) scheduled_this_week,\n" +
            "sum(visit_this_week)visit_this_week,\n" +
            "sum(early_appointment)early_appointment,\n" +
            "sum(unscheduled_this_week)unscheduled_this_week\n" +
            "FROM surge group by mflcode,year_week order by year_week asc ", nativeQuery = true)

    List<?> summaries ();
   @Query(value="SELECT year_week,\n" +
           "            sum(on_schedule) on_schedule,\n" +
           "            sum(scheduled_this_week) scheduled_this_week,\n" +
           "            sum(visit_this_week)visit_this_week,\n" +
           "            sum(early_appointment)early_appointment,\n" +
           "            sum(unscheduled_this_week)unscheduled_this_week,\n" +
           "            sum(missed_appointment_this_week)missed_appointment_this_week\n"+
           "            FROM etl.surge_weekly_report_dataset_2022_frozen group by year_week order by year_week desc limit 10" , nativeQuery = true)
   List<Tuple> getsummaries ();

   /* @Query(value = "SELECT year_week,\n" +
            "sum(on_schedule) on_schedule,\n" +
            "sum(scheduled_this_week) scheduled_this_week,\n" +
            "sum(visit_this_week)visit_this_week,\n" +
            "sum(early_appointment)early_appointment,\n" +
            "(sum(visit_this_week) + sum(early_appointment))kept_app, \n"+
            "(sum(scheduled_this_week)) -(sum(visit_this_week) + sum(early_appointment)) missed , \n"+
            "sum(unscheduled_this_week)unscheduled_this_week\n" +
            "FROM surge group by year_week order by year_week asc", nativeQuery = true)
    List<Tuple> getsummaries (); */


}