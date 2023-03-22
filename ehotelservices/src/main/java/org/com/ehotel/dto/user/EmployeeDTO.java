package org.com.ehotel.dto.user;

import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.entity.hotel.DepartmentEntity;
import org.com.ehotel.entity.hotel.RoleEntity;
import org.com.ehotel.entity.room.IncidentEntity;
import org.com.ehotel.entity.user.AppUserEntity;

import java.sql.Date;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record EmployeeDTO(
        String NAS, String phoneNumber, String firstName, String middleName, String lastName,
        String address, Double salary, Date dateOfBirth, Date registrationDate, String email,
        Integer roleId, Integer departmentId, Set<IncidentEntity> reports, Set<StayEntity> confirmedStays) {
}
