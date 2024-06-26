package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.repositories.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

@Service("facilitiesService")
public class FacilitiesService {
    Date nowDate = new Date();
    private FacilitiesRepository facilitiesRepository;
    @Autowired
    public FacilitiesService(FacilitiesRepository facilitiesRepository) {
        this.facilitiesRepository = facilitiesRepository;
    }
    public Facilities save(Facilities dataset) {
        return facilitiesRepository.save(dataset);
    }
    public void deleteBank(Facilities dataset){
        facilitiesRepository.delete(dataset);
    }
    public List<Facilities> getAllDataset(){return  facilitiesRepository.findAll();}

    public Facilities getByMFLCODE(String mflcode){
        return  facilitiesRepository.findByMflcode(mflcode);
    }
    public Facilities getByName(String id){
        return  facilitiesRepository.findByFacilityname(id);
    }
    public Facilities getByNameLike(String id){
        return  facilitiesRepository.findByFacilitynameContaining(id);
    }

    public Facilities getByFacilityNameLike(String id){
        return  facilitiesRepository.findByFacilitynameLike(id);
    }
    public Facilities getByFacilityNameEndwith(String id){
        return  facilitiesRepository.findByFacilitynameEndsWith(id);
    }
   
    public List<Object> FacilitiesByFacilityname(){
        return  facilitiesRepository.DistinctFacilityname();
    }
    public List<Object> FacilitiesByCounty(){
        return  facilitiesRepository.DistinctCounty();
    }
    public List<Object> FacilitiesBySubcounty(){
        return  facilitiesRepository.DistinctSubcounty();
    }

    public List<Object> FacilitiesByWard(){
        return  facilitiesRepository.DistinctWard();
    }

    public List<Object> CountEMRS(String emr){
        return  facilitiesRepository.CountEMRS(emr);
    }
    public List<Object> EMRDistribution(){
        return  facilitiesRepository.EMRDistribution();
    }
    public List<Object> ARTDistribution(){
        return  facilitiesRepository.ARTDistribution();
    }
    public Integer TotalARTDistribution(){
        return  facilitiesRepository.TotalARTDistribution();
    }
    public List<Facilities> searchByFnameLike(String id){
        return  facilitiesRepository.searchByFnameLike(id);
    }
    public List<Facilities> KenyaEMRFacilities(String id){
        return  facilitiesRepository.findByEmr(id);
    }
    public Facilities searchByAMRSID(List<String> id){
        return  facilitiesRepository.findByAmrsidIn(id);
    }
    public List<Tuple> CountiesPerPartner(List<String> id){
        return  facilitiesRepository.findDistinctCountyByPartner(id);
    }

}
