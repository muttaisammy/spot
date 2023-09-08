package ampath.or.ke.spot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ampath.or.ke.spot.models.DatabasesInfo;

public interface DatabasesInfoRepository extends JpaRepository<DatabasesInfo, Long> {
    List<DatabasesInfo> findAll();

    DatabasesInfo findByMflcode(String qid);

    List<DatabasesInfo> findByFacilityname(String qid);

    DatabasesInfo findById(int qid);

}
