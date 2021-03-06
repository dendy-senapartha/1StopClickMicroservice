package com.microservice.order;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:hibernate.properties"})
@ComponentScans(value = {
        @ComponentScan("com.microservice.order.repository")
})
@RequiredArgsConstructor
public class HibernateConf {

    private final Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.microservice.order.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        String driver = Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName"));
        String url = Preconditions.checkNotNull(env.getProperty("jdbc.url"));
        String user = Preconditions.checkNotNull(env.getProperty("jdbc.user"));
        String password = Preconditions.checkNotNull(env.getProperty("jdbc.pass"));
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        String dialect = Preconditions.checkNotNull(env.getProperty("hibernate.dialect"));
        String hbm2ddl = Preconditions.checkNotNull(env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
        hibernateProperties.setProperty("hibernate.dialect", dialect);

        return hibernateProperties;
    }
}
