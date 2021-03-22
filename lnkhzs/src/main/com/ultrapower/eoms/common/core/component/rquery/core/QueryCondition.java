package com.ultrapower.eoms.common.core.component.rquery.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Element;

import com.ultrapower.eoms.common.core.component.rquery.startup.StartUp;
import com.ultrapower.eoms.common.core.component.rquery.util.Parameters;
import com.ultrapower.eoms.common.core.component.rquery.util.RConstants;
import com.ultrapower.eoms.common.core.component.rquery.util.SqlReplace;
import com.ultrapower.eoms.common.core.component.rule.RuleException;
import com.ultrapower.eoms.common.core.component.rule.RuleExpression;
import com.ultrapower.eoms.common.core.util.Internation;
import com.ultrapower.eoms.common.core.util.NumberUtils;
import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;
//import com.ultrapower.eoms.common.tag.select.core.MultiSelect;
import com.ultrapower.eoms.common.tag.select.core.Select;
import com.ultrapower.eoms.ultrasm.model.DicItem;
import com.ultrapower.eoms.ultrasm.service.DicManagerService;
@SuppressWarnings({"rawtypes","unused"})
public class QueryCondition
{

    private String itype;//国际化文件类别 2012-05-10 加

    private String sqlName = "";
    private Element sqlqueryElement;
    private HashMap indirectValues;//自定义传入的值
    private int line = 0;

    public QueryCondition(String sqlName)
    {
        this.sqlName = sqlName;

        Object obj = null;
        if (StartUp.sqlmapElement != null)
        {
            obj = StartUp.sqlmapElement.get(sqlName);
            if (obj != null)
                sqlqueryElement = (Element) obj;
        }
    }


    /**
     * 获取查询条件的html字符串,变量数据从request或valuesmap中取
     * 
     * @param request
     * @param valuesMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getCondition(HttpServletRequest request, HashMap valuesMap, String conditions) {
        String lableprecent = "11";
        String textprecent = "22";
        int cols = 0;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringHidden = new StringBuffer();
        stringHidden.append("<div style='display:none;'>");
        List lstAction = null;
        if (this.sqlqueryElement != null) {
            lstAction = this.sqlqueryElement.getChildren("condition");
        }
        int lstLen = 0;
        if (lstAction != null) {
            lstLen = lstAction.size();
            if (lstLen > 0) {
                lstLen = 0;
                Element ele = (Element) lstAction.get(0);
                String strCols = StringUtils.checkNullString(ele.getAttributeValue("cols"));
                cols = NumberUtils.formatToInt(strCols);
                lableprecent = StringUtils.checkNullString(ele.getAttributeValue("labprecent"));
                textprecent = StringUtils.checkNullString(ele.getAttributeValue("textprecent"));
                lstAction = ele.getChildren();
                if (lstAction != null)
                    lstLen = lstAction.size();
            }
        }
        Element element;

        if (cols <= 0)
            cols = 3;

        line = 0;
        stringBuffer.append("<table  class=\"serachdivTable\">");
        /*
         * stringBuffer.append("<tr>"); for(int j=0;j<cols;j++) { if(j%2==0)
         * stringBuffer.append("<td style=\"width:"+lableprecent+"%\">"); else
         * stringBuffer.append("<td style=\"width:"+textprecent+"%\">");
         * stringBuffer.append("</td>"); } stringBuffer.append("</tr>");
         * 
         */
        // 查询条件客户配置逻辑添加：如果客户配置条件conditions不为空而且长度不为零，那么将显示用户配置的查询条件
        boolean allConditionFlag = true;
        String[] conditionArray = null;
        Map<String, String> conditionMap = null;
        if (conditions != null && conditions.length() > 0) {
            allConditionFlag = false;
            conditionArray = conditions.split(",");
            conditionMap = new HashMap<String, String>();
            for (String condition : conditionArray) {
                conditionMap.put(condition, condition);
            }
        }

