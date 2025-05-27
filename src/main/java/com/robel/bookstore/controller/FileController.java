package com.robel.bookstore.controller;

import com.robel.bookstore.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/images/")
public class FileController {
    @Autowired
    private FileService fileService;

    @Value("${images.user.profile}")
    String profilePath;

    @Value("${images.book.image}")
    String bookPath;

    @Value("${base.url}")
    String baseUrl;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("users/upload")
    public ResponseEntity<String> uploadProfilePic(@RequestPart("file") MultipartFile file,
                                                   @RequestPart("username") String username) throws IOException {
        String fileName = fileService.uploadProfileImage(file, profilePath, username);
        return ResponseEntity.ok().body(baseUrl + "users/" + username.toLowerCase() + "/" + fileName);
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("users/change")
    public ResponseEntity<String> changeProfilePicture(@RequestPart("file") MultipartFile file,
                                                   @RequestPart("username") String username) throws IOException {
        String fileName = fileService.changeProfileImage(file, profilePath, username);
        return ResponseEntity.ok().body(baseUrl + "users/" + username.toLowerCase() + "/" + fileName);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("users/{userName}")
    public ResponseEntity<String> deleteProfilePicture(@PathVariable("userName") String userName) throws IOException {
        fileService.deleteProfilePicture(profilePath, userName);
        return ResponseEntity.ok().body("deleted successfully");
    }

    @GetMapping("/users/{userName}/{fileName}")
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

    @GetMapping("/books/{uniqueName}/{fileName}")
    public void serveBookImg(@PathVariable("uniqueName") String uniqueName,
                               @PathVariable("fileName") String fileName,
                               HttpServletResponse response) throws IOException {


        InputStream image = fileService.getBookImages(bookPath, uniqueName, fileName);

        String extension = fileName.substring(fileName.lastIndexOf("."));
        switch (extension){
            case ".png" -> response.setContentType(MediaType.IMAGE_PNG_VALUE);
            case ".jpeg", ".jpg" -> response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            case ".gif" -> response.setContentType(MediaType.IMAGE_GIF_VALUE);
        }

        StreamUtils.copy(image, response.getOutputStream());

    }
}
