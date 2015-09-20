package eu.accesa.crowdfund.repository.mappers;

import eu.accesa.crowdfund.model.ConsultantSpeciality;
import eu.accesa.crowdfund.model.QuestionAndAnswer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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

	/**
	 * Mapper class for mapping a row in the 'consultant-category' SQL schema, to a {@link eu.accesa.crowdfund.model.ConsultantSpeciality} object.
	 * @author dragos.triteanu
	 *
	 */
	private static final class ConsultantCategoryMapper implements RowMapper<ConsultantSpeciality>{

		@Override
		public ConsultantSpeciality mapRow(ResultSet rs, int rowNum) throws SQLException {
			ConsultantSpeciality consultantCategory = new ConsultantSpeciality();
			consultantCategory.setSpecialityId(rs.getInt("specialityId"));
			consultantCategory.setSpecialityName(rs.getString("specialityName"));
			return consultantCategory;
		}
	}

	public static final RowMapper<QuestionAndAnswer> questionAndAnswerMapper(){
		return new QuestionAndAnswerMapper();
	}

	public static final RowMapper<ConsultantSpeciality> consultantCategoryMapper(){return new ConsultantCategoryMapper();}
}
