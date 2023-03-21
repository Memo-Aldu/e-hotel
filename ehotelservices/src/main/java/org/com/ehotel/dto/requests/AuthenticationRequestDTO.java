package org.com.ehotel.dto.requests;

public record AuthenticationRequestDTO(
        String email,
        String password
) {
    public boolean isValid() {
        return email != null && password != null;
    }
}
