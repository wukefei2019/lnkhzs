<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ultrapower.eoms.common.core.util.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%
	String utType = StringUtils.checkNullString(request.getParameter("utType")); // 人员模版类别
	String typeMark = StringUtils.checkNullString(request.getParameter("typeMark")); // 模版类别标识
%>
<head>
	<%@ include file="/common/core/header_form.jsp"%>
	<script type="text/javascript" src="${ctx}/common/tools/orgtree/js/commonTree.js"></script>
	<script type="text/javascript" src="${ctx}/common/javascript/date/dateFormat.js"></script>
	<script language="javascript">
		var isRadio = '<%=request.getParameter("isRadio")%>';
		window.onresize = function() 
		{
			setCenter(0, 0);
		}
		window.onload = function()
		{
			setCenter(0, 0);
		}
		
		/**
		 * 添加一个数据
		 * id：选择数据的id  text：选择数据的名称  type：添加这条数据的类型
		 */
		function addOneResult(id, text, type,mode)
		{
			if(id == '')
				return ;
			
			if(mode == undefined)
			{
				mode = '2'; //独占 2 ；共享 1；
			}
			
			if(type == 'U')
			{
				id = id.substring(0, 2)+id.substring(id.indexOf('-')+1, id.length);
			}
			
			if(document.getElementById(id) != null)
				return ;
			
			if(isRadio == '0')
				clearAll();
			var elementType = '';
			if(type == 'U')
				elementType = '人员';
			else if(type == 'D')
				elementType = '部门';
			else if(type == 'T')
				elementType = '模版';
			else if(type == 'R')
				elementType = '角色细分';
			else
				elementType = '未定义类型';
			
			var modeString = '';
			if(type == 'D')
			{
				var modeText = "";
				if(mode=='2')
				{
					modeText = "独占";
				}else
					modeText = "共享";
				
				modeString = "[<a href='#' onclick='changeModeType(\""+id+"\");return false;'><span style='color:blue'><u id='"+id+"_mode'>"+modeText+"</u></span></a>]";
			}
			$("#inertData").append("<div id="+id+" idText="+text+" idType="+type+" style='margin-right:20px;display:inline;'><nobr><b>"+text+"</b> "+modeString+"<img src=\"../../style/blue/images/del_user.jpg\" onclick=\"delOneResult('"+id+"','"+type+"')\" style=\"margin-left:2px; margin-bottom:-1px;\" alt=\"删除\"></img>；</nobr></div>");
			parent.addActor(id,text,mode); 
		}
		
		function initOneResult(id, text, type,mode)
		{
			if(id == '')
				return ;
			if(document.getElementById(id) != null)
				return ;
			
			if(type == 'G')
			{
				type = 'D';
			}
			if(isRadio == '0')
				clearAll();
			var elementType = '';
			if(type == 'U')
				elementType = '人员';
			else if(type == 'D')
				elementType = '部门';
			else if(type == 'T')
				elementType = '模版';
			else if(type == 'R')
				elementType = '角色细分';
			else
				elementType = '未定义类型';
			
			var modeString = '';
			if(type == 'D')
			{
				var modeText = "";
				if(mode=='2')
				{
					modeText = "独占";
				}else
					modeText = "共享";
				
				modeString = "[<a href='#' onclick='changeModeType(\""+id+"\");return false;'><span style='color:blue'><u id='"+id+"_mode'>"+modeText+"</u></span></a>]";
			}
			$("#inertData").append("<div id="+id+" idText="+text+" idType="+type+" style='margin-right:20px;display:inline;'><nobr><b>"+text+"</b>"+modeString+"<img src=\"../../style/blue/images/del_user.jpg\" onclick=\"delOneResult('"+id+"','"+type+"')\" style=\"margin-left:2px; margin-bottom:-1px;\" alt=\"删除\"></img>；</nobr></div>");
			parent.addActor(id,text,mode);
		}
		
		function changeModeType(id)
		{
			if(document.getElementById(id+"_mode").innerText == "独占")
			{
				mode = '1';
				document.getElementById(id+"_mode").innerText = "共享";
			}else{
				mode = '2';
				document.getElementById(id+"_mode").innerText = "独占";
			}
			parent.updateActor(id,mode);
			return false;
		}
		
		
		/**
		 * 同步删除数据信息，在本页面删除数据，同步到添加这个数据的iframe中
		 * id：选择数据的id  type：要同步的数据类型U：人 D：部门 T：模版
		 */
		function delOneResult(id, type)
		{
			
			if(id != '')
			{
				type = id.substring(0, 1);
				if(type == 'U' && id.indexOf('-')>0)
				{
					id = id.substring(0, 2)+id.substring(id.indexOf('-')+1, id.length);
				}
				$("#"+id).remove();
			}
			synchOtherFrameDel(id, type);
			parent.removeActor(id);
		}
		
		function addUserTemplate()
		{
			var selectData = this.getDataFromResult('id');
			openwindow('${ctx}/userTemplate/userTemplateInfoSimple.action?utType=<%=utType%>&typeMark=ALL&selectData='+selectData, '', 320, 350);
		}
		
		function getTimeValue()
		{
			var limittime = parseDateToSeconds(document.getElementById("limittime").value);
			var dealtime = parseDateToSeconds(document.getElementById("dealtime").value);
			return 	limittime+"_"+dealtime;		
		}
		
		function setTimeValue(limittimes,dealtimes)
		{
			document.getElementById("limittime").value = limittimes;
			document.getElementById("dealtime").value = dealtimes;
		}
		
	</script>
</head>
<body>
	<div class="content">
		<div class='page_div_bg'>
			<div class='page_div'>
				<li>
			    <eoms:lable name="wf_actor_limittime" />:
                <input type="text" id='limittime' onchange="timesetting_basic(this.value,'');" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" />
                <eoms:lable name="wf_actor_dealtime" />:
                <input type="text" id='dealtime'  onchange="timesetting_basic('',this.value);" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"  />
				<a href="#" onclick="addUserTemplate();return false;"><span style="color:blue"><u>构造模版</u></span></a>
				</li> 
			</div>
		</div>
		<div class="add_scroll_div_x" id="center">
			<div id="treeData" style="overFlow-y: scroll; height: 96%; padding: 2px 4px 0px 4px; margin-top: 2px;">
				<div id="inertData" style="padding: 0px 4px 4px 4px;"></div>
			</div>
		</div>
	</div>
</body>
</html>
