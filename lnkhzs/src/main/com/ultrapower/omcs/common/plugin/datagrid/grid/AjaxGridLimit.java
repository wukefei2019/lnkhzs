package com.ultrapower.omcs.common.plugin.datagrid.grid;

import com.ultrapower.eoms.common.plugin.datagrid.grid.Limit;

public class AjaxGridLimit implements Limit {
    private int page = 0;
    private int pageSize = 0;
    private int rowStart = 0;
    private int rowEnd = 0;
    private int totalRows = 0;
    private int totalPages = 0;
    private String sortfiled;
    private int sorttype = 1;// 1 正序 0 倒序

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public int getRowStart() {
        return rowStart;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setRowAttributes(int totalRows, int defaultRowsDisplayed) {
        this.totalRows = totalRows;
        if (totalRows > 0 && pageSize > 0) {
            totalPages = totalRows / pageSize;
            if (totalRows % pageSize > 0) {
                totalPages++;
            }
        } else {
            totalPages = 0;
        }
    }

    public int getTotalPage() {
        return totalPages;
    }

    public String getSortField() {
        return sortfiled;
    }

    public int getSortType() {
        return sorttype;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public void setRowEnd(int rowEnd) {
        this.rowEnd = rowEnd;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setSortfiled(String sortfiled) {
        this.sortfiled = sortfiled;
    }

    public void setSorttype(int sorttype) {
        this.sorttype = sorttype;
    }

}
