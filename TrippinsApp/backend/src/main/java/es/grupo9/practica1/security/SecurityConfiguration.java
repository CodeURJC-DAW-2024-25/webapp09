package es.grupo9.practica1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.grupo9.practica1.security.jwt.JwtRequestFilter;
import es.grupo9.practica1.service.RepositoryUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public RepositoryUserDetailsService userDetailService;


    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailService)
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Public endpoints (accessible to everyone, registered or not)
                .requestMatchers("/", "/index", "/about", "/contact", "/register", "/error", "/v1/api/login/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/room", "/v1/api/query").permitAll() // ✅ Anyone can see the rooms page
                .requestMatchers("/room/{code}", "/roomDetails").authenticated()
                .requestMatchers("/spa/**").permitAll()

                // Allow POST requests to /addUser for unauthenticated users
                .requestMatchers(HttpMethod.POST, "/addUser").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/api/login/forms").permitAll()

                // Allow POST requests to /addHotel for authenticated users with USER or ADMIN role
                .requestMatchers(HttpMethod.POST, "/addHotel").hasAnyRole("USER", "ADMIN")
                // Restricted endpoints
                .requestMatchers("/v1/api/users/**","/v1/api/reviews/**","/v1/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/admin/**", "/admin").hasRole("ADMIN") // Only ADMIN can access /admin
                .requestMatchers("/newHotel", "/booking", "/profile","/api/houses/**").authenticated() // Only authenticated users (registered or admin) can access /newHotel and /booking
                .anyRequest().permitAll() // Allow all other endpoints by default
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable sessions (stateless)
            )
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
            .csrf(csrf -> csrf.disable()); // Disable CSRF (not needed for stateless APIs)

        return http.build();
        
    }
    

}
