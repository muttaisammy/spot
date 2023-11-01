package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.models.AfyastatErrors;
import ampath.or.ke.spot.repositories.AfyastatClientLineListRepository;
import ampath.or.ke.spot.repositories.AfyastatErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("afyastatLineListService")
public class AfyastatLineListService {
    Date nowDate = new Date();
    private AfyastatClientLineListRepository afyastatClientLineListRepository;


    @Autowired
    public AfyastatLineListService(AfyastatClientLineListRepository afyastatClientLineListRepository) {
        this.afyastatClientLineListRepository = afyastatClientLineListRepository;
    }

    public AfyastatClientLineList save(AfyastatClientLineList dataset) {
        return afyastatClientLineListRepository.save(dataset);
    }

    public void deleteBank(AfyastatClientLineList dataset) {
        afyastatClientLineListRepository.delete(dataset);
    }




}
