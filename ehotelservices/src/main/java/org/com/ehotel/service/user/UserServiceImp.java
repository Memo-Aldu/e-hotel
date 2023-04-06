package org.com.ehotel.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.entity.user.AppUserEntity;
import org.com.ehotel.enums.AppRoles;
import org.com.ehotel.exceptions.AppEntityAlreadyExistException;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
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
    public AppUserDTO registerUser(AppUserDTO appUserDTO) {
        AppUserEntity userToSave = appUserMapper.toEntity(appUserDTO);
        // Check if user already exists
        if(userRepo.existsByEmail(appUserDTO.email())) {
            throw new AppEntityAlreadyExistException("User already exists with email: " + appUserDTO.email());
        }
        if(appUserDTO.password() == null || appUserDTO.password().isEmpty() || appUserDTO.password().length() < 4) {
            throw new BadRequestException("Password must be at least 4 characters");
        }
        if(appUserDTO.userRole() == null || appUserDTO.userRole().name().isEmpty()) {
            log.info("User role not provided, setting to default: " + AppRoles.ROLE_USER.name());
            userToSave.setUserRole(AppRoles.ROLE_USER);
        }
        return appUserMapper.toDTO(userRepo.save(userToSave));
    }

    @Override
    public AppUserDTO updateUser(String email, AppUserDTO appUserDTO) {
        AppUserEntity appUserEntity = userRepo
                .findAppUserEntityByEmail(email).orElseThrow(
                        () -> new AppEntityNotFoundException("User not found with email: " + email));
        if(appUserDTO.password() != null && !appUserDTO.password().isEmpty()) {
            if(appUserDTO.password().length() < 4) {
                throw new BadRequestException("Password must be at least 4 characters");
            }
            appUserEntity.setPassword(appUserDTO.password());
        }
        if(appUserDTO.userRole() != null && !appUserDTO.userRole().name().isEmpty()) {
            appUserEntity.setUserRole(appUserDTO.userRole());
        }
        return appUserMapper.toDTO(userRepo.save(appUserEntity));
    }

    @Override
    public void deleteUser(String email) {
        AppUserEntity user = userRepo.findAppUserEntityByEmail(email)
                .orElseThrow(() -> new AppEntityNotFoundException("User not found with email: " + email));
        userRepo.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user by email: " + email);
        return getUserByEmail(email);
    }
}
