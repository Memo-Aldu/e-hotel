package org.com.ehotel.service.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.HotelDTO;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.hotel.HotelMapper;
import org.com.ehotel.repository.hotel.HotelEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/4/2023, Tuesday
 **/
@Service @AllArgsConstructor @Slf4j
public class HotelServiceImp implements HotelService {
    private final HotelEntityRepository hotelRepository;
    private final HotelMapper hotelMapper;
    @Override
    public HotelDTO getHotelById(Integer id) {
        log.info("Getting hotel by id: " + id);
        return hotelMapper.toDTO(
                hotelRepository.findHotelEntityById(id)
                        .orElseThrow(() -> new AppEntityNotFoundException("Hotel not found"))
        );
    }

    @Override
    public Set<HotelDTO> getHotelsByChainId(Integer chainId) {
        log.info("Getting hotels by chain id: " + chainId);
        return hotelMapper.toDTOs(
                hotelRepository.findAllHotelEntityByChainId(chainId)
        );
    }

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        // Validate hotelDTO fields for null or empty
        validateFields(hotelDTO);
        log.info("Creating hotel: " + hotelDTO.name());
        return hotelMapper.toDTO(
                hotelRepository.save(
                        hotelMapper.toEntity(hotelDTO)
                )
        );
    }

    @Override
    public HotelDTO updateHotel(HotelDTO hotelDTO, Integer id) {
        HotelEntity hotelEntity = hotelRepository.findHotelEntityById(id)
                .orElseThrow(() -> new AppEntityNotFoundException("Hotel not found"));
        HotelEntity updatedHotelEntity = hotelMapper.toEntity(hotelDTO);
        if(updatedHotelEntity.getName() != null && !updatedHotelEntity.getName().isEmpty()) {
            hotelEntity.setName(updatedHotelEntity.getName());
        }
        if(updatedHotelEntity.getEmail() != null && !updatedHotelEntity.getEmail().isEmpty()) {
            hotelEntity.setEmail(updatedHotelEntity.getEmail());
        }
        if(updatedHotelEntity.getPhoneNumber() != null && !updatedHotelEntity.getPhoneNumber().isEmpty()) {
            hotelEntity.setPhoneNumber(updatedHotelEntity.getPhoneNumber());
        }
        if(updatedHotelEntity.getCity() != null && !updatedHotelEntity.getCity().isEmpty()) {
            hotelEntity.setCity(updatedHotelEntity.getCity());
        }
        if(updatedHotelEntity.getAddress() != null && !updatedHotelEntity.getAddress().isEmpty()) {
            hotelEntity.setAddress(updatedHotelEntity.getAddress());
        }
        if(updatedHotelEntity.getRating() != null && updatedHotelEntity.getRating() <= 5 && updatedHotelEntity.getRating() > 0) {
            hotelEntity.setRating(updatedHotelEntity.getRating());
        }
        if(updatedHotelEntity.getChainHotel() != null && updatedHotelEntity.getChainHotel().getId() != null) {
            hotelEntity.setChainHotel(updatedHotelEntity.getChainHotel());
        }
        log.info("Updating hotel by id: " + id);
        return hotelMapper.toDTO(
                hotelRepository.save(hotelEntity)
        );
    }

    @Override
    public void deleteHotelById(Integer id) {
        if(!hotelRepository.existsById(id)) {
            throw new AppEntityNotFoundException("Hotel not found");
        }
        log.info("Deleting hotel by id: " + id);
        hotelRepository.deleteById(id);
    }

    private void validateFields(HotelDTO hotelDTO) {
        if(hotelDTO.name() == null || hotelDTO.name().isEmpty()) {
            throw new BadRequestException("Invalid hotel name");
        }
        if(hotelDTO.email() == null || hotelDTO.email().isEmpty()) {
            throw new BadRequestException("Invalid hotel email");
        }
        if(hotelDTO.phoneNumber() == null || hotelDTO.phoneNumber().isEmpty()) {
            throw new BadRequestException("Invalid hotel phone");
        }
        if(hotelDTO.address() == null || hotelDTO.address().isEmpty()) {
            throw new BadRequestException("Invalid hotel address");
        }
        if(hotelDTO.rating() == null || hotelDTO.rating() > 5 || hotelDTO.rating() < 0) {
            throw new BadRequestException("Invalid hotel rating");
        }
        if(hotelDTO.chainHotel().id() == null) {
            throw new BadRequestException("Invalid hotel chain id");
        }
        if(hotelRepository.existsHotelEntityByEmail(hotelDTO.email())) {
            throw new BadRequestException("Hotel with email: " + hotelDTO.email() + " already exists");
        }
        if (hotelRepository.existsHotelEntityByPhoneNumber(hotelDTO.phoneNumber())) {
            throw new BadRequestException("Hotel with number: " + hotelDTO.phoneNumber() + " already exists");
        }
    }
}
