package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.PendullumData;
import ampath.or.ke.spot.repositories.PendullumDataRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pendullumDataService")
public class PendulumDataService {

    private final PendullumDataRepositories pendullumDataRepositories;

    public PendulumDataService(PendullumDataRepositories pendullumDataRepositories) {
        this.pendullumDataRepositories = pendullumDataRepositories;
    }

    public PendullumData save(PendullumData dataset) {
        return pendullumDataRepositories.save(dataset);
    }
    public List<PendullumData> FindbyIdandEdate(String pid, String date)  {
        return pendullumDataRepositories.findByPatientIdentifierAndEncounterDate(pid,date);
    }




}
