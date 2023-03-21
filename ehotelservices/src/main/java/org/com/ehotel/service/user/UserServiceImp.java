package org.com.ehotel.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.requests.RegistrationRequest;
import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.entity.user.AppUserEntity;
import org.com.ehotel.exceptions.AppEntityAlreadyExistException;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.mapper.user.AppUserMapper;
import org.com.ehotel.repository.user.AppUserEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/15/2023, Wednesday
 **/
@Service @AllArgsConstructor @Slf4j
public class UserServiceImp implements UserService {
    private final AppUserMapper appUserMapper;
    private final AppUserEntityRepository userRepo;
    @Override
    public AppUserDTO getUserByEmail(String email) {
        AppUserEntity user = userRepo.findAppUserEntityByEmail(email)
                .orElseThrow(() -> new AppEntityNotFoundException("User not found with email: " + email));
        return appUserMapper.toDTO(user);
    }

    @Override
    public AppUserDTO registerUser(RegistrationRequest request) {
        log.info("Registering user: " + request);
        // Check if user already exists
        Optional<AppUserEntity> optionalUser = userRepo
                .findAppUserEntityByEmail(request.email());
        // If user already exists, throw exception
        if (optionalUser.isPresent()) {
            log.info("User already exists with email: " + request.email());
            throw new AppEntityAlreadyExistException("User already exists with email: " + request.email());
        } else {
            // If user does not exist, create user and save it
            // Check if user role is null
            if(request.userRole() != null)
                // Modifying query cant return Entity, so we have to return email
                userRepo.insertUserData(request.email(), request.password(), request.userRole().name());
            else {
                userRepo.insertUserData(request.email(), request.password());

            }
            AppUserEntity user = userRepo.findAppUserEntityByEmail(request.email())
                    .orElseThrow(() -> new AppEntityNotFoundException("Failed to register user: " + request.email()));
            return appUserMapper.toDTO(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUserDTO user = getUserByEmail(email);
        log.info("User found with email: " + user.email());
        return user;
    }
}
