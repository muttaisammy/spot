package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.SubCounty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subcountyRepository")
public interface SubCountyRepository extends JpaRepository<SubCounty, Long> {
    List<SubCounty> findAll();
}
