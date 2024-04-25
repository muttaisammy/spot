package ampath.or.ke.spot.services;


import ampath.or.ke.spot.repositories.PendullumReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

@Service("pendullumService")
public class PendullumService {
    Date nowDate = new Date();
    private PendullumReporitory pendullumReporitory;
    @Autowired
    public PendullumService(PendullumReporitory pendullumReporitory) {
        this.pendullumReporitory = pendullumReporitory;
    }
    public List<Tuple> getAllDataset(){return  pendullumReporitory.getsummaries();}

    public List<Tuple> getRecordsWithPagination(int page, int size) {
        int offset = page * size; // Calculate offset
        return pendullumReporitory.findAllRecordsWithPagination(offset, size);
    }
}

