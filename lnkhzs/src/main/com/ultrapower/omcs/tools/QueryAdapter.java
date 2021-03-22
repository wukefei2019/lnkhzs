package com.ultrapower.omcs.tools;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.database.ConnectionPool;
import com.ultrapower.eoms.common.core.util.BeanUtils;
import com.ultrapower.eoms.common.core.web.SafeRequestWrapper;
import com.ultrapower.omcs.base.model.RollingResult;
import com.ultrapower.omcs.common.model.ICommonModel;
import com.ultrapower.omcs.utils.StringUtils;

public class QueryAdapter extends com.ultrapower.eoms.common.core.component.data.QueryAdapter {

    protected Logger logger = LoggerFactory.getLogger(QueryAdapter.class);

    public QueryAdapter(String poolName) {
        super(poolName);
    }
    
    public ICommonModel queryEntity(final String p_sql, Class<? extends ICommonModel> clazz) {
        RollingResult<? extends ICommonModel> rolling = queryEntitiesByRolling(p_sql, new Object[] {}, null, null, 20, clazz);
        return rolling.getEntities().get(0);
    }
    
    public ICommonModel queryEntity(final String p_sql, final Object[] values,Class<? extends ICommonModel> clazz) {
        RollingResult<? extends ICommonModel> rolling = queryEntitiesByRolling(p_sql, values , null, null, 20, clazz);
        return rolling.getEntities().isEmpty()? null : rolling.getEntities().get(0);
    }
    
    public RollingResult<?> queryEntities(final String p_sql, Class<? extends ICommonModel> clazz) {
        return queryEntitiesByRolling(p_sql, new Object[] {}, null, null, 20, clazz);
    }

    public RollingResult<?> queryEntities(final String p_sql, final Object[] values,
            Class<? extends ICommonModel> clazz) {
        return queryEntitiesByRolling(p_sql, values, null, null, 20, clazz);
    }

    public RollingResult<?> queryEntitiesByRolling(final String p_sql, final Object[] values,
            Class<? extends ICommonModel> clazz) {
        return queryEntitiesByRolling(p_sql, values, null, null, 20, clazz);
    }

    /**
     * [分页查询数据集]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param p_sql
     * @param parameters
     * @param lastrecord 
     * @param ordercolumns 排序字段必须出现在select子句中
     * @param pageSize
     * @param clazz 数据封装对象
     * @return
     */
    public RollingResult<? extends ICommonModel> queryEntitiesByRolling(final String p_sql, final Object[] parameters,
            String lastrecord, String ordercolumns, final int pageSize, Class<?> clazz) {
        return queryEntitiesByRolling(p_sql, parameters, lastrecord, false, ordercolumns, pageSize, clazz);
    }

