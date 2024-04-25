package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.CCCno;
import ampath.or.ke.spot.models.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CCCRepository")
public interface CCCRepository extends JpaRepository<CCCno, Long> {
   CCCno findBypersonID(int pid);
    List<CCCno> findAll();
    List<CCCno> findByStatus(int status);
}
