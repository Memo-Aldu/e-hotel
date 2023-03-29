package org.com.ehotel.configuration.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.jwt")
// Configuring JWT
public record JWTConfig(
        String secretKey, String tokenPrefix,
        String accessHeader, String refreshHeader,
        Integer tokenExpirationAfterDays,
        Integer refreshTokenExpirationAfterDays,
        String issuer, String audience
) {}
