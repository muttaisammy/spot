package ampath.or.ke.spot.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ampath.or.ke.spot.models.DatabasesInfo;
import ampath.or.ke.spot.repositories.DatabasesInfoRepository;

@Service("databasesinfoService")
public class DatabasesInfoServices {
    Date nowDate = new Date();
    private DatabasesInfoRepository databasesinfoRepository;

    @Autowired
    public DatabasesInfoServices(DatabasesInfoRepository databasesinfoRepository) {
        this.databasesinfoRepository = databasesinfoRepository;
    }

    public DatabasesInfo save(DatabasesInfo databasesinfo) {
        return databasesinfoRepository.save(databasesinfo);
    }

    public void delete(DatabasesInfo dataset) {
        databasesinfoRepository.delete(dataset);
    }

    public List<DatabasesInfo> getAllDataset() {
        return databasesinfoRepository.findAll();
    }

    public List<DatabasesInfo> getByFacility(String facility_name) {
        return databasesinfoRepository.findByFacility_name(facility_name);
    }

    public DatabasesInfo getRRIById(int id) {
        return databasesinfoRepository.findById(id);
    }
}
