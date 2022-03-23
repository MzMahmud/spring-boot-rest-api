package com.moazmahmud.spring_boot_rest_api.document;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class LocalStorageDocumentService implements DocumentService {

    @Value("${localStorage.documentService.filePathName}")
    private String filePathName;

    @Override
    public String saveFileAndGetUniqueName(MultipartFile multipartFile) {
        UUID uuid = UUID.randomUUID();
        String uniqueName = uuid + "_" + multipartFile.getOriginalFilename();
        File file = Path.of(filePathName, uniqueName).toFile();
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return uniqueName;
    }

    @Override
    public String deleteFileWithUniqueName(String uniqueName) {
        return null;
    }

    @Override
    public byte[] downloadFileWithUniqueName(String uniqueName) {
        File file = Path.of(filePathName, uniqueName).toFile();
        try (InputStream inputStream = new FileInputStream(file)) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
