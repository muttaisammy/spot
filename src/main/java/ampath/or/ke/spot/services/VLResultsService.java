package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.VLResults;
import ampath.or.ke.spot.models.VlsOrders;
import ampath.or.ke.spot.repositories.VLResultsRepository;
import ampath.or.ke.spot.repositories.VlsOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("vlsResultsService")
public class VLResultsService {
    private VLResultsRepository vlResultsRepository;
    Date nowDate = new Date();
    @Autowired
    public VLResultsService(VLResultsRepository vlResultsRepository) {
        this.vlResultsRepository = vlResultsRepository;

    }
    public VLResults save(VLResults vlsOrders) {
        return vlResultsRepository.save(vlsOrders);
    }
    public List<VLResults> getAllresults(){return vlResultsRepository.findAll();}
    public VLResults getRecenet() {
        return vlResultsRepository.findFirstByOrderByDateCreatedDesc();
    }
    public List<VLResults> getCCCAndDateCllectd(String ccc, Date date) {
        return vlResultsRepository.findByCccAndDateCollected(ccc, date);
    }

}