        String name;
        String isnewline = null;
        for (int i = 0; i < lstLen; i++) {
            element = (Element) lstAction.get(i);
            name = element.getName();
            if (RConstants.E_ACT_IF.equalsIgnoreCase(name)) {
                String exp = StringUtils.checkNullString(element.getAttributeValue("exp"));
                exp = SqlReplace.stringReplaceAllVar(exp, valuesMap);
                String expValue = "";
                try {
                    if (com.ultrapower.commons.lang3.StringUtils.isNotBlank(exp)) {
                        expValue = RuleExpression.execute(exp);
                    } else {
                        expValue = "true";
                    }
                } catch (RuleException e) {
                    e.printStackTrace();
                    return null;
                }
                List children = null;
                if (expValue.equals("true")) {
                    children = element.getChildren(RConstants.E_ACT_IF_SUCCESS);
                } else {
                    children = element.getChildren(RConstants.E_ACT_IF_ELSE);
                }
                if (children == null || children.isEmpty()) {
                    continue;
                }
                List<Element> lstActionChildren = ((Element) children.get(0)).getChildren();
                for (int j = 0; j < lstActionChildren.size(); j++) {
                    element = lstActionChildren.get(j);
                    name = element.getName();
                    if ("hidden".equals(name)) {
                        stringHidden.append(getOutHTML(request, element, valuesMap, lableprecent, textprecent));
                        // line++;
                    } else {
                        if (line % cols == 0)
                            stringBuffer.append("<tr>");
                        line++;
                        stringBuffer.append(getOutHTML(request, element, valuesMap, lableprecent, textprecent));
                        if (i == (lstLen - 1)) {
                            for (int j2 = 0; j2 < cols / 2 - lstLen % cols; j2++) {
                                stringBuffer.append("<td></td><td></td>");
                            }
                        }
                        if (line == 0 || line % cols == 0 || i == (lstLen - 1)) {
                            stringBuffer.append("</tr>");
                        }
                    }
                    if ("1".equals(isnewline)) {
                        // html = "</tr>");
                        this.line = 0;
                    }

                }
            } else {
                String attrName = element.getAttributeValue("name");
                if (attrName == null)
                    attrName = "";
                isnewline = StringUtils.checkNullString(element.getAttributeValue("isnewline"));
                if (!allConditionFlag && conditionMap.get(attrName) == null && !name.equals("hidden")) {
                    continue;
                }

                if ("hidden".equals(name)) {
                    stringHidden.append(getOutHTML(request, element, valuesMap, lableprecent, textprecent));
                    // line++;
                } else {
                    if (line % cols == 0)
                        stringBuffer.append("<tr>");
                    line++;
                    stringBuffer.append(getOutHTML(request, element, valuesMap, lableprecent, textprecent));
                    if (i == (lstLen - 1)) {
                        for (int j2 = 0; j2 < cols / 2 - lstLen % cols; j2++) {
                            stringBuffer.append("<td></td><td></td>");
                        }
                    }
                    if (line == 0 || line % cols == 0 || i == (lstLen - 1)) {
                        stringBuffer.append("</tr>");
                    }
                }
                if ("1".equals(isnewline)) {
                    // html = "</tr>");
                    this.line = 0;
                }
            }
        }
        stringBuffer.append("</table>");
        stringHidden.append(
                "</div><script> $(document).ready(function(){$('.serachdivTable select').triggerHandler('change')});</script>");
        stringBuffer.append(stringHidden.toString());
        return stringBuffer.toString();
    }

    private String getOutHTML(HttpServletRequest request, Element element, HashMap valuesMap, String lableprecent,
            String textprecent) {
        String name = element.getName();
        String html = "";
        if (name.equals("date")) {
            html = getDate(element, request, valuesMap, lableprecent, textprecent);
        } else if (name.equals("select") || name.equals("multi-select")) {
            html = getSelect(element, request, valuesMap, lableprecent, textprecent);
        } else if (name.equals("checkbox")) {
            html = getCheckbox(element, request, valuesMap, lableprecent, textprecent);
        } else if (name.equals("lotselect")) {
            html = getLotselect(element, request, valuesMap, lableprecent, textprecent);
        } else if (name.equals("dialog")) {
            html = getDialog(element, request, valuesMap, lableprecent, textprecent);
        } else if (name.equals("autocomplete")) {
            html = getAutocomplete(element, request, valuesMap, lableprecent, textprecent);
        } else if (name.equals("hidden")) {
            html = getHidden(element, request, valuesMap, lableprecent, textprecent);
        } else if (name.equals(RConstants.E_ACT_BTWN)) {
            html = getBetweenDiv(element, request, valuesMap, lableprecent, textprecent).toString();
        } else {
            html = getText(element, request, valuesMap, lableprecent, textprecent);
        }
        return html;
    }
    
    @SuppressWarnings("unchecked")
    private StringBuffer getBetweenDiv(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent,
            String textprecent) {
        List<Element> textList = element.getChildren("text");
        if(textList.size() != 2){
            throw new RuntimeException("配置错误");
        }
        Element from = textList.get(0);
        Element to = textList.get(1);
        StringBuffer sb = new StringBuffer();
        sb.append("<td colspan='2'><div class='col-xs-4 serachdivTableTd'><label>")
          .append(getLabelText(element, request, valuesMap, lableprecent, textprecent))
          .append("</label></div><div class='col-xs-3'>")
          .append(getTextElement(from, request, valuesMap, lableprecent, textprecent))
          .append("</div><div class='col-xs-1' style='text-align:center'>至</div><div class='col-xs-3'>")
          .append(getTextElement(to, request, valuesMap, lableprecent, textprecent)).append("</div>");
        sb.append("<div class='col-xs-1' style='text-align:center'></div></td>");
        return sb;
    }
    
    private StringBuffer getLabelTD(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent,
            String textprecent) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<td class=\"serachdivTableTd\" style='width:" + lableprecent + "%'>");
        stringBuffer.append(getLabelText(element, request, valuesMap, lableprecent, textprecent));
        stringBuffer.append("</td>");
        return stringBuffer;
    }
    private StringBuffer getLabelText(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent,
            String textprecent) {
        StringBuffer stringBuffer = new StringBuffer();
        String displayName = StringUtils.checkNullString(element.getAttributeValue("displayname"));
        String text = Internation.language(displayName);
        if (!text.equals(""))
            displayName = text;
        stringBuffer.append(displayName);
        stringBuffer.append("：");
        return stringBuffer;
    }

    private StringBuffer getTextOnlyTD(Element element, HttpServletRequest request, HashMap valuesMap,
            String lableprecent, String textprecent) {
        StringBuffer stringBuffer = new StringBuffer();
        if (element == null)
            return null;
        String name = StringUtils.checkNullString(element.getAttributeValue("name"));
        String istype = StringUtils.checkNullString(element.getAttributeValue("itype"));
        if (istype.equals("")) {
            istype = itype;
        }
        String value = "";
        if (request != null)
            value = request.getParameter(name);
        if (value == null) {
            Object obj = valuesMap.get(name);
            if (obj != null) {
                value = obj.toString();
            }
        }
        if (value == null) {
            String defaultvalue = StringUtils.checkNullString(element.getAttributeValue("defaultvalue"));
            if (!defaultvalue.equals("")) {
                defaultvalue = SqlReplace.stringReplaceAllVar(defaultvalue, valuesMap);
            }
            value = defaultvalue;
        }

        value = tranferQuot(value);

        String colspan = StringUtils.checkNullString(element.getAttributeValue("colspan"));
        String rowspan = StringUtils.checkNullString(element.getAttributeValue("rowspan"));

        stringBuffer.append("<td style='width:" + textprecent + "%'");
        if (!colspan.equals("")) {
            stringBuffer.append(" colspan=\"" + colspan + "\"");
        }
        if (!rowspan.equals("")) {
            stringBuffer.append(" rowspan=\"" + rowspan + "\"");
        }
        stringBuffer.append(">");
        stringBuffer.append(getTextElement(element, request, valuesMap, lableprecent, textprecent));
        stringBuffer.append("</td>");
        return stringBuffer;
    }
    
    private StringBuffer getTextElement(Element element, HttpServletRequest request, HashMap valuesMap,
            String lableprecent, String textprecent) {
        StringBuffer stringBuffer = new StringBuffer();
        if (element == null)
            return null;
        String id = StringUtils.checkNullString(element.getAttributeValue("id"));
        String name = StringUtils.checkNullString(element.getAttributeValue("name"));
        String istype = StringUtils.checkNullString(element.getAttributeValue("itype"));
        String clazz = StringUtils.checkNullString(element.getAttributeValue("class"));
        String placeholder = StringUtils.checkNullString(element.getAttributeValue("placeholder"));
        if (istype.equals("")) {
            istype = itype;
        }
        String value = "";
        if (request != null)
            value = request.getParameter(name);
        if (value == null) {
            Object obj = valuesMap.get(name);
            if (obj != null) {
                value = obj.toString();
            }
        }
        if (value == null) {
            String defaultvalue = StringUtils.checkNullString(element.getAttributeValue("defaultvalue"));
            if (!defaultvalue.equals("")) {
                defaultvalue = SqlReplace.stringReplaceAllVar(defaultvalue, valuesMap);
            }
            value = defaultvalue;
        }
        
        value = tranferQuot(value);
        
        String onclick = StringUtils.checkNullString(element.getAttributeValue("onclick"));
        
        stringBuffer.append("<input type=\"text\"");
        if (!id.equals("")) {
            stringBuffer.append(" id=\"" + id + "\"");
        }
        stringBuffer.append(" name=\"" + name + "\"");
        stringBuffer.append(" class=\"form-control " + clazz + "\"");
        stringBuffer.append(" value=\"");
        stringBuffer.append(value);
        stringBuffer.append("\" ");
        stringBuffer.append(" placeholder=\"");
        stringBuffer.append(placeholder);
        stringBuffer.append("\" ");
        if (!onclick.equals("")) {
            stringBuffer.append(" onclick=\"");
            stringBuffer.append(onclick);
            stringBuffer.append("\" ");
        }
        stringBuffer.append("/>");
        return stringBuffer;
    }
    
    private String getText(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent, String textprecent) {
        StringBuffer label = getLabelTD(element, request, valuesMap, lableprecent, textprecent);
        StringBuffer textOnly = getTextOnlyTD(element, request, valuesMap, lableprecent, textprecent);
        return label.append(textOnly).toString();
    }

    private String getAutocomplete(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent,
            String textprecent) {
        StringBuffer stringBuffer = new StringBuffer();
        if (element == null)
            return "";
        String id = StringUtils.checkNullString(element.getAttributeValue("id"));
        String name = StringUtils.checkNullString(element.getAttributeValue("name"));
        String displayName = StringUtils.checkNullString(element.getAttributeValue("displayname"));
        String clazz = StringUtils.checkNullString(element.getAttributeValue("class"));
        String value = "";
        if (request != null)
            value = request.getParameter(name);
        if (value == null) {
            Object obj = valuesMap.get(name);
            if (obj != null) {
                value = obj.toString();
            }
        }
        if (value == null) {
            String defaultvalue = StringUtils.checkNullString(element.getAttributeValue("defaultvalue"));
            if (!defaultvalue.equals("")) {
                defaultvalue = SqlReplace.stringReplaceAllVar(defaultvalue, valuesMap);
            }
            value = defaultvalue;
        }

        value = tranferQuot(value);
        String text = Internation.language(displayName);
        if (!text.equals(""))
            displayName = text;
        String colspan = StringUtils.checkNullString(element.getAttributeValue("colspan"));
        String rowspan = StringUtils.checkNullString(element.getAttributeValue("rowspan"));
        stringBuffer.append("<td class=\"serachdivTableTd\" style='width:" + lableprecent + "%'>");

        stringBuffer.append(displayName);
        stringBuffer.append("：");
        stringBuffer.append("</td>");
        stringBuffer.append("<td style='width:" + textprecent + "%'");
        if (!colspan.equals("")) {
            stringBuffer.append(" colspan=\"" + colspan + "\"");
        }
        if (!rowspan.equals("")) {
            stringBuffer.append(" rowspan=\"" + rowspan + "\"");
        }
        stringBuffer.append(">");
        stringBuffer.append("<input type=\"text\"");
        if (!id.equals("")) {
            stringBuffer.append(" id=\"" + id + "\"");
        }
        stringBuffer.append(" name=\"" + name + "\"");
        stringBuffer.append(" class=\"form-control width160 bs-autocomplete bs-style-opwin "+clazz+"\"");
        stringBuffer.append(" value=\"");
        stringBuffer.append(value);
        stringBuffer.append("\" ");
        stringBuffer.append("/>");
        stringBuffer.append("</td>");
        return stringBuffer.toString();
    }

    private String getDate(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent, String textprecent)
    {
        StringBuffer stringBuffer = new StringBuffer();
        if (element == null)
            return "";
        String name = StringUtils.checkNullString(element.getAttributeValue("name"));
        String displayName = StringUtils.checkNullString(element.getAttributeValue("displayname"));
        String value = "";
        String istype = StringUtils.checkNullString(element.getAttributeValue("itype"));
        String dateFmt = StringUtils.checkNullString(element.getAttributeValue("dateFmt"));
        if (org.apache.commons.lang3.StringUtils.isBlank(dateFmt))
        {
            dateFmt = "yyyy-MM-dd HH:mm:ss";
        }
        if (istype.equals(""))
        {
            istype = itype;
        }
        if (request != null)
        {
            value = request.getParameter(name);
        }
        if (value == null)
        {
            Object obj = valuesMap.get(name);
            if (obj != null)
            {
                value = obj.toString();
            }
        }
        if (value == null)
        {
            String defaultvalue = StringUtils.checkNullString(element.getAttributeValue("defaultvalue"));
            if (!defaultvalue.equals(""))
            {
                defaultvalue = SqlReplace.stringReplaceAllVar(defaultvalue, valuesMap);
            }
            long datetime = NumberUtils.formatToLong(defaultvalue);
            if (datetime > 20771231)
            {
                value = TimeUtils.formatIntToDateString(datetime);
            }
            else
            {
                value = defaultvalue;
            }

        }
        String text = Internation.language(displayName);
        if (!text.equals(""))
            displayName = text;
        String colspan = StringUtils.checkNullString(element.getAttributeValue("colspan"));
        String rowspan = StringUtils.checkNullString(element.getAttributeValue("rowspan"));

        stringBuffer.append("<td class=\"serachdivTableTd\" style='width:" + lableprecent + "%'>");

        stringBuffer.append(displayName);
        stringBuffer.append("：");
        stringBuffer.append("</td>");
        stringBuffer.append("<td style='width:" + textprecent + "%'");
        if (!colspan.equals(""))
        {
            stringBuffer.append(" colspan=\"" + colspan + "\"");
        }
        if (!rowspan.equals(""))
        {
            stringBuffer.append(" rowspan=\"" + rowspan + "\"");
        }
        stringBuffer.append(">");
        stringBuffer.append("<input type=\"text\"");
        stringBuffer.append(" name=\"" + name + "\" autocomplete='off' " );
        stringBuffer.append(" class=\"textInput Wdate\"");
        stringBuffer.append(" onclick=\"WdatePicker({dateFmt:'"+dateFmt+"'});\"");
        stringBuffer.append(" value=\"");
        stringBuffer.append(value);
        stringBuffer.append("\" ");
        stringBuffer.append("/>");
        stringBuffer.append("</td>");
        /*
         * if(isnewline.equals("1")) { //stringBuffer.append("</tr>");
         * this.line=0; }
         */
        return stringBuffer.toString();
    }
    
    private String getLotselect(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent, String textprecent) {
        StringBuffer label = getLabelTD(element, request, valuesMap, lableprecent, textprecent);
        StringBuffer textOnly = getTextOnlyTD(element, request, valuesMap, lableprecent, textprecent);
        return label.append(textOnly).toString();
    }

    private String getCheckbox(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent, String textprecent)
    {
    	StringBuffer stringBuffer = new StringBuffer();
        if (element == null)
            return "";
        String id = StringUtils.checkNullString(element.getAttributeValue("id"));
        String name = StringUtils.checkNullString(element.getAttributeValue("name"));
        String displayName = StringUtils.checkNullString(element.getAttributeValue("displayname"));
        String checkboxname = StringUtils.checkNullString(element.getAttributeValue("checkboxname"));
        String value = "";
        String istype = StringUtils.checkNullString(element.getAttributeValue("itype"));
        String style = StringUtils.checkNullString(element.getAttributeValue("style"));
        if (istype.equals(""))
        {
            istype = itype;
        }
        if (request != null)
            value = request.getParameter(name);
        if (value == null)
        {
            Object obj = valuesMap.get(name);
            if (obj != null)
            {
                value = obj.toString();
            }
        }
        if (value == null)
        {
            String defaultvalue = StringUtils.checkNullString(element.getAttributeValue("defaultvalue"));
            if (!defaultvalue.equals(""))
            {
                defaultvalue = SqlReplace.stringReplaceAllVar(defaultvalue, valuesMap);
            }
            value = defaultvalue;
        }
        String text = Internation.language(displayName);
        if (!text.equals(""))
            displayName = text;
        String colspan = StringUtils.checkNullString(element.getAttributeValue("colspan"));
        String rowspan = StringUtils.checkNullString(element.getAttributeValue("rowspan"));
        String dictype = StringUtils.checkNullString(element.getAttributeValue("dictype"));
        String childrenname = StringUtils.checkNullString(element.getAttributeValue("childrenname"));
        String sql = StringUtils.checkNullString(element.getChildText("sql"));
        String customStr = StringUtils.checkNullString(element.getAttributeValue("customStr"));
        String isnull = StringUtils.checkNullString(element.getChildText("isnull"));
        if(com.ultrapower.commons.lang3.StringUtils.isBlank(isnull)){
            isnull = com.ultrapower.commons.lang3.StringUtils.nvl(element.getAttributeValue("isnull"),"");
        }
        String childrenUrl = StringUtils.checkNullString(element.getAttributeValue("childrenUrl"));
        stringBuffer.append("<td class=\"serachdivTableTd\" style='width:" + lableprecent + "%'>");

        stringBuffer.append(displayName);
        stringBuffer.append("：");
        stringBuffer.append("</td>");
        stringBuffer.append("<td style='width:" + textprecent + "%'");
        if (!colspan.equals(""))
        {
            stringBuffer.append(" colspan=\"" + colspan + "\"");
        }
        if (!rowspan.equals(""))
        {
            stringBuffer.append(" rowspan=\"" + rowspan + "\"");
        }
        stringBuffer.append(">");

        String custsql=sql;
        //request
        if(custsql.indexOf("#")>0)
        {
            HashMap indirectvalues=Parameters.CollectionParameter(valuesMap);
            custsql=SqlReplace.stringReplaceAllVar(sql, indirectvalues);
        }
        
        String html = getCheckBox(checkboxname, id, name, style, childrenname, dictype, "", "", value, null, custsql, customStr, isnull, childrenUrl);
        
        stringBuffer.append(html);
        stringBuffer.append("</td>");

        return stringBuffer.toString();
    }
    private String getSelect(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent, String textprecent)
    {
        StringBuffer stringBuffer = new StringBuffer();
        if (element == null)
            return "";
        String id = StringUtils.checkNullString(element.getAttributeValue("id"));
        String name = StringUtils.checkNullString(element.getAttributeValue("name"));
        String displayName = StringUtils.checkNullString(element.getAttributeValue("displayname"));
        String value = "";
        String istype = StringUtils.checkNullString(element.getAttributeValue("itype"));
        String style = StringUtils.checkNullString(element.getAttributeValue("style"));
        if (istype.equals(""))
        {
            istype = itype;
        }

        if (request != null)
            value = request.getParameter(name);
        if (value == null)
        {
            Object obj = valuesMap.get(name);
            if (obj != null)
            {
                value = obj.toString();
            }
        }
        if (value == null)
        {
            String defaultvalue = StringUtils.checkNullString(element.getAttributeValue("defaultvalue"));
            if (!defaultvalue.equals(""))
            {
                defaultvalue = SqlReplace.stringReplaceAllVar(defaultvalue, valuesMap);
            }
            value = defaultvalue;
        }
        String text = Internation.language(displayName);
        if (!text.equals(""))
            displayName = text;
        String colspan = StringUtils.checkNullString(element.getAttributeValue("colspan"));
        String rowspan = StringUtils.checkNullString(element.getAttributeValue("rowspan"));
        String dictype = StringUtils.checkNullString(element.getAttributeValue("dictype"));
        String childrenname = StringUtils.checkNullString(element.getAttributeValue("childrenname"));
        String sql = StringUtils.checkNullString(element.getChildText("sql"));
        String customStr = StringUtils.checkNullString(element.getAttributeValue("customStr"));
        String isnull = StringUtils.checkNullString(element.getChildText("isnull"));
        if(com.ultrapower.commons.lang3.StringUtils.isBlank(isnull)){
            isnull = com.ultrapower.commons.lang3.StringUtils.nvl(element.getAttributeValue("isnull"),"");
        }
        String childrenUrl = StringUtils.checkNullString(element.getAttributeValue("childrenUrl"));
        stringBuffer.append("<td class=\"serachdivTableTd\" style='width:" + lableprecent + "%'>");

        stringBuffer.append(displayName);
        stringBuffer.append("：");
        stringBuffer.append("</td>");
        stringBuffer.append("<td style='width:" + textprecent + "%'");
        if (!colspan.equals(""))
        {
            stringBuffer.append(" colspan=\"" + colspan + "\"");
        }
        if (!rowspan.equals(""))
        {
            stringBuffer.append(" rowspan=\"" + rowspan + "\"");
        }
        stringBuffer.append(">");

        String custsql=sql;
        //request
        if(custsql.indexOf("#")>0)
        {
            HashMap indirectvalues=Parameters.CollectionParameter(valuesMap);
            custsql=SqlReplace.stringReplaceAllVar(sql, indirectvalues);
        }
        Select select = null;
        /*        if("multi-select".equalsIgnoreCase(element.getName())){
            MultiSelect mselect = new MultiSelect(id,name, style, childrenname, dictype, "", "", value, null, custsql, customStr, isnull, childrenUrl);
            mselect.setTitle(displayName);
            select = mselect;
        } else {*/
            select = new Select(id,name, style, childrenname, dictype, "", "", value, null, custsql, customStr, isnull, childrenUrl);
//        }
//      Select select = new Select(name, "", childrenname, dictype, "", "", value, null, sql, "", isnull, childrenUrl);
        stringBuffer.append(select.getSelect(request));
        stringBuffer.append("</td>");

        return stringBuffer.toString();
    }

    private String getDialog(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent, String textprecent)
    {
        StringBuffer stringBuffer = new StringBuffer();
        if (element == null)
            return "";
        String id = StringUtils.checkNullString(element.getAttributeValue("id"));
        String name = StringUtils.checkNullString(element.getAttributeValue("name"));
        String displayName = StringUtils.checkNullString(element.getAttributeValue("displayname"));
        String onclick = StringUtils.checkNullString(element.getAttributeValue("onclick"));
        String value = "";

        String istype = StringUtils.checkNullString(element.getAttributeValue("itype"));
        if (istype.equals(""))
        {
            istype = itype;
        }

        if (request != null)
            value = request.getParameter(name);
        if (value == null)
        {
            String defaultvalue = StringUtils.checkNullString(element.getAttributeValue("defaultvalue"));
            if (!defaultvalue.equals(""))
            {
                defaultvalue = SqlReplace.stringReplaceAllVar(defaultvalue, valuesMap);
            }
            value = defaultvalue;
        }
        value = tranferQuot(value);
        String text = Internation.language(displayName);
        if (!text.equals(""))
            displayName = text;
        String colspan = StringUtils.checkNullString(element.getAttributeValue("colspan"));
        String rowspan = StringUtils.checkNullString(element.getAttributeValue("rowspan"));
        //String isnewline=StringUtils.checkNullString(element.getAttributeValue("isnewline"));
        stringBuffer.append("<td class=\"serachdivTableTd\" style='width:" + lableprecent + "%'>");

        stringBuffer.append(displayName);
        stringBuffer.append("：");
        stringBuffer.append("</td>");
        stringBuffer.append("<td style='width:" + textprecent + "%'");
        if (!colspan.equals(""))
        {
            stringBuffer.append(" colspan=\"" + colspan + "\"");
        }
        if (!rowspan.equals(""))
        {
            stringBuffer.append(" rowspan=\"" + rowspan + "\"");
        }
        stringBuffer.append(">");
        stringBuffer.append("<input type=\"text\"");
        if (!id.equals(""))
        {
            stringBuffer.append(" id=\"" + id + "\"");
        }
        stringBuffer.append(" name=\"" + name + "\"");
        stringBuffer.append(" class=\"textInput\" readonly='true'");
        stringBuffer.append(" value=\"");
        stringBuffer.append(value);

        stringBuffer.append("\" ");
        if (!onclick.equals(""))
        {
            stringBuffer.append(" onclick=\"");
            stringBuffer.append(onclick);
            stringBuffer.append("\" ");
        }
        stringBuffer.append("/>");
        /*
         * stringBuffer.append("<li class=\"page_choose_button\"
         * onmouseover=\"this.className='page_choose_button_hover'\"");
         * stringBuffer.append("onmouseout=\"this.className='page_choose_button'\"/>选择</li>");
         */
        //stringBuffer.append("<li class='page_search_button' id='com_btn_search' onmouseover=\"this.className='page_search_button_hover'\" onmouseout=\"this.className='page_search_button'\" onclick=\"showsearch()\">搜索</li>");
        stringBuffer.append("</td>");
        //      if(isnewline.equals("1"))
        //      {
        //          //stringBuffer.append("</tr>");
        //          this.line=0;
        //      }
        return stringBuffer.toString();
    }

    private String getHidden(Element element, HttpServletRequest request, HashMap valuesMap, String lableprecent, String textprecent)
    {
        StringBuffer stringBuffer = new StringBuffer();
        if (element == null)
            return "";
        String value = "";
        String name = StringUtils.checkNullString(element.getAttributeValue("name"));
        if (request != null)
            value = request.getParameter(name);
        if (value == null)
        {
            String defaultvalue = StringUtils.checkNullString(element.getAttributeValue("defaultvalue"));
            if (!defaultvalue.equals(""))
            {
                defaultvalue = SqlReplace.stringReplaceAllVar(defaultvalue, valuesMap);
            }
            value = defaultvalue;
        }
        value = tranferQuot(value);
        stringBuffer.append("<input type='hidden' ");
        stringBuffer.append(" name='" + name + "'");
        stringBuffer.append(" value=\"");
        stringBuffer.append(value);
        stringBuffer.append("\" ");
        stringBuffer.append("/>");
        return stringBuffer.toString();
    }

    /**
     * 将字符串中"号转义&quto;
     * 
     * @param value
     * @return
     */
    private String tranferQuot(String value)
    {
        String result = value;
        if (result != null)
            result = result.replaceAll("\"", "&quot;");
        return result;
    }
    /**
     * 得到sql配置文件中配置的搜索条件
     * @return
     */
    public Map<String,String> getConditionConfig()
    {
        Map<String,String> conditionMap = new HashMap<String,String>();
        List lstAction = null;
        if (this.sqlqueryElement != null)
        {
            lstAction = this.sqlqueryElement.getChildren("condition");
        }
        int lstLen = 0;
        if (lstAction != null)
        {
            lstLen = lstAction.size();
            if (lstLen > 0)
            {
                lstLen = 0;
                Element ele = (Element) lstAction.get(0);
                lstAction = ele.getChildren();
                if (lstAction != null)
                    lstLen = lstAction.size();
            }
        }
        Element element;

        String name;

        for (int i = 0; i < lstLen; i++)
        {
            element = (Element) lstAction.get(i);
            name = element.getName();
            String attrName = element.getAttributeValue("name");
            String displayName = StringUtils.checkNullString(element.getAttributeValue("displayname"));
            String text = Internation.language(displayName);
            if (!text.equals(""))
                displayName = text;
            conditionMap.put(attrName, displayName);
            
        }
        return conditionMap;
    }

    public static void main(String[] args)
    {
    //  System.out.println(tranferQuot("aaaa\"dd\""));

    /*
     * String path="E:\\WorkSpace2\\eoms4\\WebRoot\\sqlconfig";
     * StartUp.loadFile(path); QueryCondition queryCondition=new
     * QueryCondition("SQL_QuerySimple_001"); String
     * result=queryCondition.getCondition(null,null);
     * System.out.println(result);
     */
    }
    

    private String getCheckBox(String checkboxname, String id,String name,String style,String childrenname,String dataDicTypeCode,String diValue,
    		String onChangeFun,String value, HashMap customData,String customSql,String customStr,String isnull,String childrenUrl) {
    	
		StringBuffer htmlStr = new StringBuffer(1024);
		
		htmlStr.append("<div id=\""+id+"\" class=\""+style+"\">");

		
		htmlStr.append("<script type='text/javascript'>");
		htmlStr.append(" function checkboxOnclick() { "
				+ " var obj = document.getElementsByName(\"" + checkboxname + "\");"
				+ " check_val = [];"
				+ " for (var k=0;k<obj.length;k++) {"
				+ "   if(obj[k].checked)"
				+ "      check_val.push(obj[k].id);"
				+ " }"
				+ " $(\"input[name='" + name + "']\").val(check_val);"
				+ "}");
		
		htmlStr.append("</script>");
		//<input type="hidden" name="types" value="">
		htmlStr.append("<input type=\"hidden\" name=\"" + name + "\" value=\"\">");
		
    	DicManagerService dicManagerService  = (DicManagerService)WebApplicationManager.getBean("dicManagerService");
		List<DicItem> dicItsmList = null;
		if("".equals(StringUtils.checkNullString(diValue))) {
			dicItsmList = dicManagerService.getRootItsmByDicType(dataDicTypeCode);				
		} else {
			dicItsmList = dicManagerService.getDicItem(diValue, dataDicTypeCode);
		}
		int dicItsmListLen = 0;
		if(dicItsmList!=null)
			dicItsmListLen = dicItsmList.size();
		
//		<input type="checkbox" name="test" value="1"/><span>1</span>
		for(int row=0;row<dicItsmListLen;row++){
			DicItem dicItem = dicItsmList.get(row);
			if(value!=null&&value.equals(dicItem.getDivalue())){
				htmlStr.append("<input onclick=\"checkboxOnclick(this)\" type=\"checkbox\" value=\""+dicItem.getDiname()+"\" id=\""+dicItem.getDiname()+"\" name=\"" + checkboxname + "\" checked=\"checked\">"
						+ "<span>" + dicItem.getDiname()+"</span>&nbsp;&nbsp;");
			}else if(value==null&&dicItem.getIsdefault().equals("1")){
				htmlStr.append("<input onclick=\"checkboxOnclick(this)\" type=\"checkbox\" value=\""+dicItem.getDiname()+"\" id=\""+dicItem.getDiname()+"\" name=\"" + checkboxname + "\" checked=\"checked\">"
						+ "<span>" + dicItem.getDiname()+"</span>&nbsp;&nbsp;");
			}else{
				htmlStr.append("<input onclick=\"checkboxOnclick(this)\" type=\"checkbox\" value=\""+dicItem.getDiname()+"\" id=\""+dicItem.getDiname()+"\" name=\"" + checkboxname + "\">"
						+ "<span>" + dicItem.getDiname()+"</span>&nbsp;&nbsp;");
			}
			if ((row+1)%5 == 0) {
				htmlStr.append("<br>");
			}
		}
		
		htmlStr.append("</div>");
		
		return htmlStr.toString();
    }
    
    public String getItype()
    {
        return itype;
    }

    public void setItype(String itype)
    {
        this.itype = itype;
    }

}
