package eu.accesa.crowdfund.repository;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Utility class for housing the {@link JdbcTemplate} queries for each repository.
 * @author dragos.triteanu
 *
 */
class JDBCQueries {
	/**
	 * {@link LandingPageRepository} queries.
	 */
	public static final String UPDATE_WYSIWYG_HTML = "UPDATE home SET html=? where id=? ";
	public static final String RETRIEVE_HTML_FOR_WYSIWYG = "SELECT html FROM home where id=?";
	
	/**
	 * {@link FAQRepository} queries.
	 */
	public static final String RETRIEVE_ALL_QAA = "SELECT * FROM faq";
	public static final String INSERT_QAA = "INSERT INTO faq(id,question,answer) VALUES(?,?,?)";
	public static final String DELETE_QAA_BY_ID = "DELETE FROM faq WHERE id=?";

	/**
	 * {@link eu.accesa.crowdfund.repository.ConsultantRepository} queries.
	 */
	public static final String INSERT_CONSULTANT_CATEGORY = "INSERT INTO consultantCategories(id,name) VALUES(?,?)";
	public static final String RETRIEVE_ALL_CATEGORIES = "SELECT * FROM consultantCategories";
	public static final String DELETE_CONSULTANT_CATEGORY_BY_ID = "DELETE FROM consultantCategories WHERE id=?";

}
