package com.moazmahmud.spring_boot_rest_api.document;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    String saveFileAndGetUniqueName(MultipartFile multipartFile);

    String deleteFileWithUniqueName(String uniqueName);

    byte[] downloadFileWithUniqueName(String uniqueName);
}
