package lv.javaguru.java2.config;

import lv.javaguru.java2.filter.SecurityFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;


public class SpringInitializer extends AbstractDispatcherServletInitializer{
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(SpringConfig.class);
        return applicationContext;
    }
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(SpringMvcConfig.class);
        return applicationContext;
    }
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/todo"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new SecurityFilter()};
    }

}
