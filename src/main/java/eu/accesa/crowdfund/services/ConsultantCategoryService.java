package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.ConsultantCategory;
import eu.accesa.crowdfund.repository.ConsultantCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service class meant for managing operations regarding consultant categories.
 * Created by Dragos on 9/16/2015.
 */
public class ConsultantCategoryService {

    @Autowired
    private ConsultantCategoryRepository consultantCategoryRepository;

    /**
     * Inserts a new {@link eu.accesa.crowdfund.model.ConsultantCategory} in the corresponding schema.
     * @param consultantCategory
     */
    public void insertConsultantCategory(final ConsultantCategory consultantCategory){
        consultantCategoryRepository.insertCategory(consultantCategory);
    }

    /**
     * Retrieves all the {@link eu.accesa.crowdfund.model.ConsultantCategory} from the corresponding schema.
     * @return a list of {@link eu.accesa.crowdfund.model.ConsultantCategory}.
     */
    public List<ConsultantCategory> retrieveAllConsultantCategories(){
        return consultantCategoryRepository.retrieveAllCategories();
    }

    /**
     * Deletes a consultant category from the corresponding schema.
     * @param consultantCategoryId the id of the to be deleted {@link eu.accesa.crowdfund.model.ConsultantCategory}.
     */
    public void deleteConsultantCategory(final String consultantCategoryId){
        consultantCategoryRepository.deleteCategoryById(consultantCategoryId);
    }
}
