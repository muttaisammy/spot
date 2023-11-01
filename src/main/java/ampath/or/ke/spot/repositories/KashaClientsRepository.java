package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.HTSSummaries;
import ampath.or.ke.spot.models.KashaClients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("kashaclientsRepository")
public interface KashaClientsRepository extends JpaRepository<KashaClients, Long> {
    List<KashaClients> findAll();
}
