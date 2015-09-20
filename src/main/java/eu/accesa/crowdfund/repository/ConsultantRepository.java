package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.ConsultantSpeciality;
import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.utils.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Consultant con = new Consultant();
        con.setId(UUIDGenerator.generateUUID());
        con.setFirstName("Sorina");
        con.setLastName("Vasiliu");
        con.setMail("sorina@mail.com");
        con.setPhoneNumber("0741509510");
        ConsultantSpeciality category = new ConsultantSpeciality();
        category.setSpecialityId("a2b3c4");
        category.setSpecialityName("Medicina");
        con.setSpeciality(category);
        List<Consultant> consultantList =new ArrayList(){};// jdbcTemplate.query(RETRIEVE_ALL_QAA,new Object[]{}, Mappers.questionAndAnswerMapper());\
        consultantList.add(con);
        con.setNumberOfActiveProjects(0);
        LOG.debug("Found :"+consultantList);
        return consultantList;
    }

    public Consultant retrieveConsultantByUid(UUID uid){
        LOG.debug("Retrieving the Consultant with uid :" + uid);

        Consultant con = new Consultant();
        con.setId("121fd12f12");
        con.setFirstName("Sorina");
        con.setLastName("Vasiliu");
        con.setMail("sorina@mail.com");
        con.setPhoneNumber("0741509510");
        ConsultantSpeciality category = new ConsultantSpeciality();
        category.setSpecialityId("a1b2c3");
        category.setSpecialityName("Medicina");
        con.setSpecialityId("123");
        con.setNumberOfActiveProjects(0);

        LOG.debug("Found :"+con);
        return con;
    }

    /**
     * Method for inserting a {@link eu.accesa.crowdfund.model.Consultant} into the 'consultants' SQL schema.
     * @param consultant the to be added {@link eu.accesa.crowdfund.model.Consultant}.
     */
    public void insertConsultant(final Consultant consultant){
        LOG.info("Inserting consultant with consultantId={}",consultant.getId());
        int update = jdbcTemplate.update(JDBCQueries.INSERT_CONSULTANT, new Object[]{consultant.getId(),
                                                                                     consultant.getLastName(),
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
}