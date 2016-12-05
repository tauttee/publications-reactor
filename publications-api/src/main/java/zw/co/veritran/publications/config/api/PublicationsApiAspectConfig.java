package zw.co.veritran.publications.config.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by tyamakura on 30/11/2016.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"zw.co.veritran.publications.api.aspects"})
public class PublicationsApiAspectConfig {
}
