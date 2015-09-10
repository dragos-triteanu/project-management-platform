package eu.accesa.crowdfund.repository.mappers;

import eu.accesa.crowdfund.model.QuestionAndAnswer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class for housing {@link RowMapper}s for various application's DAO's.
 * @author dragos.triteanu
 *
 */
public class Mappers {
	
	/**
	 * Mapper class for mapping a row in the 'faq' SQL schema, to a {@link QuestionAndAnswer} object.
	 * @author dragos.triteanu
	 *
	 */
	private static final class QuestionAndAnswerMapper implements RowMapper<QuestionAndAnswer>{

		@Override
		public QuestionAndAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
			QuestionAndAnswer qaa = new QuestionAndAnswer();
			qaa.setId(rs.getInt("id"));
			qaa.setQuestion(rs.getString("question"));
			qaa.setAnswer(rs.getString("answer"));
			return qaa;
		}
		
	}

	

	
	public static final RowMapper<QuestionAndAnswer> questionAndAnswerMapper(){
		return new QuestionAndAnswerMapper();
	}
}
