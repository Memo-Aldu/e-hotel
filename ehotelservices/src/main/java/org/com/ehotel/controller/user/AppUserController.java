package org.com.ehotel.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/15/2023, Wednesday
 **/
@Slf4j @AllArgsConstructor @RestController
@RequestMapping("/api/v1/user")
public class AppUserController {
    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<AppUserDTO> getUserByEmail(@PathVariable String email) {
        log.info("Getting user with email: " + email);
        return ResponseEntity.ok(userService.getUserByEmail(email));

    }
}
