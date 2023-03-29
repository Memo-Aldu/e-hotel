package org.com.ehotel.dto.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.com.ehotel.dto.user.EmployeeDTO;

import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record IncidentDTO(
        Integer id, String description, Date date,
        RoomDTO room, EmployeeDTO reporter) {
    @JsonIgnore
    public boolean isValidDTO() {
        return this.description != null || this.date != null || this.room != null || this.reporter != null;
    }
}
