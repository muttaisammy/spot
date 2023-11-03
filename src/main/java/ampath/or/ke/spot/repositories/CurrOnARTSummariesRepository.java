package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.CurrOnARTSummaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("curronartsummariesRepository")
public interface CurrOnARTSummariesRepository extends JpaRepository<CurrOnARTSummaries, Long> {
    List<CurrOnARTSummaries> findAll();
    CurrOnARTSummaries findById(int qid);
}
