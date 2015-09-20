package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dragos on 9/13/2015.
 */

@Service
public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

    public List<Consultant> getAllConsultants()
    {
        List<Consultant> consultantList = consultantRepository.retrieveConsultants();
        return consultantList;
    }

    public Consultant getConsultantById(int id){
        Consultant consultant = consultantRepository.retrieveConsultantByUid(id);
        return consultant;
    }

    public void createConsultant(Consultant consultant){
        consultantRepository.insertConsultant(consultant);
    }
}
