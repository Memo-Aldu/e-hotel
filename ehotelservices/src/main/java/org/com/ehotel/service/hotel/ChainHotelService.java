package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.ChainHotelDTO;

import java.util.Set;

public interface ChainHotelService {

    ChainHotelDTO  getChainHotelEntityById(Integer id);

    Set<ChainHotelDTO> getAllChainHotelEntities();

    ChainHotelDTO createChain(ChainHotelDTO chainHotelDTO);

    ChainHotelDTO updateChain(ChainHotelDTO chainHotelDTO, Integer id);

    void deleteChainHotelEntityByID(Integer id);

}
