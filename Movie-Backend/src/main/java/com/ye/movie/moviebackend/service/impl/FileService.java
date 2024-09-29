package com.ye.movie.moviebackend.service.impl;

import com.ye.movie.moviebackend.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService implements IFileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {

        // get filename
        String fileName = file.getOriginalFilename();

        // get filepath
        String filePath = path + File.separator + fileName;

        // create file object
        File f = new File(filePath);

        if ( !f.exists()){
            f.mkdir();
        }

        // copy the file and upload file to path
        Files.copy(file.getInputStream() , Paths.get(filePath) , StandardCopyOption.REPLACE_EXISTING );

        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {

        String filePath = path + File.separator + filename;

        return new FileInputStream(filePath);
    }
}
