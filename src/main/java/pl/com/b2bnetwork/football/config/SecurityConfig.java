package pl.com.b2bnetwork.football.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.com.b2bnetwork.football.providers.UserDatabaseAuthenticationProvider;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDatabaseAuthenticationProvider userDatabaseAuthenticationProvider;

    @Autowired
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth
                .authenticationProvider(userDatabaseAuthenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/js/**", "/css/**").permitAll()
                .antMatchers("/secured/admin/**").hasRole("ADMIN")
                .antMatchers("/secured/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/secured/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/secured/user/leagues/showLeagues")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and()
                .logout().logoutSuccessUrl("/login")
                .permitAll();
    }
}
