package org.com.ehotel.service.booking;

import org.com.ehotel.dto.booking.ReservationDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 2023-04-06, Thursday
 **/
public interface ReservationService {
    ReservationDTO findReservationById(Integer id);
    Set<ReservationDTO> findReservationsByCustomerNAS(String customerNAS);
    Set<ReservationDTO> findReservationByHotelId(Integer hotelId);
    ReservationDTO create(ReservationDTO reservationDTO);
    ReservationDTO update(ReservationDTO reservationDTO, Integer id);
    void deleteReservationByReservationId(Integer id);
}
