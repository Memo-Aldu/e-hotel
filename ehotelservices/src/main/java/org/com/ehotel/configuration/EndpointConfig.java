package org.com.ehotel.configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.function.Predicate;


@Component @Getter
public class EndpointConfig {
    private final String AUTH_PREFIX = "/api/v1/auth/";
    private final String USER_PREFIX = "/api/v1/user/";
    private final String[] openEndpoints = new String[]{
            AUTH_PREFIX+"authenticate", AUTH_PREFIX+"token/refresh",
            USER_PREFIX+"register"};

    private final String[] userEndpoints = new String[]{
            USER_PREFIX+"**"
    };
    private final String[] employeeEndpoints =  new String[]{};
    private final String[] adminEndpoints =  new String[]{};

    public Predicate<HttpServletRequest> isSecured =
            request -> Arrays.stream(openEndpoints).toList()
                    .stream()
                    .noneMatch(uri -> request.getRequestURL().toString().contains(uri));
}
