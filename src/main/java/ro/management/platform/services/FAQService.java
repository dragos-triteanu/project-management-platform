package ro.management.platform.services;

import java.util.List;

import ro.management.platform.model.entities.QuestionAndAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.management.platform.repository.FAQRepository;

/**
 * Service class meant for handling operations with the {@link ro.management.platform.repository.FAQRepository}.
 * Created by Dragos on 9/16/2015.
 */
@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    /**
     * Adds a new {@link ro.management.platform.model.entities.QuestionAndAnswer} into the {@link ro.management.platform.repository.FAQRepository}.
     * @param qAnda
     */
    public void addNewFAQ(final QuestionAndAnswer qAnda){
        faqRepository.insertQuestionAndAnswer(qAnda);
    }

    /**
     * Retrieves a list of all existent {@link ro.management.platform.model.entities.QuestionAndAnswer} in the {@link ro.management.platform.repository.FAQRepository}.
     * @return
     */
    public List<QuestionAndAnswer> retrieveAllFAQs(){
        return faqRepository.retrieveQuestionsAndAnswers();
    }

    /**
     * Deletes a {@link ro.management.platform.model.entities.QuestionAndAnswer} based on it's id.
     * @param faqId the id of the {@link ro.management.platform.model.entities.QuestionAndAnswer} that will be deleted.
     */
    public void deleteeFAQById(final int faqId){
        faqRepository.deleteQuestionAndAnswerById(faqId);
    }
}
