package com.ultrapower.lnkhzs.tsgd.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [题库_实体]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
@SuppressWarnings("serial")
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_DB_MANAGER")
public class DbManager implements ICommonModel {
	/*private static final long serialVersionUID = -243079311191118188L;*/

	/**
	 * ID_字段
	 */
	private String id;
	/**
	 * 管理员名字_字段
	 */
	private String administratorname;

	/**
	 * 管理员ID_字段
	 */
	private String administratorid;

	/**
	 * 地市名字_字段
	 */
	private String cityname;
	
	/**
	 * 地市id_字段
	 */
	private String cityid;
	

	
	
	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	@Column(name = "ADMINISTRATORNAME", unique = false, nullable = true, insertable = true, updatable = true, length =100)
	public String getAdministratorname() {
		return administratorname;
	}

	
	public void setAdministratorname(String administratorname) {
		this.administratorname = administratorname;
	}
	@Column(name = "ADMINISTRATORID", unique = false, nullable = true, insertable = true, updatable = true, length =50)
	public String getAdministratorid() {
		return administratorid;
	}

	public void setAdministratorid(String administratorid) {
		this.administratorid = administratorid;
	}
	@Column(name = "CITYNAME", unique = false, nullable = true, insertable = true, updatable = true, length =100)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	@Column(name = "CITYID", unique = false, nullable = true, insertable = true, updatable = true, length =50)
	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	
}
