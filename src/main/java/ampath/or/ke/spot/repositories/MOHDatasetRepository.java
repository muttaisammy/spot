package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.KHISMapping;
import ampath.or.ke.spot.models.MOHDataset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MOHDatasetRepository extends JpaRepository<MOHDataset, Long> {
    List<MOHDataset> findAll();

}
