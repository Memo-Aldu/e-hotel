package org.com.ehotel.service.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.ChainHotelDTO;
import org.com.ehotel.entity.hotel.ChainHotelEntity;
import org.com.ehotel.exceptions.AppEntityAlreadyExistException;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.hotel.ChainHotelMapper;
import org.com.ehotel.repository.hotel.ChainHotelEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/4/2023, Tuesday
 **/
@Service @AllArgsConstructor @Slf4j
public class ChainHotelServiceImp implements ChainHotelService{
    private final ChainHotelEntityRepository chainHotelEntityRepository;
    private final ChainHotelMapper chainHotelMapper;

    @Override
    public ChainHotelDTO getChainHotelById(Integer id) {
        log.info("Getting chain hotel by id: " + id);
        return chainHotelMapper.toDTO(
                chainHotelEntityRepository.findChainHotelEntityById(id)
                        .orElseThrow(() -> new AppEntityNotFoundException("Chain Hotel not found"))
        );
    }

    @Override
    public ChainHotelDTO createChainHotel(ChainHotelDTO chainHotelDTO) {
        validateFields(chainHotelDTO);
        log.info("Creating chain hotel: " + chainHotelDTO.name());
        return chainHotelMapper.toDTO(
                chainHotelEntityRepository.save(
                        chainHotelMapper.toEntity(chainHotelDTO)
                )
        );
    }
    
    @Override
    public Set<ChainHotelDTO> getAllChainHotelEntity(){
        Set<ChainHotelEntity> chain = chainHotelEntityRepository.findAllByChainHotelEntity();
        log.info("All hotels found with name: ");
        return chainHotelMapper.toDTOs(chain);
    }

    private void validateFields(ChainHotelDTO chainHotelDTO) {
        if(chainHotelDTO.name() == null || chainHotelDTO.name().isEmpty()) {
            throw new BadRequestException("Invalid chain hotel name");
        }
        if(chainHotelDTO.email() == null || chainHotelDTO.email().isEmpty()) {
            throw new BadRequestException("Invalid chain hotel email");
        }
        if(chainHotelDTO.phoneNumber() == null || chainHotelDTO.phoneNumber().isEmpty()) {
            throw new BadRequestException("Invalid chain hotel phone");
        }
        if(chainHotelDTO.address() == null || chainHotelDTO.address().isEmpty()) {
            throw new BadRequestException("Invalid chain hotel address");
        }
        if(chainHotelDTO.rating() == null || chainHotelDTO.rating() > 5 || chainHotelDTO.rating() < 0) {
            throw new BadRequestException("Invalid chain hotel rating");
        }
        if(chainHotelEntityRepository.existsChainHotelEntityByEmail(chainHotelDTO.email())) {
            throw new AppEntityAlreadyExistException("Chain hotel email already exists");
        }
        if(chainHotelEntityRepository.existsChainHotelEntityByPhoneNumber(chainHotelDTO.phoneNumber())) {
            throw new AppEntityAlreadyExistException("Chain hotel phone number already exists");
        }
    }

    @Override
    public ChainHotelDTO updateChainHotel(ChainHotelDTO chainHotelDTO, Integer id) {
        ChainHotelEntity chainHotelEntity = chainHotelEntityRepository.findChainHotelEntityById(id)
                .orElseThrow(() -> new AppEntityNotFoundException("Chain Hotel not found"));
        ChainHotelEntity chainHotelEntityUpdated = chainHotelMapper.toEntity(chainHotelDTO);
        // Update only the fields that are not null or empty
        if(chainHotelEntityUpdated.getName() != null && !chainHotelEntityUpdated.getName().isEmpty()) {
            chainHotelEntity.setName(chainHotelEntityUpdated.getName());
        }
        if(chainHotelEntityUpdated.getEmail() != null && !chainHotelEntityUpdated.getEmail().isEmpty()) {
            chainHotelEntity.setEmail(chainHotelEntityUpdated.getEmail());
        }
        if(chainHotelEntityUpdated.getPhoneNumber() != null && !chainHotelEntityUpdated.getPhoneNumber().isEmpty()) {
            chainHotelEntity.setPhoneNumber(chainHotelEntityUpdated.getPhoneNumber());
        }
        if(chainHotelEntityUpdated.getAddress() != null && !chainHotelEntityUpdated.getAddress().isEmpty()) {
            chainHotelEntity.setAddress(chainHotelEntityUpdated.getAddress());
        }
        if(chainHotelEntityUpdated.getRating() != null && chainHotelEntityUpdated.getRating() > 0 && chainHotelEntityUpdated.getRating() < 6) {
            chainHotelEntity.setRating(chainHotelEntityUpdated.getRating());
        }
        log.info("Updating chain hotel: " + chainHotelEntity.getName());
        return chainHotelMapper.toDTO(
                chainHotelEntityRepository.save(chainHotelEntity)
        );
    }

    @Override
    public void deleteChainHotelByID(Integer id) {
        if(!chainHotelEntityRepository.existsById(id)) {
            throw new AppEntityNotFoundException("Chain Hotel not found");
        }
        log.info("Deleting chain hotel by id: " + id);
        chainHotelEntityRepository.deleteById(id);
    }
}
