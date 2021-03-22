package com.ultrapower.eoms.common.core.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.h2.util.FileUtils;

import com.ultrapower.omcs.utils.StringUtils;

/**
 * Created on 2017年2月18日
 * <p>Title		 : 辽宁运维成本管理平台_[子系统统名]_[模块名]</p>
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author		 : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class CreateEntity {
    private static String source =System.getProperty("user.dir")+"\\src\\main\\";
    private static Object[][]json = new Object[][]{
//            new Object[]{"com.ultrapower.omcs.calculate.model","omcs_t_conf_",new String[]{
//            "omcs_t_conf_match_build",
//            "omcs_t_conf_room_build",
//            "omcs_t_conf_tower_build",
//            "omcs_t_conf_tower_e_discount",
//            "omcs_t_conf_tower_windpress",
//            "OMCS_T_CONF_GROUND_COST", 
//            "omcs_t_conf_induct_elec_cost",
//            "OMCS_T_ELEC_BILL",
//            "OMCS_T_ELEC_BILL_invoice",
//            "omcs_t_elec_bill_siteinfo"
//            
//            }},
        new Object[]{
                    "com.ultrapower.lnkhzs.trace.model",
                    "",
                    new String[]{
                    		"ZL_TRACE_SOURCE_DEPADMIN",
                    		"ZL_TRACE_SOURCE_LIST",
                    		"ZL_TRACE_SOURCE_BATCH",
                    		"ZL_TRACE_SOURCE_FLOW"
                    		/*"JK_BS_ACHIEVEMENTS"*/
                    		/*"JK_BS_NOTICE",*/
                    		/*"JK_BS_INDIDETAILSQL"*/
                    		/*"JK_BS_INDICATORORDER",
                    		"JK_BS_INDICATOR"*/