    /**
     * [分页查询数据集]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param p_sql
     * @param parameters
     * @param lastrecord 
     * @param ordercolumns 排序字段必须出现在select子句中
     * @param pageSize
     * @param clazz 数据封装对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public RollingResult<? extends ICommonModel> queryEntitiesByRolling(final String p_sql, final Object[] parameters,
            String lastrecord, boolean desc, String ordercolumns, final int pageSize, Class<?> clazz) {
        RollingResult<? extends ICommonModel> result = queryEntitiesByRolling(p_sql, parameters, lastrecord, desc,
                ordercolumns, pageSize);
        if (ClassUtils.getAllInterfaces(clazz).contains(ICommonModel.class)) {
            List<ICommonModel> entities = (List<ICommonModel>) result.getEntities();
            Iterator<DataRow> it = result.getRowList().iterator();
            while (it.hasNext()) {
                DataRow dataRow = (DataRow) it.next();
                ICommonModel entity = dataRow2Entiey2(dataRow, (Class<? extends ICommonModel>) clazz);
                entities.add(entity);
            }
            result.getRowList().clear();
        }
        return result;
    }

    /**
     * [将data数据部分封装成clazz指定的数据类型]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param dtRow
     * @param clazz
     * @return
     */
    @SuppressWarnings("unused")
    @Deprecated
    private ICommonModel dataRow2Entiey(DataRow dtRow, Class<? extends ICommonModel> clazz) {
        ICommonModel instance = null;
        try {
            instance = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (int j = 0; j < methods.length; j++) {
                Method method = methods[j];
                Class<?>[] types = method.getParameterTypes();
                if (method.getName().startsWith("set") && types.length == 1) {
                    String property = getSetPropertyName(method.getName());
                    Object o = dtRow.getObject(property);
                    if (o != null && o.toString().length() >= 1) {
                        // if(method.getName().indexOf("setOrdernum") >-1){
                        // System.out.println();
                        // }
                        if (o instanceof BigDecimal) {
                            if ("long".equals(types[0].toString())
                                    || "class java.lang.Long".equals(types[0].toString())) {
                                o = Long.valueOf(o.toString());
                            } else if ("double".equals(types[0].toString())
                                    || "class java.lang.Double".equals(types[0].toString())) {
                                o = Double.valueOf(o.toString());
                            } else if ("class java.lang.Integer".equals(types[0].toString())) {
                                o = Integer.valueOf(o.toString());
                            } else {
                                o = o.toString();
                            }
                        }

                        // 对时间戳专字符串的处理
                        if (o instanceof oracle.sql.TIMESTAMP && "class java.lang.String".equals(types[0].toString())) {
                            oracle.sql.TIMESTAMP t = (oracle.sql.TIMESTAMP) o;
                            o = com.ultrapower.eoms.common.core.util.DateUtils.getDateStr(t, "yyyy-MM-dd");
                        }
                        try {
                            method.invoke(instance, o);
                        } catch (Exception e) {
                            System.out.println();
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            // } catch (IllegalArgumentException e) {
            // e.printStackTrace();
            // } catch (InvocationTargetException e) {
            // e.printStackTrace();
        }
        return instance;
    }

    private ICommonModel dataRow2Entiey2(DataRow dtRow, Class<? extends ICommonModel> clazz) {
        ICommonModel instance = null;
        try {
            instance = clazz.newInstance();
            Iterator<?> it = dtRow.getRowHashMap().keySet().iterator();
            while (it.hasNext()) {
                Object o = (Object) it.next();
                String dbProperty = o.toString();
                Object value = dtRow.getObject(dbProperty);
                String property = StringUtils.toCamelCase(dbProperty);
                try {
                    BeanUtils.setProperty(instance, property, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
	        e.printStackTrace();
        }

        return instance;
    }

    /**
     * [将set方法名转换成数据库字段属性]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param name
     * @return
     */
    private String getSetPropertyName(String name) {
        StringBuffer sb = new StringBuffer();
        int size = name.substring(3).length();
        for (int i = 0; i < size; i++) {
            char a = name.charAt(2 + i);
            char A = name.charAt(3 + i);
            // 大写字母
            if (a > 91 && A < 91 && A > 64 && i != 0) {
                sb.append("_").append(A);
            } else {
                sb.append(A);
            }
        }
        return sb.toString();
    }

    public RollingResult<? extends ICommonModel> queryEntitiesByRolling(final String p_sql, final Object[] parameters,
            String lastrecord, boolean desc, String ordercolumns, final int pageSize) {
        String sql = p_sql;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        RollingResult<ICommonModel> dataTable = null;
        try {
            /* 处理参数 */
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    Object param = parameters[i];
                    if (param instanceof String) {
                        parameters[i] = SafeRequestWrapper.HTMLDecode((String) param);
                    }
                }
            }
            conn = ConnectionPool.getConn(poolName);
            // 没有传入排序字段，判断为非滚动查询
            if (ordercolumns != null) {
                if (desc) {
                    sql = "select * from (" + sql + ") where ( ? is null or " + ordercolumns + "< ?) and rownum <= ?";
                } else {
                    sql = "select * from (" + sql + ") where ( ? is null or " + ordercolumns + "> ?) and rownum <= ?";
                }
            }
            logger.info(sql+";[" + StringUtils.join(parameters, ",") + "]");
            pstmt = conn.prepareStatement(sql);
            int i = 0;
            for (; i < parameters.length; i++) {
                if (parameters[i] != null) {
                    pstmt.setObject(i + 1, parameters[i]);
                }
            }
            // 没有传入排序字段，判断为非滚动查询
            if (ordercolumns != null) {
                pstmt.setObject(++i, lastrecord);
                pstmt.setObject(++i, lastrecord);
                pstmt.setInt(++i, pageSize + 1);
            }
            resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cols = rsmd.getColumnCount();
            dataTable = new RollingResult<ICommonModel>();
            String colName;
            int[] columnType = new int[cols];
            for (int c = 0; c < cols; c++) {
                columnType[c] = rsmd.getColumnType(c + 1);
            }
            dataTable.setColumnType(columnType);
            Blob m_Blob;
            Clob m_clob;
            int size = 0;
            while (resultSet.next()) {
                size++;
                DataRow dtRow = new DataRow();
                dtRow.setOptype(4);
                for (i = 1; i <= cols; i++) {
                    colName = rsmd.getColumnName(i);
                    Object value = null;
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
                        } else {
                            value = resultSet.getObject(i); // 作通用类型处理,这样row中的类型都是Object型的。
                        }
                    }
                    dtRow.put(colName, value);
                    if (ordercolumns != null && ordercolumns.equalsIgnoreCase(colName) && pageSize >= size) {
                        dataTable.setLastrecord(value.toString());
                    }
                }
                dataTable.putDataRow(dtRow);
            }
            if (ordercolumns != null && size > pageSize) {
                dataTable.setHasMore(true);
                dataTable.getRowList().remove(size - 1);
            }
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
    
}
