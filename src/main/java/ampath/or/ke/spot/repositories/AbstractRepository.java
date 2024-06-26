package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.Abstracts;
import ampath.or.ke.spot.models.CCCno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AbstractRepository")
public interface AbstractRepository extends JpaRepository<Abstracts, Long> {
    Abstracts findById(int pid);
    List<Abstracts> findAll();
    List<Abstracts> findByTrackerNo(String status);
}
