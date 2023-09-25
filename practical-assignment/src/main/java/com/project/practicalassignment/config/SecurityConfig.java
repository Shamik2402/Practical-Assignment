//package com.project.practicalassignment.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin);
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        return http
//                .csrf(csrf-> csrf.disable())
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(auth-> {
////                    auth.requestMatchers("/**").hasRole("ADMIN");
//                    auth.requestMatchers("/login").permitAll();
//                    auth.anyRequest().authenticated();
////                    auth.requestMatchers("/stories").hasRole("ADMIN");
//                })
//                .formLogin(formLogin ->
//                        formLogin
//                                .usernameParameter("user")
//                                .passwordParameter("password")
//                                .defaultSuccessUrl("/stories")
//                                .successForwardUrl("/stories")
//                                .permitAll()
//                )
//                .logout(Customizer.withDefaults())
//                .build();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("GET"));
//        configuration.setAllowedHeaders(Arrays.asList("Content-Type"));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}
