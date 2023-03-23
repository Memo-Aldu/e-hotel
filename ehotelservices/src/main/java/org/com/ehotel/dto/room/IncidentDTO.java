package org.com.ehotel.dto.room;

import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record IncidentDTO(
        Integer id, String description, Date date,
        Integer roomId, Integer reporterId) {
}
