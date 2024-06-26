package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.AppointmentsTracker;
import ampath.or.ke.spot.models.SyncTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentsTrackerRepository extends JpaRepository<AppointmentsTracker, Long> {
    List<AppointmentsTracker> findAll();
    AppointmentsTracker findById(int id);
}
