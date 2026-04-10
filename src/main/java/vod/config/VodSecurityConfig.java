package vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class VodSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/webapi/guitars").hasAuthority("USER_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/webapi/stores").hasAuthority("USER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/webapi/guitars/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        /*UserDetails dbuser1 = User
                .withUsername("dbuser1")
                .password("dbuser1")
                .authorities("ADMIN")
                .build();
        UserDetails dbuser2 = User
                .withUsername("dbuser2")
                .password("dbuser2")
                .authorities("REGULAR")
                .build();*/

        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager();
        detailsManager.setDataSource(dataSource);
        detailsManager.setUsersByUsernameQuery("select username, password, 'true' from user where username=?");
        detailsManager.setAuthoritiesByUsernameQuery("select username, role from role where username=?");
        return detailsManager;

//        return new InMemoryUserDetailsManager(dbuser1, dbuser2);
    }

}
