//package mx.com.tp.smc;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@EnableConfigurationProperties(DataSourceProperties.class)
//public class AppConfig {
//
//	@Bean
//	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//	    return new JdbcTemplate(dataSource);
//	}
//
//    @Bean
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
//    
//    @Bean
//    @Primary 
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public HikariDataSource dataSource(DataSourceProperties properties) {
//        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
//                .build();
//    }
////    
////    @Bean
////	@Qualifier("viewJdbcTemplate")
////	public JdbcTemplate viewJdbcTemplate(@Qualifier("viewDataSource") DataSource dataSource) {
////	    return new JdbcTemplate(dataSource);
////	}
////
////    @Bean
////    @Qualifier("viewDataSource")
////	@ConfigurationProperties(prefix="view.datasource")
////	public DataSource viewDataSource() {
////		return DataSourceBuilder.create().build();
////	}
////    
////    @Bean
////    @Qualifier("viewDataSourceProperties")
////    @ConfigurationProperties("view.datasource")
////    public DataSourceProperties viewDataSourceProperties() {
////        return new DataSourceProperties();
////    }
////
////    @Bean
////    @ConfigurationProperties("view.datasource")
////    public HikariDataSource viewDataSource(@Qualifier("viewDataSourceProperties") DataSourceProperties properties) {
////        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
////                .build();
////    }
//
//}
