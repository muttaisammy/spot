package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.SubCounty;
import ampath.or.ke.spot.repositories.CountiesRespository;
import ampath.or.ke.spot.repositories.SubCountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("subcountiesService")
public class SubCountiesService {
    Date nowDate = new Date();
    private SubCountyRepository subCountyRepository;
    @Autowired
    public SubCountiesService(SubCountyRepository subCountyRepository) {
        this.subCountyRepository = subCountyRepository;
    }
    public SubCounty save(SubCounty dataset) {
        return subCountyRepository.save(dataset);
    }
    public void delete(SubCounty dataset){
        subCountyRepository.delete(dataset);
    }
    public List<SubCounty> getAllDataset(){return  subCountyRepository.findAll();}
}
