package fit.se.main.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class JPAConfiguration {
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//		factoryBean.setJpaVendorAdapter(vendorAdapter);
//
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUsername("root");
//		dataSource.setPassword("1234");
//		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=HKVTravel_Group20");
//		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//		factoryBean.setDataSource(dataSource);
//
//		Properties properties = new Properties();
//		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
//		properties.setProperty("hibernate.ddl-auto", "update");
//
//		factoryBean.setJpaProperties(properties);
//		return factoryBean;
//	}
//	
//	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
//		return new JpaTransactionManager(factory);
//	}
}
