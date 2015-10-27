package eu.accesa.crowdfund.services;

import java.util.ArrayList;
import java.util.List;

import eu.accesa.crowdfund.utils.CategoryConsultantSearch;
import eu.accesa.crowdfund.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.crowdfund.model.User;
import eu.accesa.crowdfund.repository.UserRepository;

/**
 * Created by Dragos on 9/13/2015.
 */

@Service
public class ConsultantService {


    @Autowired
    private UserRepository userRepository;

    public List<User> getAllConsultants() {
        List<User> consultantList = userRepository.retrieveConsultants();
        return consultantList;
    }

    public User getConsultantById(int id) {
        User consultant = userRepository.retrieveConsultantByUid(id);
        return consultant;
    }

    public void createUser(User user) {
        user.setPassword(Utils.Constants.CHANGEME_123);
        userRepository.insertUser(user);
    }

    public void updateConsultant(User consultant) {
        if (consultant.getCv() != null && consultant.getCv().length > 0) {
            userRepository.updateConsultantWithCv(consultant);
        } else {
            userRepository.updateConsultant(consultant);
        }
    }

    public void removeConsultant(String consultantId) {
        userRepository.deleteConsultant(consultantId);
    }

    public List<User> getConsultantsResultSearch(String searchText, CategoryConsultantSearch selectedCategory) {
        return userRepository.getConsultantsResultSearch(searchText, selectedCategory);
    }
}
