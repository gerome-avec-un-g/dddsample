package fr.geromeavecung.dddsample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        // required to fix exception There is no PasswordEncoder mapped for the id "null"
        // WARNING : unsafe use BCryptPasswordEncoder instead
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO in controller ?
        // TODO weird mix of role and authority ?
        http.authorizeRequests()
                // resources should be permitted to all
                // because successful login redirects to last restricted url
                .antMatchers("/styles/**", "/images/**").permitAll()
                .antMatchers("/**").hasAuthority("ROLE_USER")
                // for custom login page
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/");

    }


}
