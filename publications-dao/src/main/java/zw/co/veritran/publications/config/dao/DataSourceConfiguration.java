package zw.co.veritran.publications.config.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by tyamakura on 29/11/2016.
 */
@Configuration
public class DataSourceConfiguration {

    @Resource
    private Environment env;

    @Profile("db-dev")
    @Bean
    @Qualifier("publicationsDataSource")
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("pbc.jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("pbc.jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("pbc.jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("pbc.jdbc.password"));
        return dataSource;
    }
}
