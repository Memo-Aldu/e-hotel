package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.RoleDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/7/2023, Friday
 **/
public interface RoleService {
    RoleDTO findById(Integer id);
    Set<RoleDTO> findByHotelId(Integer hotelId);
    RoleDTO create(RoleDTO roleDTO);
    RoleDTO update(RoleDTO roleDTO, Integer id);
    void deleteById(Integer id);
}
