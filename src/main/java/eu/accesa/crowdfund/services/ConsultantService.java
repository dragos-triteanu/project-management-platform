package eu.accesa.crowdfund.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.crowdfund.model.User;
import eu.accesa.crowdfund.repository.ConsultantRepository;

/**
 * Created by Dragos on 9/13/2015.
 */

@Service
public class ConsultantService {

    private static final String CHANGEME_123 = "changeme123";
	@Autowired
    private ConsultantRepository consultantRepository;

    public List<User> getAllConsultants()
    {
        List<User> consultantList = consultantRepository.retrieveConsultants();
        return consultantList;
    }

    public User getConsultantById(int id){
        User consultant = consultantRepository.retrieveConsultantByUid(id);
        return consultant;
    }

    public void createConsultant(User consultant){
    	consultant.setPassword(CHANGEME_123);
        consultantRepository.insertConsultant(consultant);
    }

	public void updateConsultant(User consultant) {
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
