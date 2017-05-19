package br.com.wjaa.ranchucrutes.ws.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

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
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(this.driverClass);
        } catch (PropertyVetoException e) {
            LOG.error("Erro ao pegar o driver class do datasource", e);
        }
        dataSource.setJdbcUrl(this.url);
        dataSource.setUser(this.username);
        dataSource.setPassword(this.password);
        dataSource.setInitialPoolSize(5);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxPoolSize(10);
	    dataSource.setAutomaticTestTable("C3P0_TEST_TABLE");
        dataSource.setTestConnectionOnCheckin(true);
	    dataSource.setIdleConnectionTestPeriod(60);
        LOG.debug("m: getDataSource.end ");
        return dataSource;
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}