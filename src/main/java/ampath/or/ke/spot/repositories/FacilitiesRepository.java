package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository("facilitiesRepository")
public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {
    List<Facilities> findAll();
    Facilities findByMflcode(String qid);
    Facilities findByFacilityname(String qid);
    Facilities findByFacilitynameContaining(String qid);
    Facilities findByFacilitynameLike(String qid);
    Facilities findByFacilitynameEndsWith(String director);
    List<Facilities> findByEmr(String emr);

    Facilities findByAmrsidIn(List<String> qid);

    @Query("SELECT distinct(facilityname) FROM Facilities")
    List<Object> DistinctFacilityname();

    @Query("SELECT distinct(county) FROM Facilities")
    List<Object> DistinctCounty();

    @Query("SELECT distinct(subcounty) FROM Facilities")
    List<Object> DistinctSubcounty();

    @Query("SELECT distinct(ward) FROM Facilities")
    List<Object> DistinctWard();
    @Query("SELECT emr,count(emr) \n" +
            "FROM Facilities \n" +
            "group by emr")
    List<Object> EMRDistribution();

    @Query("SELECT county,sum(txcurr) \n" +
            "FROM Facilities  \n" +
            "group by county order by sum(txcurr) desc")
    List<Object> ARTDistribution();

    @Query("SELECT sum(txcurr) \n" +
            "FROM Facilities")
    Integer  TotalARTDistribution();

    @Query("SELECT m FROM Facilities m WHERE m.emr LIKE %:emr%")
    List<Object> CountEMRS(String emr);

    @Query("SELECT m FROM Facilities m WHERE m.facilityname LIKE %:title%")
    List<Facilities> searchByFnameLike(@Param("title") String title);
   
    @Query("SELECT m FROM Facilities m WHERE m.owner LIKE %:title%")
    List<Facilities> searchByFtypeLike(@Param("title") String title);
    //Facilities findByorgUnitName(String fname);

   // @Query("SELECT distinct(county)  FROM Facilities  WHERE Partner = ?1 ")
  //  List<?> findDistinctCountyByPartner(@Param("partner") String partner);
    @Query(value = "SELECT distinct(county) county FROM facilities WHERE Partner in ( ?1 ) order by county asc ", nativeQuery = true)
    List<Tuple> findDistinctCountyByPartner( List<String> qid);
}
