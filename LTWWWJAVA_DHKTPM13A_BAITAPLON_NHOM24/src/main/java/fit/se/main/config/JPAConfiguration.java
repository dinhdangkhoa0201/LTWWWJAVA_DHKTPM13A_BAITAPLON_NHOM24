package fit.se.main.config;

import org.springframework.context.annotation.Configuration;

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
