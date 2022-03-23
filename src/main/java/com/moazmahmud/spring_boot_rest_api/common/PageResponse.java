package com.moazmahmud.spring_boot_rest_api.common;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {
    private List<T> data;
    private Long totalRecords;
    private Integer totalPages;
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean isFirstPage;
    private Boolean isLastPage;
    private Boolean isEmpty;
    private Object additionalInfo;

    private PageResponse() {
    }

    public void setAdditionalInfo(Object additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public static <T> PageResponse<T> from(Page<T> page) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.data = page.getContent();
        pageResponse.totalRecords = page.getTotalElements();
        pageResponse.totalPages = page.getTotalPages();
        pageResponse.pageIndex = page.getNumber();
        pageResponse.pageSize = page.getSize();
        pageResponse.isFirstPage = page.isFirst();
        pageResponse.isLastPage = page.isLast();
        pageResponse.isEmpty = page.isEmpty();
        return pageResponse;
    }
}
