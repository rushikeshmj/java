package org.moviebooking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}highlight1406").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/shows/addShow","/api/bookings/getAllBookings").hasRole("ADMIN")
                .antMatchers("/api/shows/{showId}/book").permitAll() // Permit access to booking endpoint without authentication
                .antMatchers("/api/shows/getAllShows").permitAll() // Permit access to /api/shows/getAllShows without authentication
                .and()
                .httpBasic();
    }
}

