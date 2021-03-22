<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
    <head>
        <meta charset="UTF-8">
        <title>新增信息</title>
    </head>
    <body>

    </body>
    <script type="text/javascript">
    $(document).ready(function() {
    	var data0 ={"actionStr": "0"};
    	var data1 ={ "actionStr": "1","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTFlow.opinion":"提交"};
    	var data2 ={ "actionStr": "1","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"1","khzsTFlow.opinion":"提交"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室"};
    	
    	var data3 ={ "actionStr": "1","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"2","khzsTFlow.opinion":"提交"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室"};
    	var data4 ={ "actionStr": "1","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"7","khzsTFlow.opinion":"提交"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室"};
    	var data5 ={ "actionStr": "1","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"8","khzsTFlow.opinion":"提交"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室"};
    	
    	var data6 ={ "actionStr": "4","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"2","khzsTFlow.opinion":"挂起"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室"};
    	
    	var data7 ={ "actionStr": "3","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"3","khzsTFlow.opinion":"转派"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室","selectUser.depFullName":"aa","selectUser.loginName":"bb","selectUser.fullName":"cc"};
    	
    	var data8 ={ "actionStr": "3","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"2","khzsTFlow.opinion":"转派"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室","selectUser.depFullName":"aa","selectUser.loginName":"bb","selectUser.fullName":"cc"};

    	var data8 ={ "actionStr": "3","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"4","khzsTFlow.opinion":"转派"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室","selectUser.depFullName":"aa","selectUser.loginName":"bb","selectUser.fullName":"cc"};

    	var data9 ={ "actionStr": "1","khzsTMain.pid":"a19794e84612480a8649c9dd33986fe3","khzsTMain.flowstatus":"4","khzsTFlow.opinion":"转派"
    		,"khzsTMain.fullname":"系统管理员","khzsTMain.loginname":"Demo","khzsTMain.createtime":"2019-04-22 14:33:41","khzsTMain.depname":"中国移动通信集团辽宁有限公司.网络管理中心.监控资源室"};

    	var url = $ctx + "/khzs/flow/khzsTFlowAction/doAction.action?aaa="+Math.random();
    	$.post(url,data9 ).done(function(data) {
    		

    	}, "json");

    });
        
    </script>
</html>
