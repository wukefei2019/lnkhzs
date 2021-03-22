function ButtonPanel() {}
ButtonPanel.buildActionModel = function(actName, actType, actText, isFree, hasNext, isC, actDesc)
{
	var actModel = new ActionModel();
	actModel.actionName = actName;
	actModel.actionType = actType;
	actModel.actionText = actText;
	actModel.isFree = isFree;
	actModel.hasNext = hasNext;
	actModel.isClose = isC;
	actModel.actionDesc = actDesc;
	var actionName = actName;
	$("#bpp_Btn_" + actName).click(function()
	{
		ButtonPanel.openAction(actionName);
//		$("#bpp_Sys_ActionID")[0].value = actName;
//		$("#bpp_Sys_ActionType")[0].value = actType;
//		$("#bpp_Sys_ActionText")[0].value = actText;
//		$("#bpp_Sys_CloseAfterCommit")[0].value = isC;
//		ClientContext.actionID = actName;
//		ClientContext.actionType = actType;
//		ClientContext.actionText = actText;
//		ClientContext.closeAfterCommit = isC;
	}
	);
	ButtonPanel.registerBtnEvent(actModel);
	return actModel;
}
ButtonPanel.openAction = function(actionName, postFunc)
{
	var actionModel = ClientContext.fixActions.get(actionName);
	if(actionModel == null) actionModel = ClientContext.freeActions.get(actionName);
	
	$("#bpp_Sys_ActionID")[0].value = actionModel.actionName;
	$("#bpp_Sys_ActionType")[0].value = actionModel.actionType;
	$("#bpp_Sys_ActionText")[0].value = actionModel.actionText;
	$("#bpp_Sys_CloseAfterCommit")[0].value = actionModel.isClose;
	
	ClientContext.actionID = actionModel.actionName;
	ClientContext.actionType = actionModel.actionType;
	ClientContext.actionText = actionModel.actionText;
	ClientContext.closeAfterCommit = actionModel.isClose;
	
	if(actionModel.hasNext == 1)
	{
		ButtonPanel.initAction(actionModel);
		$("#bpp_ActComment").text(actionModel.actionDesc);
		if(postFunc != undefined) $("#bpp_BtnPanel").fadeOut(20, function(){$("#bpp_ActPanel").fadeIn(20, postFunc);});
		else $("#bpp_BtnPanel").fadeOut(20, function(){$("#bpp_ActPanel").fadeIn(20);});
	}
	else
	{
		ActionPanel.submit();
	}
}
ButtonPanel.initAction = function(actionModel)
{
	ButtonPanel.loadActionPage(actionModel);
}
ButtonPanel.afterInitAction = function()
{
	var funcActionList = ClientContext.initFuncMap_Action.get(ClientContext.actionID);
	var funcStatusList = ClientContext.initFuncMap_Status.get(ClientContext.baseStatus);
	if(funcActionList != null)
	{
		for(var itFuncA = funcActionList.iterator(); itFuncA.hasNext();)
		{
			itFuncA.next()();
		}
	}
	if(funcStatusList != null)
	{
		for(var itFuncS = funcStatusList.iterator(); itFuncS.hasNext();)
		{
			itFuncS.next()();
		}
	}
	try{$('#bpp_ActPanel_BtnPanel')[0].firstChild.focus();}catch(e){}
};
ButtonPanel.registerBtnEvent = function(actionModel) {}

ButtonPanel.loadActionPage = function(actionModel)
{
	$("#bpp_ActFields").append("<img src=\"runtime/theme/"+ClientContext.theme+"/images/Progress.gif\" width=\"24\" height=\"24\" />");
	$("#bpp_ActFields").load($ctx + "/ultrabpp/form/formAction/showStep.action?baseID=" + ClientContext.baseID + "&baseSchema=" + ClientContext.baseSchema + "&taskID=" + ClientContext.taskID + "&type=free&actionID=" + actionModel.actionName + "&actionType=" + actionModel.actionType + "&theme=" + ClientContext.theme + "&stamp=" + (new Date()).getTime(), function()
	{
		ButtonPanel.afterInitAction();
	});
}