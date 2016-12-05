package zw.co.veritran.publications.config.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by tyamakura on 29/11/2016.
 */
@Configuration
@PropertySource({"classpath:persistance.properties", "classpath:publications-jdbc.properties"})
@EnableJpaRepositories("zw.co.veritran.publications.repository")
@Import({DataSourceConfiguration.class, EntityManagerConfiguration.class, TransactionManagementConfiguration.class})
public class DataConfiguration {
}
