package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.CurrOnARTLineList;
import ampath.or.ke.spot.repositories.CurrOnARTLineListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;



@Service("currOnARTLineListService")
public class CurrOnARTLineListService {
    Date nowDate = new Date();
    private CurrOnARTLineListRepository currOnARTLineListRepository;
    @Autowired
    public CurrOnARTLineListService(CurrOnARTLineListRepository currOnARTLineListRepository){
        this.currOnARTLineListRepository = currOnARTLineListRepository;
    }
    public List<CurrOnARTLineList> findByMonth(Integer month) {
        return currOnARTLineListRepository.findByMonth(month);
    }

    public List<CurrOnARTLineList> findByYear(Integer year) {
        return currOnARTLineListRepository.findByYear(year);
    }

    public List<CurrOnARTLineList> findBySynced(Integer synced) {
        return currOnARTLineListRepository.findBySynced(synced);
    }
}
