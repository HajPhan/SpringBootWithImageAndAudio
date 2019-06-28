package com.example.SpringBoot_ToiecDemo.controller;

import com.example.SpringBoot_ToiecDemo.model.DBFile;
import com.example.SpringBoot_ToiecDemo.model.QuestionEntity;
import com.example.SpringBoot_ToiecDemo.payload.UploadFileResponse;
import com.example.SpringBoot_ToiecDemo.service.DBFileStorageService;
import com.example.SpringBoot_ToiecDemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private DBFileStorageService dbFileStorageService;

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("question", this.questionService.getAllToeic());
        return "home";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("toeic", new QuestionEntity());
        return "save-page";
    }

    @PostMapping(value = "/dosave")
    public String saveQuestion(@ModelAttribute QuestionEntity request) {
        boolean result = this.questionService.saveQuestion(request);
        if (result == true) {
            return "redirect:/";
        } else {
            return "redirect:/save?error";
        }
    }

    // DB
    @PostMapping(value = "/db/uploadFile")
    public UploadFileResponse dbUploadFile(@RequestParam("file") MultipartFile file) {
        DBFile dbFile = this.dbFileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping(value = "/db/uploadMultipleFile")
    public List<UploadFileResponse> dbuploadMultipleFile(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> dbUploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "db/downloadFile/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = this.dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
