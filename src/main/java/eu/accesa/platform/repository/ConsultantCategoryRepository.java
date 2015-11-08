package eu.accesa.platform.repository;

import eu.accesa.platform.model.entities.ConsultantSpeciality;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static eu.accesa.platform.repository.Queries.*;

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
     *Insert  a new {@link eu.accesa.platform.model.entities.ConsultantSpeciality} in the 'consultant-category' DB schema.
     * @param consultantCategory the to be added {@link eu.accesa.platform.model.entities.ConsultantSpeciality}.
     */
    public void insertCategory(ConsultantSpeciality consultantCategory) {
        LOG.debug("Inserting category with categoryId={}", consultantCategory.getSpecialityId());
        sessionFactory.getCurrentSession().persist(consultantCategory);
    }

    /**
     * Retrieves all {@link eu.accesa.platform.model.entities.ConsultantSpeciality}s in the 'consultant-category' DB schema.
     * @return
     */
    public List<ConsultantSpeciality> retrieveAllCategories(){
        LOG.debug("Retrieving all categories of consultants");
        List<ConsultantSpeciality> categories = sessionFactory.getCurrentSession().createCriteria(ConsultantSpeciality.class).list();
        LOG.debug("Retrieved a list of {} categories",categories.size());
         return categories;
    }

    /**
     * Retrieves all {@link eu.accesa.platform.model.entities.ConsultantSpeciality}s in the 'consultant-category' DB schema.
     * @return
     */
    public void deleteCategoryById(final int id){
        LOG.debug("Deleting category with categoryId={}",id);

        Query query = sessionFactory.getCurrentSession().getNamedQuery(DELETE_CONSULTANT_CATEGORY_BY_ID);
        query.setParameter("specialityId", id);
        query.executeUpdate();
    }
}

