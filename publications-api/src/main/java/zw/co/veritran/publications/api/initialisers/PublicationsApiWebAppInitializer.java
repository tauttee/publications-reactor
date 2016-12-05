package zw.co.veritran.publications.api.initialisers;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import zw.co.veritran.publications.config.api.SecurityConfig;
import zw.co.veritran.publications.config.api.PublicationsApiWebConfig;
import zw.co.veritran.publications.config.business.ServiceRootConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class PublicationsApiWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ServiceRootConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {PublicationsApiWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "db-dev");
    }

}
