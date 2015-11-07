package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.entities.Consultant;
import eu.accesa.crowdfund.model.entities.ConsultantSpeciality;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.*;

/**
 * Created by Dragos on 9/13/2015.
 */
@Repository
@Transactional(readOnly = false)
public class ConsultantCategoryRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *Insert  a new {@link eu.accesa.crowdfund.model.entities.ConsultantSpeciality} in the 'consultant-category' DB schema.
     * @param consultantCategory the to be added {@link eu.accesa.crowdfund.model.entities.ConsultantSpeciality}.
     */
    public void insertCategory(ConsultantSpeciality consultantCategory) {
        LOG.debug("Inserting category with categoryId={}", consultantCategory.getSpecialityId());
        sessionFactory.getCurrentSession().persist(consultantCategory);
    }

    /**
     * Retrieves all {@link eu.accesa.crowdfund.model.entities.ConsultantSpeciality}s in the 'consultant-category' DB schema.
     * @return
     */
    public List<ConsultantSpeciality> retrieveAllCategories(){
        LOG.debug("Retrieving all categories of consultants");
        List<ConsultantSpeciality> categories = sessionFactory.getCurrentSession().createCriteria(ConsultantSpeciality.class).list();
        LOG.debug("Retrieved a list of {} categories",categories.size());
         return categories;
    }

    /**
     * Retrieves all {@link eu.accesa.crowdfund.model.entities.ConsultantSpeciality}s in the 'consultant-category' DB schema.
     * @return
     */
    public void deleteCategoryById(final int id){
        LOG.debug("Deleting category with categoryId={}",id);
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(ConsultantSpeciality.class,id));
    }
}

