package TicketPackage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // This annotation indicates that the class can be used by the Spring IoC container as a source of bean definitions
@EnableWebSecurity // This annotation and the WebSecurityConfigurerAdapter work together to provide web based security
public class SecurityConfiguration{
    @Bean // This annotation tells Spring that this method will return an object that should be registered as a bean in the Spring application context
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults()) // Enable CORS and CSRF
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                .authorizeRequests() // Allow configuration of security based on HttpServletRequest
                .requestMatchers("/api/auth/**").permitAll() // Match these requests and permit all
                .requestMatchers("/api/settings/**").permitAll() // Match these requests and permit all
                .requestMatchers("/resources/**").permitAll() // Match these requests and permit all
                .requestMatchers("/swagger-ui/**").permitAll() // Match these requests and permit all
                .requestMatchers("/v3/api-docs/**").permitAll() // Match these requests and permit all
                .anyRequest().authenticated() // Any other request must be authenticated
                .and()
                .httpBasic(withDefaults()); // Enable HTTP Basic Authentication
        return http.build(); // Build the SecurityFilterChain
    }

    @Bean // This annotation tells Spring that this method will return an object that should be registered as a bean in the Spring application context
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder() // Create a user with the default password encoder
                .username("user") // Set the username
                .password("password") // Set the password
                .roles("USER") // Set the role
                .build(); // Build the UserDetails
        return new InMemoryUserDetailsManager(user); // Return an InMemoryUserDetailsManager with the UserDetails
    }
}
