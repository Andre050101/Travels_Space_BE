package org.lessons.java.travels_space.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // Cities
                .requestMatchers("/cities/create", "/cities/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/cities/**").hasAuthority("ADMIN")

                // TouristAttraction
                .requestMatchers("/touristAttractions/create", "/touristAttractions/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/touristAttractions/**").hasAuthority("ADMIN")

                // City Photos "/cities/**/photos", "/cities/**/photos/**"
                .requestMatchers(new AntPathRequestMatcher("/cities/**/photos")).hasAuthority("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/cities/**/photos/**")).hasAuthority("ADMIN")
                // Attractions photos
                .requestMatchers(new AntPathRequestMatcher("/touristAttractions/**/photos")).hasAuthority("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/touristAttractions/**/photos/**")).hasAuthority("ADMIN")

                .requestMatchers(HttpMethod.GET, "/cities", "/cities/**", "/touristAttractions",
                        "/touristAttractions/**")
                .permitAll()

                .requestMatchers("/**").permitAll()

                .and().formLogin()
                .and().logout().logoutSuccessUrl("/")
                .and().exceptionHandling();
        return http.build();
    }

    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        /* Questo provider userà x come servizio di recupero utenti via username */
        authProvider.setUserDetailsService(databaseUserDetailService());

        /* Questo provider userà y come password encoder */
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DatabaseUserDetailService databaseUserDetailService() {
        return new DatabaseUserDetailService();
    }
}
