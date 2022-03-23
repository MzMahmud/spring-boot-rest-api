package com.moazmahmud.spring_boot_rest_api.document;

import com.moazmahmud.spring_boot_rest_api.common.InternalServerException;
import com.moazmahmud.spring_boot_rest_api.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class DocumentContainerService {
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;
    private final DocumentContainerRepository documentContainerRepository;

    @Transactional
    protected void uploadFile(DocumentContainer documentContainer, MultipartFile multipartFile) {
        Document document = new Document();
        document.setOriginalFileName(multipartFile.getOriginalFilename());
        document.setContentType(multipartFile.getContentType());
        String uniqueName = documentService.saveFileAndGetUniqueName(multipartFile);
        if (uniqueName == null) {
            throw new InternalServerException("file saving failed");
        }
        document.setUniqueFileName(uniqueName);
        document.setDocumentContainer(documentContainer);
        documentRepository.save(document);
    }

    @Transactional
    public DocumentContainer uploadFiles(MultipartFile[] multipartFiles) {
        DocumentContainer documentContainer = documentContainerRepository.save(new DocumentContainer());
        Arrays.stream(multipartFiles)
              .forEach(multipartFile -> uploadFile(documentContainer, multipartFile));
        return documentContainer;
    }

    public DocumentModel downloadDocument(Long documentId) {
        Document document = documentRepository.findById(documentId)
                                              .orElseThrow(() -> new NotFoundException("document not found"));
        byte[] bytes = documentService.downloadFileWithUniqueName(document.getUniqueFileName());
        return DocumentModel
                .builder()
                .bytes(bytes)
                .document(document)
                .build();
    }
}
