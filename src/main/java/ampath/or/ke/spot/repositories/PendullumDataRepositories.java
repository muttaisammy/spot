package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.KashaDrugs;
import ampath.or.ke.spot.models.PendullumData;
import ampath.or.ke.spot.models.Pendullums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pendullumDataReporitory")
public interface PendullumDataRepositories extends JpaRepository<PendullumData, Long> {
    Page<PendullumData> findAll(Pageable pageable);
    List<PendullumData> findByPatientIdentifierAndEncounterDate(String pid,String Date);
}
