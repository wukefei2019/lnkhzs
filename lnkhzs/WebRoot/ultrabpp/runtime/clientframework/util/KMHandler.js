function KMHandler() {}

KMHandler.createKM = function(kmObj)
{
	$("#kmcaption")[0].value = Util.keyFieldReplace(kmObj.caption);
	$("#kmkeyword")[0].value = Util.keyFieldReplace(kmObj.keyword);
	$("#kmcategory")[0].value = Util.keyFieldReplace(kmObj.category);
	$("#kmcontent")[0].value = Util.keyFieldReplace(kmObj.content);
	$('#kmform').submit();
}