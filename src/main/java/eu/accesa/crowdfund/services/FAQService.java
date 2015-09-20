package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.QuestionAndAnswer;
import eu.accesa.crowdfund.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class meant for handling operations with the {@link eu.accesa.crowdfund.repository.FAQRepository}.
 * Created by Dragos on 9/16/2015.
 */
@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    /**
     * Adds a new {@link eu.accesa.crowdfund.model.QuestionAndAnswer} into the {@link eu.accesa.crowdfund.repository.FAQRepository}.
     * @param qAnda
     */
    public void addNewFAQ(final QuestionAndAnswer qAnda){
        faqRepository.insertQuestionAndAnswer(qAnda);
    }

    /**
     * Retrieves a list of all existent {@link eu.accesa.crowdfund.model.QuestionAndAnswer} in the {@link eu.accesa.crowdfund.repository.FAQRepository}.
     * @return
     */
    public List<QuestionAndAnswer> retrieveAllFAQs(){
        return faqRepository.retrieveQuestionsAndAnswers();
    }

    /**
     * Deletes a {@link eu.accesa.crowdfund.model.QuestionAndAnswer} based on it's id.
     * @param faqId the id of the {@link eu.accesa.crowdfund.model.QuestionAndAnswer} that will be deleted.
     */
    public void deleteeFAQById(final int faqId){
        faqRepository.deleteQuestionAndAnswerById(faqId);
    }
}