/*                    		"JK_BS_CHARTS",
                    		"JK_BS_INDICATOR",
                    		"JK_BS_INDICATORCHART",
                    		"JK_BS_INDICATORLIMIT",
                    		"JK_BS_INDICATORORDER",
                    		"JK_BS_ROLE",
                    		"JK_BS_ROLEINDICATOR",
                    		"JK_BS_ROLEINDICHART",
                    		"JK_BS_ROLEUSER"*/
                  }}
            };

    private static String TAB_COMMNET_TEMPLAT = 
             "/**\n"
            + " * Created on 2018年1月4日\n"
            + " * <p>Title      : 辽宁移动质量监控_[子系统统名]_[模块名]</p>\n"
            + " * <p>Description: [{0}_实体]</p>\n"

            + " * @version      : 1.0\n" + " */\n"
            + "/*--------------------------------------------------------------\n"
            + " *修改履历： 1) [修改时间] [修改人] [修改说明]；\n"
            + " *--------------------------------------------------------------\n"
            + " */";
    private static String COLUMN_COMMNET_TEMPLAT = 
             "    /**\n"
            + "     *{0}_字段 \n"
            + "     */";
    private static String SETMETHOD_COMMNET_TEMPLAT = 
             "    /**\n"
            + "    * [{0}_Set方法]\n"
            + "    * @param args\n"
            + "    */";
    private static String GETMETHOD_COMMNET_TEMPLAT = 
             "    /**\n"
            + "     * [{0}_Get方法]\n"
            + "     */";

    /**
     * [方法功能中文描述]
     * @author:
     * @param args
     * @throws SQLException
     * @throws IOException 
     */
    public static void main(String[] args) throws SQLException, IOException {
       for (int i = 0; i < json.length; i++) {
            Object []obj = json[i];
            String packages = (String) obj[0];
            String prex = (String) obj[1];
            String []tableName = (String[]) obj[2];
            for (int j = 0; j < tableName.length; j++) {
                createEntity(packages,prex,tableName[j]);
            }
        }
        
    }

    public static void createEntity(String packages,String prex,String tablename) throws SQLException, IOException{
        List<String> model = new ArrayList<String>();
        tablename = tablename.toUpperCase();
        // 注册连接eoms数据库的数据源
        Connection conn = DBUtils.getConn();
        Statement stmt = (Statement) conn.createStatement();
        ResultSet result = stmt.executeQuery("select count(1) from user_tables where table_name = '" + tablename + "'");
        while (result.next()) {// 判断是否还有下一行
            int c = result.getInt(1);// 获取数据库person表中name字段的值
            if (c != 1) {
                System.err.println("tablename error");
                return;
            }
        }
        // 表注释
        String tab_comment = "";
        result = stmt.executeQuery("select * from user_tab_comments where table_name = '" + tablename + "'");
        while (result.next()) {// 判断是否还有下一行
            tab_comment = result.getString("comments");
        }

        // 主键
        String pk = "";
        result = stmt.executeQuery("select * from user_ind_columns t where t.table_name = '" + tablename
                + "' and t.index_name like 'PK%'");
        if(result.next()) {
            pk = result.getString("column_name");
        }
        
        
        
        // 获取表结构
        Set<String> imports = new HashSet<String>();
        
        imports.add("import javax.persistence.Column;");
        imports.add("import javax.persistence.Entity;");
        imports.add("import javax.persistence.GeneratedValue;");
        imports.add("import javax.persistence.Id;");
        imports.add("import javax.persistence.Table;");
        imports.add("import org.hibernate.annotations.GenericGenerator;");
        imports.add("import com.ultrapower.omcs.base.model.IOmcsModel;");
        
        List<String> columns = new ArrayList<String>();
        Map<String, String> columnType = new HashMap<String, String>();
        Map<String, String> columnNullable = new HashMap<String, String>();
        Map<String, String> columnLength = new HashMap<String, String>();
        Map<String, String> columnDefaultValue = new HashMap<String, String>();
        result = stmt.executeQuery("select * from user_tab_columns where table_name = '" + tablename + "'");
        while (result.next()) {// 判断是否还有下一行
            String columnName = result.getString("column_name");
            columns.add(columnName);
            String dataType = result.getString("data_type");
            if ("VARCHAR2".equalsIgnoreCase(dataType) || "CHAR".equalsIgnoreCase(dataType)) {
                dataType = "String";
            } else if (StringUtils.isNotBlank(dataType) && dataType.startsWith("NUMBER")) {
                if("0".equals(result.getString("data_scale")) || result.getString("data_scale") == null){
                    dataType = "Long";
                }else {
                    dataType = "Double";
                }
            } else if ("DATE".equalsIgnoreCase(dataType) || "TIMESTAMP(6)".equalsIgnoreCase(dataType)) {
                imports.add("import java.util.Date;");
                dataType = "Date";
            } 
            if("CREATE_TIME".equals(columnName) || "LASTUPDDATE".equals(columnName)){
                columnNullable.put(result.getString("column_name"), "true");
            } else if(!"PID".equalsIgnoreCase(columnName) && "N".equals(result.getString("nullable"))){
                columnNullable.put(result.getString("column_name"), "false");
                String dataDefault = result.getString("data_default");
                if(dataDefault!=null && !"sys_guid()".equalsIgnoreCase(dataDefault)){
                    columnDefaultValue.put(result.getString("column_name"), dataDefault.replaceAll("'", "\""));
                }
            } else {
                columnNullable.put(result.getString("column_name"), "Y".equals(result.getString("NULLABLE"))? "true":"false");
            }
            columnLength.put(result.getString("column_name"), result.getString("DATA_LENGTH"));
            columnType.put(result.getString("column_name"), dataType);
        }
        // 获取注释
        Map<String, String> comments = new HashMap<String, String>();
        result = stmt.executeQuery("select * from user_col_comments where table_name = '" + tablename + "'");
        while (result.next()) {// 判断是否还有下一行
            comments.put(result.getString("column_name"), result.getString("comments"));
        }
        // 输出
        
        
        model.add("package "+ packages +";");
        model.add("");
        model.add(StringUtils.join(imports, "\n"));
        model.add("");
        model.add(MessageFormat.format(TAB_COMMNET_TEMPLAT, tab_comment));
        String className = StringUtils.capitalize(StringUtils.toCamelCase(tablename.substring(prex.length())));
        
        model.add("@Entity");
        model.add("@Table(name = \""+tablename+"\")");
        model.add("public class " + className + " implements IOmcsModel{");

        Iterator<String> colIt = columns.iterator();
        while (colIt.hasNext()) {
            String column = (String) colIt.next();
            model.add(MessageFormat.format(COLUMN_COMMNET_TEMPLAT, comments.get(column)));
            String str = "    private " + columnType.get(column) + " " + StringUtils.toCamelCase(column) ;
            if(columnDefaultValue.get(column) != null){
                str +=" = " + columnDefaultValue.get(column).trim();
            }
            str +=";\n";
            model.add(str);
        }
        //
        colIt = columns.iterator();
        while (colIt.hasNext()) {
            String column = (String) colIt.next();
            model.add(MessageFormat.format(GETMETHOD_COMMNET_TEMPLAT, comments.get(column)));
            if("pid".equalsIgnoreCase(column) || column.equalsIgnoreCase(pk)){
                model.add("    @Id");
                model.add("    @GeneratedValue(generator = \"system-uuid\")");
                model.add("    @GenericGenerator(name = \"system-uuid\", strategy = \"assigned\")");
                model.add("    @Column(name = \""+column+"\", unique=true, nullable=false, insertable=true, updatable=true, length=50 )");
            }else {
                model.add("    @Column(name = \""+column+"\", unique=false ,nullable="+columnNullable.get(column)+", insertable=true, updatable=true ,length="+columnLength.get(column)+" )");
            }
            model.add("    public " + columnType.get(column) + " get" + StringUtils.capitalize(StringUtils.toCamelCase(column)) + "() {");
            model.add("        return " + StringUtils.toCamelCase(column) + ";");
            model.add("    }\n");
            model.add(MessageFormat.format(SETMETHOD_COMMNET_TEMPLAT, comments.get(column)));
            model.add("    public void set" + StringUtils.capitalize(StringUtils.toCamelCase(column)) + "("+columnType.get(column)+" "+StringUtils.toCamelCase(column) +") {");
            model.add("        this." + StringUtils.toCamelCase(column) + " = "+StringUtils.toCamelCase(column)+";" );
            model.add("    }\n");
        }
        model.add("}");
        File java = new File(source + packages.replaceAll("\\.", "\\\\"),className+".java");
        if (java.exists()) {
            System.out.println("文件已经存在");
            java.delete();
        } 
        java.createNewFile();
        IOUtils.writeLines(model, "\n", FileUtils.openFileOutputStream(java), "UTF-8");
    }

}
