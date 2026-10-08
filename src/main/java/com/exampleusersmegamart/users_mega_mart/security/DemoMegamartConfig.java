package com.exampleusersmegamart.users_mega_mart.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class DemoMegamartConfig {
  //add support for JDBC ... no more hardcoded users :-)
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(configurer ->
                configurer
                    .requestMatchers("/api/auth/login").permitAll()
                    .requestMatchers("/api/**").authenticated()
                    .requestMatchers("/").hasRole("USER")
                    .requestMatchers("/viewCatalog/**").hasRole("USER")
                    .requestMatchers("/addCatalog/**").hasRole("ADMIN")
                    .requestMatchers("/updateCatalog/**").hasRole("OPERATOR")
                    .requestMatchers("/showMyLoginPage").permitAll()
                    .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
            .formLogin(form -> form
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .exceptionHandling(configurer ->
                configurer.accessDeniedPage("/access-denied"));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService) {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
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
