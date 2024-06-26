package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.models.Periods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("periodRepository")
public interface PeriodRepository extends JpaRepository<Periods, Long> {
    List<Periods> findByDeleteflag(int flag);
    List<Periods> findAll();
    Periods findById(int qid);
    //Periods findFirst1OrderByIdDesc();
    Periods findFirst1ByOrderByIdDesc();
    Periods findFirstByOrderByIdDesc();



}
