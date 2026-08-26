package com.exampleusersmegamart.users_mega_mart.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoMegamartConfig {
  //add support for JDBC ... no more hardcoded users :-)
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                         configurer
                                 .requestMatchers("/").hasRole("USER")
                                 .requestMatchers("/viewCatalog/**").hasRole("USER")
                                 .requestMatchers("/addCatalog/**").hasRole("ADMIN")
                                 .requestMatchers("/updateCatalog/**").hasRole("OPERATOR")

                        .requestMatchers("/showMyLoginPage").permitAll()
                        .anyRequest()
                        .authenticated()
        )
                .formLogin(form ->form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));
        return http.build();
    }

//@Bean
//public InMemoryUserDetailsManager userDetailsManager(){
//    UserDetails Supriya = User.builder()
//            .username("supriya")
//            .password("{noop}test123")
//            .roles("USER")
//            .build();
//
//    UserDetails Amit = User.builder()
//            .username("amit")
//            .password("{noop}test123")
//            .roles("OPERATOR","USER")
//            .build();
//
//    UserDetails Anand = User.builder()
//            .username("anand")
//            .password("{noop}test123")
//            .roles("ADMIN","OPERATOR","USER")
//            .build();
//
//    return new InMemoryUserDetailsManager(Supriya, Amit, Anand);

}
