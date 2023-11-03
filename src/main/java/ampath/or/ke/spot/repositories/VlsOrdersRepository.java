package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.models.VlsOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("vlsOrdersRepository")
public interface VlsOrdersRepository extends JpaRepository<VlsOrders, Long> {
    List<VlsOrders> findAll();
    List<VlsOrders> findByEncounterUuid(String uuid);

}
