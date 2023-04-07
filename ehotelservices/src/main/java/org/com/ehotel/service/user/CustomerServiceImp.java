package org.com.ehotel.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.entity.user.AppUserEntity;
import org.com.ehotel.entity.user.CustomerEntity;
import org.com.ehotel.enums.AppRoles;
import org.com.ehotel.exceptions.AppEntityAlreadyExistException;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.user.CustomerMapper;
import org.com.ehotel.repository.user.AppUserEntityRepository;
import org.com.ehotel.repository.user.CustomerEntityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/5/2023, Wednesday
 **/
@AllArgsConstructor @Service @Slf4j
public class CustomerServiceImp implements CustomerService {
    private final CustomerEntityRepository customerRepo;
    private final CustomerMapper customerMapper;
    private final AppUserEntityRepository appUserRepo;
    @Override
    public CustomerDTO findCustomerByNas(String nas) {
        log.info("Finding customer by nas: " + nas);
        return customerMapper.toDTO(
                customerRepo.findCustomerByNAS(nas)
                        .orElseThrow(() -> new AppEntityNotFoundException("Customer not found with nas: " + nas)
        ));
    }

    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        log.info("Finding customer by email: " + email);
        return customerMapper.toDTO(
                customerRepo.findCustomerByEmail(email)
                        .orElseThrow(() -> new AppEntityNotFoundException("Customer not found with email: " + email)
        ));
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        validateCustomerFields(customerDTO);
        CustomerEntity  customerEntity = customerMapper.toEntity(customerDTO);
        if(customerDTO.registrationDate() == null || customerDTO.registrationDate().toString().isEmpty()) {
            customerEntity.setRegistrationDate(java.sql.Date.valueOf(LocalDate.now()));
        }
        AppUserEntity appUserEntity = appUserRepo.findById(customerDTO.email())
                .orElseThrow(() -> new AppEntityNotFoundException("App user not found with email: " + customerDTO.email()));
        // set the app user role to customer
        appUserEntity.setUserRole(AppRoles.ROLE_CUSTOMER);
        customerEntity.setAppUser(appUserEntity);
        return customerMapper.toDTO(customerRepo.save(customerEntity));
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, String email) {
        CustomerEntity customerEntity = customerRepo.findCustomerByEmail(email)
                .orElseThrow(() -> new AppEntityNotFoundException("Customer not found with email: " + email));
        CustomerEntity updatedCustomer = customerMapper.toEntity(customerDTO);
        // check if the customer is trying to update the phone number
        if(customerDTO.phoneNumber() != null && !customerDTO.phoneNumber().isEmpty() && !customerDTO.phoneNumber().equals(customerEntity.getPhoneNumber())) {
            if (customerRepo.existsByPhoneNumber(customerDTO.phoneNumber())) {
                throw new AppEntityAlreadyExistException("Customer already exist with phone number: " + customerDTO.phoneNumber());
            }
            customerEntity.setPhoneNumber(customerDTO.phoneNumber());
        }
        if(customerDTO.firstName() != null && !customerDTO.firstName().isEmpty()) {
            customerEntity.setFirstName(updatedCustomer.getFirstName());
        }
        if(customerDTO.middleName() != null && !customerDTO.middleName().isEmpty()) {
            customerEntity.setMiddleName(updatedCustomer.getMiddleName());
        }
        if(customerDTO.lastName() != null && !customerDTO.lastName().isEmpty()) {
            customerEntity.setLastName(updatedCustomer.getLastName());
        }
        if(customerDTO.address() != null && !customerDTO.address().isEmpty()) {
            customerEntity.setAddress(updatedCustomer.getAddress());
        }
        if(customerDTO.dateOfBirth() != null && !customerDTO.dateOfBirth().toString().isEmpty()) {
            customerEntity.setDateOfBirth(updatedCustomer.getDateOfBirth());
        }
        log.info("Updating customer with nas: " + customerEntity.getNAS());
        return customerMapper.toDTO(customerRepo.save(customerEntity));
    }

    @Override
    public void delete(String nas) {
        if(!customerRepo.existsById(nas)) {
            throw new AppEntityNotFoundException("Customer not found with nas: " + nas);
        }
        log.info("Deleting customer with nas: " + nas);
        customerRepo.deleteById(nas);
    }

    @Override
    public void deleteByEmail(String email) {
        if(!customerRepo.existsByEmail(email)) {
            throw new AppEntityNotFoundException("Customer not found with email: " + email);
        }
        log.info("Deleting customer with email: " + email);
        customerRepo.deleteByEmail(email);
    }

    private void validateCustomerFields(CustomerDTO customerDTO) {
        if(customerDTO.email() == null || customerDTO.email().isEmpty()) {
            throw new AppEntityNotFoundException("Invalid customer email");
        }
        if(customerDTO.phoneNumber() == null || customerDTO.phoneNumber().isEmpty()) {
            throw new AppEntityNotFoundException("Invalid customer phone");
        }
        if(customerDTO.firstName() == null || customerDTO.firstName().isEmpty()) {
            throw new AppEntityNotFoundException("Invalid customer first name");
        }
        if(customerDTO.lastName() == null || customerDTO.lastName().isEmpty()) {
            throw new AppEntityNotFoundException("Invalid customer last name");
        }
        if(customerDTO.address() == null || customerDTO.address().isEmpty()) {
            throw new AppEntityNotFoundException("Invalid customer address");
        }
        if(customerDTO.dateOfBirth() == null || customerDTO.dateOfBirth().toString().isEmpty()) {
            throw new AppEntityNotFoundException("Invalid customer date of birth");
        }
        // check if the customer age is greater than 18
        if(Period.between(customerDTO.dateOfBirth().toLocalDate(), LocalDate.now()).getYears() < 18) {
            throw new BadRequestException("Customer must be 18 years old or older");
        }
        if(customerRepo.existsByEmail(customerDTO.email())) {
            throw new AppEntityAlreadyExistException("Customer already exists with email: " + customerDTO.email());
        }
        if (customerRepo.existsByPhoneNumber(customerDTO.phoneNumber())) {
            throw new AppEntityAlreadyExistException("Customer already exists with phone number: " + customerDTO.phoneNumber());
        }
    }
}
