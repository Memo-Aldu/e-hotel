package org.com.ehotel.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.entity.user.AppUserEntity;
import org.com.ehotel.mapper.user.AppUserMapper;
import org.com.ehotel.repository.user.AppUserEntityRepository;
import org.springframework.stereotype.Service;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/15/2023, Wednesday
 **/
@Service @AllArgsConstructor @Slf4j
public class UserServiceImp implements UserService {
    private final AppUserMapper appUserMapper;
    private final AppUserEntityRepository appUserEntityRepository;
    @Override
    public AppUserDTO getUserByEmail(String email) {
        AppUserEntity user = appUserEntityRepository.findAppUserEntityByEmail(email)
                .orElseThrow();
        log.info("User found with email: " + user.getEmail());
        return appUserMapper.toDTO(user);
    }
}
