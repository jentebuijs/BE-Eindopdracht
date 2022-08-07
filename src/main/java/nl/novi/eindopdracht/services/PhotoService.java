package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.FileInfoDto;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.repositories.FileUploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class PhotoService {
    private Path fileStoragePath;
    private final String fileStorageLocation;
    private final FileUploadRepository fileUploadRepository;

    public PhotoService(@Value("${my.upload_location}") String fileStorageLocation, FileUploadRepository fileUploadRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.fileUploadRepository = fileUploadRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    public FileUploadResponse storeFile(MultipartFile file) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }

        return fileUploadRepository.save(new FileUploadResponse(fileName, file.getContentType(), url));
    }

    public FileInfoDto downloadFile(String fileName, HttpServletRequest request) {
        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
        FileInfoDto fileInfo = new FileInfoDto();
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }
        if (resource.exists() && resource.isReadable()) {
            fileInfo.setResource(resource);
        } else {
            throw new RuntimeException("the file doesn't exist or is not a readable format");
        }
        String mimeType;
        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        fileInfo.setMimeType(mimeType);
        return fileInfo;
    }
}



