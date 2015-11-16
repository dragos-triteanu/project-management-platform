package ro.management.platform.context;

import org.springframework.scheduling.annotation.EnableScheduling;
import ro.management.platform.config.AsyncConfig;
import ro.management.platform.config.MailingConfig;
import ro.management.platform.config.HibernateConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ro.management.platform.config.MVCConfig;

@Configuration
@EnableWebMvc
@EnableScheduling
@Import({MVCConfig.class,HibernateConfig.class, MailingConfig.class, AsyncConfig.class})
@ComponentScan("ro.management.platform.*")
@PropertySource("classpath:conf/application.properties")
public class MVCAppContext {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
