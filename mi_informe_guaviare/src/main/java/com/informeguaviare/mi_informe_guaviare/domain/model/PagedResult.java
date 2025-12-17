package com.informeguaviare.mi_informe_guaviare.domain.model;

import java.util.List;

public class PagedResult<T> {
    private List<T> items;
    private int pageNumber;
    private int pageSize;
    private int totalElements;

    public PagedResult(List<T> items, int pageNumber, int pageSize, int totalElements) {
        this.items = items;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        if (pageSize == 0)
            return 0;
        return (int) Math.ceil((double) totalElements / pageSize);
    }

    public List<T> getItems() {
        return items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalElements() {
        return totalElements;
    }

}
