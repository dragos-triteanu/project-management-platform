package ro.management.platform.context;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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
@EnableAsync
public class MVCAppContext {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("conf/errors/errorMessages");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator(){
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		return factoryBean;
	}


}
