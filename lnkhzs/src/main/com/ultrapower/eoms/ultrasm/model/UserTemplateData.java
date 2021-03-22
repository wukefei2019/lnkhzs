package com.ultrapower.eoms.ultrasm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BS_T_SM_USERTEMPLATEDATA")
public class UserTemplateData {

	// Fields    

	private String pid;
	private String utid;
	private String orgid;
	private Long orgtype;

	// Constructors

	/** default constructor */
	public UserTemplateData() {
		
	}

	/** minimal constructor */
	public UserTemplateData(String pid) {
		this.pid = pid;
	}
	
	/** full constructor */
	public UserTemplateData(String pid, String utid, String orgid, Long orgtype) {
		this.pid = pid;
		this.utid = utid;
		this.orgid = orgid;
		this.orgtype = orgtype;
	}
	
	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "PID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Column(name = "UTID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getUtid() {
		return utid;
	}
	public void setUtid(String utid) {
		this.utid = utid;
	}
	
	@Column(name = "ORGID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	@Column(name = "ORGTYPE", unique = false, nullable = true, insertable = true, updatable = true, precision = 2, scale = 0)
	public Long getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(Long orgtype) {
		this.orgtype = orgtype;
	}
}
