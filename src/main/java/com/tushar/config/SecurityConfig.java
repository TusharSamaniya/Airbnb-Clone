package com.tushar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())  // Disable CSRF for API calls
	            .authorizeHttpRequests(auth -> auth
	                // Allow registration and login APIs
	                .requestMatchers("/api/users/register", "/api/users/login").permitAll()
	                
	                // Allow ALL static resources: HTML, CSS, JS, images in /static/
	                .requestMatchers("/", "/**.html", "/css/**", "/js/**", "/images/**").permitAll()
	                
	                // Allow direct access to any .html file in root (like register.html, login.html)
	                .requestMatchers("/register.html", "/login.html", "/index.html", "/dashboard.html").permitAll()
	                
	                .requestMatchers("/api/properties/**").authenticated()
	                
	                // Everything else requires authentication
	                .anyRequest().authenticated()
	            )
	            .sessionManagement(session -> session
	                .maximumSessions(1)
	                .expiredUrl("/login.html?expired")
	            )
	            .formLogin(form -> form.disable())     // We use custom login, not Spring's default
	            .httpBasic(basic -> basic.disable());

	        return http.build();
	    }

}
