package org.com.ehotel.service.user;

import org.com.ehotel.dto.user.EmployeeDTO;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/5/2023, Wednesday
**/public interface EmployeeService {
    EmployeeDTO findEmployeeByNas(String nas);
    EmployeeDTO findEmployeeByEmail(String email);
    EmployeeDTO save(EmployeeDTO employeeDTO);
    EmployeeDTO update(EmployeeDTO employeeDTO, String email);
    void delete(String nas);
    void deleteByEmail(String email);
}
