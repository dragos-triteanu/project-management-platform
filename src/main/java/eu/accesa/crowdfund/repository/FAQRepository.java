package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.entities.QuestionAndAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* Repository class for managing QuestionAndAnswer data objects.
*
* @author dragos.triteanu
*/
@Repository
@Transactional(readOnly = false)
public class FAQRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource
    private HibernateTemplate hibernateTemplate;

    /**
     * Retrieves a list of {@link QuestionAndAnswer} from the 'faq' SQL table.
     *
     * @return
     */
    public List<QuestionAndAnswer> retrieveQuestionsAndAnswers() {
        LOG.debug("Retrieving list of all QuestionAndAnswer objects");
        List<QuestionAndAnswer> questionAndAnswerEntities = hibernateTemplate.loadAll(QuestionAndAnswer.class);
        LOG.debug("Found :" + questionAndAnswerEntities);
        return questionAndAnswerEntities;
    }

    /**
     * Inserts a new {@link QuestionAndAnswer} in the 'faq' SQL table.
     *
     * @param questionAndAnswer the {@link QuestionAndAnswer} that will be inserted.
     */
    public void insertQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
        LOG.debug("Inserting QuestionAndAnswer with question=" + questionAndAnswer.getQuestion() + " and answer=" + questionAndAnswer.getAnswer());
        hibernateTemplate.persist(questionAndAnswer);
    }

    /**
     * Delete a {@link QuestionAndAnswer} from the 'faq' SQL table.
     *
     * @param id
     */
    public void deleteQuestionAndAnswerById(int id) {
        LOG.debug("Deleting QuestionAndAnswer with id=" + id);
        //int response = faqJdbcTemplate.update(DELETE_QAA_BY_ID, id);
        hibernateTemplate.delete(hibernateTemplate.get(QuestionAndAnswer.class, id));
    }
}
