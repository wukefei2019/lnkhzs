package com.ultrapower.eoms.ultrasla.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.core.web.BaseAction;
import com.ultrapower.eoms.ultrasla.model.DuetimeRule;
import com.ultrapower.eoms.ultrasla.model.EventHandlerComp;
import com.ultrapower.eoms.ultrasla.model.EventRule;
import com.ultrapower.eoms.ultrasla.model.SlaDefine;
import com.ultrapower.eoms.ultrasla.service.IDuetimeRuleService;
import com.ultrapower.eoms.ultrasla.service.IEventHandlerCompService;
import com.ultrapower.eoms.ultrasla.service.IEventRuleService;
import com.ultrapower.eoms.ultrasla.service.ISlaDefineService;
import com.ultrapower.eoms.ultrasla.util.SlaDefineUtil;
import com.ultrapower.eoms.ultrasla.util.TrigCouple;
import com.ultrapower.eoms.ultrasla.util.WfEventTrigManager;
import com.ultrapower.randomutil.RandomN;

/**
 * @author RenChenglin
 * @date 2011-11-14 下午03:47:15
 * @version
 */
public class SlaDefineAction extends BaseAction {
	private String ruleXml;
	private String slaXml;
	private String baseSchema;
	private String deledTimeR;
	private String deledEventR;
	private String scope;
	private IEventRuleService eventRuleService;
	private IDuetimeRuleService duetimeRuleService;
	private ISlaDefineService slaDefineService;
	private IEventHandlerCompService eventHandlerCompService;
	
	/**
	 * 获得事件定义ID
	 * @return
	 */
	private String getEventDefID(String baseSchema
			,String eventType,RandomN random,StringBuffer sb)
	{
		sb.delete(0, sb.length());
		sb.append("WFE-");
		sb.append(StringUtils.checkNullString(baseSchema.replace(":", "_")));
		sb.append("-");
		sb.append(eventType);
		sb.append("-");
		sb.append(random.getRandom(System.currentTimeMillis()));
		return sb.toString();
	}
	
	/**
	 * 进入SLA协议定义框架页面
	 * @return
	 */
	public String slaDefineOuterFrame(){
		return SUCCESS;
	}
	
	/**
	 * 加载SLA协议定义页面
	 * @return
	 */
	public String slaDefine(){
		if(baseSchema==null){
			baseSchema = "WF4:Demo";
		}
		return SUCCESS;
	}
	
	/**
	 * 加载SLA定义基本信息
	 */
	public void loadSlaHead(){
		SlaDefine head = slaDefineService.getBySchema(baseSchema);
		String xml = SlaDefineUtil.constructSlaXml(head);
		renderXML(xml);
	}
	
	/**
	 * 加载事件规则
	 */
	public void loadEventRule(){
		EventRule rule = eventRuleService.getById(id);
		String xml = SlaDefineUtil.constructEventRuleXml(rule);
		renderXML(xml);
	}
	
	/**
	 * 加载时限规则
	 */
	public void loadTimeRule(){
		DuetimeRule rule = duetimeRuleService.get(id);
		String xml = SlaDefineUtil.constructTimeRuleXml(rule);
		renderXML(xml);
	}
	
	/**
	 * 保存事件规则
	 */
	public void saveEventRule(){
		String ruleid = "";
		if(ruleXml!=null)
		{
			EventRule rule = SlaDefineUtil.creEvtruleFromXml(ruleXml,eventRuleService,scope);
			ruleid = StringUtils.checkNullString(eventRuleService.save(rule));
		}
		renderText(ruleid);
	}

	/**
	 * 保存时限过则
	 */
	public void saveTimeRule(){
		String ruleid = "";
		if(ruleXml!=null)
		{
			DuetimeRule rule = SlaDefineUtil.creDuetimeruleFromXml(ruleXml,duetimeRuleService);
			ruleid = StringUtils.checkNullString(duetimeRuleService.save(rule));
		}
		renderText(ruleid);
	}
	
