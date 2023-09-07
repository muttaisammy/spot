package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.repositories.CountiesRespository;
import ampath.or.ke.spot.repositories.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("countiesService")
public class CountiesService {
    Date nowDate = new Date();
    private CountiesRespository countiesRespository;
    @Autowired
    public CountiesService(CountiesRespository countiesRespository) {
        this.countiesRespository = countiesRespository;
    }
    public County save(County dataset) {
        return countiesRespository.save(dataset);
    }
    public void delete(County dataset){
        countiesRespository.delete(dataset);
    }
    public List<County> getAllDataset(){return  countiesRespository.findAll();}

}
