package com.telcel.gsrh.solicitudcurso.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence.properties", "classpath:sftp.properties" })
@ComponentScan(basePackages = {
    "com.telcel.gsrh.solicitudcurso"
})
public class PersistenceConfiguration {

    @Autowired
    private Environment env;
    
    private static final String MODEL_PACKAGE = "com.telcel.gsrh.solicitudcurso.model";
    
    @Bean(name="dataSource")
    public BasicDataSource datasource() {
        final BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        datasource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        datasource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        datasource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));
        datasource.setMaxActive(Integer.parseInt(Preconditions.checkNotNull(env.getProperty("pool.maxactivesessions"))));
        datasource.setMaxIdle(Integer.parseInt(Preconditions.checkNotNull(env.getProperty("pool.maxidle"))));
        
        return datasource;
    }
    
    @Bean(name="sessionFactory")
    @Autowired
    public LocalSessionFactoryBean sessionfactory(final DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(MODEL_PACKAGE);
        sessionFactory.setHibernateProperties(hibernateProperties());
        
        return sessionFactory;
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionmanager(final SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        
        return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptiontranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("show_sql", env.getProperty("hibernate.showsql"));
        properties.setProperty("format_sql", env.getProperty("hibernate.formatsql"));
        properties.setProperty("use_sql_comments", env.getProperty("hibernate.usesqlcomments"));
        
        return properties;
    }
}
