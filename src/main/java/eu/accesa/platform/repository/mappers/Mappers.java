package eu.accesa.platform.repository.mappers;

import eu.accesa.platform.model.*;
import eu.accesa.platform.model.entities.*;
import eu.accesa.platform.repository.FAQRepository;
import eu.accesa.platform.security.Authority;
import eu.accesa.platform.utils.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class for housing {@link RowMapper}s for various application's DAO's.
 *
 * @author dragos.triteanu
 */
public class Mappers {

    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    /**
     * Mapper class for mapping a row in the 'consultant-category' SQL schema, to a {@link eu.accesa.platform.model.entities.ConsultantSpeciality} object.
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

    private static final class ConsultantUserMapper implements RowMapper<Consultant> {

        public Consultant mapRow(ResultSet rs, int rowNum) throws SQLException {
            Consultant consultant = new Consultant();
            consultant.setUserId(rs.getInt("userId"));
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
            consultant.setPassword(rs.getString("password"));
            consultant.setRole(Authority.valueOf(rs.getString("role")));
            consultant.setLastLogin(rs.getObject("lastLogin") != null ? rs.getTimestamp("lastLogin") : null);
            return consultant;
        }
    }

    private static final class AuthenticationUserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User authUser = new User();
            authUser.setUserId(resultSet.getInt("userId"));
            authUser.setMail(resultSet.getString("email"));
            authUser.setPassword(resultSet.getString("password"));
            authUser.setRole(Authority.valueOf(resultSet.getString("role")));
            return authUser;
        }
    }

    /**
     * Mapper class for mapping a row in the 'order' SQL schema, to a {@link eu.accesa.platform.model.entities.Order} object.
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
            client.setUserId(rs.getInt("clientId"));
            try {
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setMail(rs.getString("email"));
            } catch (Exception ex) {
                LOG.info("When current user is consultant the information about client are not available. These information are retrieved from database only for administrator.");
            }
            order.setClient(client);
            return order;
        }
    }

    /**
     * Mapper class for mapping a row in the 'message' SQL schema, to a {@link eu.accesa.platform.model.Message} object
     */
    private static final class MessageMapper implements RowMapper<Message> {
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            int clientId, consultantId;
            Message message = new Message();
            Client client = new Client();
            Consultant consultant = new Consultant();

            clientId = rs.getObject("clientId") != null ? rs.getInt("clientId") : 0;
            consultantId = rs.getObject("consultantId") != null ? rs.getInt("consultantId") : 0;
            message.setMessageId(rs.getInt("messageId"));
            consultant.setUserId(consultantId);
            message.setConsultant(consultant);
            client.setUserId(clientId);
            message.setClient(client);
            message.setMessage(rs.getString("message"));
            message.setDateTime(rs.getDate("dateTime"));

            return message;
        }
    }

    private static final class BidMapper implements RowMapper<ConsultantOrder>{
        public ConsultantOrder mapRow(ResultSet rs, int rowNum) throws SQLException{
            ConsultantOrder consultantOrder = new ConsultantOrder();
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            Consultant consultant=new Consultant();
            consultant.setUserId(rs.getInt("consultantId"));
            consultantOrder.setConsultant(consultant);
            consultantOrder.setOrder(order);
            consultantOrder.setCost(rs.getDouble("cost"));
            consultantOrder.setNrOfDays(rs.getInt("nrOfDays"));
            consultantOrder.setStatus(OrderStatus.values()[rs.getInt("status")]);

            return consultantOrder;
        }
    }

    public static final RowMapper<ConsultantSpeciality> consultantCategoryMapper() {
        return new ConsultantCategoryMapper();
    }

    public static final RowMapper<Consultant> userMapper() {
        return new ConsultantUserMapper();
    }

    public static final RowMapper<Order> orderMapper() {
        return new OrderMapper();
    }

    public static final RowMapper<Message> messageMapper() {
        return new MessageMapper();
    }

    public static final RowMapper<User> authUserMapper() {
        return new AuthenticationUserMapper();
    }

    public static final RowMapper<ConsultantOrder> bidMapper() { return new BidMapper();}

}
