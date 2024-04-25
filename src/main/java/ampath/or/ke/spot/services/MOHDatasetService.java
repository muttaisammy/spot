package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.KHISMapping;
import ampath.or.ke.spot.models.MOHDataset;
import ampath.or.ke.spot.repositories.KHISMappingRepository;
import ampath.or.ke.spot.repositories.MOHDatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("mohDatasetService")
public class MOHDatasetService {
    Date nowDate = new Date();
    private MOHDatasetRepository mohDatasetRepository;
    @Autowired
    public MOHDatasetService(MOHDatasetRepository mohDatasetRepository) {
        this.mohDatasetRepository = mohDatasetRepository;
    }
    public List<MOHDataset> getAllDataset(){return  mohDatasetRepository.findAll();}
}
