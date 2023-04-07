package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.DepartmentDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/7/2023, Friday
 **/
public interface DepartmentService {
    DepartmentDTO findById(Integer id);
    Set<DepartmentDTO> findByManagerNAS(String managerNAS);
    Set<DepartmentDTO> findByHotelId(Integer hotelId);
    DepartmentDTO create(DepartmentDTO departmentDTO);
    DepartmentDTO update(DepartmentDTO departmentDTO, Integer id);
    void deleteById(Integer id);
}
