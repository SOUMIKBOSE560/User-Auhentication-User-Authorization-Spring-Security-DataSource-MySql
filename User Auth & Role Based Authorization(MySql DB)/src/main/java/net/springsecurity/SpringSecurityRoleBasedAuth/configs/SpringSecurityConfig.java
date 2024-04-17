package net.springsecurity.SpringSecurityRoleBasedAuth.configs;

import net.springsecurity.SpringSecurityRoleBasedAuth.services.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
//FOR PREAUTHORIZE METHOD SECURITY
@EnableMethodSecurity
public class SpringSecurityConfig {

    //CREATE USERS
    @Bean
    public UserDetailsService userDetailsService() {
//        User admin = (User) User.builder()
//                .username("Admin")
//                .password(encoder.encode("Admin@123"))
//                .roles("ADMIN")
//                .build();
//        User user = (User) User.builder()
//                .username("Soumik")
//                .password(encoder.encode("Soumik@1998"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
        return new UserInfoUserDetailsService();

    }

    //CONFIGURE HTTP SECURITY
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/public/**","/public/delete/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and().formLogin().and().build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // AUTHENTICATION PROVIDER CLASS FOR USER CREDENTIALS VERIFICATION & IF SUCCESSFUL THEN
    // IT CREATES A AUTHENTICATION OBJECT

    @Bean
    public AuthenticationProvider provider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
