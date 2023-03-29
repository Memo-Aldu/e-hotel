package org.com.ehotel.service.hotel;

import org.com.ehotel.dto.hotel.ChainHotelDTO;
import org.com.ehotel.entity.hotel.ChainHotelEntity;
import org.com.ehotel.entity.room.CommodityEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface ChainHotelService {

    ChainHotelDTO  getChainHotelEntityByName(String chainName);

    Set<ChainHotelDTO> getAllByChainHotelEntityByRating(short rating);
    ChainHotelDTO  getChainHotelEntityById(Integer id);

    ChainHotelDTO createChain(ChainHotelDTO chainHotelDTO);

    ChainHotelDTO updateChain(ChainHotelDTO chainHotelDTO, Integer id);

    void deleteChainHotelEntityByID(Integer id);

}
