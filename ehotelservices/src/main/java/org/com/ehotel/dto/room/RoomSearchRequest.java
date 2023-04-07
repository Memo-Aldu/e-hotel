package org.com.ehotel.dto.room;

import javax.validation.Valid;
import java.util.Locale;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/23/2023, Thursday
 **/
public record RoomSearchRequest(
        @Valid String checkInDate, @Valid String checkOutDate, String searchAddress,
        short minRating, short maxRating, String hotelChainName,
        double maxPricePerNight, int maxNumberOfGuests
) {

    public RoomSearchRequest(String checkInDate, String checkOutDate, String searchAddress,
                             short minRating, short maxRating, String hotelChainName,
                             double maxPricePerNight, int maxNumberOfGuests) {
        this.checkInDate = checkInDate.toLowerCase(Locale.ROOT);
        this.checkOutDate = checkOutDate.toLowerCase(Locale.ROOT);
        this.searchAddress = searchAddress.toLowerCase();
        this.hotelChainName = hotelChainName.toLowerCase();
        this.maxPricePerNight = maxPricePerNight;
        this.maxNumberOfGuests = maxNumberOfGuests;
        this.minRating = minRating;
        this.maxRating = maxRating;
    }

    public boolean valid() {
        if (maxPricePerNight < 0 || maxNumberOfGuests <= 0) {
            return false;
        }
        if(minRating < 1 || maxRating > 5) {
            return false;
        }
        if(minRating > maxRating)
            return false;
        return checkInDate != null && checkOutDate != null;
    }
}
