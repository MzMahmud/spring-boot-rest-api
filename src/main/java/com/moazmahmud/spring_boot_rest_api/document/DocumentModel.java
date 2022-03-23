package com.moazmahmud.spring_boot_rest_api.document;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DocumentModel {
    private Document document;
    private byte[] bytes;
}
