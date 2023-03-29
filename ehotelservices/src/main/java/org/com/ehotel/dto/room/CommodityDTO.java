package org.com.ehotel.dto.room;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record CommodityDTO(
        Integer id, RoomDTO room, String name) {
    @JsonIgnore
    public boolean isValid() {
        return name != null && !name.isBlank() && room.id() != null;
    }
}
