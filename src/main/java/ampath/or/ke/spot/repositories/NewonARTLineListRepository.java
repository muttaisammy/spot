package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.NewonARTLineList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewonARTLineListRepository extends JpaRepository<NewonARTLineList, Integer> {
    List<NewonARTLineList> findByMonth(int month);
    List<NewonARTLineList> findByYear(int year);
    List<NewonARTLineList> findBySynced(int synced);

}