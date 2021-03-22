package com.ultrapower.eoms.common.plugin.ajaxgrid.core;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.ultrapower.eoms.common.plugin.datagrid.core.DataGrid;
import com.ultrapower.eoms.common.plugin.datagrid.grid.Limit;
import com.ultrapower.eoms.common.plugin.datagrid.util.RequestUtils;
import com.ultrapower.omcs.utils.StringUtils;

public class AjaxGrid extends DataGrid {

    public AjaxGrid(PageContext p_pageContext, String sqlName) {
        super(p_pageContext, sqlName);
    }

    private String id;

    private String width;

    private String height;

    private String fixedColumns;

    private String fixedNumber;
    
    private String conditionIsOpen;

    private AjaxTable ajaxTable;
    
    private boolean autoLoad;

    private Map<String, Object> inmap;

    /**
     * 工具条
     * @param out
     * @param limit
     * @param topFlag true的说明是上面的工具栏，false说明是下方的工具栏
     * @throws IOException
     */
    protected void getToolBoor(JspWriter out, Limit limit, boolean topFlag) throws IOException {
        // toolbar 左侧
        if (leftToolAre == null)// 如果没有lefttoobar工具栏标签
            return;
        out.print("<div class='fysp_iconBox ajax-grid-toolbar");// 工具栏开始
        if (topFlag) {
            out.print(" toolbar-top");
        } else {
            out.print(" toolbar-buttom");
        }
        out.println("'>");

        if (topFlag) {
            out.println(leftToolAre);
        }
        // 工具栏右侧翻页结束
        out.println("</div>");// 工具栏结束
    }

    /**
     * 输出DataGrid视图
     */
    public void dataView() {

        width = StringUtils.nvl(width, "1200");
        height = StringUtils.nvl(height, "480s");
        if (!"true".equals(fixedColumns)) {
            fixedColumns = "false";
        }
        fixedNumber = StringUtils.nvl(fixedNumber, "1");

        JspWriter out = pageContext.getOut();
        request = (HttpServletRequest) pageContext.getRequest();
        Limit limit = RequestUtils.getWebLimit(request);
        try {

            out.println(
                    "<div id='" + id + "' class='data-grid data-grid-omcs fysp_table1Box' sqlname='" + sqlName + "'>");
            this.getTitle(out);
            this.getToolBoor(out, limit, true);
            this.getConditionAre(out);
            out.println(this.ajaxTable.getTableHtml());
            getToolBoor(out, limit, false);
            out.println("</div>");
            if(autoLoad){
                out.println("<script>");
                out.println("$.bs.table.init('" + id + "');");
                out.println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getConditionAre(JspWriter out) throws IOException {
        if (conditionAre != null && !conditionAre.equals("")) {
            if("true".equalsIgnoreCase(conditionIsOpen)){
                out.println("<div id='" + id + "_serachdiv' class='serachdiv' style='display:block;' isOpenDefault='true'>");
            } 
            else if("2".equalsIgnoreCase(conditionIsOpen)){
                
                out.println("<div id='" + id + "_serachdiv' class='serachdiv collapse'  isOpenDefault='2'>");
            }
            else {
                out.println("<div id='" + id + "_serachdiv' class='serachdiv collapse' style='display:none;'>");
            }
            out.println(conditionAre);
            out.println("</div>");
        }
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFixedColumns() {
        return fixedColumns;
    }

    public void setFixedColumns(String fixedColumns) {
        this.fixedColumns = fixedColumns;
    }

    public String getFixedNumber() {
        return fixedNumber;
    }

    public void setFixedNumber(String fixedNumber) {
        this.fixedNumber = fixedNumber;
    }

    public AjaxTable getAjaxTable() {
        return ajaxTable;
    }

    public void setAjaxTable(AjaxTable ajaxTable) {
        this.ajaxTable = ajaxTable;
    }

    public void setInmap(Map<String, Object> hashMap) {
        this.inmap = hashMap;

    }

    public String getConditionIsOpen() {
        return conditionIsOpen;
    }

    public void setConditionIsOpen(String conditionIsOpen) {
        this.conditionIsOpen = conditionIsOpen;
    }

    public boolean isAutoLoad() {
        return autoLoad;
    }

    public void setAutoLoad(boolean autoLoad) {
        this.autoLoad = autoLoad;
    }

}
