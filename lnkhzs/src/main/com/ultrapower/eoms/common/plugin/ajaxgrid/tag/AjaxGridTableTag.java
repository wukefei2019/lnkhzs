package com.ultrapower.eoms.common.plugin.ajaxgrid.tag;

import javax.servlet.jsp.tagext.Tag;

import com.ultrapower.eoms.common.plugin.ajaxgrid.core.AjaxTable;
import com.ultrapower.eoms.common.plugin.datagrid.tag.DataGrideTitleTag;

public class AjaxGridTableTag extends DataGrideTitleTag {

    private AjaxTable ajaxTable  = new AjaxTable();

    /**
     * @Description:[字段功能描述]
     */
    private static final long serialVersionUID = -7757266808216294166L;

    public int doEndTag() {
        try {
            Object bodyvalue = getBodyValue();
            if (bodyvalue != null) {
                Tag t = findAncestorWithClass(this, AjaxGrideTag.class);
                AjaxGrideTag parent = (AjaxGrideTag) t;
                ajaxTable.setTableHtml(bodyvalue.toString());
                parent.setAjaxTable(ajaxTable);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return EVAL_PAGE;
    }

    public AjaxTable getAjaxTable() {
        return ajaxTable;
    }

    public void setAjaxTable(AjaxTable ajaxTable) {
        this.ajaxTable = ajaxTable;
    }

    public String getOnLoadSuccess() {
        return ajaxTable.getOnLoadSuccess();
    }

    public void setOnLoadSuccess(String onLoadSuccess) {
        ajaxTable.setOnLoadSuccess(onLoadSuccess);
    }

}
