package org.com.ehotel.configuration.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.configuration.EndpointConfig;
import org.com.ehotel.enums.AppRoles;
import org.com.ehotel.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity @AllArgsConstructor @Slf4j
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;
    private final EndpointConfig endpoints;
    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder(10);
        // no password encoding
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(endpoints.getOpenEndpoints()).permitAll()
                .antMatchers(endpoints.getUserEndpoints())
                    .hasAnyAuthority(AppRoles.ROLE_USER.name())
                .antMatchers(endpoints.getEmployeeEndpoints())
                    .hasAnyAuthority(AppRoles.ROLE_EMPLOYEE.name(), AppRoles.ROLE_ADMIN.name())
                .antMatchers(endpoints.getAdminEndpoints())
                    .hasAnyAuthority(AppRoles.ROLE_ADMIN.name())
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(daoAuthenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}