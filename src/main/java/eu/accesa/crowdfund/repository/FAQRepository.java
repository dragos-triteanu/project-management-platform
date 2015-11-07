package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.entities.QuestionAndAnswer;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.DELETE_QAA_BY_ID;

/**
 * Repository class for managing QuestionAndAnswer data objects.
 *
 * @author dragos.triteanu
 */
@Repository
@Transactional(readOnly = false)
public class FAQRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Retrieves a list of {@link QuestionAndAnswer} from the 'faq' SQL table.
     *
     * @return
     */
    public List<QuestionAndAnswer> retrieveQuestionsAndAnswers() {
        LOG.debug("Retrieving list of all QuestionAndAnswer objects");
        List<QuestionAndAnswer> questionAndAnswerEntities = sessionFactory.getCurrentSession().createCriteria(QuestionAndAnswer.class).list();
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
        sessionFactory.getCurrentSession().persist(questionAndAnswer);
        retrieveQuestionsAndAnswers();
    }

    /**
     * Delete a {@link QuestionAndAnswer} from the 'faq' SQL table.
     *
     * @param id
     */
    public void deleteQuestionAndAnswerById(int id) {
        LOG.debug("Deleting QuestionAndAnswer with id=" + id);

        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_QAA_BY_ID);
        query.setParameter("faqId", id);
        query.executeUpdate();
    }
}
