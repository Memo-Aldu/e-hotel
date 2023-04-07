package org.com.ehotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/7/2023, Friday
 **/
@Getter
public enum Endpoints {
    AUTH("/api/v1/auth"), APP_USER("/api/v1/user"),
    CUSTOMER("/api/v1/customer"), EMPLOYEE("/api/v1/employee"),
    COMMODITY("/api/v1/commodities"), EXTENSION("/api/v1/extension"),
    INCIDENT("/api/v1/incidents"), ROOM("/api/v1/room"),
    ROOM_TYPE("/api/v1/room-type"), ROOM_VIEW("/api/v1/room-view"),
    HOTEL("/api/v1/hotel"), CHAIN_HOTEL("/api/v1/hotel-chain"),
    DEPARTMENT("/api/v1/department"), ROLE("/api/v1/role"),
    RESERVATION("/api/v1/reservation"), STAY("/api/v1/stay");

    private final String path;
    private Endpoints(String path) {
        this.path = path;
    }
}
