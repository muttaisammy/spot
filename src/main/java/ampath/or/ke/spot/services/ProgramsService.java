package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.repositories.ProgramRepository;
import ampath.or.ke.spot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("programsService")
public class ProgramsService {
    private ProgramRepository programRepository;
    private UserRepository userRepository;
    Date nowDate = new Date();
    @Autowired
    public ProgramsService(ProgramRepository programRepository,
                          UserRepository userRepository) {
        this.userRepository = userRepository;
        this.programRepository = programRepository;
    }
    public Programs getProgramByName(String cname){
        return  programRepository.findByProgramname(cname);
    }
    public Programs getPrograms(int cid){
        return  programRepository.findById(cid);
    }
   /* public Programs getProgramsByCheck(String check){
        return  programRepository.findByCheck(check);
    } */

    public List<Programs> getAllPrograms(){return  programRepository.findAll();}
    public Programs save(Programs company) {
        return programRepository.save(company);
    }
    public long count() {
        return programRepository.count();
    }
}
