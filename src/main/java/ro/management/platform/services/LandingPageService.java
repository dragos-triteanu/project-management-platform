package ro.management.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.management.platform.model.entities.User;
import ro.management.platform.repository.LandingPageRepository;
import ro.management.platform.security.Authority;
import ro.management.platform.utils.SessionUtils;

/**
 * Created by dragos.triteanu on 11/8/15.
 */
@Service
public class LandingPageService {

    @Autowired
    private LandingPageRepository landingPageRepository;

    public void updateWywywigForUser(final String wysiwygText , final int whatUsersSee){
        landingPageRepository.updateWysiwygHtmlForConsultants(wysiwygText, whatUsersSee);
    }

    public String getHTMLForWysiwyg() {
        User user = SessionUtils.GetCurrentUser();
        String htmlString = "";
        if(user.getRole().equals(Authority.CONSULTANT)){
            htmlString = landingPageRepository.getHTMLForWysiwyg(1);
        }else{
            htmlString = landingPageRepository.getHTMLForWysiwyg(2);
        }
        return htmlString;
    }

    public String getHtmlForUserByType(int userType) {
        return landingPageRepository.getHTMLForWysiwyg(userType);
    }
}
