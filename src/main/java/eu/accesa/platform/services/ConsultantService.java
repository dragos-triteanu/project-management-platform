package eu.accesa.platform.services;

import java.util.List;

import eu.accesa.platform.utils.CategoryConsultantSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import eu.accesa.platform.model.entities.Consultant;
import eu.accesa.platform.repository.UserRepository;

/**
 * Created by Dragos on 9/13/2015.
 */

@Service
public class ConsultantService {

    @Autowired
    private UserRepository userRepository;

    @Value("${users.predefined.password}")
    private String definedInitialPassword;

    public List<Consultant> getAllConsultants() {
        List<Consultant> consultantList = userRepository.retrieveConsultants();
        return consultantList;
    }

    public Consultant getConsultantById(int id) {
        Consultant consultant = userRepository.retrieveConsultantByUid(id);
        return consultant;
    }

    public void createUser(Consultant user) {
        user.setPassword(definedInitialPassword);
        if (user.getCv() != null && user.getCv().length > 0) {
            user.setCvURL("/api/service/cv?id=" + user.getUserId());
        }
        userRepository.insertUser(user);
    }

    public void updateConsultant(Consultant consultant) {
        if (consultant.getCv() != null && consultant.getCv().length > 0) {
            userRepository.updateConsultantWithCv(consultant);
        } else {
            userRepository.updateConsultant(consultant);
        }
    }

    public void removeConsultant(String consultantId) {
        userRepository.deleteConsultant(consultantId);
    }

    public List<Consultant> getConsultantsResultSearch(String searchText, CategoryConsultantSearch selectedCategory) {
        return userRepository.getConsultantsResultSearch(searchText, selectedCategory);
    }
}
