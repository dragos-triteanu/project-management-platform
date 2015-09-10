package eu.accesa.crowdfund.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import eu.accesa.crowdfund.config.MVCConfig;
import eu.accesa.crowdfund.config.DatasourceConfig;

@Configuration
@EnableWebMvc
@Import({MVCConfig.class,DatasourceConfig.class})
@ComponentScan("eu.accesa.crowdfund")
public class MVCAppContext {
	
	private static final int maxFileUploadSize = 1000000;
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(maxFileUploadSize);
		return multipartResolver;
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
