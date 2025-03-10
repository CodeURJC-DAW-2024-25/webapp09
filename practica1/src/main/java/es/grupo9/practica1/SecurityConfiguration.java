package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public RepositoryUserDetailsService userDetailService;


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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());

        http
            .authorizeHttpRequests(auth -> auth
                // Public endpoints (accessible to everyone, registered or not)
                .requestMatchers("/", "/index", "/about", "/contact", "/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()

                .requestMatchers("/room").permitAll() // ✅ Anyone can see the rooms page
                .requestMatchers("/room/{code}", "/roomDetails").authenticated()

                // Allow POST requests to /addUser for unauthenticated users
                .requestMatchers(HttpMethod.POST, "/addUser").permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()

                // Allow POST requests to /addHotel for authenticated users with USER or ADMIN role
                .requestMatchers(HttpMethod.POST, "/addHotel").hasAnyRole("USER", "ADMIN")
                // Restricted endpoints
                .requestMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN can access /admin
                .requestMatchers("/newHotel", "/booking","/profile").authenticated() // Only authenticated users (registered or admin) can access /newHotel and /booking
                .anyRequest().permitAll() // Allow all other endpoints by default
            )
            .formLogin(form -> form
                .loginPage("/login") // Custom login page
                .defaultSuccessUrl("/") // Redirect to home page after successful login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // Redirect to home page after logout
                .logoutSuccessUrl("/") // Redirect to home page after logout
                .invalidateHttpSession(true) // Invalidate the session
                .deleteCookies("JSESSIONID") // Delete cookies (if any)

                .permitAll()
            )
            .userDetailsService(userDetailService); // Use custom UserDetailsService
            // Disable CSRF at the moment
            //http.csrf(csrf -> csrf.disable());
        return http.build();
    }

}
