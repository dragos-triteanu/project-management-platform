package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.Consultant;
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
public class ConsultantRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name="crowdfundingJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Consultant> retrieveConsultants(){
        LOG.debug("Retrieving list of all Consultant objects");
        List<Consultant> consultants = jdbcTemplate.query(RETRIEVE_ALL_CONSULTANTS,Mappers.consultantMapper());
        LOG.debug("Found {} consultants",consultants.size());
        return consultants;
    }

    public Consultant retrieveConsultantByUid(int id){
        LOG.debug("Retrieving the Consultant with id :" + id);
        Consultant queryForObject = null;
        try{
        queryForObject = jdbcTemplate.queryForObject(RETRIEVE_CONSULTANT_BY_ID, new Object[]{id},Mappers.consultantMapper());
        }catch(Exception e){
        	LOG.error("Error while retrieving consultant with id={]",id,e);
        	return null;
        }
        return queryForObject;
    }

    /**
     * Method for inserting a {@link eu.accesa.crowdfund.model.Consultant} into the 'consultants' SQL schema.
     * @param consultant the to be added {@link eu.accesa.crowdfund.model.Consultant}.
     */
    public void insertConsultant(final Consultant consultant){
        LOG.info("Inserting consultant with consultantId={}",consultant.getConsultantId());
        int update = jdbcTemplate.update(INSERT_CONSULTANT, new Object[]{consultant.getLastName(),
                                                                                     consultant.getFirstName(),
                                                                                     consultant.getMail(),
                                                                                     consultant.getPhoneNumber(),
                                                                                     consultant.getAddress(),
                                                                                     consultant.getStudies(),
                                                                                     consultant.getIbanCode(),
                                                                                     consultant.getCv(),
                                                                                     consultant.getSpeciality().getSpecialityId()});
        LOG.debug("Number of rows modified by update: {}",update);
    }

	public void updateConsultant(Consultant consultant) {
		LOG.info("Updating details for consultant wit consultantId={}",consultant.getConsultantId());
        int update = jdbcTemplate.update(UPDATE_CONSULTANT,new Object[]{
																	   consultant.getLastName(),
																	   consultant.getFirstName(),
																	   consultant.getMail(),
																	   consultant.getPhoneNumber(),
																	   consultant.getAddress(),
																	   consultant.getStudies(),
																	   consultant.getIbanCode(),
																	   consultant.getSpeciality().getSpecialityId(),
																	   consultant.getConsultantId()});
        LOG.debug("Number of rows affected by update={}",update);
    }
	
	public void updateConsultantWithCv(Consultant consultant) {
		LOG.info("Updating details for consultant wit consultantId={}",consultant.getConsultantId());
        int update = jdbcTemplate.update(UPDATE_CONSULTANT_WITH_CV,new Object[]{
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
        LOG.debug("Number of rows affected by update={}",update);
    }
	

    public void deleteConsultant(String consultantId) {
        LOG.info("Deleting consultant with consultantId={}",consultantId);
        int update = jdbcTemplate.update(DELETE_CONSULTANT_BY_ID, new Object[]{consultantId});
        LOG.debug("Number of rows affected by delete = {}",update);
    }
}