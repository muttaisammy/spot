package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.ServiceTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceTrackerRepository extends JpaRepository<ServiceTracker, Long> {
    List<ServiceTracker> findAll();
  //  ServiceTracker findByMflcode(String qid);
  //  List<ServiceTracker> findByCategory(String qid);
    ServiceTracker findById(int qid);

}
