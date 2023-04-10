package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.HotelDTO;
import org.com.ehotel.dto.hotel.HotelSearchDTO;

import java.util.Set;

public interface HotelService {

    HotelDTO getHotelEntityById(Integer id);
    Set<HotelDTO> getAllHotelEntity();
    Set<HotelDTO> getHotelsByChainId(Integer chainId);
    Set<HotelDTO> searchHotel(HotelSearchDTO searchDTO);
    void deleteHotelEntityById(Integer id);
    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO updateHotel(HotelDTO hotelDTO, Integer id);

}
