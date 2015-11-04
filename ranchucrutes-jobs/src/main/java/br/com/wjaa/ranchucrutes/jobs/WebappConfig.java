package br.com.wjaa.ranchucrutes.jobs;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by wagner on 12/06/15.
 */
@Configuration
@ComponentScan("br.com.wjaa")
@PropertySource(value="classpath:conf.properties")
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class WebappConfig extends WebMvcConfigurerAdapter {

    @Value("${hibernate.connection.url}")
    private String url;

    @Value("${hibernate.connection.username}")
    private String username;

    @Value("${hibernate.connection.password}")
    private String password;

    @Value("${hibernate.connection.driver_class}")
    private String driverClass;

    @Value("classpath:hibernate.cfg.xml")
    private Resource hibernateCfg;



    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dmd = new DriverManagerDataSource();
        dmd.setDriverClassName(this.driverClass);
        dmd.setUrl(this.url);
        dmd.setUsername(this.username);
        dmd.setPassword(this.password);
        return dmd;
    }

    @Bean
    public SessionFactory getSessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(this.getDataSource());

        System.out.println("############### hibernatecfg" + this.hibernateCfg);
        sessionFactory.setConfigLocation(this.hibernateCfg);
        sessionFactory.setPackagesToScan("br.com.wjaa.ranchucrutes.jobs.entity");
        try {
            sessionFactory.afterPropertiesSet();
        } catch (Exception e) {
            System.out.println("###############= " + e.getMessage());
        }
        System.out.println("###############=" + sessionFactory.getObject());
        return sessionFactory.getObject();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(){
        return new HibernateTransactionManager(this.getSessionFactory());
    }

}