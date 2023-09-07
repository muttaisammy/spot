package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.AppointmentsTracker;
import ampath.or.ke.spot.repositories.AppointmentsTrackerRepository;
import ampath.or.ke.spot.repositories.SyncTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class AppointmentsService {
    Date nowDate = new Date();
    private AppointmentsTrackerRepository appointmentsTrackerRepository;
    @Autowired
    public AppointmentsService(SyncTrackerRepository syncTrackerRepository) {
        this.appointmentsTrackerRepository = appointmentsTrackerRepository;
    }
    public AppointmentsTracker save(AppointmentsTracker dataset) {
        return appointmentsTrackerRepository.save(dataset);
    }
    public List<AppointmentsTracker> getAll(){return  appointmentsTrackerRepository.findAll();}

}

