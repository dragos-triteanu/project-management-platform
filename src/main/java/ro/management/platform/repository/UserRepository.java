package ro.management.platform.repository;

import ro.management.platform.model.entities.Consultant;
import ro.management.platform.model.entities.User;
import ro.management.platform.utils.CategoryConsultantSearch;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ro.management.platform.repository.Queries.*;

/**
 * Created by Dragos on 9/13/2015.
 */
@Repository
@Transactional(readOnly = false)
public class UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    public List<Consultant> retrieveConsultants() {
        LOG.debug("Retrieving list of all Consultant objects");
        List<Consultant> consultants = sessionFactory.getCurrentSession().createCriteria(Consultant.class).list();
        LOG.debug("Found {} consultants", consultants.size());
        return consultants;
    }

    public Consultant retrieveConsultantByUid(int id) {
        LOG.debug("Retrieving the Consultant with id :" + id);

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Consultant.class);
        criteria.add(Restrictions.eq("userId", id));
        Consultant consultant = (Consultant)criteria.uniqueResult();

        if(consultant==null)
        {
            LOG.error("The consultant with id {} wasn't found", id);
            return null;
        }

        return consultant;
    }

    /**
     * Method for inserting a {@link ro.management.platform.model.entities.Consultant} into the 'consultants' SQL schema.
     *
     * @param consultant the to be added {@link ro.management.platform.model.entities.Consultant}.
     */
    public void insertUser(final Consultant consultant) {
        LOG.info("Inserting new  user");
        sessionFactory.getCurrentSession().persist(consultant);
        LOG.debug("Inserted user with name {}", consultant.getFirstName());
    }

    public void updateConsultant(Consultant consultant) {
        LOG.info("Updating details for consultant wit consultantId={}", consultant.getUserId());
        Query query = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_CONSULTANT);
        query.setParameter("lastName", consultant.getLastName());
        query.setParameter("firstName", consultant.getFirstName());
        query.setParameter("mail", consultant.getMail());
        query.setParameter("phoneNumber", consultant.getPhoneNumber());
        query.setParameter("address", consultant.getAddress());
        query.setParameter("studies", consultant.getStudies());
        query.setParameter("ibanCode", consultant.getIbanCode());
        query.setParameter("cardOwner",consultant.getCardOwner());
        query.setParameter("specialityId", consultant.getSpeciality().getSpecialityId());
        query.setParameter("userId", consultant.getUserId());
        int rowUpdates = query.executeUpdate();
        LOG.debug("Number of updated rows: {}", rowUpdates);
    }

    public void updateConsultantWithCv(Consultant consultant) {
        LOG.info("Updating details for consultant wit consultantId={}", consultant.getUserId());
        int updateCount = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_CONSULTANT_WITH_CV)
                .setParameter("lastName", consultant.getLastName())
                .setParameter("firstName", consultant.getFirstName())
                .setParameter("mail", consultant.getMail())
                .setParameter("phoneNumber", consultant.getPhoneNumber())
                .setParameter("address", consultant.getAddress())
                .setParameter("studies", consultant.getStudies())
                .setParameter("ibanCode", consultant.getIbanCode())
                .setParameter("cardOwner", consultant.getCardOwner())
                .setParameter("specialityId", consultant.getSpeciality().getSpecialityId())
                .setParameter("userId", consultant.getUserId())
                .setParameter("cv", consultant.getCv())
                .executeUpdate();
        LOG.debug("Number of updated rows: {}",updateCount);
    }


    public void deleteConsultant(String consultantId) {
        LOG.info("Deleting consultant with consultantId={}", consultantId);
        Query query = sessionFactory.getCurrentSession().getNamedQuery(DELETE_CONSULTANT_BY_ID);
        query.setParameter("userId",Integer.parseInt(consultantId));
        query.executeUpdate();
    }

    public User getUserForCredentials(final String mail, final String password) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("mail", mail));
        criteria.add(Restrictions.eq("password", password));

        User user =(User) criteria.uniqueResult();
        if (user == null) {
            return null;
        }

        Date date = new Date();
        user.setLastLogin(new Timestamp(date.getTime()));
        sessionFactory.getCurrentSession().update(user);

        return user;
    }

    public List<Consultant> getConsultantsResultSearch(String searchText, CategoryConsultantSearch selectedCategory) {
        LOG.info("Searching for orders that contain the word={}", searchText);
        List<Consultant> consultants =new ArrayList<>() ;

        switch (selectedCategory) {
            case NAME:
                 Query getByName = sessionFactory.getCurrentSession().createQuery(RETRIEVE_CONSULTANTS_BY_NAME);
                 getByName.setParameter("searchText", "%" + searchText + "%");
                 return getByName.list();

            case ADDRESS:
                Query getByAddress = sessionFactory.getCurrentSession().createQuery(RETRIEVE_CONSULTANTS_BY_ADDRESS);
                getByAddress.setParameter("searchText", "%" + searchText + "%");
                return getByAddress.list();
        }
        return consultants;
    }
}