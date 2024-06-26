package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.Conference_reg;
import ampath.or.ke.spot.repositories.ConferenceRegRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConferenceService {
    @Autowired
    private ConferenceRegRepository conferenceRegRepository;


    public List<Conference_reg> getAllConferenceUsers() {
        return conferenceRegRepository.findAll();
    }
    public Conference_reg save(ConferenceRegRepository conferenceRegRepository) {
        return Conference_reg.save(conferenceRegRepository);
    }
}
