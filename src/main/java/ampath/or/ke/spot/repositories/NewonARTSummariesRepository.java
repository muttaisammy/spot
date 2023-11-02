package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.NewonARTSummaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newonartsummariesRepository")
public interface NewonARTSummariesRepository extends JpaRepository<NewonARTSummaries, Long> {
    List<NewonARTSummaries> findAll();
    NewonARTSummaries findById(int qid);
}
