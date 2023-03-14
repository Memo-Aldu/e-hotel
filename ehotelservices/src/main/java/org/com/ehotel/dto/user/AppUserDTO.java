package org.com.ehotel.dto.user;

import org.com.ehotel.entity.user.CustomerEntity;
import org.com.ehotel.entity.user.EmployeeEntity;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record AppUserDTO(
        String email, String password,
        CustomerEntity customer, EmployeeEntity employee) {
}
