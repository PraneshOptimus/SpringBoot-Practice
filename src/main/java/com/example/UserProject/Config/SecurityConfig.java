package com.example.UserProject.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests( authz -> authz
                .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                .requestMatchers("/api/users").authenticated()
                .anyRequest().permitAll())

                .formLogin(form -> form.permitAll().defaultSuccessUrl("/dashboard"));
        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder pass){
        UserDetails user = User.withUsername("ASH").password(pass.encode("ASH123")).roles("USER").build();
        UserDetails admin = User.withUsername("ADMIN").password(pass.encode("ADMIN123")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
