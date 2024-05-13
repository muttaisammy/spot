package ampath.or.ke.spot.services;


import ampath.or.ke.spot.models.Abstracts;
import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.repositories.AbstractRepository;
import ampath.or.ke.spot.repositories.AfyastatClientLineListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("abstractService")
public class AbstractService {
    Date nowDate = new Date();
    private AbstractRepository abstractRepository;
    @Autowired
    public AbstractService(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }
    public List<Abstracts> getAll() {
        return abstractRepository.findAll();
    }
    public List<Abstracts> findByTrackerNo(String status) {
        return abstractRepository.findByTrackerNo(status);
    }
    public Abstracts save(Abstracts abstracts) {
        return abstractRepository.save(abstracts);
    }
}
