package ampath.or.ke.spot.repositories;


import ampath.or.ke.spot.models.Months;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthsRepository extends JpaRepository<Months, Long> {
    List<Months> findAll();

}