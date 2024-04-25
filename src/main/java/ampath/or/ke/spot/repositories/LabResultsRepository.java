package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.LabResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("labResultsRepository")
public interface LabResultsRepository extends JpaRepository<LabResults, Long> {
    List<LabResults> findAll();
    List<LabResults> findByCccAndCollectionDate(String ccc, Date collectiondate);
}
