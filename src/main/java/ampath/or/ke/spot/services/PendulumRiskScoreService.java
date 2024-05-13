package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.PendullumData;
import ampath.or.ke.spot.models.PendulumRiskScores;
import ampath.or.ke.spot.repositories.PendullumDataRepositories;
import ampath.or.ke.spot.repositories.PendulumRiskScoreRepository;
import org.springframework.stereotype.Service;

@Service("pendulumRiskScoreService")
public class PendulumRiskScoreService {
    private final PendulumRiskScoreRepository pendulumRiskScoreRepository;

    public PendulumRiskScoreService(PendulumRiskScoreRepository pendulumRiskScoreRepository) {
        this.pendulumRiskScoreRepository = pendulumRiskScoreRepository;
    }
    public PendulumRiskScores save(PendulumRiskScores dataset) {
        return pendulumRiskScoreRepository.save(dataset);
    }

}
