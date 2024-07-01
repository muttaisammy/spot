package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.ConferenceRegister;
import ampath.or.ke.spot.models.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("conferenceRepository")
public interface ConferenceRepository extends JpaRepository<ConferenceRegister, Long> {
    List<ConferenceRegister> findAll();
}