	/**
	 * 获得触发规则
	 */
	public void getTrigRule(){
		StringBuffer trigCon = new StringBuffer();
		trigCon.append("[");
		List<TrigCouple> tcList = null;
		if("form".equals(scope) && !"".equals(StringUtils.checkNullString(baseSchema)))
		{
			tcList = WfEventTrigManager.getFormTrig(baseSchema);
		}
		if("step".equals(scope))
		{
			tcList = WfEventTrigManager.getStepCommonTrig();
		}
		int len = 0;
		if(tcList!=null)
		{
			len = tcList.size();
		}
		TrigCouple tc;
		for(int i=0;i<len;i++)
		{
			tc = tcList.get(i);
			if(i!=0)
			{
				trigCon.append(",");
			}
			trigCon.append("{\"id\":\"");
			trigCon.append(tc.getId());
			trigCon.append("\",\"name\":\"");
			try {
				trigCon.append(URLEncoder.encode(StringUtils.checkNullString(tc.getName()), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				trigCon.delete(0, trigCon.length());
				trigCon.append("[");
				break;
			}
			trigCon.append("\"}");
		}
		trigCon.append("]");
		renderText(trigCon.toString());
	}
	
	/**
	 * 保存SLA定义基本信息及其触发规则关联
	 */
	public void saveSlaHead(){
		
	}
	
	/**
	 * 读取自定义通知组件
	 */
	public void readSelfNoticeComp(){
		String type = StringUtils.checkNullString(getRequest().getParameter("type"));//组件类型
		List<EventHandlerComp> comp = null;
		if("".equals(type)){
			comp = eventHandlerCompService.getAll();
		}else{
			comp = eventHandlerCompService.getByType(type);
		}
		StringBuffer jsonArr = new StringBuffer();
		jsonArr.append("[");
		if(comp!=null){
			int len = comp.size();
			EventHandlerComp temp;
			for(int i=0;i<len;i++){
				temp = comp.get(i);
				if(i!=0){
					jsonArr.append(",");
				}
				jsonArr.append("{\"pid\":\"");
				jsonArr.append(temp.getPid());
				jsonArr.append("\",\"name\":\"");
				try {
					jsonArr.append(URLEncoder.encode(temp.getComponentname(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					jsonArr.delete(0, jsonArr.length());
					jsonArr.append("[");
					break;
				}
				jsonArr.append("\"}");
			}
		}
		jsonArr.append("]");
		renderText(jsonArr.toString());
	}
	
	/**
	 * 刷新触发规则配置
	 */
	public void refreshTrigConfig(){
		try {
			WfEventTrigManager.parseTrigConfig();
			renderText("1");
		} catch (RuntimeException e) {
			e.printStackTrace();
			renderText("-1");
		}
	}
	
	public String getRuleXml() {
		return ruleXml;
	}

	public void setRuleXml(String ruleXml) {
		this.ruleXml = ruleXml;
	}

	public String getSlaXml() {
		return slaXml;
	}

	public void setSlaXml(String slaXml) {
		this.slaXml = slaXml;
	}

	public String getBaseSchema() {
		return baseSchema;
	}

	public void setBaseSchema(String baseSchema) {
		this.baseSchema = baseSchema;
	}

	public String getDeledTimeR() {
		return deledTimeR;
	}

	public void setDeledTimeR(String deledTimeR) {
		this.deledTimeR = deledTimeR;
	}

	public String getDeledEventR() {
		return deledEventR;
	}

	public void setDeledEventR(String deledEventR) {
		this.deledEventR = deledEventR;
	}
	
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public void setEventRuleService(IEventRuleService eventRuleService) {
		this.eventRuleService = eventRuleService;
	}

	public void setDuetimeRuleService(IDuetimeRuleService duetimeRuleService) {
		this.duetimeRuleService = duetimeRuleService;
	}

	public void setSlaDefineService(ISlaDefineService slaDefineService) {
		this.slaDefineService = slaDefineService;
	}

	public void setEventHandlerCompService(
			IEventHandlerCompService eventHandlerCompService) {
		this.eventHandlerCompService = eventHandlerCompService;
	}
	
}
