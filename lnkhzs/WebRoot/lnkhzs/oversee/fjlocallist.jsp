<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>
	var volid = "${param.id}";
</script>
<meta charset="UTF-8">
<title>附件查看</title>
<script type="text/javascript"
	src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type='text/javascript'>
$(document).ready(function() {									
		
		$('.uploadnew').iceuploader({
    		attachid:'${param.fileid}',
    		cachemode:true,
    		editable:false,
    		forcedel:!false,
    		savepath:'swork'
    	});
    });
</script>
</head>
<body>
<div id="flow_s" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					查看附件
				</h3>
				
			</div>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<div class="bg_gray2" style="text-align: center;margin-top: 10px;margin-bottom: 10px;">
                <div id='${param.fileid}'  class="uploadnew" > </div>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
	</div>
</body>
</html>