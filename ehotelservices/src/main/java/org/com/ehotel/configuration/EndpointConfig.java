package org.com.ehotel.configuration;

import lombok.Getter;
import org.com.ehotel.enums.Endpoints;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.function.Predicate;


@Component @Getter
public class EndpointConfig {
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final String[] openEndpoints = new String[]{
            Endpoints.AUTH.getPath() + "/**", Endpoints.APP_USER.getPath()+"/register"};

    private final String[] userEndpoints = new String[]{
            Endpoints.APP_USER.getPath() + "/**"};

    private final String[] customerEndpoints = new String[]{
            Endpoints.CUSTOMER.getPath() + "/**", Endpoints.RESERVATION.getPath() + "/**",
            Endpoints.STAY.getPath() + "/**"};

    private final String[] employeeEndpoints =  new String[]{
            Endpoints.EMPLOYEE.getPath() + "/**", Endpoints.COMMODITY.getPath() + "/**", Endpoints.EXTENSION.getPath() + "/**",
            Endpoints.INCIDENT.getPath() + "/**", Endpoints.ROOM.getPath() + "/**", Endpoints.ROOM_TYPE.getPath() + "/**",
            Endpoints.ROOM_VIEW.getPath() + "/**", Endpoints.HOTEL.getPath() + "/**", Endpoints.CHAIN_HOTEL.getPath() + "/**",
            Endpoints.DEPARTMENT.getPath() + "/**", Endpoints.ROLE.getPath() + "/**"};
    private final String[] adminEndpoints =  new String[]{};

    public Predicate<HttpServletRequest> isSecured =
            request -> Arrays.stream(openEndpoints).toList()
                    .stream()
                    .noneMatch(uri -> pathMatcher.match(uri, request.getRequestURI()));
}
