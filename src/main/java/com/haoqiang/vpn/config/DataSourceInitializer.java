package com.haoqiang.vpn.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceInitializer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected String databaseUrl = "jdbc:postgresql://localhost:5430/VPN";

    protected String databaseUserName = "admin";

    protected String databasePassword = "password";

    protected String driverClassName="org.postgresql.ds.PGSimpleDataSource";

    private BasicDataSource createDataSource(){
        BasicDataSource dataSource  = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUserName);
        dataSource.setPassword(databasePassword);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);

        return dataSource;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        logger.debug("generating datasource bean");
        DataSource dataSource = createDataSource();
        return dataSource;
    }

//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(getDataSource());
//        factoryBean.setPackagesToScan(new String[] {"com.haoqiang.vpn.domain"});
//        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//
//        Properties props = new Properties();
//        props.put("hibernate.dialect","org.hibernate.spatial.dialect.postgis.PostgisDialect");
//        props.put("hibernate.hbm2ddl.auto","validate");
//        props.put("hibernate.connection.charset","UTF-8");
//        props.put("hibernate.show_sql","true");
//
//        factoryBean.setJpaProperties(props);
//        return factoryBean;
//    }

    @Bean(name="hibernate4AnnotatedSessionFactory")
//    @DependsOn("flyway")
    @Profile({"dev","test","staging","prod"})
    //new SessionFactory(); set set set
    public LocalSessionFactoryBean getLocalSessionFactoryBean(@Autowired DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(new String[] { "com.haoqiang.vpn.domain","com.haoqiang.vpn.repository"});
        Properties props = getDefaultHibernate();
        props.put("hibernate.show_sql","false");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }

    @Bean(name="hibernate4AnnotatedSessionFactory")
//    @DependsOn("flyway")
    @Profile({"unit"})
    public LocalSessionFactoryBean getLocalSessionFactoryBeanUnit(@Autowired DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(new String[] { "com.haoqiang.vpn.domain","com.haoqiang.vpn.dao"});
        Properties props = getDefaultHibernate();
        props.put("hibernate.show_sql","true");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }

    public Properties getDefaultHibernate(){
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
        props.put("hibernate.connection.charSet","UTF-8");
        props.put("hibernate.show_sql","true");
        return props;
    }

    @Bean
    public HibernateTransactionManager transactionManager(@Autowired SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
