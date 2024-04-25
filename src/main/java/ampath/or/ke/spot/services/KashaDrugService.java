package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.models.KashaDrugs;
import ampath.or.ke.spot.repositories.KashaClientsRepository;
import ampath.or.ke.spot.repositories.KashaDrugsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("kashaDrugService")
public class KashaDrugService {
    Date nowDate = new Date();
    private KashaDrugsRepository KashadrugsRepository;
    @Autowired
    public KashaDrugService(KashaDrugsRepository KashadrugsRepository) {
        this.KashadrugsRepository = KashadrugsRepository;
    }
    public KashaDrugs save(KashaDrugs dataset) {
        return KashadrugsRepository.save(dataset);
    }
    public List<KashaDrugs> getAllDataset(){return  KashadrugsRepository.findAll();}
    public List<KashaDrugs> findByEncounterId(String encounterId){return  KashadrugsRepository.findByEncounterId(encounterId);}

    public List<KashaDrugs> findByPatientId(String person_id){return  KashadrugsRepository.findByPersonId(person_id);}


   // public List<KashaDrugs> findByDateConsentedGreaterThanEqual(int eligible,Date created_on){
   //     return  kashaClientsRepository.findByEligibleAndModifiedOnGreaterThanEqual(eligible,created_on);}

}
