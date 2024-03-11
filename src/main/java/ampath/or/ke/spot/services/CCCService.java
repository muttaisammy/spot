package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.AppointmentsTracker;
import ampath.or.ke.spot.models.CCCno;
import ampath.or.ke.spot.repositories.AppointmentsTrackerRepository;
import ampath.or.ke.spot.repositories.CCCRepository;
import ampath.or.ke.spot.repositories.SyncTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cccService")
public class CCCService {
    Date nowDate = new Date();
    private CCCRepository cccRepository;
    @Autowired
    public CCCService(CCCRepository cccRepository) {
        this.cccRepository = cccRepository;
    }
    public List<CCCno> getAll(){return  cccRepository.findAll();}
    public List<CCCno> getByStatus(int status){return  cccRepository.findByStatus(status);}
    public CCCno getByPersonId(int pid){return  cccRepository.findBypersonID(pid);}
    public CCCno save(CCCno dataset) {
        return cccRepository.save(dataset);
    }


}
