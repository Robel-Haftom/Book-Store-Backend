package com.robel.bookstore.controller;

import com.robel.bookstore.dto.ChangePasswordDTO;
import com.robel.bookstore.dto.UserCreateDTO;
import com.robel.bookstore.dto.UserResponseDTO;
import com.robel.bookstore.service.UserServices;
import com.robel.bookstore.validation.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(userServices.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("userId") Long userId,
                                                      @Validated (OnUpdate.class) @RequestBody UserCreateDTO userCreateDTO){
        return ResponseEntity.ok().body(userServices.updateUser(userId, userCreateDTO));
    }

    @PostMapping("/change-password/{userId}")
    public ResponseEntity<String> changePassword(@PathVariable("userId") Long userId,
                                                 @RequestBody ChangePasswordDTO changePasswordDTO){
        return ResponseEntity.ok().body(userServices.changePassword(userId, changePasswordDTO));
    }

}
