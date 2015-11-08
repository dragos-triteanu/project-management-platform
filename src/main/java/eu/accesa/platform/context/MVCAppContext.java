package eu.accesa.platform.context;

import eu.accesa.platform.config.HibernateConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import eu.accesa.platform.config.MVCConfig;

@Configuration
@EnableWebMvc
@Import({MVCConfig.class,HibernateConfig.class})
@ComponentScan("eu.accesa.platform")
@PropertySource("classpath:conf/application.properties")
public class MVCAppContext {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
