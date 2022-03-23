package com.moazmahmud.spring_boot_rest_api.document;

import com.moazmahmud.spring_boot_rest_api.common.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/documents")
@AllArgsConstructor
public class DocumentController extends BaseRestController {

    private final DocumentContainerService documentContainerService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFiles(
            @RequestParam("files") MultipartFile[] multipartFiles
    ) throws URISyntaxException {
        DocumentContainer documentContainer = documentContainerService.uploadFiles(multipartFiles);
        String createdUrl = "/api/documents/" + documentContainer.getId();
        return ResponseEntity
                .created(new URI(createdUrl))
                .build();
    }

    @GetMapping("/download/{documentId}")
    public ResponseEntity<byte[]> downloadDocument(
            @PathVariable("documentId") Long documentId
    ) {
        DocumentModel documentModel = documentContainerService.downloadDocument(documentId);
        String fileName = String.format(
                "attachment; filename=\"%s\"",
                documentModel.getDocument().getOriginalFileName()
        );
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(documentModel.getBytes().length)
                .body(documentModel.getBytes());
    }
}
