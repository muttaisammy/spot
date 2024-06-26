package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.CurrOnARTLineList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrOnARTLineListRepository extends JpaRepository<CurrOnARTLineList, Integer> {
    List<CurrOnARTLineList> findByMonth(int month);
    List<CurrOnARTLineList> findByYear(int year);
    List<CurrOnARTLineList> findBySynced(int synced);

}