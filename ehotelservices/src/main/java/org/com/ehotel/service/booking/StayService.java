package org.com.ehotel.service.booking;

import org.com.ehotel.dto.booking.StayDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/7/2023, Friday
 **/
public interface StayService {
    StayDTO findStayById(Integer id);
    Set<StayDTO> findStaysByCustomerNAS(String customerNAS);
    Set<StayDTO> findStayByHotelId(Integer hotelId);
    StayDTO create(StayDTO stayDTO);
    StayDTO update(StayDTO stayDTO, Integer id);
    void deleteStayByStayId(Integer id);
}
