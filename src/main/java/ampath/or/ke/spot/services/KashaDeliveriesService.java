package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.models.KashaDeliveries;
import ampath.or.ke.spot.repositories.KashaClientsRepository;
import ampath.or.ke.spot.repositories.KashaDeliveriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("kashaDeliveriesService")
public class KashaDeliveriesService {
    Date nowDate = new Date();
    private KashaDeliveriesRepository kashaDeliveriesRepository;
    @Autowired
    public KashaDeliveriesService(KashaDeliveriesRepository kashaDeliveriesRepository) {
        this.kashaDeliveriesRepository = kashaDeliveriesRepository;
    }
    public KashaDeliveries save(KashaDeliveries dataset) {
        return kashaDeliveriesRepository.save(dataset);
    }
    public List<KashaDeliveries> getAllDataset(){return  kashaDeliveriesRepository.findAll();}

    public List<KashaDeliveries> getOrderNumber(String order) {
        return kashaDeliveriesRepository.findByOrderNumber(order);
    }
    public List<KashaDeliveries> getInAMRS(int amrs) {
        return kashaDeliveriesRepository.findByInAMRS(amrs);
    }


}
