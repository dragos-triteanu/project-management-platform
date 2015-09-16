package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.ConsultantCategory;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.*;

/**
 * Created by Dragos on 9/13/2015.
 */
@Repository
public class ConsultantCategoryRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name = "crowdfundingJdbcTemplate")
    private JdbcTemplate faqJdbcTemplate;

    /**
     *Insert  a new {@link eu.accesa.crowdfund.model.ConsultantCategory} in the 'consultant-category' DB schema.
     * @param consultantCategory the to be added {@link eu.accesa.crowdfund.model.ConsultantCategory}.
     */
    public void insertCategory(ConsultantCategory consultantCategory) {
        LOG.debug("Inserting category with categoryId={}", consultantCategory.getId());
        int update = faqJdbcTemplate.update(INSERT_CONSULTANT_CATEGORY, new Object[]{consultantCategory.getId(), consultantCategory.getName()});
        LOG.debug("Number of rows modified by insert : {}",update);
    }

    /**
     * Retrieves all {@link eu.accesa.crowdfund.model.ConsultantCategory}s in the 'consultant-category' DB schema.
     * @return
     */
    public List<ConsultantCategory> retrieveAllCategories(){
        LOG.debug("Retrieving all categories of consultants");
        List<ConsultantCategory> categories = faqJdbcTemplate.query(RETRIEVE_ALL_CATEGORIES, Mappers.consultantCategoryMapper());
        LOG.debug("Retrieved a list of {} categories",categories.size());
         return categories;
    }

    /**
     * Retrieves all {@link eu.accesa.crowdfund.model.ConsultantCategory}s in the 'consultant-category' DB schema.
     * @return
     */
    public void deleteCategoryById(final String id){
        LOG.debug("Deleting category with categoryId={}",id);
        int update = faqJdbcTemplate.update(DELETE_CONSULTANT_CATEGORY_BY_ID, new Object[]{id});
        LOG.debug("Number of rows modified by delete : {}", update);
    }
}

