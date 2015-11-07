package eu.accesa.crowdfund.repository;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Utility class for housing the {@link JdbcTemplate} queries for each repository.
 * @author dragos.triteanu
 *
 */
class JDBCQueries {

	/**
	 * {@link eu.accesa.crowdfund.repository.UserRepository} queries.
	 */

	public static final String UPDATE_CONSULTANT = "UPDATE Consultant c SET c.lastName=:lastName,c.firstName=:firstName,c.mail=:mail, c.phoneNumber= :phoneNumber, c.address = :address,  c.studies= :studies, c.ibanCode=:ibanCode,c.cardOwner=:cardOwner , c.speciality.specialityId= :specialityId WHERE  c.userId= :userId";

	public static final String DELETE_CONSULTANT_BY_ID = "DELETE FROM Consultant WHERE userId=:userId";
																	     
	public static final String RETRIEVE_CONSULTANTS_BY_NAME = "SELECT user FROM Consultant user WHERE CONCAT(user.firstName, ' ', user.lastName) like :searchText OR CONCAT(user.lastName, ' ', user.firstName) like :searchText";

	public static final String RETRIEVE_CONSULTANTS_BY_ADDRESS = "SELECT user FROM Consultant user WHERE user.address like :searchText";

	/**
	 * {@link eu.accesa.crowdfund.repository.MessageRepository} queries
	 */
	public static final String RETRIEVE_ORDER_CHAT_MESSAGES = "SELECT * FROM messages WHERE orderId=? ORDER BY dateTime";

}
