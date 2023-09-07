package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.Ward;
import ampath.or.ke.spot.repositories.CountiesRespository;
import ampath.or.ke.spot.repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("wardService")
public class WardService {
    Date nowDate = new Date();
    private WardRepository wardRepository;
    @Autowired
    public WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }
    public Ward save(Ward dataset) {
        return wardRepository.save(dataset);
    }
    public void delete(Ward dataset){
        wardRepository.delete(dataset);
    }
    public List<Ward> getAllDataset(){return  wardRepository.findAll();}
}
