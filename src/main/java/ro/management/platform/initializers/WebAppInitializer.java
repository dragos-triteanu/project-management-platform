package ro.management.platform.initializers;

import ro.management.platform.context.ServiceAppContext;
import ro.management.platform.context.MVCAppContext;

import javax.servlet.*;

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
      return null;
  }
}
