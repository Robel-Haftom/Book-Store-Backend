package com.robel.bookstore.service;

import com.robel.bookstore.dto.*;

import java.util.List;

public interface UserServices {

    UserResponseDTO createUser(UserCreateDTO userCreateDTO);

    UserResponseDTO createAdmin(UserCreateDTO userCreateDTO);

    UserResponseDTO getUserById(Long userId);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long userId, UserCreateDTO userCreateDTO);

    void deleteUser(Long useId);

    String login(LoginRequestDTO userLoginDTO);

    void logout();

    String changePassword(Long userId, ChangePasswordDTO changePasswordDTO);
}
