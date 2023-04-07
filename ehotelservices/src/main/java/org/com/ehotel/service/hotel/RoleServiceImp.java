package org.com.ehotel.service.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.RoleDTO;
import org.com.ehotel.entity.hotel.RoleEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.hotel.RoleMapper;
import org.com.ehotel.repository.hotel.HotelEntityRepository;
import org.com.ehotel.repository.hotel.RoleEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/7/2023, Friday
 **/

@AllArgsConstructor @Service @Slf4j
public class RoleServiceImp implements RoleService {
    private final RoleEntityRepository roleRepository;
    private final HotelEntityRepository hotelRepository;
    private final RoleMapper roleMapper;
    @Override
    public RoleDTO findById(Integer id) {
        log.info("Getting role by id: " + id);
        return roleMapper.toDTO(
                roleRepository.findById(id)
                        .orElseThrow(() -> new AppEntityNotFoundException("Role not found"))
        );
    }

    @Override
    public Set<RoleDTO> findByHotelId(Integer hotelId) {
        log.info("Getting roles by hotel id: " + hotelId);
        return roleMapper.toDTOs(
                roleRepository.findRoleByHotelId(hotelId)
        );
    }

    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        if(roleDTO.hotel() == null || roleDTO.hotel().id() == null) {
            throw new BadRequestException("Hotel id is required");
        }
        if(!hotelRepository.existsById(roleDTO.hotel().id())) {
            throw new BadRequestException("Hotel not found");
        }
        if(roleDTO.title() == null || roleDTO.title().isEmpty()) {
            throw new BadRequestException("Role name is required");
        }
        log.info("Creating role: " + roleDTO.title());
        return roleMapper.toDTO(
                roleRepository.save(
                        roleMapper.toEntity(roleDTO)
                )
        );
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO, Integer id) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow(() -> new AppEntityNotFoundException("Role not found"));
        RoleEntity roleEntityUpdated = roleMapper.toEntity(roleDTO);
        if(roleEntityUpdated.getTitle() != null && !roleEntityUpdated.getTitle().isEmpty()) {
            roleEntity.setTitle(roleEntityUpdated.getTitle());
        }
        if(roleEntityUpdated.getDescription() != null && !roleEntityUpdated.getDescription().isEmpty()) {
            roleEntity.setDescription(roleEntityUpdated.getDescription());
        }
        log.info("Updating role: " + roleEntity.getTitle());
        return roleMapper.toDTO(
                roleRepository.save(roleEntity)
        );
    }

    @Override
    public void deleteById(Integer id) {
        if(!roleRepository.existsById(id)) {
            throw new AppEntityNotFoundException("Role not found");
        }
        log.info("Deleting role by id: " + id);
        roleRepository.deleteById(id);
    }
}
