package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.ConsultantSpeciality;
import eu.accesa.crowdfund.repository.ConsultantCategoryRepository;
import eu.accesa.crowdfund.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class meant for managing operations regarding consultant categories.
 * Created by Dragos on 9/16/2015.
 */
@Service
public class ConsultantCategoryService {

    @Autowired
    private ConsultantCategoryRepository consultantCategoryRepository;

    /**
     * Inserts a new {@link eu.accesa.crowdfund.model.ConsultantSpeciality} in the corresponding schema.
     * @param consultantCategory
     */
    public void insertConsultantCategory(final ConsultantSpeciality consultantCategory){
        consultantCategory.setSpecialityId(UUIDGenerator.generateUUID());
        consultantCategoryRepository.insertCategory(consultantCategory);
    }

    /**
     * Retrieves all the {@link eu.accesa.crowdfund.model.ConsultantSpeciality} from the corresponding schema.
     * @return a list of {@link eu.accesa.crowdfund.model.ConsultantSpeciality}.
     */
    public List<ConsultantSpeciality> retrieveAllConsultantCategories(){
        return consultantCategoryRepository.retrieveAllCategories();
    }

    /**
     * Deletes a consultant category from the corresponding schema.
     * @param consultantCategoryId the id of the to be deleted {@link eu.accesa.crowdfund.model.ConsultantSpeciality}.
     */
    public void deleteConsultantCategory(final String consultantCategoryId){
        consultantCategoryRepository.deleteCategoryById(consultantCategoryId);
    }
}