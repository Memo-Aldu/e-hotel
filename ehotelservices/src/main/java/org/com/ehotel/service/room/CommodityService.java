package org.com.ehotel.service.room;

import org.com.ehotel.dto.room.CommodityDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/26/2023, Sunday
 **/
public interface CommodityService {
    Set<CommodityDTO> getCommoditiesByRoomId(Integer roomId);
    CommodityDTO getCommodityById(Integer comId);
    void deleteAllByRoomId(Integer roomId);
    void deleteCommodityById(Integer comId);
    CommodityDTO createCommodity(CommodityDTO commodityDTO);
    CommodityDTO updateCommodity(CommodityDTO commodityDTO, Integer id);
}
