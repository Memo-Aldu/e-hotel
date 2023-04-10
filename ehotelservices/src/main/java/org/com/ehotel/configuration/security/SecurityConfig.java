package org.com.ehotel.configuration.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.configuration.EndpointConfig;
import org.com.ehotel.enums.AppRoles;
import org.com.ehotel.enums.Endpoints;
import org.com.ehotel.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // open endpoints for anyone
                .antMatchers(endpoints.getOpenEndpoints()).permitAll()
                .antMatchers(HttpMethod.GET , endpoints.getOpenGETEndpoints()).permitAll()
                .antMatchers(HttpMethod.OPTIONS , endpoints.getOpenGETEndpoints()).permitAll()

                // endpoints for user
                .antMatchers(endpoints.getUserEndpoints())
                    .hasAnyAuthority(AppRoles.ROLE_USER.name(), AppRoles.ROLE_EMPLOYEE.name(), AppRoles.ROLE_CUSTOMER.name())
                .antMatchers(HttpMethod.POST, Endpoints.CUSTOMER.getPath()+"/**", Endpoints.EMPLOYEE.getPath()+"/**")
                    .hasAnyAuthority(AppRoles.ROLE_USER.name(), AppRoles.ROLE_EMPLOYEE.name(), AppRoles.ROLE_CUSTOMER.name())
                .antMatchers(HttpMethod.GET, Endpoints.CHAIN_HOTEL.getPath() + "/**", Endpoints.DEPARTMENT.getPath() + "/**", Endpoints.ROLE.getPath() + "/**")
                    .hasAnyAuthority(AppRoles.ROLE_USER.name(), AppRoles.ROLE_EMPLOYEE.name(), AppRoles.ROLE_CUSTOMER.name())
                .antMatchers(HttpMethod.OPTIONS, Endpoints.CHAIN_HOTEL.getPath() + "/**", Endpoints.DEPARTMENT.getPath() + "/**", Endpoints.ROLE.getPath() + "/**")
                .hasAnyAuthority(AppRoles.ROLE_USER.name(), AppRoles.ROLE_EMPLOYEE.name(), AppRoles.ROLE_CUSTOMER.name())
                // endpoints for customer
                .antMatchers(endpoints.getCustomerEndpoints())
                    .hasAnyAuthority(AppRoles.ROLE_CUSTOMER.name(), AppRoles.ROLE_EMPLOYEE.name(), AppRoles.ROLE_ADMIN.name())
                // endpoints for employee
                .antMatchers(endpoints.getEmployeeEndpoints())
                    .hasAnyAuthority(AppRoles.ROLE_EMPLOYEE.name(), AppRoles.ROLE_ADMIN.name())
                // endpoints for admin TODO: Implement admin endpoints
                .antMatchers(endpoints.getAdminEndpoints())
                    .hasAnyAuthority(AppRoles.ROLE_ADMIN.name())
                .anyRequest().authenticated()
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