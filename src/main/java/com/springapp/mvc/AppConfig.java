package com.springapp.mvc;

import com.springapp.mvc.service.DbRepositoryService;
import com.springapp.mvc.service.apiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.beans.PropertyVetoException;

/**
 * Created with IntelliJ IDEA.
 * Users: maverick
 * Date: 11/7/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = "com.springapp.mvc")
//@PropertySource(value = "classpath:/application.properties")
@EnableWebMvc
@EnableTransactionManagement

public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private DbRepositoryService dbRepositoryService;

    @Bean
    public static JdbcTemplate jdbcTemplate() throws PropertyVetoException {

        org.postgresql.ds.PGPoolingDataSource source = new org.postgresql.ds.PGPoolingDataSource();
        System.out.println("making psql jdbc template");
        //source.setDataSourceName("DataSource");
        source.setServerName("localhost");
        source.setPortNumber(5432);
        source.setDatabaseName("twitter");
        source.setUser("postgres");
        source.setPassword("RMOKP089");
        source.setMaxConnections(10);
        return new JdbcTemplate(source);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new apiInterceptor(dbRepositoryService)).addPathPatterns("/api/addPost");
    }
}