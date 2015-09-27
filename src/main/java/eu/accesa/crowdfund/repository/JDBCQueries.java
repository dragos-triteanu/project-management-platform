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
	public static final String RETRIEVE_CONSULTANT_BY_ID = "SELECT * FROM consultants consultants JOIN consultantSpecialities specialities ON consultants.specialityId = specialities.specialityId WHERE consultantId=?";
	public static final String UPDATE_CONSULTANT = "UPDATE consultants SET lastName=?,"
																	     + "firstName=?,"
																	     + "email=?,"
																	     + "phoneNumber=?,"	
																	     + "address=?,"
																	     + "studies=?,"
																	     + "IBAN=?,"
																	     + "specialityId=?"
																	     + " WHERE consultantId=?";
	
	public static final String UPDATE_CONSULTANT_WITH_CV = "UPDATE consultants SET lastName=?,"
		     + "firstName=?,"
		     + "email=?,"
		     + "phoneNumber=?,"	
		     + "address=?,"
		     + "studies=?,"
		     + "IBAN=?,"
		     + "CV=?,"
		     + "specialityId=?"
		     + " WHERE consultantId=?";
	public static final String DELETE_CONSULTANT_BY_ID = "DELETE FROM consultants WHERE consultantId=?";
																	     
	
	/**
	 * {@link OrderRepository} queries.
	 */
	public static final String RETRIEVE_CONSULTANT_ORDERS = "SELECT o.orderId,o.speciality,o.subject,o.nrOfPages,o.tableOfContents,o.bibliography,o.annexes,o.message,o.clientId,co.status " +
															"FROM orders o " +
															"LEFT JOIN (SELECT * from consultantorders WHERE consultantId=?) co ON o.orderId=co.orderId " +
															"WHERE o.status = ?";
	public static final String RETRIEVE_CONSULTANT_ASSIGNED_ORDERS =  "SELECT o.orderId,o.speciality,o.subject,o.nrOfPages,o.tableOfContents,o.bibliography,o.annexes,o.message,o.clientId,co.status " +
			                                                       "FROM consultantorders co "+
			                                                       "JOIN orders o ON o.orderId=co.orderId "+
	                                                               "WHERE co.consultantId=? AND o.status=?";
	public static final String RETRIEVE_ORDER_BY_ID = "Select * from orders WHERE orderId=?";
	public static final String CREATE_ORDER = "INSERT INTO orders(speciality,subject,nrOfPages,tableOfContents,bibliography,annexes,message,status) VALUES(?,?,?,?,?,?,?,?)";

	/**
	 * {@link eu.accesa.crowdfund.repository.BidRepository} queries
	 */
	public static final String INSERT_BID = "INSERT INTO consultantOrders(orderId,consultantId,cost,nrOfDays,status) VALUES(?,?,?,?,?);";
}
