package com.ultrapower.eoms.common.core.component.rquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Element;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.core.SqlQuery;
import com.ultrapower.eoms.common.core.component.rquery.core.SqlResult;
import com.ultrapower.eoms.common.core.component.rquery.startup.StartUp;
import com.ultrapower.eoms.common.core.util.StringUtils;

public class RQuery {

	SqlResult sqlResult=null;
	private DataTable dt=null;
	private int pageSize=0;
	private int page=0;
	private int isCount=0;
	private int pageCount=0;
	private int rowCount=0;
	SqlQuery sqlQuery=null;
	private String dbpoolname="";
	private String dbType="";

	public int getQueryResultSetCount()
	{
		return this.rowCount;
	}
	
	public RQuery(final String sqlName,final HashMap p_indirectValues)
	{
		//XmlParser xmlParse=null;
		HashMap indirectValues=p_indirectValues;
		if(indirectValues==null)
			indirectValues=new HashMap();
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlName);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			dbpoolname=StringUtils.checkNullString(sqlqueryElement.getAttributeValue("poolname"));
			dbType=StringUtils.checkNullString(sqlqueryElement.getAttributeValue("dbtype")).toUpperCase();//数据类型
			sqlQuery=new SqlQuery(sqlqueryElement,indirectValues,true);
		}
	}
	
    public <T> List<T> transformTuple(Class<T> cls) {
    	List<T> result= new ArrayList<T>();
		if(sqlQuery==null)
		{
			return null;
		}
		sqlResult=sqlQuery.getSql();
		if(sqlResult!=null)
		{
			Object[] values=null;
					
			List list=sqlResult.getValues();
			int size=0;
			if(list !=null)
			{
						size=list.size();
			}
			if(size>0)
			{
				values = (Object[])list.toArray(new String[size]);
			}
			QueryAdapter queryAdapter=new QueryAdapter(dbpoolname,dbType);
			result=queryAdapter.executeObject(cls, sqlResult.getSql(), values);
		}
		return result;
    }

	
	public String getSqlString()
	{
		String result="";
		if(sqlQuery==null)
		{
			return "";
		}
		sqlResult=sqlQuery.getSql();
		if(sqlResult!=null)
		{
			Object[] values=null;
					
			List list=sqlResult.getValues();
			int size=0;
			if(list !=null)
			{
						size=list.size();
			}
			if(size>0)
			{
				values = (Object[])list.toArray(new String[size]);
			}
			QueryAdapter queryAdapter=new QueryAdapter(dbpoolname,dbType);
			result=queryAdapter.getSqlString(sqlResult.getSql(), values);
		}
		return result;
	}
	
	public DataTable getDataTable()
	{
		if(sqlQuery!=null)
		{
			sqlResult=sqlQuery.getSql();
			if(sqlResult!=null)
			{
				Object[] values=null;
				
				List list=sqlResult.getValues();
				int size=0;
				if(list !=null)
				{
					size=list.size();
				}
				if(size>0)
				{
					values = (Object[])list.toArray(new String[size]);
				}
				
				QueryAdapter queryAdapter=new QueryAdapter(dbpoolname,dbType);
				dt=queryAdapter.executeQuery(sqlResult.getSql(), values, this.getPage(), this.getPageSize(), this.getIsCount());
				this.setPageCount(queryAdapter.getPageCount());
				this.rowCount=queryAdapter.getQueryResultSetCount();
	
			}
		}
		return dt;
	}
	
	public DataTable getDataTable(int page,int pageSize,int iscount )
	{
		if(sqlQuery!=null)
		{
			sqlResult=sqlQuery.getSql();
			if(sqlResult!=null)
			{
				Object[] values=null;
				
				List list=sqlResult.getValues();
				int size=0;
				if(list !=null)
				{
					size=list.size();
				}
				if(size>0)
				{
					values = (Object[])list.toArray(new String[size]);
				}
				
				QueryAdapter queryAdapter=new QueryAdapter(dbpoolname,dbType);
				dt=queryAdapter.executeQuery(sqlResult.getSql(), values, page, pageSize, iscount);
				this.setPageCount(queryAdapter.getPageCount());
				this.rowCount=queryAdapter.getQueryResultSetCount();
	
			}
		}
		return dt;
	}
	
	public int getDataTableCount()
	{
		int dataTableCount =0;
		if(sqlQuery!=null)
		{
			sqlResult=sqlQuery.getSql();
			if(sqlResult!=null)
			{
				Object[] values=null;
				String sql = sqlResult.getSql();
				int beginIndex = sql.indexOf("/*COUNT*/");
				int endIndex =sql.indexOf("/*COUNT*/",beginIndex+1);
				sql= sql.replace(sql.substring(beginIndex, endIndex), "/*COUNT*/ count(1) ");
				List list=sqlResult.getValues();
				int size=0;
				if(list !=null)
				{
					size=list.size();
				}
				if(size>0)
				{
					values = (Object[])list.toArray(new String[size]);
				}
				QueryAdapter queryAdapter=new QueryAdapter(dbpoolname,dbType);
				dt=queryAdapter.executeQuery(sql, values);
				if(dt.length()>0) {
					DataRow row = dt.getDataRow(0);
					dataTableCount =row.getInt(0);
				}

			}
		}
		return dataTableCount;
	}
	public int getPageSize() 
	{
		return pageSize;
	}

	public void setPageSize(int pageSize) 
	{
		this.pageSize = pageSize;
	}

	public int getPage() 
	{
		return page;
	}

	public void setPage(int page) 
	{
		this.page = page;
	}

	public int getIsCount() 
	{
		return isCount;
	}

	public void setIsCount(int isCount)
	{
		this.isCount = isCount;
	}

	public int getPageCount() 
	{
		return pageCount;
	}

	public void setPageCount(int pageCount) 
	{
		this.pageCount = pageCount;
	}
	/**
	 * 返回查询的sql对象
	 * @return
	 */
	public SqlResult getSqlResult()
	{
		return sqlResult;
	}
	
}


