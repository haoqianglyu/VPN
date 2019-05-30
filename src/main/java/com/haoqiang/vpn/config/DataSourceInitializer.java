package com.haoqiang.vpn.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceInitializer {

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
        DataSource dataSource = createDataSource();
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan(new String[] {"com.haoqiang.vpn.domain"});
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties props = new Properties();
        props.put("hibernate.dialect","org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto","validate");
        props.put("hibernate.connection.charset","UTF-8");
        props.put("hibernate.show_sql","true");

        factoryBean.setJpaProperties(props);
        return factoryBean;
    }
}
