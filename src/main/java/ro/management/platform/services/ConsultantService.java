package ro.management.platform.services;

import java.util.List;

import ro.management.platform.utils.CategoryConsultantSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ro.management.platform.model.entities.Consultant;
import ro.management.platform.repository.UserRepository;

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

        if (consultant.getCv() != null && consultant.getCv().length > 0) {
            consultant.setCvURL("/api/service/cv?id=" + consultant.getUserId());
        }

        return consultant;
    }

    public void createUser(Consultant user) {
        user.setPassword(definedInitialPassword);
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
