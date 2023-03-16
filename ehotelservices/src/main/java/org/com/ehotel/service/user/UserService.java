package org.com.ehotel.service.user;

import org.com.ehotel.dto.user.AppUserDTO;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/15/2023, Wednesday
 **/
public interface UserService {
    AppUserDTO getUserByEmail(String email);
}
