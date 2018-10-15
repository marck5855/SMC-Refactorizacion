package mx.com.tp.smc.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan({"mx.com.tp.smc"})
public class MainConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean(name="HelloWorld")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    @Bean
    public ResourceBundleMessageSource messageSource(){
    	ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
    	resourceBundleMessageSource.setBasenames(new String[] {"validation"});
    	return resourceBundleMessageSource;
    }
    
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver(){
    	CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    	commonsMultipartResolver.setMaxUploadSizePerFile(20*1024*1024);
    	
    	return commonsMultipartResolver;
    }
    
}