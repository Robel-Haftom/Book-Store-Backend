package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.UserCreateDTO;
import com.robel.bookstore.dto.UserResponseDTO;
import com.robel.bookstore.entity.User;
import com.robel.bookstore.entity.UserAddress;
import com.robel.bookstore.enums.Gender;

import java.time.LocalDate;

public class UserMapper {

    public static User mapToUser(UserCreateDTO userCreateDTO){
        return User.builder()
                .firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .userName(userCreateDTO.getUserName().toLowerCase())
                .email(userCreateDTO.getEmail())
                .phoneNumber(userCreateDTO.getPhoneNumber())
                .gender(Gender.valueOf(userCreateDTO.getGender().toUpperCase()))
                .dateOfBirth(LocalDate.parse(userCreateDTO.getDateOfBirth()))
                .address(UserAddress.builder()
                        .addressLine1(userCreateDTO.getAddressLine1())
                        .addressLine2(userCreateDTO.getAddressLine2())
                        .city(userCreateDTO.getCity())
                        .state(userCreateDTO.getState())
                        .country(userCreateDTO.getCountry())
                        .postalCode(userCreateDTO.getPostalCode())
                        .build())
                .build();

    }

    public static UserResponseDTO mapToUserResponseDTO(User user){
        return new UserResponseDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole().toString(),
                user.getProfileImgUrl(),
                user.getGender().toString(),
                user.getDateOfBirth().toString(),
                user.getAddress().getAddressLine1(),
                user.getAddress().getAddressLine2(),
                user.getAddress().getCity(),
                user.getAddress().getState(),
                user.getAddress().getPostalCode(),
                user.getAddress().getCountry(),
                user.getReviews().stream().map(ReviewMapper::mapToReviewResponseDTO).toList(),
                user.getLikes().stream().map(LikeMapper::mapToLikeResponseDTO).toList(),
                user.getBookmarks().stream().map(BookmarkMapper::mapToBookmarkResponseDTO).toList(),
                user.getOrders().stream().map(OrderMapper::mapToOrderResponseDTO).toList()
        );
    }

}
