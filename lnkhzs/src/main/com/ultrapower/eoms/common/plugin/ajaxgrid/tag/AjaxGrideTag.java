package com.ultrapower.eoms.common.plugin.ajaxgrid.tag;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import bsh.StringUtil;

import com.ultrapower.eoms.common.constants.Constants;
import com.ultrapower.eoms.common.core.component.cache.manager.BaseCacheManager;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.component.rquery.core.SqlResult;
import com.ultrapower.eoms.common.core.component.rquery.util.SqlReplace;
import com.ultrapower.eoms.common.core.util.NumberUtils;
import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.plugin.ajaxgrid.core.AjaxGrid;
import com.ultrapower.eoms.common.plugin.ajaxgrid.core.AjaxTable;
import com.ultrapower.eoms.common.plugin.datagrid.core.DataGrid;
import com.ultrapower.eoms.common.plugin.datagrid.grid.Limit;
import com.ultrapower.eoms.common.plugin.datagrid.tag.DataGrideTag;
import com.ultrapower.eoms.common.plugin.datagrid.util.RequestUtils;
import com.ultrapower.eoms.common.portal.model.UserSession;

public class AjaxGrideTag extends DataGrideTag {

    private String width;
    
    private String height;
    
    private String fixedColumns;
    
    private String fixedNumber;
    
    private String conditionIsOpen;
    
    private AjaxTable ajaxTable;
    
    private boolean autoLoad = true;
    /**
     * @Description:[字段功能描述]
     */
    private static final long serialVersionUID = -3156372277012632749L;

    /**
     * [转换成子类]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @see com.ultrapower.eoms.common.plugin.datagrid.tag.DataGrideTag#setDataGrid(com.ultrapower.eoms.common.plugin.datagrid.core.DataGrid)
     * @param dataGrid.
     */
    public void setDataGrid(DataGrid dataGrid) {
        AjaxGrid ajaxGrid = new AjaxGrid(pageContext, sqlname);
        ajaxGrid.setWidth(width);
        ajaxGrid.setHeight(height);
        ajaxGrid.setId(super.getId());
        ajaxGrid.setFixedColumns(fixedColumns);
        ajaxGrid.setFixedNumber(fixedNumber);
        ajaxGrid.setConditionIsOpen(conditionIsOpen);
        ajaxGrid.setAjaxTable(ajaxTable);
        ajaxGrid.setAutoLoad(autoLoad);
        if(hashMap==null) hashMap = new HashMap();
        ajaxGrid.setInmap(hashMap);
        super.dataGrid = ajaxGrid;
    }
    
    public int doStartTag() {
        //this.title="";
        this.leftToolAre=null;
        this.conditionAre=null;
        this.gridtitle="";
        this.tablerow="";
        setDataGrid(null);
        return EVAL_BODY_INCLUDE;
    }
    /**
     * [给子类中的特殊字段赋值]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @see com.ultrapower.eoms.common.plugin.datagrid.tag.DataGrideTag#doEndTag()
     * @return.
     */
    public int doEndTag() {
        AjaxGrid ajaxGrid = ((AjaxGrid) super.dataGrid);
        ajaxGrid.setAjaxTable(ajaxTable);
        int reuslt = super.doEndTag();
        return reuslt;

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