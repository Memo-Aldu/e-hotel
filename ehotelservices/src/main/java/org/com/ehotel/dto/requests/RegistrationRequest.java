package org.com.ehotel.dto.requests;

import org.com.ehotel.enums.AppRoles;

import javax.validation.Valid;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 2023-03-21, Tuesday
 **/
public record RegistrationRequest(
        @Valid String email,
        @Valid String password,
        AppRoles userRole
) {
    public boolean isValid() {
        return email != null && password != null;
    }
}
