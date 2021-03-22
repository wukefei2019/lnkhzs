<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
    <title>满意度统计表导入</title>
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
            var msg = "即将导入业务数据。是否继续操作？\n导入数据文件=>" + $("#fileName").val();
            if (checked() && confirm(msg)) {
            	$.bs.tips("数据正在导入,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:false});
                $.ajaxFileUpload({
                    url : $ctx + "/satisfation/sjkhmyd/importExcel.action",
                    secureuri: false,
                    //name:'inputFile',
                    fileElementId: 'xls',//file标签的id
                    dataType: 'json',//返回数据的类型
                    success:function(data, status){
                       if("success"==data){
                        	$.bs.tips("导入成功",{level:"success",$target:$("form"),clear:true,auto_close:true});
                        	//window.parent.$.bs.modal.close()
                        }else{
                        	$.bs.tips("导入失败",{level:"danger",$target:$("form"),clear:true,auto_close:true});
                        	
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
</head>
<body>
    <div>
        <div class="bg_gray2">
            <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
            <form action="${ctx }/quality/tagMaintain/importExcel.action"  enctype="multipart/form-data"
                method="post" target="frm">
                <input type="hidden" name="callback" value="import_callback"/>
                <!-- <input type="hidden" id='importCallback' name="importResultModel.callback" value="import_callback" /> --> 
                <input type="hidden" id='fileName' name="fileName" value="" />
                <a href="${ctx }/lnkhzs/satisfaction/sjkhmyd.xlsx">手机客户满意度统计表模板下载</a>
                <div class="tableTitle">
                    <label class="fontsize14 color_3 marginLR18">数据导入</label> 
                    <input type="button" class="base3_btn1 floatRight10 marginTop6" value="导入" onclick="upload()">
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
    </div>
</body>
</html>