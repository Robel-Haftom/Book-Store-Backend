package com.robel.bookstore.controller.superAdmin;

import com.robel.bookstore.dto.UserCreateDTO;
import com.robel.bookstore.dto.UserResponseDTO;
import com.robel.bookstore.service.UserServices;
import com.robel.bookstore.validation.OnCreate;
import com.robel.bookstore.validation.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/super-admin")
public class SuperAdminController {

    @Autowired
    private UserServices userServices;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping()
    public ResponseEntity<UserResponseDTO> register(@Validated(OnCreate.class) @RequestBody UserCreateDTO userCreateDTO){
        return ResponseEntity.ok().body(userServices.createAdmin(userCreateDTO));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(userServices.getUserById(userId));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok().body(userServices.getAllUsers());
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("userId") Long userId,
                                                      @Validated (OnUpdate.class) @RequestBody UserCreateDTO userCreateDTO){
        return ResponseEntity.ok().body(userServices.updateUser(userId, userCreateDTO));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
        userServices.deleteUser(userId);
        return ResponseEntity.ok().body("user deleted successfully");
    }

}
