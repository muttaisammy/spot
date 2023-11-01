package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.repositories.AfyastatClientLineListRepository;
import ampath.or.ke.spot.repositories.AfyastatErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("afyastatClientLineListService")
public class AfyastatClientLineListService {
    Date nowDate = new Date();
    private AfyastatClientLineListRepository afyastatClientLineListRepository;
    @Autowired
    public AfyastatClientLineListService(AfyastatClientLineListRepository afyastatClientLineListRepository) {
        this.afyastatClientLineListRepository = afyastatClientLineListRepository;
    }
    public List<AfyastatClientLineList> getAllTests() {
        return afyastatClientLineListRepository.findAll();
    }
    public List<AfyastatClientLineList> getFirst1000Tests() {
        return afyastatClientLineListRepository.findFirst1000ByOrderByDataEntryDateDesc();
    }

    public AfyastatClientLineList save(AfyastatClientLineList afyastatClientLineList) {
        return afyastatClientLineListRepository.save(afyastatClientLineList);
    }

    public AfyastatClientLineList getHTSById(Integer id) {
        return afyastatClientLineListRepository.getById(id);
    }

    public AfyastatClientLineList updateHTS(AfyastatClientLineList afyastatClientLineList) {
        return afyastatClientLineListRepository.save(afyastatClientLineList);
    }

    public void deleteHTSById(Integer id) {
        afyastatClientLineListRepository.deleteById(id);
    }

    public List<AfyastatClientLineList> findByLocationId(Integer locationId) {
        return afyastatClientLineListRepository.findByLocationId(locationId);
    }

    public List<AfyastatClientLineList> findByFinalTestResult(String finalResult) {
        return afyastatClientLineListRepository.findByFinalTestResult(finalResult);
    }

    public List<AfyastatClientLineList> findByEntryPoint(String entryPoint) {
        return afyastatClientLineListRepository.findByEntryPoint(entryPoint);
    }


    public List<AfyastatClientLineList> findBySDP(String sdp) {
        return null;
    }

    public List<AfyastatClientLineList> findByProvider(String provider) {
        return afyastatClientLineListRepository.findByProvider(provider);
    }

    public List<AfyastatClientLineList> findByTestDate(Date testDate) {
        return afyastatClientLineListRepository.findByTestDate(testDate);
    }

    public List<AfyastatClientLineList> findByDataEntryDate(Date dataEntryDate) {
        return afyastatClientLineListRepository.findByDataEntryDate(dataEntryDate);
    }

    public List<AfyastatClientLineList> findByGender(String gender) {
        return afyastatClientLineListRepository.findByGender(gender);
    }

    public List<AfyastatClientLineList> findByUuid(String uuid) {
        return afyastatClientLineListRepository.findByUuid(uuid);
    }

    public List<AfyastatClientLineList> pullAllHTSFromDatabase() {
        return null;
    }

    public List<AfyastatClientLineList> pullHTSFromDatabaseAsOf(Date endData) {
        return null;
    }
    public List<AfyastatClientLineList> pullHTSFromDatabaseBetween(Date startDate, Date endDate) {
        return null;
    }

    public List<AfyastatClientLineList> pullHTSFromDatabaseAfter(Date specificDate) {
        return null;
    }

    public  AfyastatClientLineList pullLatestSycedRecord(){
        return afyastatClientLineListRepository.findFirstByOrderByIdDesc();
    }
}
