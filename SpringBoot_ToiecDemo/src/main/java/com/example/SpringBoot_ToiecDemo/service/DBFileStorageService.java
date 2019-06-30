package com.example.SpringBoot_ToiecDemo.service;

import com.example.SpringBoot_ToiecDemo.exception.FileStorageException;
import com.example.SpringBoot_ToiecDemo.exception.MyFileNotFoundException;
import com.example.SpringBoot_ToiecDemo.model.DBFile;
import com.example.SpringBoot_ToiecDemo.repository.DBFileRepository;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the files name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            return this.dbFileRepository.save(dbFile);

        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public DBFile getFile(String fileId) {
        return this.dbFileRepository
                .findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }


    public Image generateImage(DBFile file){
        String id = file.getId();
        StreamResource sr = new StreamResource("file",()->{
            DBFile dbFile = this.dbFileRepository.findById(id).get();
            return new ByteArrayInputStream(dbFile.getData());
        });

        sr.setContentType("image/png");
        Image image = new Image(sr, "profile-picture");
        return image;
    }

}
