package zw.co.veritran.publications.config.business;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by tyamakura on 29/11/2016.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "zw.co.veritran.publications.business.aspects"})
public class BusinessAspectConfiguration {
}
