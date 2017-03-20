package br.com.wjaa.ranchucrutes.ws.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by wagner on 12/06/15.
 */
@Configuration
@ComponentScan("br.com.wjaa")
@EnableWebMvc
@PropertySource(value="classpath:conf.properties")
@ImportResource(value = {"classpath:queries/ws-queries.xml"})
@EnableTransactionManagement
public class WebappConfig extends WebMvcConfigurerAdapter {

    private static Log LOG = LogFactory.getLog(WebappConfig.class);

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
        LOG.debug("m: getDataSource.init ");
        BasicDataSource dmd = new BasicDataSource();
        dmd.setDriverClassName(this.driverClass);
        dmd.setUrl(this.url);
        dmd.setUsername(this.username);
        dmd.setPassword(this.password);
        dmd.setInitialSize(10);
        dmd.setMaxActive(50);
        dmd.setMaxIdle(10);
        LOG.debug("m: getDataSource.end ");
        return dmd;
    }

    @Bean
    public SessionFactory getSessionFactory(){
        LOG.debug("m: getSessionFactory.init ");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(this.getDataSource());

        LOG.debug("m: getSessionFactory.hibernateCfg: " + this.hibernateCfg);
        sessionFactory.setConfigLocation(this.hibernateCfg);
        sessionFactory.setPackagesToScan("br.com.wjaa.ranchucrutes.ws.entity");
        try {
            sessionFactory.afterPropertiesSet();
        } catch (Exception e) {
            LOG.error("m: getSessionFactory.error:", e);
        }
        LOG.debug("m: getSessionFactory.sessionFactory.getObject: " + sessionFactory.getObject());
        return sessionFactory.getObject();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager htm = new HibernateTransactionManager(this.getSessionFactory());

        return htm;
    }

}