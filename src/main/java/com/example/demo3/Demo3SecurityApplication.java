package com.example.demo3;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.demo3")
public class Demo3SecurityApplication {
	
//	@Autowired
//	private DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(Demo3SecurityApplication.class, args);
	}
	
//	public EntityManagerFactory entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//		emf.setDataSource(dataSource);
//		emf.setJpaVendorAdapter(jpaVendorAdapter);
//		emf.setPackagesToScan("com.mysource.model");
//		emf.setPersistenceUnitName("default");
//		emf.afterPropertiesSet();
//		return emf.getObject();
//	}
}
