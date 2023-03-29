package org.com.ehotel.service.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.IncidentDTO;
import org.com.ehotel.entity.room.IncidentEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.room.IncidentMapper;
import org.com.ehotel.repository.room.IncidentEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 2023-03-28, Tuesday
 **/
@AllArgsConstructor @Service @Slf4j
public class IncidentServiceImp implements IncidentService {
    private final IncidentEntityRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    @Override
    public Set<IncidentDTO> getIncidentsByRoomId(Integer roomId) {
        log.info("Getting all incidents by room id: " + roomId);
        return incidentMapper.toDTOs(
                incidentRepository.findIncidentEntitiesByRoomId(roomId)
        );
    }

    @Override
    public IncidentDTO getIncidentById(Integer incidentId) {
        log.info("Getting incident by id: " + incidentId);
        return incidentMapper.toDTO(
                incidentRepository.findIncidentById(incidentId)
                        .orElseThrow(() -> new AppEntityNotFoundException("Incident not found"))
        );
    }

    @Override
    public void deleteAllByRoomId(Integer roomId) {
        log.info("Deleting all incidents by room id: " + roomId);
        incidentRepository.deleteAllByRoomId(roomId);
    }

    @Override
    public void deleteIncidentById(Integer incidentId) {
        if(!incidentRepository.existsById(incidentId)) {
            throw new AppEntityNotFoundException("Incident not found");
        }
        log.info("Deleting incident by id: " + incidentId);
        incidentRepository.deleteById(incidentId);
    }

    @Override
    public IncidentDTO createIncident(IncidentDTO incidentDTO) {
        log.info("Creating incident: " + incidentDTO);
        if(incidentDTO.room() == null) {
            throw new BadRequestException("Room is required");
        }
        if(incidentDTO.reporter() == null) {
            throw new BadRequestException("Reporter is required");
        }
        return incidentMapper.toDTO(
                incidentRepository.save(incidentMapper.toEntity(incidentDTO))
        );
    }

    @Override
    public IncidentDTO updateIncident(IncidentDTO incidentDTO, Integer id) {
        log.info("Updating incident: " + incidentDTO);
        IncidentEntity incidentEntity = incidentRepository
                .findById(id).orElseThrow(() -> new AppEntityNotFoundException("Incident not found"));
        IncidentEntity updatedIncident = incidentMapper.toEntity(incidentDTO);
        if(incidentDTO.room() != null) {
            incidentEntity.setRoom(updatedIncident.getRoom());
        }
        if(incidentDTO.reporter() != null) {
            incidentEntity.setReporter(updatedIncident.getReporter());
        }
        if(incidentDTO.description() != null) {
            incidentEntity.setDescription(updatedIncident.getDescription());
        }
        if(incidentDTO.date() != null) {
            incidentEntity.setDate(incidentDTO.date());
        }
        return incidentMapper.toDTO(
                incidentRepository.save(incidentEntity)
        );
    }
}
