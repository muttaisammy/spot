package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.KHISMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KHISMappingRepository extends JpaRepository<KHISMapping, Long> {
    List<KHISMapping> findAll();
    List<KHISMapping> findByDataElement(String dataelement);
    //List<KHISMapping> findByPersonId(String patient_Id);

}

