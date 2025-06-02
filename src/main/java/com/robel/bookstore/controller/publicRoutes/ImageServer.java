package com.robel.bookstore.controller.publicRoutes;

import com.robel.bookstore.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/public/images")
public class ImageServer {
    @Autowired
    private FileService fileService;

    @Value("${images.book.image}")
    String bookPath;

    @Value("${images.user.profile}")
    String profilePath;


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

    @GetMapping("/{userName}/{fileName}")
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
