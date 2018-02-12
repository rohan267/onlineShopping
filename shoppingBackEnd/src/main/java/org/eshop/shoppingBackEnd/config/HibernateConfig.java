package org.eshop.shoppingBackEnd.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages= {"org.eshop.shoppingBackEnd.dto"})
@EnableTransactionManagement
public class HibernateConfig {

	private final static String DATABSAE_URL="jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String DATABSAE_DRIVER="org.h2.Driver";
	private final static String DATABSAE_DIALECT="org.hibernate.dialect.H2Dialect";
	private final static String DATABSAE_USERNAME="sa";
	private final static String DATABSAE_PASSWORD="";

	@Bean("dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DATABSAE_DRIVER);
		dataSource.setUrl(DATABSAE_URL);
		dataSource.setUsername(DATABSAE_USERNAME);
		dataSource.setPassword(DATABSAE_PASSWORD);
		
		return dataSource;
	}
	
	//Session Factory
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("org.eshop.shoppingBackEnd.dto");
		return builder.buildSessionFactory();
	}

	// Hibernate properties
	private Properties getHibernateProperties() {
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", DATABSAE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
//		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.hbm2ddl.auto", "update");
//
		return properties;
	}
	
	// 
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		
		return transactionManager;
	}
}
