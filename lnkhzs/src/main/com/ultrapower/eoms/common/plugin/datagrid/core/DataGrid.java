package com.ultrapower.eoms.common.plugin.datagrid.core;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.jdom.Element;

import com.ultrapower.eoms.common.core.component.rquery.startup.StartUp;
import com.ultrapower.eoms.common.core.util.Internation;
import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.plugin.datagrid.grid.Limit;
import com.ultrapower.eoms.common.plugin.datagrid.util.RequestUtils;

public class DataGrid {

    protected String name;
	protected PageContext pageContext;
	protected String leftToolAre;//工具条左侧区域
	protected String conditionAre;//查询条件区域
	protected String tablerow;//行数据
	protected String gridtitle;//
	private String title;//标题
	private String ititle;//国际化的标题
	private String action;
	private String tquery;
	protected HttpServletRequest request;
	protected String sqlName;
	private String formid;
	private String pagetype;
	private int pagesize;
	private String omcs;
	//分页垂直位置:top,bottom
	protected String pageHorizontalAlign = "top";
	
	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getTquery() {
		return tquery;
	}

	public void setTquery(String tquery) {
		this.tquery = tquery;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public DataGrid(PageContext p_pageContext,String sqlName)
	{
		this.sqlName=sqlName;
		pageContext=p_pageContext;
	}

	/**
	 * 输出DataGrid视图
	 */
	public void dataView()
	{

		JspWriter out = pageContext.getOut();
		request=(HttpServletRequest)pageContext.getRequest();
		//System.out.println(request.getParameter("workSheetTitle2"));
		//System.out.println(request.getParameter("workSheetTitle"));
		String sorfield=StringUtils.checkNullString(request.getParameter(GridConstants.HIDDEN_SORTFIELD));
		String sorttype=StringUtils.checkNullString(request.getParameter(GridConstants.HIDDEN_SORTTYPE));
		String id = StringUtils.checkNullString(request.getParameter(GridConstants.HIDDEN_MENUID));
		if(sorttype.equals(""))
		{
			sorttype="0";//0升序 
		}
		Limit limit=RequestUtils.getWebLimit(request);
		int totalpage=0;
		if(limit!=null)
			totalpage=limit.getTotalPage();
		try 
		{
			if("true".equals(omcs)){
				//DataGridFnc.getJSFunction(out);
				String formid=StringUtils.checkNullString(this.getFormid());
				if(formid.equals(""))
				{
					formid="form1";
				}
				
				if(action!=null && !action.equals("")){
					out.println("<form id='"+formid+"' method='post' action='"+action+"'  class='data-grid data-grid-omcs'>");
				}else{
					out.println("<form id='"+formid+"' method='post' class='data-grid data-grid-omcs'>");
				}

				//<div class=\"fysp_table1Box\"> begin
				out.println("<div class=\"fysp_table1Box\">"); 
				this.getTitle(out);
				this.getToolBoorOmcs(out,limit,true);
				this.getConditionAreOmcs(out);
				out.println("   <div class='ui-table-frame' >");
				out.println("	<table class=\"ui-table table text_align_center table-hover \" >");
				if(gridtitle!=null){
					out.print("<thead class=\"table_head4 color_3\">");
					out.println(this.gridtitle);
					out.print("</thead>");
				}
					
				if(tablerow!=null){
					out.print("<tbody>");
					out.println(tablerow);
					out.print("</tbody>");
				}
					
				out.println("</table>");
				out.println("</div>");
				
				getToolBoorOmcs(out,limit,false);			
				out.println("<div>");
				out.println("<input id='"+GridConstants.HIDDEN_TOTAL_PAGES+"' name='"+GridConstants.HIDDEN_TOTAL_PAGES+"' type='hidden' value='"+totalpage+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_CHECKBOX_SELECTVALUES+"' name='"+GridConstants.HIDDEN_CHECKBOX_SELECTVALUES+"' type='hidden' value=''/> ");
				out.println("<input id='"+GridConstants.HIDDEN_ISTRANFER+"' name='"+GridConstants.HIDDEN_ISTRANFER+"' type='hidden' value=''/> ");
				out.println("<input id='"+GridConstants.HIDDEN_SORTFIELD+"' name='"+GridConstants.HIDDEN_SORTFIELD+"' type='hidden' value='"+sorfield+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_SORTTYPE+"' name='"+GridConstants.HIDDEN_SORTTYPE+"' type='hidden' value='"+sorttype+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_MENUID+"' name='"+GridConstants.HIDDEN_MENUID+"' type='hidden' value='"+id+"'/> ");
				out.println("</div>");
				
				//<div class=\"fysp_table1Box\"> end
				out.println("</div>");
				
				out.println("</form>");
				
				//begin 添加用于导出功能的FORM 2014-12-09 fany
				out.println("<form id='exportForm' name='exportForm' method='post' action=''>");
				out.println("<input id='"+GridConstants.HIDDEN_TOTAL_PAGES+"' name='"+GridConstants.HIDDEN_TOTAL_PAGES+"' type='hidden' value='"+totalpage+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_CHECKBOX_SELECTVALUES+"' name='"+GridConstants.HIDDEN_CHECKBOX_SELECTVALUES+"' type='hidden' value=''/> ");
				out.println("<input id='"+GridConstants.HIDDEN_ISTRANFER+"' name='"+GridConstants.HIDDEN_ISTRANFER+"' type='hidden' value=''/> ");
				out.println("<input id='"+GridConstants.HIDDEN_SORTFIELD+"' name='"+GridConstants.HIDDEN_SORTFIELD+"' type='hidden' value='"+sorfield+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_SORTTYPE+"' name='"+GridConstants.HIDDEN_SORTTYPE+"' type='hidden' value='"+sorttype+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_MENUID+"' name='"+GridConstants.HIDDEN_MENUID+"' type='hidden' value='"+id+"'/> ");
				this.getConditionAre(out);
				out.println("</form>");
			}else{
				//DataGridFnc.getJSFunction(out);
				String formid=StringUtils.checkNullString(this.getFormid());
				if(formid.equals(""))
				{
					formid="form1";
				}
				if(action!=null && !action.equals("")){
					out.println("<form id='"+formid+"' method='post' action='"+action+"'>");
				}else{
					out.println("<form id='"+formid+"' method='post'>");
				}

				out.println("<div class='content'>");
				getTitle(out);
				this.getConditionAre(out);
				getToolBoor(out,limit,true);
				out.println("	<div class='scroll_div' id='center'>"); //<!--滚动条div-->

				out.println("	<table id='tab' class='tableborder'>");
				if(gridtitle!=null)
					out.println(this.gridtitle);
				if(tablerow!=null)
					out.println(tablerow);
				out.println("	</table>");
				//out.println("<div style=\"padding-left:30px;padding-top:30px;\"/>"); 

				//out.println("</div>");//out.println("<div class='scroll_div' id='center'>");
				out.println("</div>");
				getToolBoor(out,limit,false);			
				out.println("<div >");
				out.println("<input id='"+GridConstants.HIDDEN_TOTAL_PAGES+"' name='"+GridConstants.HIDDEN_TOTAL_PAGES+"' type='hidden' value='"+totalpage+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_CHECKBOX_SELECTVALUES+"' name='"+GridConstants.HIDDEN_CHECKBOX_SELECTVALUES+"' type='hidden' value=''/> ");
				out.println("<input id='"+GridConstants.HIDDEN_ISTRANFER+"' name='"+GridConstants.HIDDEN_ISTRANFER+"' type='hidden' value=''/> ");
				out.println("<input id='"+GridConstants.HIDDEN_SORTFIELD+"' name='"+GridConstants.HIDDEN_SORTFIELD+"' type='hidden' value='"+sorfield+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_SORTTYPE+"' name='"+GridConstants.HIDDEN_SORTTYPE+"' type='hidden' value='"+sorttype+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_MENUID+"' name='"+GridConstants.HIDDEN_MENUID+"' type='hidden' value='"+id+"'/> ");
				out.println("</div>");
				out.println("</div>");
				out.println("</form>");
				//begin 添加用于导出功能的FORM 2014-12-09 fany
				out.println("<form id='exportForm' name='exportForm' method='post' action=''>");
				out.println("<input id='"+GridConstants.HIDDEN_TOTAL_PAGES+"' name='"+GridConstants.HIDDEN_TOTAL_PAGES+"' type='hidden' value='"+totalpage+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_CHECKBOX_SELECTVALUES+"' name='"+GridConstants.HIDDEN_CHECKBOX_SELECTVALUES+"' type='hidden' value=''/> ");
				out.println("<input id='"+GridConstants.HIDDEN_ISTRANFER+"' name='"+GridConstants.HIDDEN_ISTRANFER+"' type='hidden' value=''/> ");
				out.println("<input id='"+GridConstants.HIDDEN_SORTFIELD+"' name='"+GridConstants.HIDDEN_SORTFIELD+"' type='hidden' value='"+sorfield+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_SORTTYPE+"' name='"+GridConstants.HIDDEN_SORTTYPE+"' type='hidden' value='"+sorttype+"'/> ");
				out.println("<input id='"+GridConstants.HIDDEN_MENUID+"' name='"+GridConstants.HIDDEN_MENUID+"' type='hidden' value='"+id+"'/> ");
				this.getConditionAre(out);
				out.println("</form>");
			}
			//end 2014-12-09
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setGridtitle(String gridtitle)
	{
		this.gridtitle=gridtitle;
	}
	public void setTablerow(String tablerow)
	{
		this.tablerow=tablerow;
	}
	public void setLeftToolAre(String leftToolAre)
	{
		this.leftToolAre=leftToolAre;
	}
	public void setConditionAre(String conditionAre)
	{
		this.conditionAre=conditionAre;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getItitle() {
		return ititle;
	}

	public void setItitle(String ititle) {
		this.ititle = ititle;
	}

	protected void getTitle(JspWriter out) throws IOException
	{
		
		if(title!=null && !"".equals(title))
		{
			out.println("<div class='title_right'>");
			out.println("	<div class='title_left'>");
			out.println("		<span class='title_bg'>");
			out.println("			<span class='title_icon2'>"+title+"</span>");
			out.println("			</span>");
			getTQueryHtml(out);
			out.println("		<span class='title_xieline'>");
			out.println("		</span>");
			out.println("	</div>");
			out.println("</div>");
		}
		else if(!"".equals(StringUtils.checkNullString(ititle)))
		{
			out.println("<div class='title_right'>");
			out.println("	<div class='title_left'>");
			out.println("		<span class='title_bg'>");
			out.println("			<span class='title_icon2'>");
			out.println(Internation.language(ititle));
			out.println("			</span>");
			out.println("		</span>");
			getTQueryHtml(out);
			out.println("		<span class='title_xieline'>");
			out.println("		</span>");
			out.println("	</div>");
			out.println("</div>");
		}
	}
	
	private void getTQueryHtml(JspWriter out)  throws IOException
	{
		
		out.println("<span style=\"float:right;\">");
		if(this.tquery!=null &&"true".equals(tquery))
		{
			String tName=StringUtils.checkNullString(request.getParameter(GridConstants.TQUERY_NAME));
			//String value=StringUtils.checkNullString(request.getParameter(tName));
			List lstCondition=this.getConditionElement();
			int lstLen=0;
			if(lstCondition!=null)
				lstLen=lstCondition.size();
			
			String value=StringUtils.checkNullString(request.getParameter(GridConstants.TQUERY_VALUE));
			
			out.println("<select name='"+GridConstants.TQUERY_NAME+"' 12> style=\"margin-top: 0px;\"");
			Element element;
			String istquery;
			for(int i=0;i<lstLen;i++)
			{
				element=(Element)lstCondition.get(i);
				String name=StringUtils.checkNullString(element.getAttributeValue("name"));
				istquery=StringUtils.checkNullString(element.getAttributeValue("tquery"));
				if(istquery.equals("true"))
				{
					String displayName=StringUtils.checkNullString(element.getAttributeValue("displayname"));
					String text=Internation.language(displayName);
					if(!text.equals(""))
						displayName=text;
					
					
					if(tName.equals(name))
					{
						out.println("<option value='"+name+"' selected > ");	
					}
					else
					{
						out.println("<option value='"+name+"'> ");
					}
					out.println(text);
					out.println("</option>");
				}
			}
			out.println("</select>");
			out.println("<input name='"+GridConstants.TQUERY_VALUE+"' style='width:200px' value='"+value+"' />" +
					"<input type='submit' value='搜索' onclick='tquerysubmit();' class='searchButton' onmouseover=\"this.className='searchButton_hover'\" onmouseout=\"this.className='searchButton'\"  />"
					+ "<input name='searchB' type='button' value='高级搜索' class='searchadv_button'  onclick='showsearch()' onmouseover=\"this.className='searchadv_button_hover'\" onmouseout=\"this.className='searchadv_button'\"/>"
					);
		}				
		out.println("</span>");
	}
	private List getConditionElement()
	{
		Element sqlqueryElement=null;
		if(StartUp.sqlmapElement!=null)
		{
			Object obj=StartUp.sqlmapElement.get(sqlName);
			if(obj!=null)
				sqlqueryElement=(Element)obj;
		}
		
		List lstCondition=null;
		if(sqlqueryElement!=null)
		{
			lstCondition=sqlqueryElement.getChildren("condition");
			Element ele=(Element)lstCondition.get(0);
			lstCondition=ele.getChildren();
				
		}
		return lstCondition;
	}
	
	private void getConditionAreOmcs(JspWriter out) throws IOException
	{
		if(conditionAre!=null && !conditionAre.equals(""))
		{
			if(name!=null)
			{
				out.println("<div class=\"searchDiv\" id=\""+name+"_serachdiv\" style=\"display:none;\">");
			}
			else
			{
				out.println("<div class=\"searchDiv\" id=\"serachdiv\" style=\"display:none;\">");
			}
			out.println(conditionAre);
			out.println("</div>");
		}

	}
	
	protected void getConditionAre(JspWriter out) throws IOException
	{
		if(conditionAre!=null && !conditionAre.equals(""))
		{
			if(name!=null)
			{
				out.println("<div id='"+name+"_serachdiv' class='serachdiv' style='display:none;'>");
			}
			else
			{
				out.println("<div id='serachdiv' class='serachdiv' style='display:none;'>");
			}
			out.println("	<div class='type_condition'><span>请输入查询条件</span></div>");
			out.println(conditionAre);
			out.println("</div>");
		}

	}
	/**
	 * 工具条
	 * @param out
	 * @param limit
	 * @param topFlag true的说明是上面的工具栏，false说明是下方的工具栏
	 * @throws IOException
	 */
	protected void getToolBoor(JspWriter out,Limit limit,boolean topFlag)throws IOException
	{
		//toolbar 左侧
		if(leftToolAre==null)//如果没有lefttoobar工具栏标签
			return ;
		out.println("<div class='page_div_bg'>");//工具栏开始
		
		out.println("	<div class='page_div'>");//工具栏左侧开始 
		if(topFlag)
		{
			out.println(leftToolAre);
		}
		
		//out.println("</div >");
		out.println("	</div>");//工具栏左侧结束 
	
		//分页
		if(topFlag&&"top".equals(this.pageHorizontalAlign))
		{
			getToolBoorPage(out,limit);
		}
		if(!topFlag&&"bottom".equals(this.pageHorizontalAlign))
		{
			getToolBoorPage(out,limit);
		}
		//工具栏右侧翻页结束
		out.println("</div>");//工具栏结束
	}
	
	private void getToolBoorOmcs(JspWriter out,Limit limit,boolean topFlag)throws IOException
	{
		//toolbar 左侧
		if(leftToolAre==null)//如果没有lefttoobar工具栏标签
			return ;
		out.println("<div class=\"fysp_iconBox\">");//工具栏开始
		if(topFlag)
		{
			out.println(leftToolAre);
		}
		
		//out.println("</div >");
		//out.println("	</div>");//工具栏左侧结束 
	
		//分页
		if(topFlag&&"top".equals(this.pageHorizontalAlign))
		{
			getToolBoorPageOmcs(out,limit);
		}
		if(!topFlag&&"bottom".equals(this.pageHorizontalAlign))
		{
			getToolBoorPageOmcs(out,limit);
		}
		//工具栏右侧翻页结束
		out.println("</div>");//工具栏结束
	}
	private void getToolBoorPageOmcs(JspWriter out,Limit limit)throws IOException
	{
		int number=0;
		//工具栏右侧翻页开始
		if("simple".equals(StringUtils.checkNullString(pagetype)))
		{
			out.println("<ul class=\"fysp_page_box\">");
			out.println("<li class=\"fysp_page fysp_page_arrow_left\" onclick=\"return tranferPage('previous')\"></li>");
		    number=0;
		    int currentpage=0;
		    int pagesize=this.pagesize;
		    int totalrows=0;
		    if(limit!=null)
			{
				number=limit.getTotalPage();
				currentpage=limit.getPage();
				pagesize=this.pagesize==0?limit.getPageSize():this.pagesize;
				totalrows=limit.getTotalRows();
			}
		    out.println("<li>&nbsp;"+currentpage+"&nbsp;/&nbsp;"+number+"&nbsp;</li>");
		    out.println("<li class=\"fysp_page fysp_page_arrow_right\" onclick=\"return tranferPage('next')\"></li>");
		    out.println("<li><span>第</span>&nbsp;<input type=\"text\" id='"+GridConstants.HIDDEN_CURRENT_PAGE+"' name='"+GridConstants.HIDDEN_CURRENT_PAGE+"' value=\""+currentpage+"\" class=\"fysp_inputText1\" />&nbsp;<span>页</span></li>");
		    out.println("<li class=\"fysp_btn_go\" onclick=\"return tranferPage('go')\">GO</li>");
		    out.println("</ul>");
		    
			/*out.println("	<select id='"+GridConstants.HIDDEN_CURRENT_PAGE+"' name='"+GridConstants.HIDDEN_CURRENT_PAGE+"' onchange=\"return tranferPage('go')\" style=\"display:none\">  \n" );
			for(int i=1;i<(number+1);i++)
			{
				if(currentpage==i)
					out.println("<option  selected value='"+i+"'>"+i+"</option>");
				else
					out.println("<option value='"+i+"'>"+i+"</option>");
			}
			out.println("	</select>\n");
			
			out.println("<li class='nextpage' onclick=\"return tranferPage('next')\" onmouseover=\"this.className='nextpage_hover'\" onmouseout=\"this.className='nextpage'\" title='下一页'></li>");
			out.println("<li class='lastpage' onclick=\"return tranferPage('last')\" onmouseover=\"this.className='lastpage_hover'\" onmouseout=\"this.className='lastpage'\" title='最后一页'></li>");
			out.println("<li><div class='page_split_line'></div></li>");
		    out.println("<li>共"+totalrows+"条</li>");
		    out.println("</ul>");
			out.println("</span>"); //out.println("<span class='pagenumber'>");
*/		}
		else
		{
			out.println("<ul class=\"fysp_page_box\">");
			out.println("<li class=\"fysp_page fysp_page_arrow_left_stop\" onclick=\"return tranferPage('frist')\"></li>");
			out.println("<li class=\"fysp_page fysp_page_arrow_left\" onclick=\"return tranferPage('previous')\"></li>");
		    number=0;
		    int currentpage=0;
		    int pagesize=this.pagesize;
		    int totalrows=0;
		    if(limit!=null)
			{
				number=limit.getTotalPage();
				currentpage=limit.getPage();
				pagesize=this.pagesize==0?limit.getPageSize():this.pagesize;
				totalrows=limit.getTotalRows();
			}
		    out.println("<li><span>第</span>&nbsp;<input type=\"text\" id='"+GridConstants.HIDDEN_CURRENT_PAGE+"' name='"+GridConstants.HIDDEN_CURRENT_PAGE+"' value=\""+currentpage+"\" class=\"fysp_inputText1\" />&nbsp;<span>页</span></li>");
		    out.println("<li class=\"fysp_page fysp_page_arrow_right\" onclick=\"return tranferPage('next')\"></li>");
		    out.println("<li class=\"fysp_page fysp_page_arrow_right_stop\" onclick=\"return tranferPage('last')\"></li>");
		    
		    out.println("<li class=\"fysp_btn_go\" onclick=\"return tranferPage('go')\">GO</li>");
		    out.println("<li><span>每页</span></li>");
			out.println("<li>");
			out.println("<select class='fysp_selectText1' id='"+GridConstants.HIDDEN_PAGES_SIZE+"' name='"+GridConstants.HIDDEN_PAGES_SIZE+"' onchange=\"return tranferPage('setsize')\">   ");
			if(pagesize==5)
				out.println("	<option  value='5' selected>5</option> ");
			else
				out.println("	<option value='5'>5</option> ");
			if(pagesize==10)
				out.println("	<option value='10' selected>10</option> ");
			else
				out.println("	<option value='10'>10</option> ");
			if(pagesize==20)
				out.println("	<option value='20' selected>20</option> ");
			else
				out.println("	<option value='20'>20</option> ");
			if(pagesize==50)
				out.println("	<option value='50' selected>50</option> ");
			else
				out.println("	<option value='50'>50</option> ");	
			if(pagesize==100)
				out.println("	<option value='100' selected>100</option> ");
			else
				out.println("	<option value='100'>100</option> ");	
			out.println("</select>");
			out.println("</li>");
			out.println("<li><span>条</span></li>");
			out.println("<li><span>共&nbsp;"+number+"&nbsp;页</span></li>");
		    out.println("<li><span>共&nbsp;"+totalrows+"&nbsp;条数据</span></li>");
		    out.println("</ul>");
		    
		    /*
			out.println("<span class='pagenumber'>");
			out.println("<ul>");
			//out.println("<li>");
			out.println("	<li class='firstpage' onclick=\"return tranferPage('frist')\"  onmouseover=\"this.className='firstpage_hover'\" onmouseout=\"this.className='firstpage'\" title='第一页'></li>");
			out.println("	<li class='prepage' onclick=\"return tranferPage('previous')\" onmouseover=\"this.className='prepage_hover'\" onmouseout=\"this.className='prepage'\" title='上一页'></li>");
			//out.println("</li>");
		    number=0;
		    //Limit limit=RequestUtils.getWebLimit((HttpServletRequest)pageContext.getRequest()); 
		    int currentpage=0;
		    int pagesize=0;
		    int totalrows=0;
		    if(limit!=null)
			{
				number=limit.getTotalPage();
				currentpage=limit.getPage();
				pagesize=limit.getPageSize();
				totalrows=limit.getTotalRows();
			}
			out.println("<li> \n");
			out.println("	<select class='paddingHack' id='"+GridConstants.HIDDEN_CURRENT_PAGE+"' name='"+GridConstants.HIDDEN_CURRENT_PAGE+"' onchange=\"return tranferPage('go')\" >  \n" );
			for(int i=1;i<(number+1);i++)
			{
				if(currentpage==i)
					out.println("<option  selected value='"+i+"'>"+i+"</option>");
				else
					out.println("<option value='"+i+"'>"+i+"</option>");
			}
			out.println("	</select>\n");
			out.println("</li>");
			out.println("<li>/"+number+"页</li>");
			//out.println("<li>");
			out.println("	<li class='nextpage' onclick=\"return tranferPage('next')\" onmouseover=\"this.className='nextpage_hover'\" onmouseout=\"this.className='nextpage'\" title='下一页'></li>");
			out.println("	<li class='lastpage' onclick=\"return tranferPage('last')\" onmouseover=\"this.className='lastpage_hover'\" onmouseout=\"this.className='lastpage'\" title='最后一页'></li>");
			//out.println("</li>");
			out.println("<li><div class='page_split_line'></div></li>");
			out.println("<li>每页</li>");
			out.println("<li>");
			out.println("<select class='paddingHack' id='"+GridConstants.HIDDEN_PAGES_SIZE+"' name='"+GridConstants.HIDDEN_PAGES_SIZE+"' onchange=\"return tranferPage('setsize')\">   ");
			if(pagesize==5)
				out.println("	<option  value='5' selected>5</option> ");
			else
				out.println("	<option value='5'>5</option> ");
			if(pagesize==10)
				out.println("	<option value='10' selected>10</option> ");
			else
				out.println("	<option value='10'>10</option> ");
			if(pagesize==20)
				out.println("	<option value='20' selected>20</option> ");
			else
				out.println("	<option value='20'>20</option> ");
			if(pagesize==50)
				out.println("	<option value='50' selected>50</option> ");
			else
				out.println("	<option value='50'>50</option> ");		
			out.println("</select>");
			out.println("</li>");
			out.println("<li>条</li>");
		    out.println("<li><div class='page_split_line'></div></li>");
		    out.println("<li>共"+totalrows+"条数据</li>");
		    out.println("</ul>");
			out.println("</span>"); //out.println("<span class='pagenumber'>");
*/		}
	}
	protected void getToolBoorPage(JspWriter out,Limit limit)throws IOException
	{
		int number=0;
		//工具栏右侧翻页开始
		if("simple".equals(StringUtils.checkNullString(pagetype)))
		{
			out.println("<span class='pagenumber'>");
			out.println("<ul>");
			out.println("<li class='firstpage' onclick=\"return tranferPage('frist')\"  onmouseover=\"this.className='firstpage_hover'\" onmouseout=\"this.className='firstpage'\" title='第一页'></li>");
			out.println("<li class='prepage' onclick=\"return tranferPage('previous')\" onmouseover=\"this.className='prepage_hover'\" onmouseout=\"this.className='prepage'\" title='上一页'></li>");
		    number=0;
		    int currentpage=0;
		    int pagesize=0;
		    int totalrows=0;
		    if(limit!=null)
			{
				number=limit.getTotalPage();
				currentpage=limit.getPage();
				pagesize=limit.getPageSize();
				totalrows=limit.getTotalRows();
			}
		    
			out.println("	<select id='"+GridConstants.HIDDEN_CURRENT_PAGE+"' name='"+GridConstants.HIDDEN_CURRENT_PAGE+"' onchange=\"return tranferPage('go')\" style=\"display:none\">  \n" );
			for(int i=1;i<(number+1);i++)
			{
				if(currentpage==i)
					out.println("<option  selected value='"+i+"'>"+i+"</option>");
				else
					out.println("<option value='"+i+"'>"+i+"</option>");
			}
			out.println("	</select>\n");
			out.println("<li>"+currentpage+"/"+number+"页</li>");
			out.println("<li class='nextpage' onclick=\"return tranferPage('next')\" onmouseover=\"this.className='nextpage_hover'\" onmouseout=\"this.className='nextpage'\" title='下一页'></li>");
			out.println("<li class='lastpage' onclick=\"return tranferPage('last')\" onmouseover=\"this.className='lastpage_hover'\" onmouseout=\"this.className='lastpage'\" title='最后一页'></li>");
			out.println("<li><div class='page_split_line'></div></li>");
		    out.println("<li>共"+totalrows+"条</li>");
		    out.println("</ul>");
			out.println("</span>"); //out.println("<span class='pagenumber'>");
		}
		else
		{
			out.println("<span class='pagenumber'>");
			out.println("<ul>");
			//out.println("<li>");
			out.println("	<li class='firstpage' onclick=\"return tranferPage('frist')\"  onmouseover=\"this.className='firstpage_hover'\" onmouseout=\"this.className='firstpage'\" title='第一页'></li>");
			out.println("	<li class='prepage' onclick=\"return tranferPage('previous')\" onmouseover=\"this.className='prepage_hover'\" onmouseout=\"this.className='prepage'\" title='上一页'></li>");
			//out.println("</li>");
		    number=0;
		    //Limit limit=RequestUtils.getWebLimit((HttpServletRequest)pageContext.getRequest()); 
		    int currentpage=0;
		    int pagesize=0;
		    int totalrows=0;
		    if(limit!=null)
			{
				number=limit.getTotalPage();
				currentpage=limit.getPage();
				pagesize=limit.getPageSize();
				totalrows=limit.getTotalRows();
			}
			out.println("<li> \n");
			out.println("	<select class='paddingHack' id='"+GridConstants.HIDDEN_CURRENT_PAGE+"' name='"+GridConstants.HIDDEN_CURRENT_PAGE+"' onchange=\"return tranferPage('go')\" >  \n" );
			for(int i=1;i<(number+1);i++)
			{
				if(currentpage==i)
					out.println("<option  selected value='"+i+"'>"+i+"</option>");
				else
					out.println("<option value='"+i+"'>"+i+"</option>");
			}
			out.println("	</select>\n");
			out.println("</li>");
			out.println("<li>/"+number+"页</li>");
			//out.println("<li>");
			out.println("	<li class='nextpage' onclick=\"return tranferPage('next')\" onmouseover=\"this.className='nextpage_hover'\" onmouseout=\"this.className='nextpage'\" title='下一页'></li>");
			out.println("	<li class='lastpage' onclick=\"return tranferPage('last')\" onmouseover=\"this.className='lastpage_hover'\" onmouseout=\"this.className='lastpage'\" title='最后一页'></li>");
			//out.println("</li>");
			out.println("<li><div class='page_split_line'></div></li>");
			out.println("<li>每页</li>");
			out.println("<li>");
			out.println("<select class='paddingHack' id='"+GridConstants.HIDDEN_PAGES_SIZE+"' name='"+GridConstants.HIDDEN_PAGES_SIZE+"' onchange=\"return tranferPage('setsize')\">   ");
			if(pagesize==5)
				out.println("	<option  value='5' selected>5</option> ");
			else
				out.println("	<option value='5'>5</option> ");
			if(pagesize==10)
				out.println("	<option value='10' selected>10</option> ");
			else
				out.println("	<option value='10'>10</option> ");
			if(pagesize==20)
				out.println("	<option value='20' selected>20</option> ");
			else
				out.println("	<option value='20'>20</option> ");
			if(pagesize==50)
				out.println("	<option value='50' selected>50</option> ");
			else
				out.println("	<option value='50'>50</option> ");		
			out.println("</select>");
			out.println("</li>");
			out.println("<li>条</li>");
		    out.println("<li><div class='page_split_line'></div></li>");
		    out.println("<li>共"+totalrows+"条数据</li>");
		    out.println("</ul>");
			out.println("</span>"); //out.println("<span class='pagenumber'>");
		}
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPagetype() {
		return pagetype;
	}

	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getPageHorizontalAlign()
	{
		return pageHorizontalAlign;
	}

	public void setPageHorizontalAlign(String pageHorizontalAlign)
	{
		this.pageHorizontalAlign = pageHorizontalAlign;
	}

	public String getOmcs() {
		return omcs;
	}

	public void setOmcs(String omcs) {
		this.omcs = omcs;
	}
	
}
