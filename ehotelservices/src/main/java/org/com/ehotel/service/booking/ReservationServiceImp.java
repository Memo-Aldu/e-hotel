package org.com.ehotel.service.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.booking.ReservationDTO;
import org.com.ehotel.entity.booking.ReservationEntity;
import org.com.ehotel.enums.ReservationStatus;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.booking.ReservationMapper;
import org.com.ehotel.repository.booking.ReservationEntityRepository;
import org.com.ehotel.repository.user.CustomerEntityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 2023-04-06, Thursday
 **/
@AllArgsConstructor @Service @Slf4j
public class ReservationServiceImp implements ReservationService {
    private final ReservationEntityRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CustomerEntityRepository customerRepository;

    @Override
    public ReservationDTO findReservationById(Integer id) {
        log.info("Getting reservation by id: " + id);
        return reservationMapper.toDTO(
                reservationRepository.findReservationById(id)
                        .orElseThrow(() -> new AppEntityNotFoundException("Reservation not found"))
        );
    }

    @Override
    public Set<ReservationDTO> findReservationsByCustomerNAS(String customerNAS) {
        log.info("Getting reservations by customer NAS: " + customerNAS);
        return reservationMapper.toDTOs(
                reservationRepository.findReservationsByCustomerNAS(customerNAS)
        );
    }

    @Override
    public Set<ReservationDTO> findReservationByHotelId(Integer hotelId) {
        log.info("Getting reservations by hotel id: " + hotelId);
        return reservationMapper.toDTOs(
                reservationRepository.findReservationByHotelId(hotelId)
        );
    }

    @Override @Transactional
    public ReservationDTO create(ReservationDTO reservationDTO) {
        if(reservationDTO.customer().NAS() == null || reservationDTO.customer().NAS().isEmpty()) {
            throw new BadRequestException("Invalid customer NAS");
        }
        if(reservationDTO.reservedRooms() == null || reservationDTO.reservedRooms().isEmpty()) {
            throw new BadRequestException("Invalid reserved rooms");
        }
        if(reservationDTO.totalPrice() == null || reservationDTO.totalPrice() < 0 ) {
            throw new BadRequestException("Invalid total price");
        }
        if(reservationDTO.checkInDate() == null || reservationDTO.checkOutDate() == null) {
            throw new BadRequestException("Invalid check in/out date");
        }
        if(reservationDTO.checkInDate().after(reservationDTO.checkOutDate())) {
            throw new BadRequestException("Check in date must be before check out date");
        }
        if(reservationDTO.checkInDate().before(Date.from(Instant.now()))) {
            throw new BadRequestException("Check in date must be after today");
        }
        if(reservationDTO.checkOutDate().before(Date.from(Instant.now()))) {
            throw new BadRequestException("Check out date must be after today");
        }
        if(!customerRepository.existsById(reservationDTO.customer().NAS())) {
            throw new BadRequestException("Customer not found");
        }
        ReservationEntity reservationEntity = reservationMapper.toEntity(reservationDTO);
        if(reservationEntity.getStatus() == null) {
            reservationEntity.setStatus(ReservationStatus.PENDING);
        }
        if(reservationEntity.getCreationDate() == null) {
            reservationEntity.setCreationDate(java.sql.Date.valueOf(LocalDate.now()));
        }
        return reservationMapper.toDTO(
                reservationRepository.save(reservationEntity)
        );
    }

    @Override
    public ReservationDTO update(ReservationDTO reservationDTO, Integer id) {
        ReservationEntity reservationEntity = reservationRepository
                .findReservationById(id).orElseThrow(
                        () -> new AppEntityNotFoundException("Reservation not found"));
        ReservationEntity reservationEntityUpdated = reservationMapper.toEntity(reservationDTO);
        // check if reservation is approved
        if(reservationEntity.getStatus() == ReservationStatus.APPROVED) {
            throw new BadRequestException("Reservation already approved, cannot be updated");
        }
        // check if reservation is cancelled
        if(reservationEntity.getStatus() == ReservationStatus.CANCELED) {
            throw new BadRequestException("Reservation already cancelled, cannot be updated");
        }
        // check if reservation is rejected
        if(reservationEntity.getStatus() == ReservationStatus.REJECTED) {
            throw new BadRequestException("Reservation rejected, cannot be updated");
        }
        if(reservationDTO.checkInDate() != null && reservationDTO.checkInDate().after(Date.from(Instant.now()))) {
            if(reservationDTO.checkOutDate() != null) {
                if(reservationDTO.checkInDate().after(reservationDTO.checkOutDate())) {
                    throw new BadRequestException("Check in date must be before check out date");
                }
            } else {
                if(reservationEntity.getCheckOutDate().before(reservationDTO.checkInDate())) {
                    throw new BadRequestException("Check in date must be before check out date");
                }
                reservationEntity.setCheckInDate(reservationDTO.checkInDate());
            }
        }
        if(reservationDTO.checkOutDate() != null && reservationDTO.checkOutDate().after(Date.from(Instant.now()))) {
            if(reservationDTO.checkInDate() != null) {
                if(reservationDTO.checkInDate().after(reservationDTO.checkOutDate())) {
                    throw new BadRequestException("Check in date must be before check out date");
                }
                reservationEntity.setCheckOutDate(reservationDTO.checkOutDate());
            } else {
                if(reservationEntity.getCheckInDate().after(reservationDTO.checkOutDate())) {
                    throw new BadRequestException("Check in date must be before check out date");
                }
                reservationEntity.setCheckOutDate(reservationDTO.checkOutDate());
            }
        }
        if(reservationDTO.totalPrice() != null) {
            if(reservationDTO.totalPrice() < 0) {
                throw new BadRequestException("Invalid total price");
            }
            reservationEntity.setTotalPrice(reservationDTO.totalPrice());
        }
        if(reservationDTO.specialRequest() != null && !reservationDTO.specialRequest().isEmpty()) {
            reservationEntity.setSpecialRequest(reservationDTO.specialRequest());
        }
        if(reservationDTO.status() != null) {
            reservationEntity.setStatus(reservationDTO.status());
        }
        if(reservationDTO.reservedExtensions() != null && !reservationDTO.reservedExtensions().isEmpty()) {
            reservationEntity.setReservedExtensions(
                    reservationEntityUpdated.getReservedExtensions());
        }
        log.info("Updating reservation by id: " + reservationEntity.getId());
        try {
            return reservationMapper.toDTO(
                    reservationRepository.save(reservationEntity));
        } catch (EntityNotFoundException e) {
            throw new AppEntityNotFoundException(e.getLocalizedMessage());
        } catch (Exception e) {
            throw new BadRequestException(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteReservationByReservationId(Integer id) {
        if(!reservationRepository.existsById(id)) {
            throw new AppEntityNotFoundException("Reservation not found");
        }
        log.info("Deleting reservation by id: " + id);
        reservationRepository.deleteById(id);
    }
}
