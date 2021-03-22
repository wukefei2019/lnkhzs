package com.ultrapower.lnkhzs.quality.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;

import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [服务请求明细日报表_实体]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *标签
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_BQSG_T_ADMIN")
public class TAGAdmin implements ICommonModel {

	/**
	 * ID_字段
	 */
	private String id;

	/**
	 * 修改时间_字段
	 */
	private String changetime;

	/**
	 * 修改人员ID_字段
	 */
	private String changepeople;
	
	/**
	 * 修改人员_字段
	 */
	private String changepeoplename;

	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CHANGETIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getChangetime() {
		return changetime;
	}

	public void setChangetime(String changetime) {
		this.changetime = changetime;
	}

	@Column(name = "CHANGEPEOPLE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getChangepeople() {
		return changepeople;
	}

	public void setChangepeople(String changepeople) {
		this.changepeople = changepeople;
	}

	@Column(name = "CHANGEPEOPLENAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getChangepeoplename() {
		return changepeoplename;
	}

	public void setChangepeoplename(String changepeoplename) {
		this.changepeoplename = changepeoplename;
	}
	
	

}
