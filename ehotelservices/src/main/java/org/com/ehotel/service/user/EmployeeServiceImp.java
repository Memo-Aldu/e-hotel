package org.com.ehotel.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.user.EmployeeDTO;
import org.com.ehotel.entity.user.EmployeeEntity;
import org.com.ehotel.exceptions.AppEntityAlreadyExistException;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.user.EmployeeMapper;
import org.com.ehotel.repository.user.AppUserEntityRepository;
import org.com.ehotel.repository.user.EmployeeEntityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/5/2023, Wednesday
**/
@AllArgsConstructor @Service @Slf4j
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeEntityRepository employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final AppUserEntityRepository appUserRepo;

    @Override
    public EmployeeDTO findEmployeeByNas(String nas) {
        log.info("Finding employee by nas: " + nas);
        return employeeMapper.toDTO(
                employeeRepo.findEmployeeByNAS(nas)
                        .orElseThrow(() -> new AppEntityNotFoundException("Employee not found with nas: " + nas))
        );
    }

    @Override
    public EmployeeDTO findEmployeeByEmail(String email) {
        log.info("Finding employee by email: " + email);
        return employeeMapper.toDTO(
                employeeRepo.findEmployeeByEmail(email)
                        .orElseThrow(() -> new AppEntityNotFoundException("Employee not found with email: " + email))
        );
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        validateEmployeeFields(employeeDTO);
        EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeDTO);
        if(employeeDTO.registrationDate() == null || employeeDTO.registrationDate().toString().isEmpty()) {
            employeeEntity.setRegistrationDate(java.sql.Date.valueOf(LocalDate.now()));
        }
        if(!appUserRepo.existsByEmail(employeeDTO.email())) {
            throw new AppEntityNotFoundException("App user not found with email: " + employeeDTO.email());
        }
        return employeeMapper.toDTO(employeeRepo.save(employeeEntity));
    }
    // TODO: Update email, role, department
    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO, String email) {
        EmployeeEntity employeeEntity = employeeRepo.findEmployeeByEmail(email)
                .orElseThrow(() -> new AppEntityNotFoundException("Employee not found with email: " + email));
        // check if the customer is trying to update the phone number
        if(employeeDTO.phoneNumber() != null && !employeeDTO.phoneNumber().isEmpty() && !employeeDTO.phoneNumber().equals(employeeEntity.getPhoneNumber())) {
            if (employeeRepo.existsByPhoneNumber(employeeDTO.phoneNumber())) {
                throw new AppEntityAlreadyExistException("Customer already exist with phone number: " + employeeDTO.phoneNumber());
            }
            employeeEntity.setPhoneNumber(employeeDTO.phoneNumber());
        }
        if(employeeDTO.firstName() != null && !employeeDTO.firstName().isEmpty() && !employeeDTO.firstName().equals(employeeEntity.getFirstName())) {
            employeeEntity.setFirstName(employeeDTO.firstName());
        }
        if(employeeDTO.middleName() != null && !employeeDTO.middleName().isEmpty() && !employeeDTO.middleName().equals(employeeEntity.getMiddleName())) {
            employeeEntity.setMiddleName(employeeDTO.middleName());
        }
        if(employeeDTO.lastName() != null && !employeeDTO.lastName().isEmpty() && !employeeDTO.lastName().equals(employeeEntity.getLastName())) {
            employeeEntity.setLastName(employeeDTO.lastName());
        }
        if(employeeDTO.address() != null && !employeeDTO.address().isEmpty() && !employeeDTO.address().equals(employeeEntity.getAddress())) {
            employeeEntity.setAddress(employeeDTO.address());
        }
        if(employeeDTO.salary() != null && !employeeDTO.salary().toString().isEmpty() && !employeeDTO.salary().equals(employeeEntity.getSalary())) {
            if(employeeDTO.salary() < 0) {
                throw new BadRequestException("Salary cannot be negative");
            }
            employeeEntity.setSalary(employeeDTO.salary());
        }
        if(employeeDTO.dateOfBirth() != null && !employeeDTO.dateOfBirth().toString().isEmpty() && !employeeDTO.dateOfBirth().equals(employeeEntity.getDateOfBirth())) {
            employeeEntity.setDateOfBirth(employeeDTO.dateOfBirth());
        }
        log.info("Updating employee with email: " + email);
        return employeeMapper.toDTO(employeeRepo.save(employeeEntity));
    }

    @Override
    public void delete(String nas) {
        if(!employeeRepo.existsByNAS(nas)) {
            throw new AppEntityNotFoundException("Employee not found with nas: " + nas);
        }
        log.info("Deleting employee with nas: " + nas);
        employeeRepo.deleteEmployeeByNAS(nas);
    }

    @Override
    public void deleteByEmail(String email) {
        if(!employeeRepo.existsByEmail(email)) {
            throw new AppEntityNotFoundException("Employee not found with email: " + email);
        }
        log.info("Deleting employee with email: " + email);
        employeeRepo.deleteEmployeeByEmail(email);
    }

    private void validateEmployeeFields(EmployeeDTO employeeDTO) {
        if(employeeDTO.NAS() == null || employeeDTO.NAS().isEmpty()) {
            throw new BadRequestException("NAS is required");
        }
        if(employeeDTO.firstName() == null || employeeDTO.firstName().isEmpty()) {
            throw new BadRequestException("First name is required");
        }
        if(employeeDTO.lastName() == null || employeeDTO.lastName().isEmpty()) {
            throw new BadRequestException("Last name is required");
        }
        if(employeeDTO.email() == null || employeeDTO.email().isEmpty()) {
            throw new BadRequestException("Email is required");
        }
        if(employeeDTO.phoneNumber() == null || employeeDTO.phoneNumber().isEmpty()) {
            throw new BadRequestException("Phone number is required");
        }
        if(employeeDTO.address() == null || employeeDTO.address().isEmpty()) {
            throw new BadRequestException("Address is required");
        }
        if(employeeDTO.salary() == null || employeeDTO.salary().toString().isEmpty()) {
            throw new BadRequestException("Invalid salary");
        }
        if(employeeDTO.role().id() == null) {
            throw new BadRequestException("Role is required");
        }
        if(employeeDTO.department().id() == null) {
            throw new BadRequestException("Department is required");
        }
        if(employeeDTO.dateOfBirth() == null || employeeDTO.dateOfBirth().toString().isEmpty()) {
            throw new BadRequestException("Invalid date of birth");
        }
        if(employeeDTO.salary().compareTo(0.0) < 0) {
            throw new BadRequestException("Salary must be greater than 0");
        }
    }
}
