package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.ChainHotelDTO;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/4/2023, Tuesday
 **/
public interface ChainHotelService {
    ChainHotelDTO  getChainHotelById(Integer id);
    ChainHotelDTO createChainHotel(ChainHotelDTO chainHotelDTO);
    ChainHotelDTO updateChainHotel(ChainHotelDTO chainHotelDTO, Integer id);
    void deleteChainHotelByID(Integer id);
}
