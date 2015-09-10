package eu.accesa.crowdfund.context;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import eu.accesa.crowdfund.config.DatasourceConfig;
import eu.accesa.crowdfund.config.SecurityConfig;

@Configuration
@ComponentScan(basePackages={"eu.accesa.services","eu.accesa.crowdfund.repository","eu.accesa.crowdfund.security"})
@Import(value={DatasourceConfig.class,SecurityConfig.class})
public class ServiceAppContext extends WebMvcConfigurerAdapter  {
	
	@Override
    public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
        converters.add(new BufferedImageHttpMessageConverter());
    }
    
    @Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
