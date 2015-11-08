package eu.accesa.platform.config;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:conf/datasource.properties")
@EnableTransactionManagement
public class DatasourceConfig {
	
	@Value("${crowdfunding.datasource.url}")
	private String dbUrl;
	
	@Value("${crowdfunding.datasource.username}")
	private String dbUsername;
	
	@Value("${crowdfunding.datasource.password}")
	private String dbPassword;
	
	@Value("${crowdfunding.datasource.class}")
	private String dbClassName;
	
	@Value("${crowdfunding.datasource.schema}")
	private String dbSchema;

	@Value("${crowdfunding.datasource.recreateDB}")
	private boolean recreateDB;

	@Bean(name="crowdfundingJdbcTemplate")
	public JdbcTemplate crowdfundingJdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

	@Bean(name="dataSource")
	public DataSource dataSource(){
		DataSource dataSource = datasourceCreator();
		if(recreateDB) {
			DatabasePopulatorUtils.execute(datasourcePopulator(), dataSource);
		}

		return dataSource;
	}
	
	private DatabasePopulator datasourcePopulator(){
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(false);
		databasePopulator.addScript(new ClassPathResource(dbSchema));
		return databasePopulator;
	}

	@SuppressWarnings("unchecked")
	private SimpleDriverDataSource datasourceCreator(){
		SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
		simpleDriverDataSource.setUrl(dbUrl);
		simpleDriverDataSource.setUsername(dbUsername);
		simpleDriverDataSource.setPassword(dbPassword);
		try{
			simpleDriverDataSource.setDriverClass((Class<? extends Driver>) Class.forName(dbClassName));
		}catch(Exception e){
			
		}
		return simpleDriverDataSource;
	}
}
