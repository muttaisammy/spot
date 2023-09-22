package ampath.or.ke.spot.services;


import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.models.HIVSummaries;
import ampath.or.ke.spot.repositories.FacilitiesRepository;
import ampath.or.ke.spot.repositories.HIVSummariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("hivsummariesService")
public class HIVSummariesService {
    Date nowDate = new Date();
    private HIVSummariesRepository hivSummariesRepository;
    @Autowired
    public HIVSummariesService(HIVSummariesRepository hivSummariesRepository) {
        this.hivSummariesRepository = hivSummariesRepository;
    }

    public HIVSummaries save(HIVSummaries dataset) {
        return hivSummariesRepository.save(dataset);
    }

}
