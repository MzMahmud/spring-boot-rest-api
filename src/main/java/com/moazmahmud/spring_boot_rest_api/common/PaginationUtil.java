package com.moazmahmud.spring_boot_rest_api.common;

public class PaginationUtil {
    public static final Integer DEFAULT_PAGE_INDEX = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer MAX_PAGE_SIZE = 100;

    public static Integer parseInteger(String intStr, Integer defaultValue) {
        try {
            return Integer.parseInt(intStr);
        } catch (Exception exception) {
            return defaultValue;
        }
    }

    public static Integer getPageIndex(String pageIndexStr) {
        return getPageIndex(pageIndexStr, DEFAULT_PAGE_INDEX);
    }

    public static Integer getPageIndex(String pageIndexStr, Integer defaultValue) {
        if (pageIndexStr == null) {
            return defaultValue;
        }
        Integer pageIndex = parseInteger(pageIndexStr, defaultValue);
        return pageIndex < 0 ? defaultValue : pageIndex;
    }

    public static Integer getPageSize(String pageSize) {
        return getPageSize(pageSize, DEFAULT_PAGE_SIZE);
    }

    public static Integer getPageSize(String pageSizeStr, Integer defaultValue) {
        if (pageSizeStr == null) {
            return defaultValue;
        }
        Integer pageSize = parseInteger(pageSizeStr, defaultValue);
        return pageSize < 0 ? DEFAULT_PAGE_SIZE
                            : Math.min(MAX_PAGE_SIZE, pageSize);
    }
}
