package ampath.or.ke.spot.services;


import ampath.or.ke.spot.models.CurrOnARTSummaries;
import ampath.or.ke.spot.repositories.CurrOnARTSummariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("currOnARTSummariesService")
public class CurrOnARTSummariesService {
    Date nowDate = new Date();
    private CurrOnARTSummariesRepository currOnARTSummariesRepository;
    @Autowired
    public CurrOnARTSummariesService(CurrOnARTSummariesRepository currOnARTSummariesRepository) {
        this.currOnARTSummariesRepository = currOnARTSummariesRepository;
    }

    public CurrOnARTSummaries save(CurrOnARTSummaries dataset) {
        return currOnARTSummariesRepository.save(dataset);
    }

}
