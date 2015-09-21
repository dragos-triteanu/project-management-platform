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
	public static final String INSERT_QAA = "INSERT INTO faq(question,answer) VALUES(?,?)";
	public static final String DELETE_QAA_BY_ID = "DELETE FROM faq WHERE id=?";

	/**
	 * {@link eu.accesa.crowdfund.repository.ConsultantRepository} queries.
	 */
	public static final String INSERT_CONSULTANT_CATEGORY = "INSERT INTO consultantSpecialities(specialityName) VALUES(?)";
	public static final String RETRIEVE_ALL_CATEGORIES = "SELECT * FROM consultantSpecialities";
	public static final String DELETE_CONSULTANT_CATEGORY_BY_ID = "DELETE FROM consultantSpecialities WHERE specialityId=?";

	public static final String INSERT_CONSULTANT = "INSERT INTO consultants(lastname,firstname,email,phoneNumber,address,studies,IBAN,CV,specialityId) VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String RETRIEVE_ALL_CONSULTANTS = "SELECT * FROM consultants consultants JOIN consultantSpecialities specialities ON consultants.specialityId = specialities.specialityId";
	public static final String RETRIEVE_CONSULTANT_BY_ID = "SELECT * FROM consultants consultants JOIN consultantSpecialities specialities ON consultants.specialityId = specialities.specialityId WHERE id=?";

	/**
	 * {@link OrderRepository} queries.
	 */
	public static final String RETRIEVE_ORDERS_BY_STATUS = "Select * from orders WHERE status=?";
	public static final String RETRIEVE_ORDER_BY_ID = "Select * from orders WHERE id=?";
}