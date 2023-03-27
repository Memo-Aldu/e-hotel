package org.com.ehotel.service.room;

import org.com.ehotel.dto.room.ExtensionDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/27/2023, Monday
 **/
public interface ExtensionService {
    Set<ExtensionDTO> getExtensionsByRoomId(Integer roomId);
    ExtensionDTO getExtensionById(Integer extId);
    ExtensionDTO createExtension(ExtensionDTO extensionDTO);
    ExtensionDTO updateExtension(ExtensionDTO extensionDTO, Integer id);
    void deleteAllByRoomId(Integer roomId);
    void deleteExtensionById(Integer extId);

}
