package com.example.jan2023.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //providing Authentication to the users based on username and password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("user")).roles("USER").and()
                .withUser("admin").password(encoder.encode("admin")).roles("USER","ADMIN","SECURITY","NETWORK").and()
                .withUser("jones").password(encoder.encode("password")).roles("ADMIN").and()
                .withUser("clark").password(encoder.encode("password")).roles("SECURITY").and()
                .withUser("scott").password(encoder.encode("password")).roles("NETWORK");
    }

    //providing Authorization based on the roles

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/emp/get/**").hasRole("USER")
                .antMatchers("/emp/all").permitAll()
                .antMatchers("/emp/add").hasRole("ADMIN")
                .and()
                .httpBasic();
    }
}
