package eu.accesa.crowdfund.repository;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Utility class for housing the {@link JdbcTemplate} queries for each repository.
 * @author dragos.triteanu
 *
 */
class JDBCQueries {


	public static final String UPDATE_CONSULTANT = "UPDATE Consultant c SET c.lastName=:lastName,c.firstName=:firstName,c.mail=:mail, c.phoneNumber= :phoneNumber, c.address = :address,  c.studies= :studies, c.ibanCode=:ibanCode,c.cardOwner=:cardOwner , c.speciality.specialityId= :specialityId WHERE  c.userId= :userId";
	public static final String ORDER_UPDATE = "UPDATE Order o SET o.domain=:domain, o.subject=:subject, o.nrOfPages=:nrOfPages, o.tableOfContents=:tableOfContents, o.bibliography=:bibliography, o.message=:message, o.orderStatus=:orderStatus WHERE o.orderId=:orderId";


	public static final String DELETE_CONSULTANT_BY_ID = "DELETE FROM Consultant WHERE userId=:userId";
	public static final String DELETE_BID = "DELETE FROM ConsultantOrder WHERE consultantId=:consultantId and orderId=:orderId";
	public static final String DELETE_QAA_BY_ID = "DELETE FROM QuestionAndAnswer WHERE id=:faqId";
	public static final String DELETE_CONSULTANT_CATEGORY_BY_ID = "DELETE FROM ConsultantSpeciality WHERE specialityId=:specialityId";
																	     

	public static final String RETRIEVE_CONSULTANTS_BY_NAME = "SELECT user FROM Consultant user WHERE CONCAT(user.firstName, ' ', user.lastName) like :searchText OR CONCAT(user.lastName, ' ', user.firstName) like :searchText";
	public static final String RETRIEVE_CONSULTANTS_BY_ADDRESS = "SELECT user FROM Consultant user WHERE user.address like :searchText";

}
