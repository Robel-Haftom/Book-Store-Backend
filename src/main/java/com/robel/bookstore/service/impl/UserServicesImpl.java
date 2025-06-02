package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.*;
import com.robel.bookstore.entity.Cart;
import com.robel.bookstore.entity.User;
import com.robel.bookstore.entity.UserAddress;
import com.robel.bookstore.enums.Gender;
import com.robel.bookstore.enums.UserRole;
import com.robel.bookstore.exception.EmailExistException;
import com.robel.bookstore.exception.PasswordIncorrectException;
import com.robel.bookstore.exception.UserNameExistException;
import com.robel.bookstore.exception.UserNotFoundException;
import com.robel.bookstore.mapper.UserMapper;
import com.robel.bookstore.repository.CartRepository;
import com.robel.bookstore.repository.UserRepository;
import com.robel.bookstore.security.CustomUserDetailService;
import com.robel.bookstore.service.UserServices;
import com.robel.bookstore.security.JwtUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilityService jwtUtilityService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Value("${base.url}")
    String profileBaseUrl;

    @Override
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        userRepository.findByUserName(userCreateDTO.getUserName())
                .ifPresent((user) -> {
                    throw new UserNameExistException("this username "
                            + userCreateDTO.getUserName() + " already taken please use other user name ");
                });

        userRepository.findByEmail(userCreateDTO.getEmail())
                .ifPresent((user) -> {
                    throw new EmailExistException("this email "
                            + userCreateDTO.getEmail() + " already taken please use other user name ");
                });

        // creating cart for new user
        Cart cart = Cart.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .totalPrice((double)0)
                .build();


        User user = UserMapper.mapToUser(userCreateDTO);
        user.setHashedPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setRole(UserRole.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setProfileImgUrl(profileBaseUrl + "newuser/" + "user.png");

        User savedUser = userRepository.save(user);

        cart.setUser(savedUser);
        Cart savedCart = cartRepository.save(cart);

        savedUser.setCart(savedCart);
        User userWithCart = userRepository.save(savedUser);

        return UserMapper.mapToUserResponseDTO(userWithCart);
    }

    @Override
    public UserResponseDTO createAdmin(UserCreateDTO userCreateDTO) {
        userRepository.findByUserName(userCreateDTO.getUserName())
                .ifPresent((user) -> {
                    throw new UserNameExistException("there is username "
                            + userCreateDTO.getUserName() + " already taken please use other user name ");
                });

        userRepository.findByEmail(userCreateDTO.getEmail())
                .ifPresent((user) -> {
                    throw new EmailExistException("this email "
                            + userCreateDTO.getEmail() + " already taken please use other user name ");
                });

        User admin = UserMapper.mapToUser(userCreateDTO);
        admin.setHashedPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        admin.setRole(UserRole.ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        admin.setEnabled(true);
        admin.setAccountNonLocked(true);
        admin.setAccountNonExpired(true);
        admin.setCredentialsNonExpired(true);
        admin.setProfileImgUrl(profileBaseUrl + "newuser/" + "user.png");

        //creating cart for the admin if he is changed to user role he can use it
        Cart cart = Cart.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .totalPrice((double)0)
                .build();

        User savedAdmin = userRepository.save(admin);
        cart.setUser(savedAdmin);
        Cart savedCart = cartRepository.save(cart);

        savedAdmin.setCart(savedCart);
        User adminWithCart = userRepository.save(savedAdmin);

        return UserMapper.mapToUserResponseDTO(adminWithCart);
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user found with this  id: " + userId));

        return UserMapper.mapToUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper::mapToUserResponseDTO).toList();
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserCreateDTO userCreateDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user found with this id: " + userId));

        boolean isUpdated = false;

        if(Objects.nonNull(userCreateDTO.getFirstName()) && !"".equalsIgnoreCase(userCreateDTO.getFirstName())){
            existingUser.setFirstName(userCreateDTO.getFirstName());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getLastName()) && !"".equalsIgnoreCase(userCreateDTO.getLastName())){
            existingUser.setLastName(userCreateDTO.getLastName());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getUserName()) && !"".equalsIgnoreCase(userCreateDTO.getUserName())){
            userRepository.findByUserNameAndUserIdNot(userCreateDTO.getUserName(), userId)
                    .ifPresent((user) -> {
                        throw new UserNameExistException("user name " + userCreateDTO.getUserName()
                                +" is already taken please use other user name");
                    });
            existingUser.setUserName(userCreateDTO.getUserName());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getEmail()) && !"".equalsIgnoreCase(userCreateDTO.getEmail())){
            userRepository.findByEmailAndUserIdNot(userCreateDTO.getEmail(), userId)
                    .ifPresent((user) -> {
                        throw new EmailExistException("this email "+ userCreateDTO
                                .getEmail() +" is already taken please use other email id");
                    });
            existingUser.setEmail(userCreateDTO.getEmail());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getPassword()) && !"".equalsIgnoreCase(userCreateDTO.getPassword())){
            existingUser.setHashedPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getPhoneNumber()) && !"".equalsIgnoreCase(userCreateDTO.getPhoneNumber())){
            existingUser.setPhoneNumber(userCreateDTO.getPhoneNumber());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getDateOfBirth()) && !"".equalsIgnoreCase(userCreateDTO.getDateOfBirth())){
            existingUser.setDateOfBirth(LocalDate.parse(userCreateDTO.getDateOfBirth()));
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getGender()) && !"".equalsIgnoreCase(userCreateDTO.getGender())){
            existingUser.setGender(Gender.valueOf(userCreateDTO.getGender()));
            isUpdated = true;
        }

        UserAddress currentAddress = existingUser.getAddress();

        if(Objects.nonNull(userCreateDTO.getAddressLine1()) && !"".equalsIgnoreCase(userCreateDTO.getAddressLine1())){
            currentAddress.setAddressLine1(userCreateDTO.getAddressLine1());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getAddressLine2()) && !"".equalsIgnoreCase(userCreateDTO.getAddressLine2())){
            currentAddress.setAddressLine2(userCreateDTO.getAddressLine2());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getCity()) && !"".equalsIgnoreCase(userCreateDTO.getCity())){
            currentAddress.setCity(userCreateDTO.getCity());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getState()) && !"".equalsIgnoreCase(userCreateDTO.getState())){
            currentAddress.setState(userCreateDTO.getState());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getCountry()) && !"".equalsIgnoreCase(userCreateDTO.getCountry())){
            currentAddress.setCountry(userCreateDTO.getCountry());
            isUpdated = true;
        }
        if(Objects.nonNull(userCreateDTO.getPostalCode()) && !"".equalsIgnoreCase(userCreateDTO.getPostalCode())){
            currentAddress.setPostalCode(userCreateDTO.getPostalCode());
            isUpdated = true;
        }

        if(isUpdated) {
            existingUser.setUpdatedAt(LocalDateTime.now());
            existingUser.setAddress(currentAddress);
            User updatedUser = userRepository.save(existingUser);
            return UserMapper.mapToUserResponseDTO(updatedUser);
        };

        return UserMapper.mapToUserResponseDTO(existingUser);
    }

    @Override
    public void deleteUser(Long useId) {
        User user = userRepository.findById(useId)
                .orElseThrow(() -> new UserNotFoundException("No user found with this id: " + useId));

        userRepository.delete(user);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO userLoginDTO) {
        User user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("No user found with this email id: " + userLoginDTO.getEmail()));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getEmail(),
                            userLoginDTO.getPassword()
                    )
            );

             UserDetails userDetails = customUserDetailService.loadUserByUsername(userLoginDTO.getEmail());
             String accessToken = jwtUtilityService.generateToken(userDetails);

            return UserMapper.mapTOLoginResponseDTO(UserMapper.mapToUserResponseDTO(user), accessToken);
        } catch (UserNotFoundException exception){
            throw exception;
        } catch (AuthenticationException exception){
            throw new PasswordIncorrectException("Password is incorrect");
        }

    }

    @Override
    public void logout() {
        System.out.println("User logged out successfully");
    }

    @Override
    public String changePassword(Long userId, ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user found with this id: " + userId));
        if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getHashedPassword())){
            throw new UserNotFoundException("Old password is not correct");
        }
        if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())){
            throw new UserNotFoundException("New password and confirm password are not same");
        }
        user.setHashedPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        user.setUpdatedAt(LocalDateTime.now());
        User updatedUser = userRepository.save(user);
        return "Password changed successfully";
    }
}
