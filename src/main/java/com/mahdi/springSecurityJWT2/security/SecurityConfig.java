package com.mahdi.springSecurityJWT2.security;

import com.mahdi.springSecurityJWT2.service.CustomeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomeUserDetailService customeUserDetailService;

    @Autowired
    public SecurityConfig(CustomeUserDetailService customeUserDetailService) {
        this.customeUserDetailService = customeUserDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/sign_up").permitAll()
                .antMatchers("/v1/floor1/office1").hasRole("USER")
                .antMatchers("/v1/floor1/office2").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/logPage")
                .loginProcessingUrl("/authenticateTheUser").permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),customeUserDetailService));
    }
}
