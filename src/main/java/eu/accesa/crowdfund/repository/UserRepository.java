package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.User;
import eu.accesa.crowdfund.repository.mappers.Mappers;

import eu.accesa.crowdfund.utils.CategoryConsultantSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.*;

/**
 * Created by Dragos on 9/13/2015.
 */
@Repository
public class UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name = "crowdfundingJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<User> retrieveConsultants() {
        LOG.debug("Retrieving list of all Consultant objects");
        List<User> consultants = jdbcTemplate.query(RETRIEVE_ALL_CONSULTANTS, Mappers.userMapper());
        LOG.debug("Found {} consultants", consultants.size());
        return consultants;
    }

    public User retrieveConsultantByUid(int id) {
        LOG.debug("Retrieving the Consultant with id :" + id);
        User queryForObject = null;
        try {
            queryForObject = jdbcTemplate.queryForObject(RETRIEVE_CONSULTANT_BY_ID, new Object[]{id}, Mappers.userMapper());
        } catch (Exception e) {
            LOG.error("Error while retrieving consultant with id={]", id, e);
            return null;
        }
        return queryForObject;
    }

    /**
     * Method for inserting a {@link eu.accesa.crowdfund.model.User} into the 'consultants' SQL schema.
     *
     * @param consultant the to be added {@link eu.accesa.crowdfund.model.User}.
     */
    public void insertUser(final User consultant) {
        LOG.info("Inserting new  user");
        jdbcTemplate.update(INSERT_USER, new Object[]{consultant.getLastName(),
                consultant.getFirstName(),
                consultant.getMail(),
                consultant.getPhoneNumber(),
                consultant.getAddress(),
                consultant.getStudies(),
                consultant.getIbanCode(),
                consultant.getCv(),
                consultant.getSpeciality().getSpecialityId(),
                consultant.getPassword(),
                consultant.getRole().getRole(),
                consultant.getLastLogin()});

        LOG.debug("Inserted user with name {}", consultant.getFirstName());
    }

    public void updateConsultant(User consultant) {
        LOG.info("Updating details for consultant wit consultantId={}", consultant.getConsultantId());
        int update = jdbcTemplate.update(UPDATE_CONSULTANT, new Object[]{
                consultant.getLastName(),
                consultant.getFirstName(),
                consultant.getMail(),
                consultant.getPhoneNumber(),
                consultant.getAddress(),
                consultant.getStudies(),
                consultant.getIbanCode(),
                consultant.getSpeciality().getSpecialityId(),
                consultant.getConsultantId()});
        LOG.debug("Number of rows affected by update={}", update);
    }

    public void updateConsultantWithCv(User consultant) {
        LOG.info("Updating details for consultant wit consultantId={}", consultant.getConsultantId());
        int update = jdbcTemplate.update(UPDATE_CONSULTANT_WITH_CV, new Object[]{
                consultant.getLastName(),
                consultant.getFirstName(),
                consultant.getMail(),
                consultant.getPhoneNumber(),
                consultant.getAddress(),
                consultant.getStudies(),
                consultant.getIbanCode(),
                consultant.getCv(),
                consultant.getSpeciality().getSpecialityId(),
                consultant.getConsultantId()});
        LOG.debug("Number of rows affected by update={}", update);
    }


    public void deleteConsultant(String consultantId) {
        LOG.info("Deleting consultant with consultantId={}", consultantId);
        int update = jdbcTemplate.update(DELETE_CONSULTANT_BY_ID, new Object[]{consultantId});
        LOG.debug("Number of rows affected by delete = {}", update);
    }

    @Transactional
    public User getUserForCredentials(final String mail, final String password) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(RETRIEVE_USER_BY_CREDENTIALS, new Object[]{mail, password}, Mappers.authUserMapper());
        } catch (Exception e) {
            LOG.info("Could not retrieve details for user with username={}", mail);
            return user;
        }
        jdbcTemplate.update(UPDATE_LAST_LOGIN, new Object[]{mail, password});
        return user;
    }

    public List<User> getConsultantsResultSearch(String searchText, CategoryConsultantSearch selectedCategory) {
        LOG.info("Searching for orders that contain the word={}", searchText);
        List<User> consultants = new ArrayList<>();
        switch (selectedCategory) {
            case NAME:
                consultants = jdbcTemplate.query(RETRIEVE_CONSULTANTS_BY_NAME, new Object[]{"%" + searchText + "%", "%" + searchText + "%"}, Mappers.userMapper());
                break;
            case ADDRESS:
                consultants = jdbcTemplate.query(RETRIEVE_CONSULTANTS_BY_ADDRESS, new Object[]{"%" + searchText + "%"}, Mappers.userMapper());
                break;
        }
        LOG.debug("Found :" + consultants);
        return consultants;
    }
}