package org.com.ehotel.dto.hotel;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ChainHotelDTO(
        Integer id, String name, String email, String phoneNumber,
        Short rating, String address) {
    @JsonIgnore
    public boolean isValidDto() {
        return id != null || name == null || email == null || phoneNumber == null;
    }
}
