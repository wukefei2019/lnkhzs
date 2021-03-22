package com.ultrapower.omcs.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.omcs.base.web.BaseAction;

public class DicManager4FlowAction extends BaseAction {

    /**
     * @Description:[字段功能描述]
     */
    private static final long serialVersionUID = 179881589872587957L;

    public void getChildNode() throws IOException {
        String dicvalue = this.getRequest().getParameter("dicvaluePara");
        if(StringUtils.isBlank(dicvalue)){
        	return;
        }
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("flow", dicvalue);
        RQuery rq = new RQuery("SQL_BASEINFO_FLOW_SUBFLOW.QUERY", param);
        DataTable dt = rq.getDataTable();
        List<DataRow> rows = dt.getRowList();
        int dicitsmListLen = 0;
        if (rows != null)
            dicitsmListLen = rows.size();
        String text = "";
        for (int i = 0; i < dicitsmListLen; i++) {
            DataRow dicItem = rows.get(i);
            String value = dicItem.getString("divalue");
            String name = dicItem.getString("DINAME");
            text += value + "," + name + ";";
        }
        if (text != "")
            text = text.substring(0, text.length() - 1);
        this.renderText(text);

    }

    public void getChildNode2() throws IOException {
        String dicvalue = this.getRequest().getParameter("dicvaluePara");
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("pcityname", dicvalue);
        RQuery rq = new RQuery("SQL_BASEINFO_CITY_COUNTY_CODE.QUERY", param);
        DataTable dt = rq.getDataTable();
        List<DataRow> rows = dt.getRowList();
        int dicitsmListLen = 0;
        if (rows != null)
            dicitsmListLen = rows.size();
        String text = "";
        for (int i = 0; i < dicitsmListLen; i++) {
            DataRow dicItem = rows.get(i);
            String value = dicItem.getString("ZH_LABEL");
            String name = dicItem.getString("ZH_LABEL");
            text += value + "," + name + ";";
        }
        if (text != "")
            text = text.substring(0, text.length() - 1);
        this.renderText(text);

    }

}
