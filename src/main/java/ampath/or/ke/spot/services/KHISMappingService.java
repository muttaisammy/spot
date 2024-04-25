package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.KHISMapping;
import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.models.KashaDrugs;
import ampath.or.ke.spot.repositories.KHISMappingRepository;
import ampath.or.ke.spot.repositories.KashaDrugsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("khisMappingService")
public class KHISMappingService {
    Date nowDate = new Date();
    private KHISMappingRepository khisMappingRepository;
    @Autowired
    public KHISMappingService(KHISMappingRepository khisMappingRepository) {
        this.khisMappingRepository = khisMappingRepository;
    }
    public List<KHISMapping> getAllDataset(){return  khisMappingRepository.findAll();}
    public List<KHISMapping> getByDataElement(String dataelement){return  khisMappingRepository.findByDataElement(dataelement);}
    public KHISMapping save(KHISMapping dataset) {
        return khisMappingRepository.save(dataset);
    }

}
