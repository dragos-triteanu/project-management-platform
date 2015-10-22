package eu.accesa.crowdfund.repository.mappers;

import eu.accesa.crowdfund.model.*;
import eu.accesa.crowdfund.security.Authority;
import eu.accesa.crowdfund.utils.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class for housing {@link RowMapper}s for various application's DAO's.
 *
 * @author dragos.triteanu
 */
public class Mappers {

    /**
     * Mapper class for mapping a row in the 'faq' SQL schema, to a {@link QuestionAndAnswer} object.
     *
     * @author dragos.triteanu
     */
    private static final class QuestionAndAnswerMapper implements RowMapper<QuestionAndAnswer> {

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
     *
     * @author dragos.triteanu
     */
    private static final class ConsultantCategoryMapper implements RowMapper<ConsultantSpeciality> {

        @Override
        public ConsultantSpeciality mapRow(ResultSet rs, int rowNum) throws SQLException {
            ConsultantSpeciality consultantCategory = new ConsultantSpeciality();
            consultantCategory.setSpecialityId(rs.getInt("specialityId"));
            consultantCategory.setSpecialityName(rs.getString("specialityName"));
            return consultantCategory;
        }
    }

    private static final class ConsultantUserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User consultant = new User();
            consultant.setConsultantId(rs.getInt("userId"));
            consultant.setLastName(rs.getString("lastName"));
            consultant.setFirstName(rs.getString("firstName"));
            consultant.setMail(rs.getString("email"));
            consultant.setPhoneNumber(rs.getString("phoneNumber"));
            consultant.setAddress(rs.getString("address"));
            consultant.setStudies(rs.getString("studies"));
            consultant.setIbanCode(rs.getString("IBAN"));
            ConsultantSpeciality speciality = new ConsultantSpeciality();
            speciality.setSpecialityId(rs.getInt("specialityId"));
            speciality.setSpecialityName(rs.getString("specialityName"));
            consultant.setSpeciality(speciality);
            consultant.setCv(rs.getBytes("cv"));
            if (consultant.getCv() != null && consultant.getCv().length > 0) {
                consultant.setCvURL("/api/service/cv?id=" + consultant.getConsultantId());
            }
            consultant.setUsername(rs.getString("username"));
            consultant.setPassword(rs.getString("password"));
            consultant.setRole(Authority.valueOf(rs.getString("role")));
            return consultant;
        }
    }

    private static final class AuthenticationUserMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User authUser = new User();
            authUser.setConsultantId(resultSet.getInt("userId"));
            authUser.setUsername(resultSet.getString("username"));
            authUser.setPassword(resultSet.getString("password"));
            authUser.setRole(Authority.valueOf(resultSet.getString("role")));
            return authUser;
        }
    }


    /**
     * Mapper class for mapping a row in the 'order' SQL schema, to a {@link eu.accesa.crowdfund.model.Order} object.
     */
    private static final class OrderMapper implements RowMapper<Order> {
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setDomain(rs.getString("speciality"));
            order.setSubject(rs.getString("subject"));
            order.setNrOfPages(rs.getInt("nrOfPages"));
            order.setTableOfContents(rs.getString("tableOfContents"));
            order.setBibliography(rs.getString("bibliography"));
            order.setAnnexes(rs.getBytes("annexes"));
            order.setMessage(rs.getString("message"));
            if (rs.getObject("status") != null) {
                order.setOrderStatus(OrderStatus.values()[rs.getInt("status")]);
            } else {
                order.setOrderStatus(OrderStatus.ACCEPTED);
            }
            Client client = new Client();
            client.setId(rs.getInt("clientId"));
            order.setClient(client);

            return order;
        }
    }

    /**
     * Mapper class for mapping a row in the 'message' SQL schema, to a {@link eu.accesa.crowdfund.model.Message} object
     */
    private static final class MessageMapper implements RowMapper<Message> {
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            int clientId, consultantId;
            Message message = new Message();
            Client client = new Client();
            User consultant = new User();

            clientId = rs.getObject("clientId") != null ? rs.getInt("clientId") : 0;
            consultantId = rs.getObject("consultantId") != null ? rs.getInt("consultantId") : 0;
            message.setMessageId(rs.getInt("messageId"));
            consultant.setConsultantId(consultantId);
            message.setConsultant(consultant);
            client.setId(clientId);
            message.setClient(client);
            message.setMessage(rs.getString("message"));
            message.setDateTime(rs.getDate("dateTime"));

            return message;
        }
    }


    public static final RowMapper<QuestionAndAnswer> questionAndAnswerMapper() {
        return new QuestionAndAnswerMapper();
    }

    public static final RowMapper<ConsultantSpeciality> consultantCategoryMapper() {
        return new ConsultantCategoryMapper();
    }

    public static final RowMapper<User> userMapper() {
        return new ConsultantUserMapper();
    }

    public static final RowMapper<Order> orderMaper() {
        return new OrderMapper();
    }

    public static final RowMapper<Message> messageMapper() {
        return new MessageMapper();
    }

    public static final RowMapper<User> authUserMapper() {return new AuthenticationUserMapper();}
}
