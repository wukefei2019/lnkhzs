function fileChanged(){
	$("#fileName").val($("#files").val());
	
	$("body").append('<div id="showDemo" style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" style="margin-top: 25%;" class="add"/></div></div>');
	
	//上传附件
	var returnPath="";
	var myDate = new Date();
	var tYear = myDate.getFullYear();
	var tMonth = myDate.getMonth();
	var m = tMonth + 1;
	if (m.toString().length == 1) {
		m = "0" + m;
	}
	var timestamp = myDate.getTime();
	returnPath="/"+tYear+m+"/ServiceSupervisor/"+timestamp;
	
	
	
	$("#showAllSelectedFiles").empty();
	for (var i = 0; i < $("#files")[0].files.length; i++) {
		var filefix = $("#files")[0].files[i].name;
		//filefix = filefix.substring(filefix.indexOf("."), filefix.length);
		console.log(filefix);
		
		$("#showAllSelectedFiles").append("<div><a>"+filefix+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>");
	}
	
	$("#showDemo").remove();
	/*
	$("body").append('<div id="showDemo" style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" style="margin-top: 25%;" class="add"/></div></div>');

	var url = encodeURI($ctx + "/tsgd/swork/addSend.action");
	var returnPath="";
	var myDate = new Date();
	var tYear = myDate.getFullYear();
	var tMonth = myDate.getMonth();
	var m = tMonth + 1;
	if (m.toString().length == 1) {
		m = "0" + m;
	}
	var timestamp = myDate.getTime();
	returnPath="/"+tYear+m+"/ServiceSupervisor/"+timestamp;
	
	$("#appendix_address").val(returnPath);
	console.log(returnPath);
	//发起
	//上传附件
	if ($("#files")[0].files.length == 0) {
		//调公务
		getGWPublish(url);
	} else {


		for (var i = 0; i < $("#files")[0].files.length; i++) {
			var formData = new FormData();
			formData.append("files", $("#files")[0].files[i]);
			var filefix = $("#files")[0].files[i].name;
			filefix = filefix.substring(filefix.indexOf("."), filefix.length);
			formData.append("filefix", filefix);
			formData.append("path", returnPath);
			$.ajax({
				type : 'POST',
				url : $ctx + "/tsgd/swork/addFiles.action",
				data : formData,
				processData : false, // 使数据不做处理
				contentType : false, // 不要设置Content-Type请求头
				dataType : "json",
				async : false,
				mimeType : "multipart/form-data",
				success : function(data) {
					console.log(11);
					if (i == $("#files")[0].files.length - 1) {
						//最后一个上传完毕,调公务
						getGWPublish(url);
					}
				},
				error : function(data) {
					console.log(22);
					if (i == $("#files")[0].files.length - 1) {
						//最后一个上传完毕,调公务
						getGWPublish(url);
					}

				//console.log(data);
				}
			});


		}
	}*/
}