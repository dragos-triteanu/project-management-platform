package eu.accesa.crowdfund.initializers;

import java.nio.charset.StandardCharsets;
import eu.accesa.crowdfund.context.ServiceAppContext;
import eu.accesa.crowdfund.context.MVCAppContext;

import javax.servlet.*;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setInitParameter("dispatchOptionsRequest", "true");
    registration.setAsyncSupported(true);
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] { ServiceAppContext.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { MVCAppContext.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
    return new Filter[] { characterEncodingFilter };
  }
}
