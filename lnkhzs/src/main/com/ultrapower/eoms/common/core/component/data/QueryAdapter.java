package com.ultrapower.eoms.common.core.component.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ultrapower.eoms.common.core.component.dacp.DacpResult;
import com.ultrapower.eoms.common.core.component.dacp.JSONUtils;
import com.ultrapower.eoms.common.core.component.dacp.ParamBean;
import com.ultrapower.eoms.common.core.component.database.ConnectionPool;
import com.ultrapower.eoms.common.core.util.DBUtils;
import com.ultrapower.eoms.common.core.util.StringUtils;

import net.sf.json.JSONObject;


public class QueryAdapter {
    
	private static Pattern UNDERLINE_PATTERN = Pattern.compile("_([a-z])");
	
    protected Logger logger = LoggerFactory.getLogger(QueryAdapter.class);
    
    protected static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(15000)  
            .setConnectTimeout(15000)  
            .setConnectionRequestTimeout(15000)  
            .build();
    
//    protected String dacpurl = "http://192.168.1.81:8055/lnzljk/HttpServlet";
    protected String dacpurl = "http://10.68.66.145/dacpServer/HttpServlet";
    protected String poolName = "";

    private String dbType = "ORACLE";
    /**
        用于executeQuery方式时查询的总行数
     */
    private int rowCount = 0;

    /**
    用于executeQuery方式时查询的当前页数
     */
    // private int pageNumber = 0;

    /**
    用于executeQuery方式时查询的每页行数
     */
    private int pageSize = 0;

    /**
    用于executeQuery方式时查询的总页数
     */
    private int pageCount = 0;

    /**构造函数
       @param poolName - 数据连接池名称，如果没有则取默认的连接池
       @roseuid 4B56BE3002D4
     */
    public int getPageCount() {
        // int result=0;
        if (this.rowCount > 0 && pageSize > 0) {
            pageCount = rowCount / pageSize;
            if ((rowCount % pageSize) > 0)
                pageCount++;
        }

        return pageCount;
    }

    public int getQueryResultSetCount() {
        return this.rowCount;
    }

    public QueryAdapter(String poolName, String dbType) {
        this.poolName = StringUtils.checkNullString(poolName).trim();
        this.dbType = StringUtils.checkNullString(dbType).trim();
        if ("".equals(this.dbType)) {
            this.dbType = "ORACLE";
        }
    }

    public QueryAdapter(String poolName) {
        this.poolName = StringUtils.checkNullString(poolName).trim();
    }

    /**
     * 构造函数
       @roseuid 4B56CB8800CF
     */
    public QueryAdapter() {

    }

    /**
     * ORACLE
     * @param p_sql
     * @param values
     * @return
     */
    public DataTable executeQuery(String p_sql, final Object... values) {
        return executeQuery(p_sql, values, 0, 0, 0);
    }

    public DataTable executeQuery(String p_sql, Object[] values, int p_isCount) {
        return executeQuery(p_sql, values, 0, 0, p_isCount);
    }

    /**
     * DACP
     * @param p_sql
     * @param values
     * @return
     */
    public DataTable executeDacpQuery(String p_sql, final Object... values) {
        return executeDacpQuery(p_sql, values, 0, 0, 0);
    }

    public DataTable executeDacpQuery(String p_sql, Object[] values, int p_isCount) {
        return executeDacpQuery(p_sql, values, 0, 0, p_isCount);
    }
    
    /**
     * 取得满足sql条件的记录
     * @param p_curpage 当前页码，从1开始计数
     * @param p_pageSize 当前每页记录数
     * @param p_sql sql语句
     * @param values 参数
     * @return
     */
    public DataTable executeQueryTopN(String p_sql, int p_curpage, int p_pageSize, Object... values) {
        if (p_curpage < 1)
            p_curpage = 1;
        return executeQuery(p_sql, values, p_curpage, p_pageSize, 0);
    }
    
    
    

    /**
     * 取得count数
     * @param p_sql
     * @param values
     * @return
     */
    public Long getCount(String p_sql, Object... values) {
        DataTable dt = this.executeQuery(p_sql, values);
        if (dt == null || dt.length() == 0) {
            return 0L;
        } else {
            return dt.getDataRow(0).getLong(0);
        }
    }
    

