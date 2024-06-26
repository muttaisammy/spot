package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.Abstracts;
import ampath.or.ke.spot.models.Conference_reg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConferenceRegRepository extends JpaRepository<Conference_reg, Long> {
    List<Conference_reg> findAll();
}
