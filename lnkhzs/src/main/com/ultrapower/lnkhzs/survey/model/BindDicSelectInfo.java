package com.ultrapower.lnkhzs.survey.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [问卷题库关系表_实体]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "BS_T_SM_DICSELEINFO")
public class BindDicSelectInfo implements ICommonModel {
	/**
	 * id_字段
	 */
	private String id;

	/**
	 * 时长/天数_字段
	 */
	private String daynum;
	
	
	/**
	 * 短信内容_字段
	 */
	private String message_info;
	
	/**
	 *派发人数_字段
	 */
	private String people_num;
	
	
	
	
	@Column(name = "PEOPLE_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPeople_num() {
		return people_num;
	}

	public void setPeople_num(String people_num) {
		this.people_num = people_num;
	}

	/**
	 * [id_Get方法]
	 * 
	 * @author:
	 */
	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	/**
	 * [id_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	/**
	 * [时长/天数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "DAYNUM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDaynum() {
		return daynum;
	}

	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}
	
	/**
	 * [短信内容_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "MESSAGE_INFO", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getMessage_info() {
		return message_info;
	}

	public void setMessage_info(String message_info) {
		this.message_info = message_info;
	}
	
	

	
	
	
	
	
	

	

}
