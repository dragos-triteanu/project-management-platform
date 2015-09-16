package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.ConsultantCategory;
import eu.accesa.crowdfund.model.Consultant;
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
    private JdbcTemplate faqJdbcTemplate;

    public List<Consultant> retrieveConsultants(){
        LOG.debug("Retrieving list of all Consultant objects");
        Consultant con = new Consultant();
        con.setUid(UUID.randomUUID());
        con.setFirstName("Sorina");
        con.setLastName("Vasiliu");
        con.setMail("sorina@mail.com");
        con.setPhoneNumber("0741509510");
        con.setNumerOfActiveProjects(0);
        ConsultantCategory category = new ConsultantCategory();
        category.setId("a2b3c4");
        category.setName("Medicina");
        con.setCategory(category);
        List<Consultant> consultantList =new ArrayList(){};// faqJdbcTemplate.query(RETRIEVE_ALL_QAA,new Object[]{}, Mappers.questionAndAnswerMapper());\
        consultantList.add(con);
        LOG.debug("Found :"+consultantList);
        return consultantList;
    }

    public Consultant retrieveConsultantByUid(UUID uid){
        LOG.debug("Retrieving the Consultant with uid :" + uid);

        Consultant con = new Consultant();
        con.setUid(uid);
        con.setFirstName("Sorina");
        con.setLastName("Vasiliu");
        con.setMail("sorina@mail.com");
        con.setPhoneNumber("0741509510");
        con.setNumerOfActiveProjects(0);
        ConsultantCategory category = new ConsultantCategory();
        category.setId("a1b2c3");
        category.setName("Medicina");
        con.setCategory(category);

        LOG.debug("Found :"+con);
        return con;
    }
}
