package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.repository.ConsultantRepository;
import eu.accesa.crowdfund.repository.FAQRepository;
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
    private ConsultantRepository faqRepository;

    public List<Consultant> getAllConsultants()
    {
        List<Consultant> consultantList = faqRepository.retrieveConsultants();
        return consultantList;
    }

    public Consultant getConsultantByUid(UUID uid)
    {
        Consultant consultant = faqRepository.retrieveConsultantByUid(uid);
        return consultant;
    }
}
