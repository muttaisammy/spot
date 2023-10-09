package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.HTS;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IHTSService {
    List<HTS> getAllTests();

    HTS saveStudent(HTS student);

    HTS getHTSById(Integer id);

    HTS updateHTS(HTS student);

    void deleteHTSById(Integer id);

    List<HTS> findByLocationId(Integer locationId);

    List<HTS> findByFinalTestResult(String finalResult);
    List<HTS> findByEntryPoint(String entryPoint);
    List<HTS> findBySDP(String sdp);
    List<HTS> findByProvider(String provider);
    List<HTS> findByTestDate(Date testDate);
    List<HTS> findByDataEntryDate(Date dataEntryDate);
    List<HTS> findByGender(String gender);
    List<HTS> findByUuid(String uuid);
    List<HTS> pullAllHTSFromDatabase();

    List<HTS> pullHTSFromDatabaseAsOf(Date endData);

    List<HTS> pullHTSFromDatabaseBetween(Date startDate, Date endDate);
    List<HTS> pullHTSFromDatabaseAfter(Date specificDate);
}
