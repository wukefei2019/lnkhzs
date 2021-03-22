package com.ultrapower.wfinterface.model;

import java.lang.reflect.Method;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ultrapower.eoms.common.core.util.TimeUtils;

public class GwInfo {
	GwInfo model;
	private String id;
	private String step;
	private String activity;
	private String executor;
	private String opinion;
	private String depart;
	private String operate;
	private String time;
	private String form;
	private String reporttext;
	private String uid;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getReporttext() {
		return reporttext;
	}
	public void setReporttext(String reporttext) {
		this.reporttext = reporttext;
	}
	
	
	@SuppressWarnings("unchecked")
	public void parseXml2Model(String xml) {
		Document dom;
		Object obj;
		try {
			//GwInfo gi=new GwInfo();
			dom = DocumentHelper.parseText(xml);
			Element root = dom.getRootElement();
			Element rm=root.element("MsgBody");
			List<Element> fieldInfo =rm.elements();
			Class<?> clz = this.getClass();
			obj = clz.newInstance();
			for (Element e : fieldInfo) {
				//System.out.println(e.getName());
				String fieldEnName =e.getName();// e.elementText("fieldEnName");
				String fieldContent =e.getText();
				try {
					Method method = clz.getDeclaredMethod(
							"set" + this.firstCharUpper(fieldEnName),
							String.class);
					method.invoke(obj, fieldContent);
					if (fieldEnName.endsWith("Time")) {
						method = clz.getDeclaredMethod(
								"set" + this.firstCharUpper(fieldEnName)
										+ "Long", long.class);
						method.invoke(obj,
								TimeUtils.formatDateStringToInt(fieldContent));
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			this.model = (GwInfo) obj;
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	public String firstCharUpper(String param) {
		if (param.charAt(0) >= 'a' && param.charAt(0) <= 'z') {
			char[] paramC = param.toCharArray();
			paramC[0] -= 32;
			param = String.valueOf(paramC);
		}
		return param;
	}

	public GwInfo getModel() {
		return model;
	}

	public void setModel(GwInfo model) {
		this.model = model;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	
	/*private String gWYear;
	private String gWNo;
	private String gWTitle;
	private String gWCreator;
	private String gWDate;
	private String gWFile;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private Long createtime;
	private int status;
	private Long opttime;
	private String errorMessage;
	private String pid;

	public GwInfo() {
		super();

	}

	@SuppressWarnings("unchecked")
	public void parseXml2Model(String xml) {
		Document dom;
		Object obj;
		try {
			//GwInfo gi=new GwInfo();
			dom = DocumentHelper.parseText(xml);
			Element root = dom.getRootElement();
			Element rm=root.element("MsgBody");
			List<Element> fieldInfo =rm.elements();
			Class<?> clz = this.getClass();
			obj = clz.newInstance();
			for (Element e : fieldInfo) {
				//System.out.println(e.getName());
				String fieldEnName =e.getName();// e.elementText("fieldEnName");
				String fieldContent =e.getText();
				try {
					Method method = clz.getDeclaredMethod(
							"set" + this.firstCharUpper(fieldEnName),
							String.class);
					method.invoke(obj, fieldContent);
					if (fieldEnName.endsWith("Time")) {
						method = clz.getDeclaredMethod(
								"set" + this.firstCharUpper(fieldEnName)
										+ "Long", long.class);
						method.invoke(obj,
								TimeUtils.formatDateStringToInt(fieldContent));
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			this.model = (GwInfo) obj;
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	public String firstCharUpper(String param) {
		if (param.charAt(0) >= 'a' && param.charAt(0) <= 'z') {
			char[] paramC = param.toCharArray();
			paramC[0] -= 32;
			param = String.valueOf(paramC);
		}
		return param;
	}

	public GwInfo getModel() {
		return model;
	}

	public void setModel(GwInfo model) {
		this.model = model;
	}

	public String getGWYear() {
		return gWYear;
	}

	public void setGWYear(String gWYear) {
		this.gWYear = gWYear;
	}

	public String getGWNo() {
		return gWNo;
	}

	public void setGWNo(String gWNo) {
		this.gWNo = gWNo;
	}

	public String getGWTitle() {
		return gWTitle;
	}

	public void setGWTitle(String gWTitle) {
		this.gWTitle = gWTitle;
	}

	public String getGWCreator() {
		return gWCreator;
	}

	public void setGWCreator(String gWCreator) {
		this.gWCreator = gWCreator;
	}

	public String getGWDate() {
		return gWDate;
	}

	public void setGWDate(String gWDate) {
		this.gWDate = gWDate;
	}

	public String getGWFile() {
		return gWFile;
	}

	public void setGWFile(String gWFile) {
		this.gWFile = gWFile;
	}

	public String getATTRIBUTE1() {
		return attribute1;
	}

	public void setATTRIBUTE1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getATTRIBUTE2() {
		return attribute2;
	}

	public void setATTRIBUTE2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getATTRIBUTE3() {
		return attribute3;
	}

	public void setATTRIBUTE3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getATTRIBUTE4() {
		return attribute4;
	}

	public void setATTRIBUTE4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getATTRIBUTE5() {
		return attribute5;
	}

	public void setATTRIBUTE5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getOpttime() {
		return opttime;
	}

	public void setOpttime(Long opttime) {
		this.opttime = opttime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}*/

}
