package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.ChainHotelDTO;
import org.com.ehotel.dto.hotel.HotelDTO;
import java.util.Set;

public interface HotelService {

    HotelDTO getHotelEntityByName(String name);

    Set<HotelDTO> getAllHotelEntityByRating(short rating);
    HotelDTO getHotelEntityById(Integer id);
    HotelDTO getHotelEntityByAddress(String address);

    Set<HotelDTO> getAllHotelEntityByChainName(String name);

    void deleteHotelEntityById(Integer id);

    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO updateHotel(HotelDTO hotelDTO, Integer id);

}
