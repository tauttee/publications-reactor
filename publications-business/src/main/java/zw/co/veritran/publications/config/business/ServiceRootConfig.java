package zw.co.veritran.publications.config.business;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import zw.co.veritran.publications.config.dao.DataConfiguration;
import zw.co.veritran.publications.config.utils.UtilsConfig;

/**
 * Created by tyamakura on 29/11/2016.
 */
@Configuration
@EnableTransactionManagement
@Import({UtilsConfig.class, DataConfiguration.class, BusinessAspectConfiguration.class})
@ComponentScan(basePackages = { "zw.co.veritran.publications.business.services"})
public class ServiceRootConfig {
}
