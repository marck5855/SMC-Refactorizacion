package mx.com.tp.smc;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@SpringBootApplication(scanBasePackages= {"mx.com.tp.smc"})
public class TpSmcWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TpSmcWebApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TpSmcWebApplication.class, args);
	}  
	
}