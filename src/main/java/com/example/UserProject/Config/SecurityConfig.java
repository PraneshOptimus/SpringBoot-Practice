package com.example.UserProject.Config;


import com.example.UserProject.Services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                .requestMatchers("/api/users").authenticated()
                .anyRequest().permitAll()
        )
                .formLogin( form -> form.permitAll().defaultSuccessUrl("/dashboard"))
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetails(){
//        UserDetails user = User.withUsername("ASH").password(pass.encode("ASH123")).roles("USER").build();
//        UserDetails admin = User.withUsername("Pranesh").password(pass.encode("admin123")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(admin, user);

        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider DaoAuthenticator(){
        DaoAuthenticationProvider dao =  new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetails());
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
