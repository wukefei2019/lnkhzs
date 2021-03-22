<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
    <title>溯源问题批量验收导入</title>
    <script language="JavaScript" src='${ctx}/common/plugin/ajaxupload/ajaxfileupload.js' type='text/javascript'></script>
    <script type="text/javascript"> 
        function checked() {

            if (!$("#xls").val()) {
                alert("请选择待导入的“业务数据”文件");
                return false;
            }

            return true;
        }
        function upload() {
            $("#fileName").val($("#xls").val());
            var msg = "即将导入业务数据。若之前数据存在，则会被删除。是否继续操作？\n导入数据文件=>" + $("#fileName").val();
            if (checked() && confirm(msg)) {
            	$.bs.tips("数据正在导入,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:false});
                $.ajaxFileUpload({
                    url : $ctx + "/trace/traceSource/importExcelDone.action",
                    secureuri: false,
                    //name:'inputFile',
                    fileElementId: 'xls',//file标签的id
                    dataType: 'json',//返回数据的类型
                    success:function(data, status){
                        if(data.indexOf("成功")>0){
                        	alert("导入成功");
                        	$.bs.tips(data,{level:"success",$target:$("form"),clear:true,auto_close:true});
                        	window.parent.$.bs.table.refresh("table0");
                        	window.close();
                        }else{
                        	alert(data);
                        }    
                    }
                });
            }
        }
        
        function import_callback(level,msg,other_param){
            $.bs.tips(msg,{level:level,$target:$("form"),clear:true,auto_close:true});
            if(level=="success"){
               parent.$.bs.table.refresh("table0");
            } 
        }
    </script>
    <script type="text/javascript"> 
        function checkImport() {
        	$('#officeContent').html("");
            $("#fileName").val($("#xls").val());
            var msg = "即将导入业务数据。若之前数据存在，则会被删除。是否继续操作？\n导入数据文件=>" + $("#fileName").val();
            if (checked() && confirm(msg)) {
            	/* $.bs.tips("数据正在导入,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:false}); */
                $.ajaxFileUpload({
                    url : $ctx + "/trace/traceSource/checkImport.action",
                    secureuri: false,
                    //name:'inputFile',
                    data:{fileName:$("#fileName").val()},
                    fileElementId: 'xls',//file标签的id
                    dataType: 'text',//返回数据的类型
                    success:function(data, status){
                        if("success"==status){
                        	alert("预览导入成功")
                        	$('#officeContent').append(data);
                        	$('#officeContent').find('table').attr('class','table table-hover text_align_center table-no-bordered text-nowrap');
                        }else{
                        	alert("预览导入失败")
                        }    
                    }
                });
            }
        }
    </script>
</head>
<body style="overflow-x: visible;">
    <div>
        <div class="bg_gray2">
            <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
            <form action="${ctx }/quality/tagMaintain/importExcel.action"  enctype="multipart/form-data"
                method="post" target="frm">
                <input type="hidden" name="callback" value="import_callback"/>
                <!-- <input type="hidden" id='importCallback' name="importResultModel.callback" value="import_callback" /> --> 
                <input type="hidden" id='fileName' name="fileName" value="" />
                <%-- <a href="${ctx }/lnkhzs/trace/sywtqdby.xlsx">溯源问题清单表样模板下载</a> --%>
                <div class="tableTitle">
                    <label class="fontsize14 color_3 marginLR18">数据导入</label> 
                    <input type="button" class="base3_btn1 floatRight10 marginTop6" value="确认导入" onclick="upload()">
                    <input type="button" class="base3_btn1 floatRight10 marginTop6" value="导入预览" onclick="checkImport()">
                </div>
                <div class="padding20 border1 borderTop1">
                    <table width="100%" class="table_body2">
                        <tr>
                            <td width="12%" class="textalignRight paddingRight10"><label class="color_red">*</label></td>
                            <td width="13%" colspan="3"><input type="file" id="xls" name="xls" value=""
                                accept="*.xls;*.xlsx" /></td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        
		<div class="bootstrap-table">
	        <div id="officeContent" class="fixed-table-container"></div>
        </div>
    </div>
</body>
</html>