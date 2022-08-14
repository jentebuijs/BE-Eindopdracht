package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.FileInfoDto;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.services.PhotoService;
import nl.novi.eindopdracht.services.ProfileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/photos")
public class PhotoController {
    private final PhotoService photoService;
    private final ProfileService profileService;

    public PhotoController(PhotoService photoService, ProfileService profileService) {
        this.photoService = photoService;
        this.profileService = profileService;
    }

    @PostMapping("/upload/{username}")
    FileUploadResponse uploadFile(@PathVariable String username, @RequestParam("file") MultipartFile file){
        return photoService.storeFile(file, username);
    }

    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        FileInfoDto fileInfo = photoService.downloadFile(fileName, request);
        Resource resource = fileInfo.getResource();
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileInfo.getMimeType())).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }
}
