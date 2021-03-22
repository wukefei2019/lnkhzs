<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
    <head>
        <meta charset="UTF-8">
        <title>案例库设置</title>
    </head>
    <script type='text/javascript'>
    $(function(){
    	var status = "${param.status}";
    	$('input:radio[value='+status+']').attr('checked','true');
    })
   	function setting(){
    	var pid = "${param.id}";
   		$.post($ctx +'/khzs/flow/khzsTFlowAction/szalk.action?khzsTMain.pid='+pid+"&khzsTMain.example="+$('input[name="setting"]:checked').val()).done(function(result) {
       		alert("设置成功");
       		parent.$.bs.modal.close();
       	})
   	}
    </script>
    
    <body>
        <div style="text-align:center">
		   <div class="radio">
		     	<label><input type="radio" name="setting" id="unopen" value="公开（可评论）">公开</label>&nbsp;&nbsp;&nbsp;
		     	<label><input type="radio" name="setting" id="overt" value="公开（不可评论）">公开（不可评论）</label>&nbsp;&nbsp;&nbsp;
		     	<label><input type="radio" name="setting" id="comment" value="不公开" >不公开</label>
		   </div>
		   <div>
		   		<button class="btn btn-primary " type="button" onclick="setting()">确定</button>
		   </div>
        </div>
   	</body>
</html>
