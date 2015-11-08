package eu.accesa.platform.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.platform.model.entities.ConsultantSpeciality;
import eu.accesa.platform.repository.ConsultantCategoryRepository;

/**
 * Service class meant for managing operations regarding consultant categories.
 * Created by Dragos on 9/16/2015.
 */
@Service
public class ConsultantCategoryService {

    @Autowired
    private ConsultantCategoryRepository consultantCategoryRepository;

    /**
     * Inserts a new {@link eu.accesa.platform.model.entities.ConsultantSpeciality} in the corresponding schema.
     * @param consultantCategory
     */
    public void insertConsultantCategory(final ConsultantSpeciality consultantCategory){
        consultantCategoryRepository.insertCategory(consultantCategory);
    }

    /**
     * Retrieves all the {@link eu.accesa.platform.model.entities.ConsultantSpeciality} from the corresponding schema.
     * @return a list of {@link eu.accesa.platform.model.entities.ConsultantSpeciality}.
     */
    public List<ConsultantSpeciality> retrieveAllConsultantCategories(){
        return consultantCategoryRepository.retrieveAllCategories();
    }

    /**
     * Deletes a consultant category from the corresponding schema.
     * @param consultantCategoryId the id of the to be deleted {@link eu.accesa.platform.model.entities.ConsultantSpeciality}.
     */
    public void deleteConsultantCategory(final int consultantCategoryId){
        consultantCategoryRepository.deleteCategoryById(consultantCategoryId);
    }
}
