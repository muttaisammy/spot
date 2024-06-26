package ampath.or.ke.spot.repositories;

import ampath.or.ke.spot.models.AfyastatErrors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AfyastatErrorsRepository extends JpaRepository<AfyastatErrors, Long> {
    List<AfyastatErrors> findAll();
    AfyastatErrors findByEid(int id);
    AfyastatErrors findById(int id);
    List<AfyastatErrors> findTop25ByDiscriminator(String discriminator );
    List<AfyastatErrors> findByDiscriminator(String discriminator );
    List<AfyastatErrors> findByDiscriminatorAndResponsecodeIsNull(String discriminator );
    List<AfyastatErrors> findByDiscriminatorAndResponsecode(String discriminator,String code );

    List<AfyastatErrors> findByResponsecodeIsNull( );
    List<AfyastatErrors> findByDiscriminatorAndLocationAndResponsecodeIsNull(String discriminator,String location );
    //List<AfyastatErrors> findByMidAndOrgunitAndDatasetAndPeriod(int mid,String org,String dataset,String period);
    //List<Alldata> findByStatus(String status);
}

