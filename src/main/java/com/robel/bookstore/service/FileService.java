package com.robel.bookstore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService {

    String uploadProfileImage(MultipartFile file, String filePath, String userName) throws IOException;
    String changeProfileImage(MultipartFile file, String filePath, String userName) throws IOException;

    void deleteProfilePicture(String path, String userName) throws IOException;

    List<String> uploadBookImage(List<MultipartFile> allImg, String filePath, String uniqueName) throws IOException;

    void deleteBookImage(String path, String uniqueName) throws IOException;

    InputStream getProfileImage(String path, String userName, String fileName) throws IOException;
    InputStream getBookImages(String path, String userName, String fileName) throws IOException;

}
