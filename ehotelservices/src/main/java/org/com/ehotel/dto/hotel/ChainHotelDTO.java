package org.com.ehotel.dto.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ChainHotelDTO(
        Integer id, String name, String email, String phoneNumber,
        Short rating, String address) {
    @JsonIgnore
    public boolean isValidDto() {
        return name != null || email != null || phoneNumber != null || rating != null || address != null;
    }
}
