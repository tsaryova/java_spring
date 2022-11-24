package sia.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.models.User;
import sia.tacocloud.repos.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
//@EnableGlobalMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
          User user = userRepository.findByUsername(username);
          if (user != null) return user;

          throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests() //можно задать пути и шаблоны url и требования безопасности
                    .antMatchers("/design", "/orders").hasRole("USER")
                    .antMatchers("/", "/**").permitAll()

                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design")

                .and()
                    .logout()
                    .logoutSuccessUrl("/")

                // Make H2-Console non-secured; for debug purposes
                .and()
                    .csrf()
                        .ignoringAntMatchers("/h2-console/**")

                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                .and()
                    .headers()
                        .frameOptions()
                            .sameOrigin()

                .and()
                .build();
    }

    //users in memory auth example
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(new User(
//                "buzz", passwordEncoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
//        ));
//        userList.add(new User(
//                "woody", passwordEncoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
//        ));
//        return new InMemoryUserDetailsManager(userList);
//    }
}
