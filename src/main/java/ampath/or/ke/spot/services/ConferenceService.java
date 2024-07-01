package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.Abstracts;
import ampath.or.ke.spot.models.CCCno;
import ampath.or.ke.spot.models.ConferenceRegister;
import ampath.or.ke.spot.repositories.CCCRepository;
import ampath.or.ke.spot.repositories.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("conferenceService")
public class ConferenceService {
    Date nowDate = new Date();
    private ConferenceRepository conferenceRepository;
    @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }
    public List<ConferenceRegister> getAll(){return  conferenceRepository.findAll();}

    public ConferenceRegister save(ConferenceRegister conferenceRegister) {
        return conferenceRepository.save(conferenceRegister);
    }
}
