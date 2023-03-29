package org.com.ehotel.service.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.ExtensionDTO;
import org.com.ehotel.entity.room.ExtensionEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.mapper.room.ExtensionMapper;
import org.com.ehotel.repository.room.ExtensionEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/27/2023, Monday
 **/
@Service @Slf4j @AllArgsConstructor
public class ExtensionServiceImp implements ExtensionService{
    private final ExtensionEntityRepository extensionRepository;
    private final ExtensionMapper extensionMapper;
    @Override
    public Set<ExtensionDTO> getExtensionsByRoomId(Integer roomId) {
        log.info("Getting extensions by room id: " + roomId);
        return extensionMapper.toDTOs(
                extensionRepository.getExtensionsByRoomId(roomId)
        );
    }

    @Override
    public ExtensionDTO getExtensionById(Integer extId) {
        log.info("Getting extension by id: " + extId);
        return extensionMapper.toDTO(
                extensionRepository.getExtensionById(extId).orElseThrow(
                                () -> new AppEntityNotFoundException("Extension not found with id: " + extId)
                        ));
    }

    @Override
    public ExtensionDTO createExtension(ExtensionDTO extensionDTO) {
        log.info("Creating extension: " + extensionDTO);
        return extensionMapper.toDTO(
                extensionRepository.save(extensionMapper.toEntity(extensionDTO))
        );
    }

    @Override
    public ExtensionDTO updateExtension(ExtensionDTO extensionDTO, Integer id) {
        log.info("Updating extension: " + extensionDTO);
        ExtensionEntity extensionEntity = extensionRepository.findById(id).orElseThrow(
                () -> new AppEntityNotFoundException("Extension not found with id: " + id)
        );
        if(extensionDTO.name() != null) {
            extensionEntity.setName(extensionDTO.name());
        }
        if(extensionDTO.price() != null) {
            extensionEntity.setPrice(extensionDTO.price());
        }
        if(extensionDTO.room() != null) {
            extensionEntity.setRoom(extensionMapper.toEntity(extensionDTO).getRoom());
        }
        return extensionMapper.toDTO(extensionRepository.save(extensionEntity));
    }

    @Override
    public void deleteAllByRoomId(Integer roomId) {
        log.info("Deleting all extensions by room id: " + roomId);
        extensionRepository.deleteAllByRoomId(roomId);
    }

    @Override
    public void deleteExtensionById(Integer extId) {
        if(!extensionRepository.existsById(extId))
            throw new AppEntityNotFoundException("Extension not found with id: " + extId);
        log.info("Deleting extension by id: " + extId);
        extensionRepository.deleteById(extId);
    }
}
