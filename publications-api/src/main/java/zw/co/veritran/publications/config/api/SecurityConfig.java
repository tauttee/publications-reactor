package zw.co.veritran.publications.config.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by tyamakura on 30/11/2016.
 */
@Configuration
@EnableWebSecurity
@ImportResource(locations = "classpath:spring-security-config.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String REALM = "VERITRAN_REALM";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("veritran").password("veritran#").roles("SYSTEM_ACCOUNT");
        auth.userDetailsService(userDetailsService);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/rest/**").hasRole("SYSTEM_ACCOUNT")
                .and().httpBasic().realmName(REALM).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }



}
