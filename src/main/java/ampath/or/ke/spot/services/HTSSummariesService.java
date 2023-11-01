package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.HIVSummaries;
import ampath.or.ke.spot.models.HTSSummaries;
import ampath.or.ke.spot.repositories.HIVSummariesRepository;
import ampath.or.ke.spot.repositories.HTSSummariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("htssummariesService")
public class HTSSummariesService {
    Date nowDate = new Date();
    private HTSSummariesRepository htsSummariesRepository;
    @Autowired
    public HTSSummariesService(HTSSummariesRepository htsSummariesRepository) {
        this.htsSummariesRepository = htsSummariesRepository;
    }

    public HTSSummaries save(HTSSummaries dataset) {

        return htsSummariesRepository.save(dataset);
    }

}

