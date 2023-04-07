package org.com.ehotel.entity.reviews;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.entity.user.CustomerEntity;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "reviews")
@Getter @Setter
public class ReviewEntity {
    @EmbeddedId
    private ReviewPK id;

    @MapsId("hotelId")
    @JoinColumn(name = "hotel_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private HotelEntity hotel;

    @MapsId("customerNAS")
    @JoinColumn(name = "customer_nas")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CustomerEntity customer;

    @Column(name = "review_rating")
    private Short rating;

    @Column(name = "review_comment")
    private String comment;

    @Column(name = "review_date")
    private Date date;
}
