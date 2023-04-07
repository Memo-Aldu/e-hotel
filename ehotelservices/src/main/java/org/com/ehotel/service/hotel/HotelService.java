package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.HotelDTO;
import java.util.Set;

public interface HotelService {

    HotelDTO getHotelEntityById(Integer id);
    Set<HotelDTO> getAllHotelEntity();
    Set<HotelDTO> getHotelsByChainId(Integer chainId);
    Set<HotelDTO> searchHotel(String query);
    void deleteHotelEntityById(Integer id);
    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO updateHotel(HotelDTO hotelDTO, Integer id);

}
