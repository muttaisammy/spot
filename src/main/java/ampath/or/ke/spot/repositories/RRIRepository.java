package ampath.or.ke.spot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.models.RRI;

public interface RRIRepository extends JpaRepository<RRI, Long> {
    List<RRI> findAll();
    RRI findByMflcode(String qid);
    List<RRI> findByCategory(String qid);
    RRI findById(int qid);
    
}
