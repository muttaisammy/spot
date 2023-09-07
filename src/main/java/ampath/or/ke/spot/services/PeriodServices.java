package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.Periods;
import ampath.or.ke.spot.repositories.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("periodsService")
public class PeriodServices {
    private PeriodRepository periodRepository;

    Date nowDate = new Date();
    @Autowired
    public PeriodServices(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;

    }
    public Periods getCurrentPeriod(){
        return  periodRepository.findFirst1ByOrderByIdDesc();
    }

}
