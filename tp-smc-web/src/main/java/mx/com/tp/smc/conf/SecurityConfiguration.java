package mx.com.tp.smc.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.context.annotation.Scope;
//import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import mx.com.tp.smc.entity.Role;
import mx.com.tp.smc.entity.TrolEntity;
import mx.com.tp.smc.entity.TrolEntity2;
import mx.com.tp.smc.manager.RolManager;
import mx.com.tp.smc.repository.CmenuRepository;
import mx.com.tp.smc.request.UserRequest;
import mx.com.tp.smc.response.RoleList;
import mx.com.tp.smc.response.UsersList;
import mx.com.tp.smc.service.RolService;
import mx.com.tp.smc.service.RolServiceBack;
import mx.com.tp.smc.service.TokenService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private RolManager rolManager;
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	private RolService rolService;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private DataSource dataSourse; 
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
//	@Autowired //anotacion que inyacta las dependencias a un objeto (build)
//	public void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception //metodo que recibe una instancia de AuthenticationManagerBuilder 
//	{
//		auth.jdbcAuthentication().dataSource(dataSourse).passwordEncoder(passwordEncoder()) //n el metodo passwordEncoder le pasamis la instancia passwordEncoder
//		.usersByUsernameQuery(Constant.SQL_configureGlobalSecurity1)//consulta sql para la autenticacion (ligin)
//		.authoritiesByUsernameQuery(Constant.SQL_configureGlobalSecurity2);//consulta para obtener los roles 
//	}
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSourse).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery(Constant.SQL_configureGlobalSecurity1)
				.authoritiesByUsernameQuery(Constant.SQL_configureGlobalSecurity2);

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
			try {
				RoleList responseManager = rolManager.returnAllRoles();

			Role rol = new Role();
			String rolStr = "";
			String cadenaRol = "";
			
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());

				if ((Boolean) responseManager.getSuccess()) {

					for (mx.com.tp.smc.response.Role role : responseManager.getListRole()) {

						if (role.getRolRole() != null) {

						System.out.println("SI ENTRO AL IF rolStr Marcko");

						rol.setRolRole((String) role.getRolRole());
					}

					int i = 0;
					rolStr = rolStr + ("'" + rol.getRolRole() + (i == responseManager.getListRole().size() - 1 ? "'" : "',"));
//					i++;
				}

				cadenaRol = "hasAnyRole(" + rolStr + ")";

				System.out.println("****CADENA COMPLETA***** " + cadenaRol);

				// hasAnyRole('ROLE_USER_MASTER,ROLE_USER_ADMINISTRATOR,ROLE_USER_QUALITY,ROLE_USER_OPERATION,ROLE_USER_CLIENTE,ROLE_USER_LOCAL,ROLE_PRUEBA,ROLE_PRUEBA_TELLO,ROLE_SAGARPA,ROL_USUARIO_PRUEBASQA,ROL_USUARIO_PRUEBAS')

				http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/admin/**").access(cadenaRol)
						.antMatchers("/operator/**", "/home/**").access(cadenaRol).and().formLogin().loginPage("/login")
						.successHandler(customSuccessHandler).usernameParameter("username")
						.passwordParameter("password").and().exceptionHandling()
						.accessDeniedPage("/accessdenied").and().anonymous().disable();

				//Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
				//		auth.getCredentials(), updatedAuthorities);

				//SecurityContextHolder.getContext().setAuthentication(newAuth);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * http.authorizeRequests() .antMatchers("/").permitAll()
		 * .antMatchers("/admin/**").access(
		 * "hasAnyRole('ROLE_TPE','ROLE_USER_MASTER','ROLE_USER_LOCAL','ROLE_USER_ADMINISTRATOR','ROLE_USER_QUALITY','ROLE_USER_OPERATION','ROLE_USER_CLIENTE','ROL_PRUEBAII')")
		 * .antMatchers("/operator/**", "/home/**").access(
		 * "hasAnyRole('ROLE_TPE','ROLE_USER_MASTER','ROLE_USER_LOCAL','ROLE_USER_ADMINISTRATOR','ROLE_USER_QUALITY','ROLE_USER_OPERATION','ROLE_USER_CLIENTE','ROL_PRUEBAII')")
		 * .and().formLogin().loginPage("/login").successHandler(
		 * customSuccessHandler)
		 * .usernameParameter("username").passwordParameter("password")
		 * .and().csrf()
		 * .and().exceptionHandling().accessDeniedPage("/accessdenied")
		 * .and().anonymous().disable();
		 */

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public Properties jpaProperties() {
//		Properties jpaProperties = new Properties();
//		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
//		jpaProperties.put("hibernate.show_sql", "true");
//		jpaProperties.put("hibernate.format_sql", "true");
//		return jpaProperties;
//	}

//	@Bean
//	public DataSource dataSource() throws IOException {
//
//		Properties p = new Properties();
//		InputStream input = null;
//
//		input = getClass().getClassLoader().getResourceAsStream("application.properties");
//		p.load(input);
//
//		DriverManagerDataSource manager = new DriverManagerDataSource();
//		manager.setDriverClassName(p.getProperty("spring.datasource.driver-class-name"));
//		manager.setUrl(p.getProperty("spring.datasource.url"));
//		manager.setUsername(p.getProperty("spring.datasource.username"));
//		manager.setPassword(p.getProperty("spring.datasource.password"));
////		manager.setConnectionProperties(jpaProperties());
//		return manager;
//	}

}