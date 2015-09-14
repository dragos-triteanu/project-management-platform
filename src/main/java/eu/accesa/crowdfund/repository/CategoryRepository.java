package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.Category;
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
public class CategoryRepository {

    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name = "crowdfundingJdbcTemplate")
    private JdbcTemplate faqJdbcTemplate;



}

