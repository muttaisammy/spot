package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.HTSSummaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("htssummariesRepository")
public interface HTSSummariesRepository extends JpaRepository<HTSSummaries, Long> {
    List<HTSSummaries> findAll();
    HTSSummaries findById(int qid);
}
