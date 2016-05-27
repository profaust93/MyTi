package lv.javaguru.java2.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.database.hibernate")
@EnableTransactionManagement
public class TestSpringConfig {

    private static final String DATABASE_PROPERTIES_FILE = "database.properties";

    @Bean
    public static PropertySourcesPlaceholderConfigurer prodPropertiesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[] {
                new ClassPathResource(DATABASE_PROPERTIES_FILE)
        };
        p.setLocations(resourceLocations);
        return p;
    }

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql}") boolean showSql,
            @Value("${hibernate.format_sql}") boolean formatSql,
            @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl) {

        return new SpringConfig().hibernateProperties(dialect, showSql, formatSql, hbm2ddl);
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource(
            @Value("${driverClass}") String driver,
            @Value("${dbUrl}") String url,
            @Value("${database.userName}") String user,
            @Value("${password}") String password) throws PropertyVetoException {

        return new SpringConfig().dataSource(driver, url, user, password);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource,
                                         @Value("${hibernate.packagesToScan}") String packagesToScan,
                                         @Qualifier("hibernateProperties") Properties properties) throws Exception {
        return new SpringConfig().sessionFactory(dataSource, packagesToScan, properties);
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
