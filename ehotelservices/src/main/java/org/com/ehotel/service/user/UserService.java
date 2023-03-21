package org.com.ehotel.service.user;

import org.com.ehotel.dto.requests.RegistrationRequest;
import org.com.ehotel.dto.user.AppUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/15/2023, Wednesday
 **/
public interface UserService extends UserDetailsService {
    AppUserDTO getUserByEmail(String email);
    AppUserDTO registerUser(RegistrationRequest request);
}
