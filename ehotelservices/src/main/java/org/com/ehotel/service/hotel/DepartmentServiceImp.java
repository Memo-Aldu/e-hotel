package org.com.ehotel.service.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.DepartmentDTO;
import org.com.ehotel.entity.hotel.DepartmentEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.hotel.DepartmentMapper;
import org.com.ehotel.repository.hotel.DepartmentEntityRepository;
import org.com.ehotel.repository.hotel.HotelEntityRepository;
import org.com.ehotel.repository.user.EmployeeEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/7/2023, Friday
 **/
@Service @AllArgsConstructor @Slf4j
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentEntityRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final EmployeeEntityRepository employeeRepository;
    private final HotelEntityRepository hotelRepository;
    @Override
    public DepartmentDTO findById(Integer id) {
        log.info("Getting department by id: " + id);
        return departmentMapper.toDTO(
                departmentRepository.findDepartmentById(id)
                        .orElseThrow(() -> new AppEntityNotFoundException("Department not found"))
        );
    }

    @Override
    public Set<DepartmentDTO> findByManagerNAS(String managerNAS) {
        log.info("Getting department by manager NAS: " + managerNAS);
        return departmentMapper.toDTOs(
                departmentRepository.findDepartmentManagerNAS(managerNAS)
        );
    }

    @Override
    public Set<DepartmentDTO> findByHotelId(Integer hotelId) {
        log.info("Getting departments by hotel id: " + hotelId);
        return departmentMapper.toDTOs(
                departmentRepository.findDepartmentByHotelId(hotelId)
        );
    }

    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        if(departmentDTO.hotel() == null || departmentDTO.hotel().id() == null) {
            throw new BadRequestException("Hotel id is required");
        }
        if(!hotelRepository.existsById(departmentDTO.hotel().id())) {
            throw new BadRequestException("Hotel not found");
        }
        if(departmentDTO.managerNAS() == null || departmentDTO.managerNAS().isEmpty()) {
            throw new BadRequestException("Manager NAS is required");
        }
        if(!employeeRepository.existsByNAS(departmentDTO.managerNAS())) {
            throw new BadRequestException("Manager not found");
        }
        if(departmentDTO.name() == null || departmentDTO.name().isEmpty()) {
            throw new BadRequestException("Department name is required");
        }
        log.info("Creating department: " + departmentDTO);
        return departmentMapper.toDTO(
                departmentRepository.save(
                        departmentMapper.toEntity(departmentDTO)
                )
        );
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO, Integer id) {
        DepartmentEntity departmentEntity = departmentRepository.findDepartmentById(id)
                .orElseThrow(() -> new AppEntityNotFoundException("Department not found"));
        // can't update hotel id;
        DepartmentEntity updatedDepartmentEntity = departmentMapper.toEntity(departmentDTO);
        if(updatedDepartmentEntity.getName() != null && !updatedDepartmentEntity.getName().isEmpty()) {
            departmentEntity.setName(updatedDepartmentEntity.getName());
        }
        if(departmentDTO.managerNAS() != null && !departmentDTO.managerNAS().isEmpty()) {
            departmentEntity.setManager(updatedDepartmentEntity.getManager());
        }
        if(!employeeRepository.existsByNAS(departmentDTO.managerNAS())) {
            throw new BadRequestException("Manager not found");
        }
        log.info("Updating department: " + departmentEntity);
        return departmentMapper.toDTO(
                departmentRepository.save(departmentEntity)
        );
    }

    @Override
    public void deleteById(Integer id) {
        if(!departmentRepository.existsById(id)) {
            throw new AppEntityNotFoundException("Department not found");
        }
        log.info("Deleting department by id: " + id);
        departmentRepository.deleteById(id);
    }
}
