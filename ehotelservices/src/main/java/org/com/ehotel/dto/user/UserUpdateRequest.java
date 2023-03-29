package org.com.ehotel.dto.user;

import org.com.ehotel.enums.AppRoles;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/22/2023, Wednesday
 **/
public record UserUpdateRequest(
        String password,
        AppRoles userRole
) {
    public boolean isValid() {
        return password != null || userRole != null;
    }
}
