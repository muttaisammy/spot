package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.Pendullums;
import ampath.or.ke.spot.models.Periods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository("pendullumReporitory")
public interface PendullumReporitory extends JpaRepository<Pendullums, Long> {

   List<Pendullums> findAll();
   @Query(value="select \n" +
           "patient_identifier,\n" +
           "     gender,\n" +
           "     birthdate,\n"+
           "     Encounter_Date,\n" +
           "     height,\n" +
           "     weight,\n" +
           "     Next_clinical_appointment,\n" +
           "     Diagnosis_Date,\n" +
           "     Tuberculosis_Treatment_Plan,\n" +
           "     Current_WHO_HIV_Stage,\n" +
           "     CD4_Count,\n" +
           "     Is_ART,\n" +
           "     Is_PMTCT,\n" +
           "     Viral_Load,\n" +
           "     Adherence,\n" +
           "     Education_Level,\n" +
           "     Screening_For_STI,\n" +
           "     Classification_Of_Malnutrition,\n" +
           "     Isoniazid_Use,\n" +
           "     Cotrimoxazole_Use\n" +
           "     from ampath_spot_live.pendullum_data_new_new" , nativeQuery = true)
   Page<?> getAllsummaries (Pageable pageable);
   @Query(value="select \n" +
           "patient_identifier,\n" +
           "     gender,\n" +
           "     birthdate,\n"+
           "     Encounter_Date,\n" +
           "     height,\n" +
           "     weight,\n" +
           "     Next_clinical_appointment,\n" +
           "     Diagnosis_Date,\n" +
           "     Tuberculosis_Treatment_Plan,\n" +
           "     Current_WHO_HIV_Stage,\n" +
           "     CD4_Count,\n" +
           "     Is_ART,\n" +
           "     Is_PMTCT,\n" +
           "     Viral_Load,\n" +
           "     Adherence,\n" +
           "     Education_Level,\n" +
           "     Screening_For_STI,\n" +
           "     Classification_Of_Malnutrition,\n" +
           "     Isoniazid_Use,\n" +
           "     Cotrimoxazole_Use\n" +
           "     from ampath_spot_live.pendullum_data_new_new" , nativeQuery = true)
   List<Tuple> getsummaries ();

   @Query(value="select \n" +
           "patient_identifier,\n" +
           "     gender,\n" +
           "     birthdate,\n"+
           "     Encounter_Date,\n" +
           "     height,\n" +
           "     weight,\n" +
           "     Next_clinical_appointment,\n" +
           "     Diagnosis_Date,\n" +
           "     Tuberculosis_Treatment_Plan,\n" +
           "     Current_WHO_HIV_Stage,\n" +
           "     CD4_Count,\n" +
           "     Is_ART,\n" +
           "     Is_PMTCT,\n" +
           "     Viral_Load,\n" +
           "     Adherence,\n" +
           "     Education_Level,\n" +
           "     Screening_For_STI,\n" +
           "     Classification_Of_Malnutrition,\n" +
           "     Isoniazid_Use,\n" +
           "     Cotrimoxazole_Use\n" +
           "     from ampath_spot_live.pendullum_data_new_new" , nativeQuery = true)
   List<Tuple> findAllRecordsWithPagination(
           @Param("offset") int offset,
           @Param("limit") int limit
   );


}

