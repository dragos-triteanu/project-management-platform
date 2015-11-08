package eu.accesa.platform.services;

import java.util.List;

import eu.accesa.platform.model.entities.QuestionAndAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.platform.repository.FAQRepository;

/**
 * Service class meant for handling operations with the {@link eu.accesa.platform.repository.FAQRepository}.
 * Created by Dragos on 9/16/2015.
 */
@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    /**
     * Adds a new {@link eu.accesa.platform.model.entities.QuestionAndAnswer} into the {@link eu.accesa.platform.repository.FAQRepository}.
     * @param qAnda
     */
    public void addNewFAQ(final QuestionAndAnswer qAnda){
        faqRepository.insertQuestionAndAnswer(qAnda);
    }

    /**
     * Retrieves a list of all existent {@link eu.accesa.platform.model.entities.QuestionAndAnswer} in the {@link eu.accesa.platform.repository.FAQRepository}.
     * @return
     */
    public List<QuestionAndAnswer> retrieveAllFAQs(){
        return faqRepository.retrieveQuestionsAndAnswers();
    }

    /**
     * Deletes a {@link eu.accesa.platform.model.entities.QuestionAndAnswer} based on it's id.
     * @param faqId the id of the {@link eu.accesa.platform.model.entities.QuestionAndAnswer} that will be deleted.
     */
    public void deleteeFAQById(final int faqId){
        faqRepository.deleteQuestionAndAnswerById(faqId);
    }
}
