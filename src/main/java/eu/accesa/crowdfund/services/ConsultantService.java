package eu.accesa.crowdfund.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.repository.ConsultantRepository;

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

	public void updateConsultant(Consultant consultant) {
		if(consultant.getCv() != null && consultant.getCv().length > 0){
			consultantRepository.updateConsultantWithCv(consultant);
		}else{
			consultantRepository.updateConsultant(consultant);
		}
	}

    public void removeConsultant(String consultantId) {
        consultantRepository.deleteConsultant(consultantId);
    }
}
