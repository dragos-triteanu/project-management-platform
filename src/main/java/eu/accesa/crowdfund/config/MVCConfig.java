package eu.accesa.crowdfund.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.ext.beans.BeansWrapper;

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Bean
	public BeansWrapper beansWrapper(){
		BeansWrapper beansWrapper = new BeansWrapper();
		beansWrapper.setExposeFields(true);
		return beansWrapper;
	}

    @Bean
    public WebContentInterceptor webContentInterceptor() {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheSeconds(0);
        interceptor.setUseExpiresHeader(true);
        interceptor.setUseCacheControlHeader(true);
        interceptor.setUseCacheControlNoStore(true);
        return interceptor;
    }


	@Bean
	public FreeMarkerConfigurer freemarkerConfig(){
		FreeMarkerConfigurer freemarkerConfig = new FreeMarkerConfigurer();
		Map<String,Object> properties = new HashMap<>();
		properties.put("objectWrapper", beansWrapper());
		freemarkerConfig.setFreemarkerVariables(properties);
		freemarkerConfig.setTemplateLoaderPath("/WEB-INF/views/freemarker");
		return freemarkerConfig;
	}
	
	@Bean
	public FreeMarkerViewResolver viewResolver(){
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setCache(true);
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".ftl");
		viewResolver.setContentType("text/html;charset=UTF-8");
		viewResolver.setRequestContextAttribute("rc");
		viewResolver.setExposeSpringMacroHelpers(true);
		viewResolver.setExposeSessionAttributes(true);
		return viewResolver;
	}
}
