package com.userman.usermanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

	@Autowired
	public AuthenticationSuccessHandler CustomSuccesshandler;
	
	
	@Bean
	public UserDetailsService getuserdetailservice() {
		return new UserDetailServiceImpl();
	}
	
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/teacher/**").hasRole("TEACHER")
                .requestMatchers("/**").permitAll()
                
            )
            .formLogin(form -> form
                .loginPage("/signin")
                .loginProcessingUrl("/login")
                .successHandler( CustomSuccesshandler)
            )
            .csrf(csrf -> csrf.disable()); // Disable CSRF for testing; reconsider in production

        return http.build();
    }
    
    @Bean
    public DaoAuthenticationProvider getDaoAuthProvider() {
    	
    	DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(getuserdetailservice());
    	daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
    	
    	return daoAuthenticationProvider;
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	

    	auth.authenticationProvider(getDaoAuthProvider());
    	
    	
    }
    
    
    
    
    
}
