package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.SMTPServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SMTPServerRepository extends JpaRepository<SMTPServer, Long> {
        SMTPServer findBySectionid(String sid);
        List<SMTPServer> findAll();
        SMTPServer findByHost(String host);
        SMTPServer findFirstByOrderByIdAsc();
        }
