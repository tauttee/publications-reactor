package zw.co.veritran.publications.config.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by tyamakura on 30/11/2016.
 */
@Configuration
public class UtilsConfig {
    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
