package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.models.SubCounty;
import ampath.or.ke.spot.models.SurgeReport;
import ampath.or.ke.spot.repositories.CountiesRespository;
import ampath.or.ke.spot.repositories.SurgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

@Service("careService")
public class SurgeService {
    Date nowDate = new Date();
    private SurgeRepository surgeRepository;
    @Autowired
    public SurgeService(SurgeRepository surgeRepository) {
        this.surgeRepository = surgeRepository;
    }
    public SurgeReport getTopRecord()
    {return  surgeRepository.findFirstByOrderByIdDesc();}
    public List<SurgeReport> getAllData() {
        return  surgeRepository.findAll();
    }

    public List<SurgeReport> getPersonPerWeek(int pid,int yw) {
        return  surgeRepository.findByPersonIdAndYearWeek(pid,yw);
    }
    public List<?> getSummaries() {
        return  surgeRepository.summaries();
    }

    public List<Tuple> getallSummaries() {
        return  surgeRepository.getsummaries();
    }

    public List<SurgeReport> getNullMFLCODE(int mflcode) {
        return  surgeRepository.findByMflcode(mflcode);
    }


    public SurgeReport save(SurgeReport surgeReport) {
        return surgeRepository.save(surgeReport);
    }

}
