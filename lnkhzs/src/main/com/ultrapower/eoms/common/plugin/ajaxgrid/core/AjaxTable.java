package com.ultrapower.eoms.common.plugin.ajaxgrid.core;

public class AjaxTable {
    
    private String tableHtml;

    private String onLoadSuccess;
    
    public String getOnLoadSuccess() {
        return onLoadSuccess;
    }

    public void setOnLoadSuccess(String onLoadSuccess) {
        this.onLoadSuccess = onLoadSuccess;
    }

    public String getTableHtml() {
        return tableHtml;
    }

    public void setTableHtml(String tableHtml) {
        this.tableHtml = tableHtml;
    }
}