    /**
     * 将带参数的SQL转换为普通的SQL
     */
    public String getSqlString(final String p_sql, final Object[] values) {
        StringBuffer sqlt = new StringBuffer();
        int chartLen = 0;
        if (p_sql != null) {
            chartLen = p_sql.length();
        }

        int in = 0;
        for (int chartAt = 0; chartAt < chartLen; chartAt++) {
            if (p_sql.charAt(chartAt) == '?') {
                sqlt.append("'" + StringUtils.checkNullString(values[in]) + "'");
                in++;
            } else
                sqlt.append(p_sql.charAt(chartAt));
        }
        // System.out.println("Query By SQL: "+sqlt.toString());
        return sqlt.toString();

    }

    protected Connection conn = null;
    
    public <T> List<T> executeObject(Class<T> cls,String p_sql, final Object... values) {
        return executeObject(cls, p_sql, values, 0, 0, 0);
    }
    
    public <T> List<T> executeObject(Class<T> cls, final String p_sql, final Object[] values, final int p_curpage, final int p_pageSize, final int p_isCount){
        List list = null;
        
        this.pageSize = p_pageSize;
        String sql = p_sql;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            conn = ConnectionPool.getConn(this.poolName);
            int intStartRow = 0;
            if (p_curpage == 1)
                intStartRow = 1;
            else if (p_curpage > 1)
                intStartRow = (p_curpage - 1) * p_pageSize + 1;
            else
                intStartRow = 0;
            int intRowCount = p_curpage * p_pageSize;

            int vLens = 0;
            if (values != null)
                vLens = values.length;
            if (p_isCount == 2) {
                logger.debug("Query By SQL: {}",getSqlString(p_sql, values));
            }
            if (p_isCount > 0)// 查询数据总行数
                this.rowCount = getQueryRowCount(p_sql, values);

            if (p_curpage > 0 && p_pageSize > 0) {
                // if(vLens>0)//变量绑定的分页
                sql = QueryControl.bindGetSqlStringForPagination(sql, dbType, p_curpage, p_pageSize);
                /*
                 * else
                 * sql=QueryControl.getSqlStringForPagination(sql,dbType,p_curpage
                 * ,p_pageSize);
                 */
            }

            pstmt = conn.prepareStatement(sql);

            int i;
            for (i = 0; i < vLens; i++) {
                if (values[i] != null) {
                    pstmt.setObject(i + 1, values[i]);
                }
            }

            if (p_curpage > 0 && p_pageSize > 0) {
                if (this.dbType.equals("ORACLE")) {
                    i++;
                    pstmt.setObject(i, String.valueOf(intRowCount));
                    i++;
                    pstmt.setObject(i, String.valueOf(intStartRow));
                } else if (this.dbType.equals("MYSQL")) {
                    i++;
                    // pstmt.setObject( i, String.valueOf(intStartRow));
                    pstmt.setInt(i, intStartRow - 1);
                    i++;
                    // pstmt.setObject( i, String.valueOf(p_pageSize));
                    pstmt.setInt(i, p_pageSize);
                } else if (this.dbType.equals("SQLSERVER")) {

                } else if (this.dbType.equals("DACP")) {
                    i++;
                    pstmt.setObject(i, String.valueOf(intRowCount));
                    i++;
                    pstmt.setObject(i, String.valueOf(intStartRow));
                }

            }

            resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cols = rsmd.getColumnCount();
            // 去掉为分页加行数的数据列
            if (dbType.equals("ORACLE") && p_curpage > 0 && p_pageSize > 0) {
                cols--;
            }
       
            String colName;
            int[] columnType = new int[cols];
            for (int c = 0; c < cols; c++) {
                columnType[c] = rsmd.getColumnType(c + 1);
            }
            
            
            Blob m_Blob;
            Clob m_clob;
            list = new ArrayList<T>();
            Map<String,Object> map = null;
            T t = null;
            
            while (resultSet.next()) {
                
                t = cls.newInstance();
                map = new HashMap<String,Object>();
                for (i = 1; i <= cols; i++) {
                    colName = rsmd.getColumnName(i);
                    Object value;
                    if (resultSet.getObject(i) == null)
                        value = "";
                    else {
                        if (rsmd.getColumnType(i) == java.sql.Types.CLOB) {
                            m_clob = resultSet.getClob(i);
                            value = getClobString(m_clob);
                        } else if (rsmd.getColumnType(i) == java.sql.Types.BLOB
                                || rsmd.getColumnType(i) == java.sql.Types.LONGVARBINARY) {
                            m_Blob = resultSet.getBlob(i);
                            value = getBlobString(m_Blob);
                        } else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP){
                            value = resultSet.getTimestamp(i).toString();
                        } else if (rsmd.getColumnType(i) == java.sql.Types.DATE){
                            value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getDate(i));
                        } else
                            value = resultSet.getObject(i); // 作通用类型处理,这样row中的类型都是Object型的。
                    }
                    if(colName.contains("_")){
                    	map.put(camelName(colName), value);
                    }else{
                    	map.put(colName.toLowerCase(), value);
                    }
                }
                BeanUtils.populate(t, map);
                list.add(t);
            }// while(resultSet.next())
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception ex) {
            }
            ConnectionPool.free(conn);
        }
        
        return list;
    }

    public DataTable executeQuery(final String p_sql, final Object[] values, final int p_curpage, final int p_pageSize,
            final int p_isCount) {
        this.pageSize = p_pageSize;
        String sql = p_sql;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        DataTable dataTable = null;
        try {
            conn = ConnectionPool.getConn(this.poolName);
            int intStartRow = 0;
            if (p_curpage == 1)
                intStartRow = 1;
            else if (p_curpage > 1)
                intStartRow = (p_curpage - 1) * p_pageSize + 1;
            else
                intStartRow = 0;
            int intRowCount = p_curpage * p_pageSize;

            int vLens = 0;
            if (values != null)
                vLens = values.length;
            logger.debug("Query By SQL: {}",getSqlString(p_sql, values));
            if (p_isCount > 0)// 查询数据总行数
                this.rowCount = getQueryRowCount(p_sql, values);

            if (p_curpage > 0 && p_pageSize > 0) {
                // if(vLens>0)//变量绑定的分页
                sql = QueryControl.bindGetSqlStringForPagination(sql, dbType, p_curpage, p_pageSize);
                /*
                 * else
                 * sql=QueryControl.getSqlStringForPagination(sql,dbType,p_curpage
                 * ,p_pageSize);
                 */
            }

            pstmt = conn.prepareStatement(sql);

            int i;
            for (i = 0; i < vLens; i++) {
                if (values[i] != null) {
                    pstmt.setObject(i + 1, values[i]);
                }
            }

            if (p_curpage > 0 && p_pageSize > 0) {
                if (this.dbType.equals("ORACLE")) {
                    i++;
                    pstmt.setObject(i, String.valueOf(intRowCount));
                    i++;
                    pstmt.setObject(i, String.valueOf(intStartRow));
                } else if (this.dbType.equals("MYSQL")) {
                    i++;
                    // pstmt.setObject( i, String.valueOf(intStartRow));
                    pstmt.setInt(i, intStartRow - 1);
                    i++;
                    // pstmt.setObject( i, String.valueOf(p_pageSize));
                    pstmt.setInt(i, p_pageSize);
                } else if (this.dbType.equals("SQLSERVER")) {

                } else if (this.dbType.equals("DACP")) {
                    i++;
                    pstmt.setObject(i, String.valueOf(intRowCount));
                    i++;
                    pstmt.setObject(i, String.valueOf(intStartRow));
                }

            }

            resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cols = rsmd.getColumnCount();
            // 去掉为分页加行数的数据列
            if (dbType.equals("ORACLE") && p_curpage > 0 && p_pageSize > 0) {
                cols--;
            }
            dataTable = new DataTable("QTABLE");
            String colName;
            int[] columnType = new int[cols];
            for (int c = 0; c < cols; c++) {
                columnType[c] = rsmd.getColumnType(c + 1);
            }
            dataTable.setColumnType(columnType);
            Blob m_Blob;
            Clob m_clob;
            while (resultSet.next()) {
                DataRow dtRow = new DataRow();
                dtRow.setOptype(4);
                for (i = 1; i <= cols; i++) {
                    colName = rsmd.getColumnName(i);
                    Object value;
                    if (resultSet.getObject(i) == null)
                        value = "";
                    else {                        
                        if (rsmd.getColumnType(i) == java.sql.Types.CLOB) {
                            m_clob = resultSet.getClob(i);
                            value = getClobString(m_clob);
                        } else if (rsmd.getColumnType(i) == java.sql.Types.BLOB
                                || rsmd.getColumnType(i) == java.sql.Types.LONGVARBINARY) {
                            m_Blob = resultSet.getBlob(i);
                            value = getBlobString(m_Blob);
                        } else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP){
                            value = resultSet.getTimestamp(i).toString();
                        } else if (rsmd.getColumnType(i) == java.sql.Types.DATE){
                            value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getDate(i));
                        } else
                            value = resultSet.getObject(i); // 作通用类型处理,这样row中的类型都是Object型的。

                        
                        
                        
                    }
                    dtRow.put(colName, value);
                }
                dataTable.putDataRow(dtRow);
            }// while(resultSet.next())
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception ex) {
            }
            ConnectionPool.free(conn);
        }
        return dataTable;
    }

    public DataTable executeDacpQuery(final String p_sql, final Object[] values, final int p_curpage, final int p_pageSize,
            final int p_isCount) {
        this.pageSize = p_pageSize;
        String sql = p_sql;
        DataTable dataTable = null;
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        ParamBean bean = new ParamBean();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        int intStartRow = 0;
        if (p_curpage == 1)
            intStartRow = 1;
        else if (p_curpage > 1)
            intStartRow = (p_curpage - 1) * p_pageSize + 1;
        else
            intStartRow = 0;
        int intRowCount = p_curpage * p_pageSize;

        int vLens = 0;
        if (values != null)
            vLens = values.length;
        logger.debug("Query By SQL: {}",getSqlString(p_sql, values));
        if (p_isCount > 0)// 查询数据总行数
            this.rowCount = getDacpQueryRowCount(p_sql, values);

        if (p_curpage > 0 && p_pageSize > 0) {
            // if(vLens>0)//变量绑定的分页
            sql = QueryControl.bindGetSqlStringForPagination(sql, dbType, p_curpage, p_pageSize);
            /*
             * else
             * sql=QueryControl.getSqlStringForPagination(sql,dbType,p_curpage
             * ,p_pageSize);
             */
        }
        
        int i;
        for (i = 0; i < vLens; i++) {
            if (values[i] != null) {
//                    pstmt.setObject(i + 1, values[i]);
                map.put(String.valueOf(i + 1), values[i]);
            }
        }

        if (p_curpage > 0 && p_pageSize > 0) {
            if (this.dbType.equals("ORACLE")) {
                i++;
                //pstmt.setObject(i, String.valueOf(intRowCount));
                map.put(String.valueOf(i), String.valueOf(intRowCount));
                i++;
                //pstmt.setObject(i, String.valueOf(intStartRow));
                map.put(String.valueOf(i), String.valueOf(intStartRow));
            } else if (this.dbType.equals("MYSQL")) {
                i++;
                //pstmt.setInt(i, intStartRow - 1);
                map.put(String.valueOf(i), intStartRow - 1);
                i++;
                // pstmt.setObject( i, String.valueOf(p_pageSize));
                //pstmt.setInt(i, p_pageSize);
                map.put(String.valueOf(i), p_pageSize);
            } else if (this.dbType.equals("SQLSERVER")) {

            } else if (this.dbType.equals("DACP")) {
            	 i++;
                 //pstmt.setObject(i, String.valueOf(intRowCount));
                 map.put(String.valueOf(i), intRowCount);
                 i++;
                 //pstmt.setObject(i, String.valueOf(intStartRow));
                 map.put(String.valueOf(i), intStartRow - 1);
            }

        }

        // 切换HTTP接口数据源
        String url = dacpurl;
    	bean.setSql(sql);
        bean.setParamMap(map);
        JSONObject json = JSONObject.fromObject(bean);
        
        DacpResult dacpresult = doDacpQuery(json, url);
        if (dacpresult != null && dacpresult.getList() != null) {
        	list = dacpresult.getList();
        }

        dataTable = new DataTable("QTABLE");
        String colName;
        
        for (Map<String, Object> mao: list) {

            DataRow dtRow = new DataRow();
            dtRow.setOptype(4);
        	for (String cname : mao.keySet()) {
            	
        		colName = cname;
        		Object value;
        		if (mao.get(cname) == null) {
        			value = "";
        		} else {
        			value = mao.get(cname);
        		}
        		dtRow.put(colName, value);
            }
        	dataTable.putDataRow(dtRow);
        }
            
        return dataTable;
    }
    
    public DataTable executeDacpQueryDouble(final String p_sql, final Object[] values, final int p_curpage, final int p_pageSize,
            final int p_isCount) {
        this.pageSize = p_pageSize;
        String sql = p_sql;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        DataTable dataTable = null;
        try {
//            conn = ConnectionPool.getConn(this.poolName);
            conn = DBUtils.getDacpConn();
            int intStartRow = 0;
            if (p_curpage == 1)
                intStartRow = 1;
            else if (p_curpage > 1)
                intStartRow = (p_curpage - 1) * p_pageSize + 1;
            else
                intStartRow = 0;
            int intRowCount = p_curpage * p_pageSize;

            int vLens = 0;
            if (values != null)
                vLens = values.length;
            logger.debug("Query By SQL: {}",getSqlString(p_sql, values));
            if (p_isCount > 0)// 查询数据总行数
                this.rowCount = getDacpQueryRowCountDouble(p_sql, values);

            if (p_curpage > 0 && p_pageSize > 0) {
                // if(vLens>0)//变量绑定的分页
                sql = QueryControl.bindGetSqlStringForPagination(sql, dbType, p_curpage, p_pageSize);
                /*
                 * else
                 * sql=QueryControl.getSqlStringForPagination(sql,dbType,p_curpage
                 * ,p_pageSize);
                 */
            }

            pstmt = conn.prepareStatement(sql);

            int i;
            for (i = 0; i < vLens; i++) {
                if (values[i] != null) {
                    pstmt.setObject(i + 1, values[i]);
                }
            }

            if (p_curpage > 0 && p_pageSize > 0) {
                if (this.dbType.equals("ORACLE")) {
                    i++;
                    pstmt.setObject(i, String.valueOf(intRowCount));
                    i++;
                    pstmt.setObject(i, String.valueOf(intStartRow));
                } else if (this.dbType.equals("MYSQL")) {
                    i++;
                    // pstmt.setObject( i, String.valueOf(intStartRow));
                    pstmt.setInt(i, intStartRow - 1);
                    i++;
                    // pstmt.setObject( i, String.valueOf(p_pageSize));
                    pstmt.setInt(i, p_pageSize);
                } else if (this.dbType.equals("SQLSERVER")) {

                } else if (this.dbType.equals("dacp")) {
                    i++;
                    pstmt.setObject(i, String.valueOf(intRowCount));
                    i++;
                    pstmt.setObject(i, String.valueOf(intStartRow - 1));
                }

            }

            resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cols = rsmd.getColumnCount();
            // 去掉为分页加行数的数据列
            if (dbType.equals("ORACLE") && p_curpage > 0 && p_pageSize > 0) {
                cols--;
            }
            dataTable = new DataTable("QTABLE");
            String colName;
            int[] columnType = new int[cols];
            for (int c = 0; c < cols; c++) {
                columnType[c] = rsmd.getColumnType(c + 1);
            }
            dataTable.setColumnType(columnType);
            Blob m_Blob;
            Clob m_clob;
            while (resultSet.next()) {
                DataRow dtRow = new DataRow();
                dtRow.setOptype(4);
                for (i = 1; i <= cols; i++) {
                    colName = rsmd.getColumnName(i);
                    Object value;
                    if (resultSet.getObject(i) == null)
                        value = "";
                    else {                        
                        if (rsmd.getColumnType(i) == java.sql.Types.CLOB) {
                            m_clob = resultSet.getClob(i);
                            value = getClobString(m_clob);
                        } else if (rsmd.getColumnType(i) == java.sql.Types.BLOB
                                || rsmd.getColumnType(i) == java.sql.Types.LONGVARBINARY) {
                            m_Blob = resultSet.getBlob(i);
                            value = getBlobString(m_Blob);
                        } else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP){
                            value = resultSet.getTimestamp(i).toString();
                        } else if (rsmd.getColumnType(i) == java.sql.Types.DATE){
                            value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getDate(i));
                        } else
                            value = resultSet.getObject(i); // 作通用类型处理,这样row中的类型都是Object型的。

                        
                        
                        
                    }
                    dtRow.put(colName, value);
                }
                dataTable.putDataRow(dtRow);
            }// while(resultSet.next())
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception ex) {
            }
            ConnectionPool.free(conn);
        }
        return dataTable;
    }
    
    @SuppressWarnings("null")
	public DacpResult doDacpQuery(JSONObject json, String url) {
	    CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;
        DacpResult dacpresult = new DacpResult();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        
    	try {
	        httpClient = HttpClients.createDefault(); 
	        httpPost.setConfig(requestConfig);
	        StringEntity stringentity = new StringEntity(URLEncoder.encode(json.toString(),"UTF-8"));
	        stringentity.setContentEncoding("UTF-8");    
	        stringentity.setContentType("application/json"); 
	        httpPost.setEntity(stringentity);
	        // 执行请求  
	        System.out.println("Dacp端口开始连接：" + new Date());
	        response = httpClient.execute(httpPost);  
	        int ret = response.getStatusLine().getStatusCode();
	        if (ret==200) {
	        	entity = response.getEntity();  
	            responseContent = EntityUtils.toString(entity, "UTF-8");  
//	            System.out.println(responseContent);
	        } else {
	        	System.out.println("请求出现错误，http接口错误代码：" + ret);
	        }
		} catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	        try {  
	            // 关闭连接,释放资源  
	            if (response != null) {  
	                response.close();  
	            }  
	            if (httpClient != null) {  
	                httpClient.close();  
	            }  
            	System.out.println("Dacp端口断开连接：" + new Date());
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }
    	
    	try {
        	if (responseContent != null && !"null".equals(responseContent)) {
        		List<Map<String, Object>> lis = JSONUtils.toList(responseContent);
        		dacpresult.setList(lis);
//        		dacpresult = JSONUtils.toBean(responseContent, DacpResult.class);
        	}
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
    	
    	return dacpresult;
    }
    
    private int getDacpQueryRowCount(String p_sql, Object[] p_values) {
        int result = 0;
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        ParamBean bean = new ParamBean();
        
        String sql = getReBuildCountSQL(p_sql);
        if ("".equals(sql))
            return result;
        int vLens = 0;
        if (p_values != null) {
            vLens = p_values.length;
        }
        
        int i;
        for (i = 0; i < vLens; i++) {
            if (p_values[i] != null) {
//                pstmt.setObject(i + 1, p_values[i]);
            	map.put(String.valueOf(i + 1), p_values[i]);
            }
        }

        String url = dacpurl;
    	bean.setSql(sql);
        bean.setParamMap(map);
        JSONObject json = JSONObject.fromObject(bean);
        
        DacpResult dacpresult = doDacpQuery(json, url);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if (dacpresult != null && dacpresult.getList() != null) {
        	list = dacpresult.getList();
        }
        
        for (Map<String, Object> mao: list) {
        	if (mao.get("rownumm") != null){
        		return (Integer) mao.get("rownumm");
        	}
        }
        
        return result;
    }
    
    private int getDacpQueryRowCountDouble(String p_sql, Object[] p_values) {

        int result = 0;
        String sql = getReBuildCountSQL(p_sql);
        if ("".equals(sql))
            return result;
        int vLens = 0;
        if (p_values != null)
            vLens = p_values.length;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
//            conn = ConnectionPool.getConn(this.poolName);
            conn = DBUtils.getDacpConn();
            pstmt = conn.prepareStatement(sql);
            int i;
            for (i = 0; i < vLens; i++) {
                if (p_values[i] != null) {
                    pstmt.setObject(i + 1, p_values[i]);
                }
            }
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ConnectionPool.free(conn);
        }
        return result;
    }
    
    public DataTable executeQuery(final String p_sql, final Object[] values, final int p_curpage, final int p_pageSize,
            final int p_isCount, boolean isDBLink) throws SQLException {
        if(!isDBLink){
            return executeQuery(p_sql, values, p_curpage, p_pageSize, p_isCount);
        }
        String sql = p_sql;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        DataTable dataTable = null;
        try {
            conn = ConnectionPool.getConn(this.poolName);
            int vLens = 0;
            if (values != null)
                vLens = values.length;
            logger.debug("Query By SQL: " + getSqlString(p_sql, values));
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < vLens; i++) {
                if (values[i] != null) {
                    pstmt.setObject(i + 1, values[i]);
                }
            }
            resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cols = rsmd.getColumnCount();
            // 去掉为分页加行数的数据列
            if (dbType.equals("ORACLE") && p_curpage > 0 && p_pageSize > 0) {
                cols--;
            }
            dataTable = new DataTable("QTABLE");
            String colName;
            int[] columnType = new int[cols];
            for (int c = 0; c < cols; c++) {
                columnType[c] = rsmd.getColumnType(c + 1);
            }
            dataTable.setColumnType(columnType);
            Blob m_Blob;
            Clob m_clob;
            while (resultSet.next()) {
                DataRow dtRow = new DataRow();
                dtRow.setOptype(4);
                for (int i = 1; i <= cols; i++) {
                    colName = rsmd.getColumnName(i);
                    Object value;
                    if (resultSet.getObject(i) == null)
                        value = "";
                    else {
                        if (rsmd.getColumnType(i) == java.sql.Types.CLOB) {
                            m_clob = resultSet.getClob(i);
                            value = getClobString(m_clob);
                        } else if (rsmd.getColumnType(i) == java.sql.Types.BLOB
                                || rsmd.getColumnType(i) == java.sql.Types.LONGVARBINARY) {
                            m_Blob = resultSet.getBlob(i);
                            value = getBlobString(m_Blob);
                        } else
                            value = resultSet.getObject(i); // 作通用类型处理,这样row中的类型都是Object型的。
                    }
                    dtRow.put(colName, value);
                }
                dataTable.putDataRow(dtRow);
            }
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstmt != null)
                pstmt.close();
            ConnectionPool.free(conn);
        }
        return dataTable;
    }

    /**
     * 执行更新或删除语句
     * @param p_sql
     * @param values
     * @return
     * @throws SQLException 
     */
    public boolean executeUpdate(final String p_sql, final Object... values) throws SQLException {
        PreparedStatement pstmt = null;
        Connection conn = null;
        // boolean rs = false;
        try {
            conn = ConnectionPool.getConn(this.poolName);
            int vLens = values == null ? 0 : values.length;

            pstmt = conn.prepareStatement(p_sql);

            int i;
            for (i = 0; i < vLens; i++) {
                if (values[i] != null) {
                    pstmt.setObject(i + 1, values[i]);
                }
            }
            logger.debug(p_sql +";[" + com.ultrapower.omcs.utils.StringUtils.join(values, ",") + "]");
            pstmt.execute();
        } catch (SQLException ex) {
            logger.debug(p_sql +";[" + com.ultrapower.omcs.utils.StringUtils.join(values, ",") + "]");
            throw ex;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 执行更新或删除语句
     * @param p_sql
     * @param values
     * @return
     */
    public boolean executeMutiUpdate(List<String[]> list) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        // boolean rs = false;
        try {
            conn = ConnectionPool.getConn(this.poolName);
            int len = list == null ? 0 : list.size();

            if (len > 0) {
                for (String[] str : list) {
                    int strlen = str.length;
                    if (strlen >= 1) {
                        pstmt = conn.prepareStatement(str[0]);
                        for (int i = 1; i < strlen; i++) {
                            if (str[i] != null)
                                pstmt.setObject(i, str[i]);
                        }
                        pstmt.execute();
                    }
                }
            }
        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 批量DML执行SQL
     * @param p_sql
     * @param list
     * @param fileds
     * @return
     */
    public <T> boolean executeUpdateBatch(final String p_sql, List<T> list, String[] fileds) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        int[] rs;
        int i, j;
        try {
            conn = ConnectionPool.getConn(this.poolName);
            int vLens = fileds == null ? 0 : fileds.length;
            int lLens = list == null ? 0 : list.size();
            pstmt = conn.prepareStatement(p_sql);
            for (i = 0; i < lLens; i++) {

                Object t = list.get(i);
                for (j = 0; j < vLens; j++) {
                    pstmt.setObject(j + 1, BeanUtils.getSimpleProperty(t, fileds[j]));
                }
                pstmt.addBatch();
            }

            rs = pstmt.executeBatch();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 批量DML执行SQL
     * @param p_sql
     * @param list
     * @param fileds
     * @return
     */
    public boolean executeUpdateBatchArray(final String p_sql, List<String[]> list) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        int[] rs;
        int i, j;
        try {
            conn = ConnectionPool.getConn(this.poolName);
            int lLens = list == null ? 0 : list.size();
            pstmt = conn.prepareStatement(p_sql);
            for (i = 0; i < lLens; i++) {

                String[] t = list.get(i);
                for (j = 0; j < t.length; j++) {
                    pstmt.setObject(j + 1, t[j]);
                }
                pstmt.addBatch();
            }

            rs = pstmt.executeBatch();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    /**
       根据SQL查询数据,返回第一行数据DataRow
       @param p_sql
       @param p_values
       @param p_pageNum
       @param p_pageSize
       @param p_isCount
       @return com.ultrapower.system.data.DataRow
       @roseuid 4B56CC4A0266
     */
    /*
     * public DataRow executeQueryDataRow(String p_sql, Object[] p_values, int
     * p_pageNum, int p_pageSize, int p_isCount) { return null; }
     */

    private int getQueryRowCount(String p_sql, Object[] p_values) {
        int result = 0;
        String sql = getReBuildCountSQL(p_sql);
        if ("".equals(sql))
            return result;
        int vLens = 0;
        if (p_values != null)
            vLens = p_values.length;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = ConnectionPool.getConn(this.poolName);
            pstmt = conn.prepareStatement(sql);
            int i;
            for (i = 0; i < vLens; i++) {
                if (p_values[i] != null) {
                    pstmt.setObject(i + 1, p_values[i]);
                }
            }
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ConnectionPool.free(conn);
        }
        return result;
    }

    private String getReBuildCountSQL(String sql) {
        String regstr = "/*COUNT*/";
        String strSelect = sql.toString();// "select /*COUNT*/ base.c1
                                          // /*COUNT*/ from ";//   count(*) ‘
        int startlen = strSelect.indexOf(regstr);
        // strSelect=strSelect.replaceFirst("/\\*COUNT\\*/","");
        int endlen = strSelect.indexOf(regstr, startlen + 1);
        int sublen = strSelect.indexOf("order by");
        if (sublen < 1) {
        	sublen = strSelect.length();
        }
        
        if (startlen < 0 || endlen < 0)
            return "";
        
        strSelect = strSelect.substring(0, startlen) + " count(0) as ROWNUMM " + strSelect.substring(endlen + 9, sublen);
        return strSelect;
    }

    /**
     * 获取大字段(Clob)中的字符串值
     * @param p_clob
     * @return
     */
    protected String getClobString(Clob p_clob) {
        String strValue = "";

        if (p_clob != null) {
            try {
                if (p_clob.length() > 0) {
                    strValue = p_clob.getSubString((long) 1, (int) p_clob.length());
                }
            } catch (Exception ex) {
                strValue = "";
            }
        }
        return strValue;
    }

    /**
     * 读取Blob字符串
     * @param p_blob
     * @return
     */
    protected String getBlobString(Blob p_blob) {
        InputStream is;
        StringBuffer buf = new StringBuffer();
        InputStreamReader r = null;
        try {
            is = p_blob.getBinaryStream();
            r = new InputStreamReader(is);
            int c;
            for (;;) {
                c = r.read();
                if (c <= 0)
                    break;
                buf.append((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (r != null)
                    r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buf.toString();
    }
    
    public static String camelName(String name) {
    	     StringBuilder result = new StringBuilder();
    	     // 快速检查
    	     if (name == null || name.isEmpty()) {
    	         // 没必要转换
    	         return "";
    	     } else if (!name.contains("_")) {
    	         // 不含下划线，仅将首字母小写
    	         return name.substring(0, 1).toLowerCase() + name.substring(1);
    	     }
    	     // 用下划线将原始字符串分割
    	     String camels[] = name.split("_");
    	     for (String camel :  camels) {
    	         // 跳过原始字符串中开头、结尾的下换线或双重下划线
    	         if (camel.isEmpty()) {
    	             continue;
    	         }
    	         // 处理真正的驼峰片段
    	         if (result.length() == 0) {
    	             // 第一个驼峰片段，全部字母都小写
    	             result.append(camel.toLowerCase());
    	         } else {
    	             // 其他的驼峰片段，首字母大写
    	             result.append(camel.substring(0, 1).toUpperCase());
    	             result.append(camel.substring(1).toLowerCase());
    	         }
    	     }
    	     return result.toString();
	 }
}
