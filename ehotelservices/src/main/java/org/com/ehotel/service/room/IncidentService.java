package org.com.ehotel.service.room;

import org.com.ehotel.dto.room.IncidentDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 2023-03-28, Tuesday
 **/
public interface IncidentService {
    Set<IncidentDTO> getIncidentsByRoomId(Integer roomId);
    IncidentDTO getIncidentById(Integer incidentId);
    void deleteAllByRoomId(Integer roomId);
    void deleteIncidentById(Integer incidentId);
    IncidentDTO createIncident(IncidentDTO incidentDTO);
    IncidentDTO updateIncident(IncidentDTO incidentDTO, Integer id);
}
