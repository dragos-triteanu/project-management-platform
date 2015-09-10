package eu.accesa.crowdfund.repository;

import static eu.accesa.crowdfund.repository.JDBCQueries.*;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import eu.accesa.crowdfund.model.QuestionAndAnswer;
import eu.accesa.crowdfund.repository.mappers.Mappers;

/**
 * Repository class for managing QuestionAndAnswer data objects.
 * @author dragos.triteanu
 *
 */
@Repository
public class FAQRepository {
	private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);
	
	@Resource(name="crowdfundingJdbcTemplate")
	private JdbcTemplate faqJdbcTemplate;
	
	/**
	 * Retrieves a list of {@link QuestionAndAnswer} from the 'faq' SQL table.
	 * @return
	 */
	public List<QuestionAndAnswer> retrieveQuestionsAndAnswers(){
		LOG.debug("Retrieving list of all QuestionAndAnswer objects");
		List<QuestionAndAnswer> qaaList = faqJdbcTemplate.query(RETRIEVE_ALL_QAA,new Object[]{}, Mappers.questionAndAnswerMapper());
		LOG.debug("Found :"+qaaList);
		return qaaList;
	}
	
	/**
	 * Inserts a new {@link QuestionAndAnswer} in the 'faq' SQL table.
	 * @param questionAndAnswer the {@link QuestionAndAnswer} that will be inserted. 
	 */
	public void insertQuestionAndAnswer(QuestionAndAnswer questionAndAnswer){
		LOG.debug("Inserting QuestionAndAnswer with question="+questionAndAnswer.getQuestion() + " and answer="+questionAndAnswer.getAnswer());
		int response = faqJdbcTemplate.update(INSERT_QAA, new Object[]{questionAndAnswer.getQuestion(),questionAndAnswer.getAnswer()});
		LOG.debug("Number of rows modified ="+response);
	}
	
	/**
	 * Delete a {@link QuestionAndAnswer} from the 'faq' SQL table.
	 * @param id
	 */
	public void deleteQuestionAndAnswerById(String id){
		LOG.debug("Deleting QuestionAndAnswer with id="+id);
		int response = faqJdbcTemplate.update(DELETE_QAA_BY_ID,new Object[]{id});
		LOG.debug("Number of rows modified = "+response);
	}
}
