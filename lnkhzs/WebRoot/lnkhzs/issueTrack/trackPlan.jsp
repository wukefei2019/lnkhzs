<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/common/plugin/track/css/ystep.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'>
	$(document).ready(function() {
		var pid = "${pid}";
		var url = $ctx + "/track/trackSource/ajaxGetJobData.action?pid=" + pid;
		$.post(url).done(function(data) {
			$("#jobs").html("");
			var stepht = "<table class='tableType'>";
			for (var i = 0; i < data.length; i++) {
				stepht = stepht + "<tr><th> 部门：" + data[i].jobdepart + "";
				stepht = stepht + "<th>， 处理人：" + data[i].jobperson + "";
				stepht = stepht + "<th>， 工作内容：" + data[i].jobcontent + "";
				stepht = stepht + "<th>， 月报日期：" + data[i].jobdate + "";
			}
			stepht = stepht + "</table>";
			$("#jobs").append(stepht);
			if (data !=null && data.length>0) {
				$("#ybdiv").show();
			}
		});
	});
	
	
</script>

    <head>
        <meta charset="UTF-8">
        <title>计划内容</title>
    </head>
    <body>
        <div class="Ct Dialog contract form-page" >
        	<c:if test="${plandata.id != null && plandata.id != ''}">
	            <div class='panel panel-primary'>
	                <div class='panel-heading'>
	                    <h3 class='panel-title'>计划内容</h3> 
	                </div>
					<div class="form-table">
						<div class='row'>
							<div class='col-xs-3'>
								<label >计划处理人部门：</label>
							</div>
							<div class='col-xs-9'>
								<input type='text' class='form-control' id='plandepart' readonly='true' value='${plandata.plandepart}' style="width:64%"/>
							</div>
						</div>
						<div class='row'>
							<div class='col-xs-3'>
								<label >处理人：</label>
							</div>
							<div class='col-xs-9'>
								<input type='text' class='form-control' id='planperson' readonly='true' value='${plandata.planperson}' style="width:64%" />
							</div>
						</div>
						<div class='row'>
							<%-- <div class='col-xs-3'>
								<label >计划目标：</label>
							</div>
							<div class='col-xs-9'>
								<input type='text' class='form-control'	id='plantarget' readonly='true' value='${plandata.plantarget}' style="width:64%" />
							</div> --%>
							<div class='col-xs-3'>
								<label >计划内容：</label>
							</div>
							<div class='col-xs-9'>
								<input type='text' class='form-control'	id='plancontent' readonly='true' value='${plandata.plancontent}' style="width:64%" />
							</div>
						</div>
						<div class='row'>
							<div class='col-xs-3'>
								<label >计划日期：</label>
							</div>
							<div class='col-xs-9'>
								<input type='text' class='form-control'	id='plandate' readonly='true' value='${plandata.plandate}' style="width:64%" />
							</div>
						</div>
					</div>
	           	</div>
           	</c:if>
           	<div class='panel panel-primary' style="display:none" id="ybdiv">
				<div class='panel-heading'>
	                <h3 class='panel-title'>月报内容</h3> 
	            </div>
	            <div id='jobs'>
	            </div>
            </div>
        </div>
   	</body>
</html>
