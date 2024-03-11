package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.repositories.AfyastatErrorsRepository;
import ampath.or.ke.spot.repositories.KashaClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("kashaService")
public class KashaClientsServices {
    Date nowDate = new Date();
    private KashaClientsRepository kashaClientsRepository;
    @Autowired
    public KashaClientsServices(KashaClientsRepository kashaClientsRepository) {
        this.kashaClientsRepository = kashaClientsRepository;
    }
    public KashaClients save(KashaClients dataset) {
        return kashaClientsRepository.save(dataset);
    }
    public List<KashaClients> getAllDataset(){return  kashaClientsRepository.findAll();}

    public List<KashaClients> findByDateConsentedGreaterThanEqual(Date created_on){
        return  kashaClientsRepository.findByModifiedOnGreaterThanEqual(created_on);}


    public List<KashaClients> getByIdentifier(String ccc){return  kashaClientsRepository.findByIdentifier(ccc);}

    public List<KashaClients> getDatasetByDate(){return  kashaClientsRepository.findAll();}
}
