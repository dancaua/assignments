//package com.abc.empapp.security;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication().withUser("mike").password("mike123").roles("admin", "employee");
//        auth.inMemoryAuthentication().withUser("jenny").password("jenny123").roles("employee");
//        auth.inMemoryAuthentication().withUser("min").password("min").roles("admin");
//        System.out.println("Internal users loaded");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/flights/all").permitAll()
//                .antMatchers("/admin").hasRole("admin")
//                .antMatchers("/flights/type").hasRole("employee")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // avoid
//    }
//
//
//}
