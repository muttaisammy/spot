package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countiesRepository")
public interface CountiesRespository extends JpaRepository<County, Long> {
    List<County> findAll();
}
