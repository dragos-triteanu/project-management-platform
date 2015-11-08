package ro.management.platform.context;

import ro.management.platform.config.HibernateConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ro.management.platform.config.MVCConfig;

@Configuration
@EnableWebMvc
@Import({MVCConfig.class,HibernateConfig.class})
@ComponentScan("ro.management.platform")
@PropertySource("classpath:conf/application.properties")
public class MVCAppContext {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
