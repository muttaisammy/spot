package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.MOHReports;
import ampath.or.ke.spot.repositories.MOHReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("mohReportsService")
public class MOHReportsService {
    Date nowDate = new Date();
    private MOHReportsRepository mohReportsRepository;
    @Autowired
    public MOHReportsService(MOHReportsRepository mohReportsRepository) {
        this.mohReportsRepository = mohReportsRepository;
    }
    public List<MOHReports> getAllDataset(){
        return  mohReportsRepository.findAll();}
}

