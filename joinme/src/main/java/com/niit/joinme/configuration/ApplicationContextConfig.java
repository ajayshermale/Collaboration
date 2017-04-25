package com.niit.joinme.configuration;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:XE");
	    dataSource.setUsername("joinme");
	    dataSource.setPassword("sys");
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
	    properties.setProperty("hibernate.default_schema", "JOINME");
	    properties.setProperty("hibernate.show_sql", "true");
	    properties.setProperty("hibernate.format_sql", "true");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	    
	    return dataSource;
	}
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    sessionBuilder.scanPackages("com.niit.joinme.model");
	    sessionBuilder.addAnnotatedClasses(User.class);
	    sessionBuilder.addProperties(getHibernateProperties());
	    return sessionBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.hbm2ddl.auto", "update");
	    properties.put("hibernate.default_schema", "JOINME");
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.format_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	    
	    return properties;
	}
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	 
	    return transactionManager;
	}
}
