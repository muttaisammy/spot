package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.Programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("programRepository")
public interface ProgramRepository extends JpaRepository<Programs, Long> {
    Programs findByProgramname(String cname);
    List<Programs> findAll();
    Programs findById(int qid);
   // Programs findByCheck(String check);
    // List<Company> countAll();

}