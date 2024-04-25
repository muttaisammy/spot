package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface AfyastatClientLineListRepository extends JpaRepository<AfyastatClientLineList, Integer> {
    List<AfyastatClientLineList> findByLocationId(int locationId);
    List<AfyastatClientLineList> findByFinalTestResult(String finalResult);
    List<AfyastatClientLineList> findByEntryPoint(String entryPoint);
    List<AfyastatClientLineList> findBySdp(String sdp);
    List<AfyastatClientLineList> findByProvider(String provider);
    List<AfyastatClientLineList> findByTestDate(Date testDate);
    List<AfyastatClientLineList> findByDataEntryDate(Date dataEntryDate);
    List<AfyastatClientLineList> findByGender(String gender);
    List<AfyastatClientLineList> findByUuid(String uuid);

    List<AfyastatClientLineList> findFirst1000ByOrderByDataEntryDateDesc();
    AfyastatClientLineList findFirstByOrderByIdDesc();

}