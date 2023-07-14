//package com.gdsc.CGLH.config;
//
//import com.gdsc.CGLH.config.filter.CustomAuthFaliureHandler;
//import com.gdsc.CGLH.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private CustomAuthFaliureHandler customAuthFaliureHandler;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf().disable() // CSRF 비활성화
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("loginId")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/home", true)
//                .failureHandler(customAuthFaliureHandler)
//                .permitAll()
//                .and()
//                .logout().permitAll();
//
//
//        return http.build();
//    }
//}
