package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.NewonARTLineList;
import ampath.or.ke.spot.repositories.NewonARTLineListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("newonARTLineListService")
public class NewonARTLineListService {
    Date nowDate = new Date();
    private NewonARTLineListRepository newonARTLineListRepository;
    @Autowired
    public NewonARTLineListService(NewonARTLineListRepository newonARTLineListRepository){
        this.newonARTLineListRepository = newonARTLineListRepository;
    }
    public List<NewonARTLineList> findByMonth(Integer month) {
        return newonARTLineListRepository.findByMonth(month);
    }

    public List<NewonARTLineList> findByYear(Integer year) {
        return newonARTLineListRepository.findByYear(year);
    }

    public List<NewonARTLineList> findBySynced(Integer synced) {
        return newonARTLineListRepository.findBySynced(synced);
    }
}
