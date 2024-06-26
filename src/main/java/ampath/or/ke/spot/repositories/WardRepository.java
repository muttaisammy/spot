package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.SubCounty;
import ampath.or.ke.spot.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wardRepository")
public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findAll();
}
