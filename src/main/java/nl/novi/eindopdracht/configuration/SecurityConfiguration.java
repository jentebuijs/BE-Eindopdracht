package nl.novi.eindopdracht.configuration;

import nl.novi.eindopdracht.filters.JwtRequestFilter;
import nl.novi.eindopdracht.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final JwtService jwtService;

    public SecurityConfiguration(DataSource dataSource, JwtService jwtService) {
        this.dataSource = dataSource;
        this.jwtService = jwtService;
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder amb) throws Exception {
        amb
                .jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select user_username, authority_id from user_authorities where user_username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/messages/admin").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/profiles").hasAuthority("ADMIN")
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
