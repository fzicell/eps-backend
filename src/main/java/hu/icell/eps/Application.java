package hu.icell.eps;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(
				"jdbc:mysql://172.18.0.3:3306/mysql?verifyServerCertificate=false&useSSL=false&requireSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("mysqlpass");

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(getDataSource());
		entityManagerFactory.setPackagesToScan("hu.icell.eps");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		additionalProperties.put("hibernate.show_sql", "true");
		additionalProperties.put("hibernate.hbm2ddl.auto", "none");
		entityManagerFactory.setJpaProperties(additionalProperties);

		return entityManagerFactory;
	}

}
