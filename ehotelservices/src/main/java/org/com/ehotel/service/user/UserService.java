package org.com.ehotel.service.user;

import org.com.ehotel.dto.user.UserRegistrationRequest;
import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.dto.user.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/15/2023, Wednesday
 **/
public interface UserService extends UserDetailsService {
    AppUserDTO getUserByEmail(String email);
    AppUserDTO registerUser(UserRegistrationRequest request);
    AppUserDTO updateUser(String email, UserUpdateRequest request);
    void deleteUser(String email);
}
