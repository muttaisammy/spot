package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.SyncTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SyncTrackerRepository extends JpaRepository<SyncTracker, Long> {
        List<SyncTracker> findAll();
        SyncTracker findById(int id);
}
