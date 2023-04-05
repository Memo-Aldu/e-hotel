package org.com.ehotel.entity.reviews;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class ReviewPK implements java.io.Serializable {

    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "customer_nas")
    private String customerNAS;
}