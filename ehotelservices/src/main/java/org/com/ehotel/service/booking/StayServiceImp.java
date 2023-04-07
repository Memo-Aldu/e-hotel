package org.com.ehotel.service.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.booking.StayDTO;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.enums.PaymentStatus;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.booking.StayMapper;
import org.com.ehotel.repository.booking.StayEntityRepository;
import org.com.ehotel.repository.user.CustomerEntityRepository;
import org.com.ehotel.repository.user.EmployeeEntityRepository;
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
 * @created : 4/7/2023, Friday
 **/
@AllArgsConstructor @Service @Slf4j
public class StayServiceImp implements StayService {
    private final StayEntityRepository stayRepository;
    private final StayMapper stayMapper;
    private final CustomerEntityRepository customerRepository;
    private final EmployeeEntityRepository employeeRepository;

    @Override
    public StayDTO findStayById(Integer id) {
        log.info("Getting stay by id: " + id);
        return stayMapper.toDTO(
                stayRepository.findStayById(id)
                        .orElseThrow(() -> new AppEntityNotFoundException("Stay not found"))
        );
    }

    @Override
    public Set<StayDTO> findStaysByCustomerNAS(String customerNAS) {
        log.info("Getting stays by customer NAS: " + customerNAS);
        return stayMapper.toDTOs(
                stayRepository.findStayByCustomerNAS(customerNAS)
        );
    }

    @Override
    public Set<StayDTO> findStayByHotelId(Integer hotelId) {
        log.info("Getting stays by hotel id: " + hotelId);
        return stayMapper.toDTOs(
                stayRepository.findStayByHotelId(hotelId)
        );
    }

    @Override @Transactional
    public StayDTO create(StayDTO stayDTO) {
        if(stayDTO.customer() == null || stayDTO.customer().NAS() == null || stayDTO.customer().NAS().isEmpty()) {
            throw new BadRequestException("Customer NAS is required");
        }
        if(stayDTO.rooms() == null || stayDTO.rooms().isEmpty()) {
            throw new BadRequestException("Invalid reserved rooms");
        }
        if(stayDTO.employee() == null || stayDTO.employee().NAS() == null || stayDTO.employee().NAS().isEmpty()) {
            throw new BadRequestException("Employee NAS is required");
        }
        if(!customerRepository.existsById(stayDTO.customer().NAS())) {
            throw new BadRequestException("Customer not found");
        }
        if(!employeeRepository.existsById(stayDTO.employee().NAS())) {
            throw new BadRequestException("Employee not found");
        }
        if(stayDTO.paymentTotal() == null || stayDTO.paymentTotal() < 0) {
            throw new BadRequestException("Payment is invalid");
        }
        if(stayDTO.checkInDate() == null || stayDTO.checkOutDate() == null) {
            throw new BadRequestException("Invalid check in/out date");
        }
        if(stayDTO.checkInDate().after(stayDTO.checkOutDate())) {
            throw new BadRequestException("Check in date must be before check out date");
        }
        if(stayDTO.checkInDate().before(Date.from(Instant.now()))) {
            throw new BadRequestException("Check in date must be after today");
        }
        if(stayDTO.checkOutDate().before(Date.from(Instant.now()))) {
            throw new BadRequestException("Check out date must be after today");
        }
        StayEntity stayEntity = stayMapper.toEntity(stayDTO);
        if(stayDTO.paymentStatus() == null) {
            stayEntity.setPaymentStatus(PaymentStatus.UNPAID);
        }
        if(stayDTO.creationDate() == null) {
            stayEntity.setCreationDate(java.sql.Date.valueOf(LocalDate.now()));
        }
        log.info("Creating stay: " + stayEntity);
        return stayMapper.toDTO(
                stayRepository.save(stayEntity)
        );
    }

    @Override
    public StayDTO update(StayDTO stayDTO, Integer id) {
        StayEntity stayEntity = stayRepository.findStayById(id)
                .orElseThrow(() -> new AppEntityNotFoundException("Stay not found"));
        StayEntity updatedStayEntity = stayMapper.toEntity(stayDTO);
        if(stayDTO.paymentStatus() != null) {
            stayEntity.setPaymentStatus(updatedStayEntity.getPaymentStatus());
        }
        if(stayDTO.paymentTotal() != null && stayDTO.paymentTotal() >= 0) {
            stayEntity.setPaymentTotal(updatedStayEntity.getPaymentTotal());
        }
        if(stayDTO.checkInDate() != null && stayDTO.checkInDate().after(Date.from(Instant.now()))) {
            if(stayDTO.checkOutDate() != null) {
                if(stayDTO.checkInDate().after(stayDTO.checkOutDate())) {
                    throw new BadRequestException("Check in date must be before check out date");
                }
            } else {
                if(stayEntity.getCheckOutDate().before(stayDTO.checkInDate())) {
                    throw new BadRequestException("Check in date must be before check out date");
                }
                stayEntity.setCheckInDate(stayDTO.checkInDate());
            }
        }
        if(stayDTO.checkOutDate() != null && stayDTO.checkOutDate().after(Date.from(Instant.now()))) {
            if(stayDTO.checkInDate() != null) {
                if(stayDTO.checkInDate().after(stayDTO.checkOutDate())) {
                    throw new BadRequestException("Check in date must be before check out date");
                }
                stayEntity.setCheckOutDate(stayDTO.checkOutDate());
            } else {
                if(stayEntity.getCheckInDate().after(stayDTO.checkOutDate())) {
                    throw new BadRequestException("Check in date must be before check out date");
                }
                stayEntity.setCheckOutDate(stayDTO.checkOutDate());
            }
        }
        if(stayDTO.requestedExtensions() != null && !stayDTO.requestedExtensions().isEmpty()) {
            log.info("Updating stay extensions");
            stayEntity.setRequestedExtensions(
                    updatedStayEntity.getRequestedExtensions());
        }
        log.info("Updating stay by id: " + stayEntity.getId());
        try {
            return stayMapper.toDTO(
                    stayRepository.save(stayEntity));
        } catch (EntityNotFoundException e) {
            throw new AppEntityNotFoundException(e.getLocalizedMessage());
        } catch (Exception e) {
            throw new BadRequestException(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteStayByStayId(Integer id) {
        if(!stayRepository.existsById(id)) {
            throw new AppEntityNotFoundException("Stay not found");
        }
        log.info("Deleting stay by id: " + id);
        stayRepository.deleteById(id);
    }
}
