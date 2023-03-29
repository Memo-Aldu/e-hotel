package org.com.ehotel.service.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.ChainHotelDTO;
import org.com.ehotel.entity.hotel.ChainHotelEntity;
import org.com.ehotel.mapper.hotel.ChainHotelMapper;
import org.com.ehotel.repository.hotel.ChainHotelEntityRepository;
import org.springframework.stereotype.Service;

import org.com.ehotel.exceptions.AppEntityNotFoundException;

import java.util.Set;


@Service
@AllArgsConstructor
@Slf4j
public class ChainHotelServiceImp implements ChainHotelService {
    private final ChainHotelMapper chainHotelMapper;
    private final ChainHotelEntityRepository chainHotelEntityRepository;


    @Override
    public ChainHotelDTO getChainHotelEntityByName(String chain_name) {
        ChainHotelEntity chainHotel = chainHotelEntityRepository.findChainHotelEntityByName(chain_name).orElseThrow();
        log.info("Chain hotel found with email: " + chainHotel.getEmail());
        return chainHotelMapper.toDTO(chainHotel);
    }

    @Override
    public Set<ChainHotelDTO> getAllByChainHotelEntityByRating(short rating) {
        Set<ChainHotelEntity> chainHotel = chainHotelEntityRepository.findAllByChainHotelEntityByRating(rating);
        log.info("Chain hotel found with rating: " + rating);
        return chainHotelMapper.toDTOs(chainHotel);
    }

    @Override
    public ChainHotelDTO getChainHotelEntityById(Integer id) {
        ChainHotelEntity chainHotel = chainHotelEntityRepository.findChainHotelEntityById(id).orElseThrow();
        log.info("Chain hotel found with rating: " + id);
        return chainHotelMapper.toDTO(chainHotel);
    }

    @Override
    public ChainHotelDTO createChain(ChainHotelDTO chainHotelDTO) {
        log.info("Creating a hotel chain: " + chainHotelDTO);
        return chainHotelMapper.toDTO(chainHotelEntityRepository.
                save(chainHotelMapper.toEntity(chainHotelDTO)));
    }

    @Override
    public ChainHotelDTO updateChain(ChainHotelDTO chainHotelDTO, Integer id) {
        log.info("Updating hotel chain: " + chainHotelDTO);
        ChainHotelEntity chainHotel = chainHotelEntityRepository.findChainHotelEntityById(id).orElseThrow(
                () -> new AppEntityNotFoundException("Hotel chain not found with id: " + id)
        );
        if(chainHotelDTO.name() != null) {
            chainHotel.setName(chainHotelDTO.name());
        }
        if(chainHotelDTO.address() != null) {
            chainHotel.setAddress(chainHotelDTO.address());
        }
        if(chainHotelDTO.rating() != null) {
            chainHotel.setRating(chainHotelDTO.rating());
        }
        if(chainHotelDTO.email() != null) {
            chainHotel.setEmail(chainHotelDTO.email());
        }
        if(chainHotelDTO.phoneNumber() != null) {
            chainHotel.setPhoneNumber(chainHotelDTO.phoneNumber());
        }
        return chainHotelMapper.toDTO(chainHotelEntityRepository.save(chainHotel));
    }
    @Override
    public void deleteChainHotelEntityByID(Integer chain_ID) {
        chainHotelEntityRepository.deleteChainHotelEntityByID(chain_ID);
        log.info("Chain hotel deleted");
    }
}

