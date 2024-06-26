
package ampath.or.ke.spot.services;


import ampath.or.ke.spot.models.NewonARTSummaries;
import ampath.or.ke.spot.repositories.NewonARTSummariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("newonARTSummariesService")
public class NewonARTSummariesService {
    Date nowDate = new Date();
    private NewonARTSummariesRepository newonARTSummariesRepository;
    @Autowired
    public NewonARTSummariesService(NewonARTSummariesRepository newonARTSummariesRepository) {
        this.newonARTSummariesRepository = newonARTSummariesRepository;
    }

    public NewonARTSummaries save(NewonARTSummaries dataset) {
        return newonARTSummariesRepository.save(dataset);
    }

}
