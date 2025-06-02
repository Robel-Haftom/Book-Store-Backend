package com.robel.bookstore.service.impl;

import com.robel.bookstore.entity.User;
import com.robel.bookstore.repository.UserRepository;
import com.robel.bookstore.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private UserRepository userRepository;

    @Value("${base.url}")
    String baseUrl;

    @Override
    public String uploadProfileImage(MultipartFile file, String filePath, Long userId) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String newFileName = LocalDateTime.now().format(formatter) + file.getOriginalFilename();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String imgFilePath = filePath + user.getUserName().toLowerCase() + File.separator + newFileName;

        File img = new File(filePath + File.separator + user.getUserName().toLowerCase());
        if(!img.exists()){
            img.mkdirs();
        }

        Files.copy(file.getInputStream(), Paths.get(imgFilePath), StandardCopyOption.REPLACE_EXISTING);
        String profileImgUrl =  baseUrl + user.getUserName().toLowerCase() + "/" + newFileName;
        user.setProfileImgUrl(profileImgUrl);
        userRepository.save(user);

        return profileImgUrl;
    }

    @Override
    public String changeProfileImage(MultipartFile file, String filePath, Long userId) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //first delete the existing file or files
        Path dirPath = Paths.get((filePath + File.separator + user.getUserName().toLowerCase()));
        List<File> files = new ArrayList<>();
        File directory = new File(dirPath.toString());

        if(directory.isDirectory()){
            File[] fileArray = directory.listFiles();
            if(fileArray != null){
                for (File f : fileArray){
                    if(f.isFile()){
                        files.add(f);
                    }
                }
            }
        }

        for (File f : files){
            Files.delete(Paths.get(f.getPath()));
        }

        String newFileName = LocalDateTime.now().format(formatter) + file.getOriginalFilename();
        String imgFilePath = filePath + user.getUserName().toLowerCase() + File.separator + newFileName;

        Files.copy(file.getInputStream(), Paths.get(imgFilePath), StandardCopyOption.REPLACE_EXISTING);
        String profileImgUrl =  baseUrl + user.getUserName().toLowerCase() + "/" + newFileName;
        user.setProfileImgUrl(profileImgUrl);
        userRepository.save(user);

        return profileImgUrl;
    }

    @Override
    public void deleteProfilePicture(String path, Long userId) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Path dirPath = Paths.get((path + File.separator + user.getUserName().toLowerCase()));
        List<File> files = new ArrayList<>();
        File directory = new File(dirPath.toString());

        if(directory.isDirectory()){
            File[] fileArray = directory.listFiles();
            if(fileArray != null){
                for (File f : fileArray){
                    if(f.isFile()){
                        files.add(f);
                    }
                }
            }
        }

        for (File f : files){
            Files.delete(Paths.get(f.getPath()));
        }

        Files.delete(dirPath);

        user.setProfileImgUrl(baseUrl + "newuser/" + "user.png");
        userRepository.save(user);
    }

    @Override
    public List<String> uploadBookImage(List<MultipartFile> allImg, String filePath, String uniqueName) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        List<String> fileNames = new ArrayList<>();
        String newFileName = "";
        for (MultipartFile file : allImg) {
            String fileName = file.getOriginalFilename();
            if(!fileName.isEmpty()){
                newFileName = LocalDateTime.now().format(formatter) + fileName;
                String imgFilePath = filePath + uniqueName + File.separator + newFileName;

                File img = new File(filePath + File.separator + uniqueName);
                if(!img.exists()){
                    img.mkdirs();
                }

                Files.copy(file.getInputStream(), Paths.get(imgFilePath), StandardCopyOption.REPLACE_EXISTING);
            }
            fileNames.add(newFileName);
        }

        return fileNames;
    }

    @Override
    public void deleteBookImage(String path, String uniqueName) throws IOException {
        Path dirPath = Paths.get((path + File.separator + uniqueName));
        List<File> files = new ArrayList<>();
        File directory = new File(dirPath.toString());

        if(directory.isDirectory()){
            File[] fileArray = directory.listFiles();
            if(fileArray != null){
                for (File f : fileArray){
                    if(f.isFile()){
                        files.add(f);
                    }
                }
            }
        }

        for (File f : files){
            Files.delete(Paths.get(f.getPath()));
        }

        Files.delete(dirPath);
    }

    @Override
    public InputStream getProfileImage(String path, String userName, String fileName) throws IOException {
        String imagePath = path + "/" + userName + "/" + fileName;
        return new FileInputStream(imagePath);
    }
    @Override
    public InputStream getBookImages(String path, String uniqueName, String fileName) throws IOException {
        String imagePath = path + "/" + uniqueName + "/" + fileName;
        return new FileInputStream(imagePath);
    }
}
