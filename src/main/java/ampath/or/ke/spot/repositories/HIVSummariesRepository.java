package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.models.HIVSummaries;
import ampath.or.ke.spot.models.RRI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hivsummariesRepository")
public interface HIVSummariesRepository extends JpaRepository<HIVSummaries, Long> {
    List<HIVSummaries> findAll();
   // HIVSummaries findByMonthAndYear(String qid);
   // List<HIVSummaries> findByMflcode(String qid);
    HIVSummaries findById(int qid);
}
