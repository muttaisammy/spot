package ampath.or.ke.spot.repositories;


import ampath.or.ke.spot.models.Years;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YearsRepository extends JpaRepository<Years, Long> {
     List<Years> findAll();

}