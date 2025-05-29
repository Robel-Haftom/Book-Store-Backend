package com.robel.bookstore.controller.user;

import com.robel.bookstore.dto.ChangePasswordDTO;
import com.robel.bookstore.dto.UserCreateDTO;
import com.robel.bookstore.dto.UserResponseDTO;
import com.robel.bookstore.entity.User;
import com.robel.bookstore.exception.UserNotFoundException;
import com.robel.bookstore.repository.UserRepository;
import com.robel.bookstore.service.FileService;
import com.robel.bookstore.service.UserServices;
import com.robel.bookstore.validation.OnUpdate;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private FileService fileService;

    @Value("${images.user.profile}")
    String profilePath;

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
                                                 @Valid @RequestBody ChangePasswordDTO changePasswordDTO){
        return ResponseEntity.ok().body(userServices.changePassword(userId, changePasswordDTO));
    }

    @PostMapping("/images/upload/{userId}")
    public ResponseEntity<String> uploadProfilePic(@RequestPart("file") MultipartFile file,
                                                   @PathVariable("userId") Long userId) throws IOException {
        String imgUrl = fileService.uploadProfileImage(file, profilePath, userId);
        return ResponseEntity.ok().body("Profile picture uploaded successfully : " + imgUrl);
    }


    @PostMapping("/images/change/{userId}")
    public ResponseEntity<String> changeProfilePicture(@RequestPart("file") MultipartFile file,
                                                       @PathVariable("userId") Long userId) throws IOException {
        String imgUrl = fileService.changeProfileImage(file, profilePath, userId);
        return ResponseEntity.ok().body("Profile picture changed successfully : " + imgUrl);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/images/delete/{userId}")
    public ResponseEntity<String> deleteProfilePicture(@PathVariable("userId") Long userId) throws IOException {
        fileService.deleteProfilePicture(profilePath, userId);
    return ResponseEntity.ok().body("deleted successfully");
    }

    @GetMapping("/images/{userName}/{fileName}")
    public void serveProfileImg(@PathVariable("userName") String userName,
                                @PathVariable("fileName") String fileName,
                                HttpServletResponse response) throws IOException {

        InputStream image = fileService.getProfileImage(profilePath, userName, fileName);

        String extension = fileName.substring(fileName.lastIndexOf("."));
        switch (extension){
            case ".png" -> response.setContentType(MediaType.IMAGE_PNG_VALUE);
            case ".jpeg", ".jpg" -> response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            case ".gif" -> response.setContentType(MediaType.IMAGE_GIF_VALUE);
        }

        StreamUtils.copy(image, response.getOutputStream());

    }



}
