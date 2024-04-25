package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.models.KashaDrugs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KashaDrugsRepository extends JpaRepository<KashaDrugs, Long>{
    List<KashaDrugs> findAll();
    List<KashaDrugs> findByEncounterId(String encounter_id);
    List<KashaDrugs> findByPersonId(String patient_Id);

    }

