package com.stankin.machine.core.config;

import com.stankin.machine.core.service.JWTFilter;
import com.stankin.machine.core.service.domain.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate")
                .permitAll()
                .and().authorizeRequests().antMatchers("/tech/**").hasAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/detail/**").hasAnyAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/draft/**").hasAnyAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/employee/**").hasAnyAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/report/**").hasAnyAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/location/**").hasAnyAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/machine/**").hasAnyAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/plan/**").hasAnyAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/loss/**").hasAnyAuthority("ROLE_ADMIN")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
