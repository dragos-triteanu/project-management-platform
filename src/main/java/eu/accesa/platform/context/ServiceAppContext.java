package eu.accesa.platform.context;

import eu.accesa.platform.config.HibernateConfig;
import eu.accesa.platform.config.WebSocketConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import eu.accesa.platform.config.SecurityConfig;

@Configuration
@ComponentScan(basePackages={"eu.accesa.platform.api.*", "eu.accesa.platform.repository"})
@Import(value={HibernateConfig.class, SecurityConfig.class, WebSocketConfig.class})
public class ServiceAppContext extends WebMvcConfigurerAdapter  {
	
    @Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
