package org.com.ehotel.dto.room;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ExtensionDTO(
        Integer id, String name, Double price, RoomDTO room) {
    @JsonIgnore
    public boolean isValidDto() {
        return name != null || price != null || room != null;
    }
}
