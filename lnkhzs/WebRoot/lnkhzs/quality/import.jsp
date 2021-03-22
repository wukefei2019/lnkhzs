<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
    <title>文件导入</title>
    <script type="text/javascript">

        function checked() {            
            if (!$("#xls").val()) {
                alert("请选择待导入的文件");
                return false;
            }

            return true;
        }
        function upload() {

            var msg = "确定导入数据？";
            if (checked() && confirm(msg)) {
            	 var form = $("form")[0];
            	 var url = $ctx + "/quality/tagMaintain/importExcel.action";
                 form.action =url;
                 form.enctype = "multipart/form-data";
                 form.submit();
                 $.bs.tips("数据正在比较,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:false});
            }
        }

        
        function import_callback_new(level,msg,other_param){
        	alert("数据比较时间较长，请稍后到附件管理中查看");
        }
    </script>
</head>
<body>
    <div class="Ct Dialog">
        <div class="bg_gray2">
            <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
            <form action="${ctx }/quality/tagMaintain/importExcel.action"  enctype="multipart/form-data"
                method="post" target="frm">
                <input type="hidden" name="callback" value="import_callback_new"/>
                <div class="tableTitle">
                    <label class="fontsize14 color_3 marginLR18">标签比较</label> 
                    <input type="button" class="base3_btn1 floatRight10 marginTop6" value="比较" onclick="upload()">
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