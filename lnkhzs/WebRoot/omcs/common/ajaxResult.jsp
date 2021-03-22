<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>导入数据结果</title>
<script type="text/javascript"> 
</script>
</head>
<script type="text/javascript"> 
    function funcMsg(){
        if($(".errorMessage").size() > 0 ){
            var msg = $(".errorMessage").text().replace(/\"/g,"");
            parent.${callback}('danger',msg,{
                file:'${importResultModel.otherParam.file}'
            });
        }else {
            parent.${callback}('${importResultModel.level}','${importResultModel.msg}',{
                file:'${importResultModel.otherParam.file}'
            });
        }
    }
</script>
<body onload="funcMsg()">

    <s:fielderror cssStyle="color:red" ></s:fielderror>
</body>

</html>