package com.ultrapower.omcs.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.ultrapower.eoms.fulltext.common.util.PinYinUtils;
import com.univocity.parsers.common.processor.ColumnProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class CVSImport {

    private static String TAB_PREX = "XLS_";

    private static String CHARSET = "UTF-8";
    // private static String CHARSET = "GBK";

    private static String[] CSV = new String[] {
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\电费表样与数据\\OLT的配置.csv",
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\电费表样与数据\\普通空调的配置.csv",
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\电费表样与数据\\中央空调系统的配置.csv",
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\电费表样与数据\\中央空调主机的配置.csv,
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\电费表样与数据\\专用空调的配置.csv"

            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\电费表样与数据\\传输板卡的配置查询结果.csv"
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\租赁费计算-from杨阳\\分表\\铁塔新建.csv"
            // ,"F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\租赁费计算-from杨阳\\分表\\原产权方.csv"
            // ,"F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\租赁费计算-from杨阳\\分表\\存量改造.csv"
            // ,"F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\租赁费计算-from杨阳\\分表\\存量自改.csv"
            // ,"F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\租赁费计算-from杨阳\\分表\\既有共享.csv")
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\铁塔租费测试数据-吴桂强\\铁塔新建.csv"
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\铁塔租费测试数据-吴桂强\\原产权方.csv"
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\铁塔租费测试数据-吴桂强\\存量改造.csv"
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\铁塔租费测试数据-吴桂强\\存量自改.csv"
            // "F:\\ultrapower\\辽宁运维成本管理平台\\docs\\运维成本管理\\铁塔租费测试数据-吴桂强\\既有共享.csv"
//            "起租表字段.csv"
//            "铁塔公司塔类产品月账单.csv",
//            "OMCS_ELEC_STANDING_BOOK_SHEET.csv"
            "TEMP.csv"
            };

    public static void main(String[] args) throws SQLException, IOException {
        // 注册连接eoms数据库的数据源
        Connection conn = null;
        try {
            conn = DBUtils.getConn();
            for (int i = 0; i < CSV.length; i++) {
                File csv = null;
                    csv = new File(ToolsUtils.getRealPath(CVSImport.class,CSV[i]));
                if (!csv.exists()) {
                    System.err.println("文件不存在！" + CSV[i].toString());
                }
                if (!csv.toString().endsWith(".csv")) {
                    System.err.println("文件类型不正确！" + CSV[i].toString());
                }
                importCSV(conn, csv);
            }

        } finally {
            DBUtils.close(conn);
        }

    }

    private static String TABLE_NAME;

    public static void importCSV(Connection conn, File csv) throws IOException, SQLException {
        CsvParserSettings settings = new CsvParserSettings();
        CsvParser parser = new CsvParser(settings);
        parser.beginParsing(csv);
        createTable(conn, csv, parser.parseNext());
        // setTableName(csv);
        importData4CSV(conn, csv, parser);

    }

    private static void createTable(Connection conn, File csv, String[] titles) throws IOException, SQLException {
        List<String> createTableSqls = getCreateTableSql(csv, titles);
        Statement stsm = conn.createStatement();
        int result = 0;
        Iterator<String> it = createTableSqls.iterator();
        while (it.hasNext() && result == 0) {
            String sql = (String) it.next();
            System.out.println(sql);
            try {
                result = stsm.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.println("建表执行结果：" + result);
        }
        DBUtils.close(stsm);
    }

    private static List<String> getCreateTableSql(File csv, String[] titles) throws IOException {
        List<String> sqlList = new ArrayList<String>();
        // 字段对照关系表
        CVSImport.class.getClassLoader().getResource("/");
        Properties prop = new Properties();
        String propFileName = "com\\ultrapower\\omcs\\tools\\column_name.properties";
        InputStreamReader isr = new InputStreamReader(
                CVSImport.class.getClassLoader().getResourceAsStream(propFileName), "UTF-8");
        prop.load(isr);
            
        // PropertiesLoaderUtils.loadAllProperties("column_name.properties");
        // 表英文名
        String fileName = csv.getName().replaceAll("\\.csv", "");
        if(fileName.matches("[A-Z_]*")){
            TABLE_NAME =  fileName;
        }else if(fileName.equals("起租表字段")){
            TABLE_NAME = "OMCS_TOWER_RENT_SHEET_BATCH";
        }else if(fileName.equals("铁塔公司塔类产品月账单")){
            TABLE_NAME = "CT_TOWER_MONTH_BILL";
        }else {
            System.out.println(PinYinUtils.getPinYinHeadChar(fileName.replaceAll("的", "_")));
            TABLE_NAME = TAB_PREX + PinYinUtils.getPinYinHeadChar(fileName.replaceAll("的", "_")).toUpperCase();
        }

        // 表注释
        // 获得第一行表头数据
        List<String> nameList = new ArrayList<String>();
        List<String> nameSqlList = new ArrayList<String>();// omcs_t_tower_rent_bill_batch
        List<String> columnCommnetSqlList = new ArrayList<String>();
        for (int i = 1; i < titles.length; i++) {
            if(titles[i] == null ){
                continue;
            }
            String comment = titles[i].replaceAll("\\*", "");
            String name = prop.getProperty(comment);
            if (StringUtils.isBlank(name)) {
                name = comment.replaceAll("/", "_");
                if (name.indexOf("（") > -1)
                    name = name.substring(0, name.indexOf("（"));
                if (name.indexOf("(") > -1)
                    name = name.substring(0, name.indexOf("("));

                name = PinYinUtils.getPinYinHeadChar(name);
            }
            int repeatTime = 0;
            while (nameList.contains(name)) {
                name += (repeatTime++);
            }
            nameList.add(name);
            if (name.equals("PID")) {
                nameSqlList.add("PID VARCHAR2(50)  default sys_guid() not null");
            } 
            //特殊处理字段
            else if (name.matches("REAL_HANG_HEIGHT_NUM1[1-9]?$") || name.matches("GROUND_ORIG_COST[1-9]?$")
                    || name.matches("ADD_ANT_CNT[1-9]?$")|| name.matches("ADD_SYS_CNT[1-9]?$")|| name.matches("PRODUCT_UNIT_CNT[1-9]?$")) {
                nameSqlList.add(MessageFormat.format("{0} VARCHAR2(50)", name));
            } 
            else if ("DATA_STATUS".equals(name)) {
                nameSqlList.add(MessageFormat.format("{0} Number(2)", name));
            } 
            else if (name.matches("^.*_CODE[0-9]?$")) {
                nameSqlList.add(MessageFormat.format("{0} VARCHAR2(50)", name));
            } else if (name.matches("^.*_COST[1-9]?$")) {
                nameSqlList.add(MessageFormat.format("{0} NUMBER(20,4)", name));
            } else if (name.matches("^.*_NUM[1-9]?$") ) {
                nameSqlList.add(MessageFormat.format("{0} NUMBER(7,2)", name));
            } else if (name.matches("^.*_DATE[1-9]?$")) {
                nameSqlList.add(MessageFormat.format("{0} DATE", name));
            } else if(name.matches("^.*_DCT[1-9]?$")
                    || name.matches("^.*_DCT_XS[1-9]?$")) {
                nameSqlList.add(MessageFormat.format("{0} NUMBER(4,2)", name));
            } else if (name.matches("^.*_CNT[1-9]?$")) {
                nameSqlList.add(MessageFormat.format("{0} NUMBER(3)", name));
            } else {
                nameSqlList.add(MessageFormat.format("{0} VARCHAR2(255)", name));
            }

            columnCommnetSqlList
                    .add(MessageFormat.format("comment on column {0}.{1} is ''{2}''", TABLE_NAME, name, comment));
        }
        sqlList.add(MessageFormat.format("create table {0} ({1})", TABLE_NAME, StringUtils.join(nameSqlList, ",")));
        sqlList.add(MessageFormat.format("comment on table {0}  is ''{1}''", TABLE_NAME, fileName));
        sqlList.addAll(columnCommnetSqlList);
        return sqlList;

    }

    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    private static void importData4CSV(Connection conn, File csv, CsvParser lines) throws IOException, SQLException {
        Statement stat = null;
        int result = 0;
        try {
            stat = conn.createStatement();
            String[] lineArr = null;
            while ((lineArr = lines.parseNext()) != null) {
                //TODO 
                System.out.println(lineArr);
            }
        } finally {
            DBUtils.close(stat);
            System.out.println("插入执行结果：" + result);
        }

    }

}
