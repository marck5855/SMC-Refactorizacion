package mx.com.tp.smc.conf.web;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InitConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebInitializer.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {};
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
    	Filter [] singleton = { new ProjectFilter()};
    	return singleton;
    } 
}