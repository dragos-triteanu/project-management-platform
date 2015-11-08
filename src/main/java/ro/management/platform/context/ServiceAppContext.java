package ro.management.platform.context;

import ro.management.platform.config.HibernateConfig;
import ro.management.platform.config.WebSocketConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ro.management.platform.config.SecurityConfig;

@Configuration
@ComponentScan(basePackages={"ro.management.platform.api.*", "ro.management.platform.repository"})
@Import(value={HibernateConfig.class, SecurityConfig.class, WebSocketConfig.class})
public class ServiceAppContext extends WebMvcConfigurerAdapter  {
	
    @Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
