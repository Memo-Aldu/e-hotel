package org.com.ehotel.service.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.CommodityDTO;
import org.com.ehotel.entity.room.CommodityEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.mapper.room.CommodityMapper;
import org.com.ehotel.repository.room.CommodityEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/26/2023, Sunday
 **/
@Service @Slf4j @AllArgsConstructor
public class CommodityServiceImp implements CommodityService {
    private final CommodityEntityRepository commodityRepository;
    private final CommodityMapper commodityMapper;

    @Override
    public Set<CommodityDTO> getCommoditiesByRoomId(Integer roomId) {
        return commodityMapper.toDTOs(commodityRepository.findAllByRoomId(roomId));
    }

    @Override
    public CommodityDTO getCommodityById(Integer comId) {
        return commodityMapper.toDTO(commodityRepository
                .findCommodityEntityById(comId).orElseThrow(() ->
                new AppEntityNotFoundException("Commodity not found with id: " + comId)));
    }

    @Override
    public void deleteAllByRoomId(Integer roomId) {
        commodityRepository.deleteAllByRoomId(roomId);
    }

    @Override
    public void deleteCommodityById(Integer comId) {
        commodityRepository.deleteById(comId);
    }

    @Override
    public CommodityDTO createCommodity(CommodityDTO commodityDTO) {
       return commodityMapper.toDTO(commodityRepository
               .save(commodityMapper.toEntity(commodityDTO)));
    }

    @Override
    public CommodityDTO updateCommodity(CommodityDTO commodityDTO, Integer id) {
        CommodityEntity commodityEntity = commodityRepository.findById(id).orElseThrow(() ->
                new AppEntityNotFoundException("Commodity not found with id: " + id));
        commodityEntity.setName(commodityDTO.name());
        commodityEntity.setRoom(commodityMapper.toEntity(commodityDTO).getRoom());
        return commodityMapper.toDTO(commodityRepository.save(commodityEntity));
    }
}
