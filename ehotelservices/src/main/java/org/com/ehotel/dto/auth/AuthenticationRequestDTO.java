package org.com.ehotel.dto.auth;

public record AuthenticationRequestDTO(
        String email,
        String password
) {
    public boolean isValid() {
        return email != null && password != null;
    }
}
