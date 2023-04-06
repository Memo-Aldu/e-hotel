package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.ChainHotelDTO;

import java.util.Set;

public interface ChainHotelService {

    ChainHotelDTO getChainHotelById(Integer id);

    Set<ChainHotelDTO> getAllChainHotelEntity();

    ChainHotelDTO createChainHotel(ChainHotelDTO chainHotelDTO);

    ChainHotelDTO updateChainHotel(ChainHotelDTO chainHotelDTO, Integer id);

    void deleteChainHotelByID(Integer id);

}
