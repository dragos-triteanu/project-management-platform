package eu.accesa.crowdfund.context;

import eu.accesa.crowdfund.config.WebSocketConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import eu.accesa.crowdfund.config.DatasourceConfig;
import eu.accesa.crowdfund.config.SecurityConfig;

@Configuration
@ComponentScan(basePackages={"eu.accesa.crowdfund.api.*","eu.accesa.crowdfund.repository"})
@Import(value={DatasourceConfig.class, SecurityConfig.class, WebSocketConfig.class})
public class ServiceAppContext extends WebMvcConfigurerAdapter  {
	
    @Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
