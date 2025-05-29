package com.robel.bookstore.controller.publicRoutes;

import com.robel.bookstore.dto.LoginRequestDTO;
import com.robel.bookstore.dto.UserCreateDTO;
import com.robel.bookstore.dto.UserResponseDTO;
import com.robel.bookstore.service.UserServices;
import com.robel.bookstore.validation.OnCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/public/auth/")
public class AuthController {

    @Autowired
    private UserServices userServices;

    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> register(@Validated(OnCreate.class) @RequestBody UserCreateDTO userCreateDTO){
        return ResponseEntity.ok().body(userServices.createUser(userCreateDTO));
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@Validated(OnCreate.class) @RequestBody LoginRequestDTO userLoginDTO){
        return ResponseEntity.ok().body(
                Map.of("accessToken", userServices.login(userLoginDTO))
        );
    }

    @PostMapping("logout")
    public ResponseEntity<String> login(){
        userServices.logout();
        return ResponseEntity.ok().body("Logged out successfully");
    }

}
