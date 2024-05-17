package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.models.KashaDeliveries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("kashaDeliveriesRepository")
public interface KashaDeliveriesRepository extends JpaRepository<KashaDeliveries, Long> {
    List<KashaDeliveries> findAll();
    List<KashaDeliveries> findByOrderNumber(String ccno);
    List<KashaDeliveries> findByInAMRS(int inamrs);
   /// List<KashaDeliveries> findByEligible(int eligible);
   List<KashaDeliveries> findByInAMRSAndDeliverySuccessful(int inamrs,String status);


}
