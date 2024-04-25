package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.MOHDataset;
import ampath.or.ke.spot.models.MOHReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MOHReportsRepository extends JpaRepository<MOHReports, Long> {
    List<MOHReports> findAll();

}
