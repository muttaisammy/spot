package ampath.or.ke.spot.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ampath.or.ke.spot.models.RRI;
import ampath.or.ke.spot.repositories.RRIRepository;

@Service("rriService")
public class RRIService {
     Date nowDate = new Date();
    private RRIRepository rriRepository;
    @Autowired
    public RRIService(RRIRepository rriRepository) {
        this.rriRepository = rriRepository;
    }
    public RRI save(RRI rri) {
        return rriRepository.save(rri);
    }
    public void delete(RRI dataset){
        rriRepository.delete(dataset);
    }
    public List<RRI> getAllDataset(){return  rriRepository.findAll();}
     public List<RRI> getAllDatasetPerCat(String cat ){return  rriRepository.findByCategory( cat);}
     public RRI getRRIById(int id){return  rriRepository.findById(id);}
    
}
