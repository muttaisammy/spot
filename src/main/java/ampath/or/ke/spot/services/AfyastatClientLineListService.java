package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.HTS;
import ampath.or.ke.spot.repositories.HTSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class  HTSService {
    @Autowired
    private HTSRepository htsRepository;


    public List<HTS> getAllTests() {
        return htsRepository.findAll();
    }

    public HTS saveStudent(HTS hts) {
        return htsRepository.save(hts);
    }

    public HTS getHTSById(Integer id) {
        return htsRepository.getById(id);
    }

    public HTS updateHTS(HTS hts) {
        return htsRepository.save(hts);
    }

    public void deleteHTSById(Integer id) {
        htsRepository.deleteById(id);
    }

    public List<HTS> findByLocationId(Integer locationId) {
        return htsRepository.findByLocationId(locationId);
    }

    public List<HTS> findByFinalTestResult(String finalResult) {
        return htsRepository.findByFinalTestResult(finalResult);
    }

    public List<HTS> findByEntryPoint(String entryPoint) {
        return htsRepository.findByEntryPoint(entryPoint);
    }


    public List<HTS> findBySDP(String sdp) {
        return null;
    }

    public List<HTS> findByProvider(String provider) {
        return htsRepository.findByProvider(provider);
    }

    public List<HTS> findByTestDate(Date testDate) {
        return htsRepository.findByTestDate(testDate);
    }

    public List<HTS> findByDataEntryDate(Date dataEntryDate) {
        return htsRepository.findByDataEntryDate(dataEntryDate);
    }

    public List<HTS> findByGender(String gender) {
        return htsRepository.findByGender(gender);
    }

    public List<HTS> findByUuid(String uuid) {
        return htsRepository.findByUuid(uuid);
    }

    public List<HTS> pullAllHTSFromDatabase() {
        return null;
    }


    public List<HTS> pullHTSFromDatabaseAsOf(Date endData) {
        return null;
    }

    public List<HTS> pullHTSFromDatabaseBetween(Date startDate, Date endDate) {
        return null;
    }


    public List<HTS> pullHTSFromDatabaseAfter(Date specificDate) {
        return null;
    }
}
