<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
    <head>
        <meta charset="UTF-8">
        <title>新增信息</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/khzs/addDepAdmin.js"></script>
    </head>
    <body>
        <div class="Ct Dialog contract form-page" >
            <div class='panel panel-primary'>
                <div class='panel-heading'>
                    <h3 class='panel-title'>新增部门主管配置信息</h3>
                    <a class='btn floatRight10' onclick='saveAdd()'>保存</a>
                </div>
                    <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
                    <form id='form' name='form' action='${ctx}/khzs/depadmin/saveDepAdmin.action' method='post' target="frm">
                        <input type="hidden" name="khzsTDepadmin.pid" value="${khzsTDepadmin.pid }"/>
                        <table width='100%' class="form-table table-bordered">
	                        <tr>
	                        	<input type="hidden" name="khzsTDepadmin.depid" value="${khzsTDepadmin.depid }"/>
	                            <td class='label required'>部门：</td><td class="dep"><input type='text' class='inputText1 width160' name='khzsTDepadmin.depname' value='${khzsTDepadmin.depname}' /><span class="glyphicon glyphicon-plus" style="margin:10px;"></span></td>
	                            <input type="hidden" name="khzsTDepadmin.loginname" value="${khzsTDepadmin.loginname }"/>
	                            <td class='label required'>人员：</td><td class="user"><input type='text' class='inputText1 width160' name='khzsTDepadmin.fullname' value='${khzsTDepadmin.fullname}' /><span class="glyphicon glyphicon-plus" style="margin:10px;"></span></td>
	                        </tr>
                        </table>
                    </form>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        function saveAdd() {
            var msg=checkNull();
            if(msg!=""){
                $.bs.tips(msg,{level:"danger",$target:$("form"),auto_close:false});
                return;
            }
           /*  $.bs.tips("正在保存,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:true}); */
		    $.post($ctx + '/khzs/depadmin/saveDepAdmin.action',$("form").serializeArray()).done(function(result){
	    		if (result == 'success') {
	    			/*$.bs.tips("操作成功",{ level:"success"},function(){
	    				 //刷新父页面
	    				  $.bs.tips("操作成功",{ level:"success"}); */
	    				 alert("操作成功");
	    				 $((opener || parent).document).find(".btn-link.icon-refresh2").click();
	    				 window.close();
	                 //});
	    		} else {
	    			 /* $.bs.tips("操作失败",{ level:"danger"}); */ 
	    			 alert("操作失败");
	    		}
	    	});
        }
    </script>
</html>
