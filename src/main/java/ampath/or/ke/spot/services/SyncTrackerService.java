package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.SyncTracker;
import ampath.or.ke.spot.repositories.SyncTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("syncTrackerServices")
public class SyncTrackerService {
    Date nowDate = new Date();
    private SyncTrackerRepository syncTrackerRepository;
    @Autowired
    public SyncTrackerService(SyncTrackerRepository syncTrackerRepository) {
        this.syncTrackerRepository = syncTrackerRepository;
    }
    public SyncTracker save(SyncTracker dataset) {
        return syncTrackerRepository.save(dataset);
    }

}


