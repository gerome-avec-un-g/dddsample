package fr.geromeavecung.dddsample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationInMemory extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("buzz")
                .password("infinity")
                .authorities("ROLE_USER")
                .and()
                .withUser("woody")
                .password("bullseye")
                .authorities("ROLE_USER");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // fixing exception There is no PasswordEncoder mapped for the id "null"
        // WARNING : unsafe use BCryptPasswordEncoder instead
        // adding .password("{noop}password") to auth.inMemoryAuthentication()
        // in previous method doesn't work
        return NoOpPasswordEncoder.getInstance();
    }


}
