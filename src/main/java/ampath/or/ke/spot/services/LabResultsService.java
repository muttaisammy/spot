package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.LabResults;
import ampath.or.ke.spot.repositories.LabResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("labResultsService")
public class LabResultsService {
    Date nowDate = new Date();
    private LabResultsRepository labResultsRepository;
    @Autowired
    public LabResultsService(LabResultsRepository labResultsRepository) {
        this.labResultsRepository = labResultsRepository;
    }
    public LabResults save(LabResults dataset) {
        return labResultsRepository.save(dataset);
    }
    public void delete(LabResults dataset){
        labResultsRepository.delete(dataset);
    }
    public List<LabResults> getAllDataset(){return  labResultsRepository.findAll();}

}
