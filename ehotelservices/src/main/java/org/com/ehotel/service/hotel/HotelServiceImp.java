package org.com.ehotel.service.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.HotelDTO;
import org.com.ehotel.entity.hotel.ChainHotelEntity;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.mapper.hotel.HotelMapper;
import org.com.ehotel.repository.hotel.HotelEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@AllArgsConstructor
@Slf4j
public class HotelServiceImp implements HotelService{
    private final HotelMapper hotelMapper;
    private final HotelEntityRepository hotelEntityRepository;

    @Override
    public HotelDTO getHotelEntityByName(String name) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityByName(name).orElseThrow();
        log.info("Hotel found with name: " + hotel.getName());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public Set<HotelDTO> getAllHotelEntityByRating(short rating) {
        Set<HotelEntity> hotel = hotelEntityRepository.findAllHotelEntityByRating(rating);
        log.info("Hotel found with rating: " + rating);
        return hotelMapper.toDTOs(hotel);
    }

    @Override
    public HotelDTO getHotelEntityById(Integer id) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityById(id).orElseThrow();
        log.info("Hotel found with id: " + hotel.getId());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public HotelDTO getHotelEntityByAddress(String address) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityByAddress(address).orElseThrow();
        log.info("Hotel found with name: " + hotel.getName());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public Set<HotelDTO> getAllHotelEntityByChainName(String name) {
        Set<HotelEntity> hotel = hotelEntityRepository.findAllHotelEntityByChainName(name);
        log.info("All hotels found with name: " + name);
        return hotelMapper.toDTOs(hotel);
    }

    @Override
    public void deleteHotelEntityById(Integer id) {
        hotelEntityRepository.deleteHotelEntityById(id);
    }

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        log.info("Creating a hotel chain: " + hotelDTO);
        return hotelMapper.toDTO(hotelEntityRepository.
                save(hotelMapper.toEntity(hotelDTO)));
    }

    @Override
    public HotelDTO updateHotel(HotelDTO hotelDTO, Integer id) {
        log.info("Updating hotel: " + hotelDTO);
        HotelEntity hotel = hotelEntityRepository.findHotelEntityById(id).orElseThrow(
                () -> new AppEntityNotFoundException("Hotel chain not found with id: " + id)
        );
        if(hotelDTO.name() != null) {
            hotel.setName(hotelDTO.name());
        }
        if(hotelDTO.address() != null) {
            hotel.setAddress(hotelDTO.address());
        }
        if(hotelDTO.rating() != null) {
            hotel.setRating(hotelDTO.rating());
        }
        if(hotelDTO.email() != null) {
            hotel.setEmail(hotelDTO.email());
        }
        if(hotelDTO.phoneNumber() != null) {
            hotel.setPhoneNumber(hotelDTO.phoneNumber());
        }
        return hotelMapper.toDTO(hotelEntityRepository.save(hotel));
    }


    /**

     @Override
    public HotelDTO getHotelEntityByName(String hotel_name) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityByName(hotel_name).orElseThrow();
        log.info("Hotel found with email: " + hotel.getEmail());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public HotelDTO getHotelEntityByAddress(String hotel_address) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityByAddress(hotel_address).orElseThrow();
        log.info("Hotel found with address: " + hotel.getAddress());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public HotelDTO getHotelEntityByEmail(String hotel_email) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityByName(hotel_email).orElseThrow();
        log.info("Hotel found with email: " + hotel.getEmail());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public HotelDTO getHotelEntityByPhoneNumber(String hotel_phone_number) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityByPhoneNumber(hotel_phone_number).orElseThrow();
        log.info("Hotel found with email: " + hotel.getEmail());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public HotelDTO getHotelEntityByRating(short hotel_rating) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityByRating(hotel_rating).orElseThrow();
        log.info("Hotel found with rating: " + hotel.getRating());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public HotelDTO getHotelEntityByChainHotelName(String chain_name) {
        HotelEntity hotel = hotelEntityRepository.findHotelEntityByChainHotelName(chain_name).orElseThrow();
        log.info("Hotel found with email: " + hotel.getChainHotel().getName());
        return hotelMapper.toDTO(hotel);
    }

    @Override
    public void updateHotelRating(int rating, int hotel_ID) {
        hotelEntityRepository.updateHotelRating(rating,hotel_ID);
        log.info("Hotel found with email: ");
    }

    @Override
    public void updateHotelName(String hotel_name, int hotel_ID) {
        hotelEntityRepository.updateHotelName(hotel_name,hotel_ID);
        log.info("Hotel name Updated: ");
    }

    @Override
    public void updateHotelAddress(String hotel_address, int hotel_ID) {
        hotelEntityRepository.updateHotelAddress(hotel_address,hotel_ID);
        log.info("Hotel address updated: ");}


    @Override
    public void deleteHotelEntityByID(int hotel_ID) {
        hotelEntityRepository.deleteHotelEntityByID(hotel_ID);
        log.info("Chain hotel deleted");
    }

    @Override
    public void insertHotelEntity(int hotel_ID, int chain_ID, String hotel_name, String hotel_address, String hotel_email, String hotel_phone_number, int rating) {
        hotelEntityRepository.insertHotelEntity(hotel_ID,chain_ID,hotel_name,hotel_address,hotel_email,hotel_phone_number,rating);
    }
    */
}
