package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.SMTPServer;
import ampath.or.ke.spot.models.SurgeReport;
import ampath.or.ke.spot.models.VLResults;
import ampath.or.ke.spot.models.VlsOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VLResultsRepository extends JpaRepository<VLResults, Long> {
    List<VLResults> findAll();
   // List<VLResults> findByEncounterUuid(String uuid);
    VLResults findFirstByOrderByDateCreatedDesc();
   List<VLResults> findByCccAndDateCollected(String ccc, Date dc);
}
