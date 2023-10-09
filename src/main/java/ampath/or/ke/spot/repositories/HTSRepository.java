package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.HTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface HTSRepository extends JpaRepository<HTS, Integer> {
    List<HTS> findByLocationId(int locationId);
    List<HTS> findByFinalTestResult(String finalResult);
    List<HTS> findByEntryPoint(String entryPoint);
    List<HTS> findBySdp(String sdp);
    List<HTS> findByProvider(String provider);
    List<HTS> findByTestDate(Date testDate);
    List<HTS> findByDataEntryDate(Date dataEntryDate);
    List<HTS> findByGender(String gender);
    List<HTS> findByUuid(String uuid);
}