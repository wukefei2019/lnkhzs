package com.ultrapower.omcs.tools;

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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.ultrapower.omcs.utils.StringUtils;

/**
 * Created on 2017年2月18日
 * <p>Title		 : 辽宁运维成本管理平台_[子系统统名]_[模块名]</p>
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author		 : 佟广恩 tongguangen@ultrapower.com.cn
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
//                    "com.ultrapower.omcs.common",
//                    "com.ultrapower.omcs.baseinfo",
//               ，     "com.ultrapower.omcs.cost",
//                    "com.ultrapower.omcs.maintenance.costapply",
                    "com.ultrapower.lnkhzs.trace",
//                    "com.ultrapower.omcs.nmc.bud_fill",
//                    "com.ultrapower.omcs.nmc.bg.bench",
                    "WG_",
                    new String[]{
                            "ZL_TRACE_SOURCE_SEND_FLOW",
//                            "ZL_TRACE_SOURCE_FLOW",
//                            "ZL_KHZS_QUESTION",
//                            "ZL_KHZS_QUESTIONNAIRE",
//                            "ZL_KHZS_RELATIONSHIP",
//                            "NGCS_WF_CMPLNTS_ARC_DETAIL_XC",
//                            "NGCS_WF_CMPLNTS_ARC_DETAIL",
//                            "OMCS_CT_ELEC_BILL_UNIT",
//                            "OMCS_CT_RENT_BILL_UNIT",
//                            "OMCS_CT_SF_RENT_BILL_UNIT",
//                            "OMCS_MICRO_RENT_BILL_UNIT",
                            ""
                  }
            }
                            
            };

    private static String TAB_COMMNET_TEMPLAT = 
             "/**\n"
            + " * Created on 2019年4月19日\n"
            + " * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>\n"
            + " * <p>Description: [{0}]</p>\n"
            + " * @author       : \n"
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
            + "    * @author:\n"
            + "    * @param args\n"
            + "    */";
    private static String GETMETHOD_COMMNET_TEMPLAT = 
             "    /**\n"
            + "     * [{0}_Get方法]\n"
            + "     * @author:\n"
            + "     */";

    /**
     * [方法功能中文描述]
     * @author:周鑫 zhouxin1@ultrapower.com.cn
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
                if(StringUtils.isBlank(tableName[j])){
                    continue;
                }
                if(initTable(tableName[j])){
                    createEntity(packages,prex,tableName[j]);
//                    createService(packages, prex, tableName[j]);
//                    createMgr(packages, prex, tableName[j]);
//                    createWeb(packages, prex, tableName[j]);
                }
            }
        }
        
    }
    // 表注释
    static String tab_comment = "";
    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet result = null;
    static String pk = "";
    static String entityPackage = "";
    static String entityName = null;
    static String servicePackage = "";
    static String serviceName = null;
    static {
        try {
            conn = DBUtils.getConn();
            stmt = (Statement) conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public static boolean initTable(String tablename) throws SQLException{
     // 注册连接eoms数据库的数据源
        ResultSet result = stmt.executeQuery("select count(1) from user_tab_columns where table_name = '" + tablename + "'");
        while (result.next()) {// 判断是否还有下一行
            int c = result.getInt(1);// 获取数据库person表中name字段的值
            if (c == 0) {
                System.err.println("tablename error");
                return false;
            }
        }
        
        result = stmt.executeQuery("select * from user_tab_comments where table_name = '" + tablename + "'");
        while (result.next()) {// 判断是否还有下一行
            tab_comment = result.getString("comments");
        }
        // 主键
        result = stmt.executeQuery("select * from user_ind_columns t where t.table_name = '" + tablename
                + "' and t.index_name like 'PK%'");
        if(result.next()) {
            pk = result.getString("column_name");
        }
        return true;
    }
    public static void createService(String packages,String prex,String tablename) throws IOException{
        servicePackage = packages + ".service";
        List<String> model = new ArrayList<String>();
        tablename = tablename.toUpperCase();
        serviceName = "I" + StringUtils.capitalize(StringUtils.toCamelCase(tablename.substring(prex.length()))) + "Service";

        List<String> imports = new ArrayList<String>();
//        imports.add("import javax.persistence.Column;");
//        imports.add("import javax.persistence.Entity;");
//        imports.add("import javax.persistence.GeneratedValue;");
//        imports.add("import javax.persistence.Id;");
//        imports.add("import javax.persistence.Table;");
//        imports.add("import org.hibernate.annotations.GenericGenerator;");
//        imports.add("import com.ultrapower.omcs.common.model.ICommonModel;");
        model.add("package "+ servicePackage +";");
        model.add("");
        model.add(StringUtils.join(imports, "\n"));
        model.add("");
        model.add(MessageFormat.format(TAB_COMMNET_TEMPLAT, tab_comment + "_接口"));
        
        model.add("public interface " + serviceName + " {");
        model.add("}");
        File java = new File(source + servicePackage.replaceAll("\\.", "\\\\"),serviceName+".java");
        output(java, model, true);
    }
    public static void createMgr(String packages,String prex,String tablename) throws IOException{
        packages += ".manager";
        List<String> model = new ArrayList<String>();
        tablename = tablename.toUpperCase();
        String className = StringUtils.capitalize(StringUtils.toCamelCase(tablename.substring(prex.length()))) + "MgrImpl";
        List<String> imports = new ArrayList<String>();
        imports.add("import " + entityPackage + "." + entityName + ";");
        imports.add("import "+servicePackage+"."+serviceName+";");
        imports.add("import com.ultrapower.eoms.common.core.dao.IDao;");
        model.add("package "+ packages +";");
        model.add("");
        model.add(StringUtils.join(imports, "\n"));
        model.add("");
        model.add(MessageFormat.format(TAB_COMMNET_TEMPLAT, tab_comment + "_接口"));
        
        model.add("public class " + className + " implements "+serviceName+" {");
        model.add("");
        model.add("    private IDao<"+entityName+"> " + StringUtils.uncapitalize(entityName) + "Dao;");
        model.add("");
        model.addAll(getSetMethod("IDao<"+entityName+">", StringUtils.uncapitalize(entityName) + "Dao"));
        model.add("");
        model.add("}");
        File java = new File(source + packages.replaceAll("\\.", "\\\\"),className+".java");
        output(java, model, true);
    }
    public static void createWeb(String packages,String prex,String tablename) throws IOException{
        packages += ".web";
        List<String> model = new ArrayList<String>();
        tablename = tablename.toUpperCase();
        String className = StringUtils.capitalize(StringUtils.toCamelCase(tablename.substring(prex.length()))) + "Action";
        List<String> imports = new ArrayList<String>();
        imports.add("import "+servicePackage+"."+serviceName+";");
        imports.add("import com.ultrapower.omcs.base.web.BaseAction;");
        model.add("package "+ packages +";");
        model.add("");
        model.add(StringUtils.join(imports, "\n"));
        model.add("");
        model.add(MessageFormat.format(TAB_COMMNET_TEMPLAT, tab_comment + "_WEB"));
        
        model.add("public class " + className + " extends BaseAction {");
        model.add("");
        model.add("    private static final long serialVersionUID = -1L;");
        model.add("");
        model.add("    private "+serviceName + " " + StringUtils.uncapitalize(entityName+"Service")+";");
        model.add("");
        model.addAll(getSetMethod(serviceName, StringUtils.uncapitalize(entityName+"Service")));
        model.add("");
        model.add("}");
        File java = new File(source + packages.replaceAll("\\.", "\\\\"),className+".java");
        output(java, model, true);
    }
    
    
    public static void createEntity(String packages,String prex,String tablename) throws SQLException, IOException{
        entityPackage =  packages += ".model";
        List<String> model = new ArrayList<String>();
        tablename = tablename.toUpperCase();
        
        
        // 获取表结构
        Set<String> imports = new HashSet<String>();
        
        imports.add("import javax.persistence.Column;");
        imports.add("import javax.persistence.Entity;");
        imports.add("import javax.persistence.GeneratedValue;");
        imports.add("import javax.persistence.Id;");
        imports.add("import javax.persistence.Table;");
        imports.add("import org.hibernate.annotations.GenericGenerator;");
        imports.add("import com.ultrapower.omcs.common.model.ICommonModel;");
        
        List<String> columns = new ArrayList<String>();
        Map<String, String> columnType = new HashMap<String, String>();
        Map<String, String> columnNullable = new HashMap<String, String>();
        Map<String, String> columnLength = new HashMap<String, String>();
        Map<String, String> columnDefaultValue = new HashMap<String, String>();
        result = stmt.executeQuery("select * from user_tab_columns where table_name = '" + tablename + "' order by column_name");
        while (result.next()) {// 判断是否还有下一行
            String columnName = result.getString("column_name");
            columns.add(columnName);
            String dataType = result.getString("data_type");
            if ("VARCHAR2".equalsIgnoreCase(dataType) || "CHAR".equalsIgnoreCase(dataType) || "CLOB".equalsIgnoreCase(dataType) ) {
                dataType = "String";
            } else if (StringUtils.isNotBlank(dataType) && dataType.startsWith("NUMBER")) {
                if("0".equals(result.getString("data_scale"))){
                    dataType = "Integer";
                }else {
                    dataType = "Double";
                }
            } else if ("DATE".equalsIgnoreCase(dataType) || "TIMESTAMP(6)".equalsIgnoreCase(dataType)) {
                imports.add("import java.util.Date;");
                dataType = "Date";
            } else if ("BLOB".equalsIgnoreCase(dataType)) {
                dataType = "byte[]";
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
        model.add(MessageFormat.format(TAB_COMMNET_TEMPLAT, tab_comment + "_实体"));
        entityName = StringUtils.capitalize(StringUtils.toCamelCase(tablename.substring(prex.length())));
        
        model.add("@Entity");
        model.add("@Table(name = \""+tablename+"\")");
        model.add("public class " + entityName + " implements ICommonModel {");

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
            } else if ("byte[]".equals(columnType.get(column))) {
                model.add("    @Column(name = \""+column+"\", columnDefinition = \"BLOB\" ,unique=false ,nullable="+columnNullable.get(column)+", insertable=true, updatable=true ,length="+columnLength.get(column)+" )");
            } else {
                model.add("    @Column(name = \""+column+"\", unique=false ,nullable="+columnNullable.get(column)+", insertable=true, updatable=true ,length="+columnLength.get(column)+" )");
            }
            model.addAll(getGetMethod(columnType.get(column), StringUtils.toCamelCase(column)));
            model.add(MessageFormat.format(SETMETHOD_COMMNET_TEMPLAT, comments.get(column)));
            model.addAll(getSetMethod(columnType.get(column), StringUtils.toCamelCase(column)));
            model.add("");
        }
        model.add("}");
        File java = new File(source + packages.replaceAll("\\.", "\\\\"),entityName+".java");
        output(java, model, true);
    }
    
    public static void output(File java,List<String> model,boolean flag) throws IOException{
        if (java.exists()) {
            System.out.println("文件已经存在：" + java.getName());
            if(flag){
                java.delete();
            } else {
                return ;
            }
        } 
        java.createNewFile();
        System.out.println("文件已经创建：" + java.getName());
        IOUtils.writeLines(model, "\n", FileUtils.openOutputStream(java), "UTF-8");
    }

    
    public static List<String> getGetMethod(String type,String property){
        List<String> lines = new ArrayList<String>();
        lines.add("    public " + type + " get" + StringUtils.capitalize(property) + "() {");
        lines.add("        return " + property + ";");
        lines.add("    }");
        return lines;
    }
    public static List<String> getSetMethod(String type,String property){
        List<String> lines = new ArrayList<String>();
        lines.add("    public void set" + StringUtils.capitalize(property) + "("+type+" "+property +") {");
        lines.add("        this." + property+ " = "+property+";" );
        lines.add("    }\n");
        return lines;
    }
}
