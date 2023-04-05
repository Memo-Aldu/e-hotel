package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.HotelDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/4/2023, Tuesday
 **/
public interface HotelService {
    HotelDTO getHotelById(Integer id);
    Set<HotelDTO> getHotelsByChainId(Integer chainId);
    HotelDTO createHotel(HotelDTO hotelDTO);
    HotelDTO updateHotel(HotelDTO hotelDTO, Integer id);
    void deleteHotelById(Integer id);
}
