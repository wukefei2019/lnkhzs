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
@Table(name = "ZL_BQSG_T_INFO")
public class TAGInfo implements ICommonModel {

	/**
	 * ID_字段
	 */
	private String id;

	/**
	 * 申报人_字段
	 */
	private String reporter;

	/**
	 * 申报时间_字段
	 */
	private String reporttime;

	/**
	 * 申报状态：待提交，已提交
	 */
	private String reportstatus;
	
	/**
	 * 审批状态：待审批，通过，拒绝
	 */
	private String approvalstatus;

	/**
	 * 工单号_字段
	 */
	private String worknumber;

	/**
	 * 工单号绑定的七级标签_字段
	 */
	private String worktag;

	/**
	 * 业务内容_字段
	 */
	private String workinfo;

	/**
	 * 七级标签_字段
	 */
	private String tag;

	/**
	 * 调整建议_字段
	 */
	private String changeview;

	/**
	 * 审批人_字段
	 */
	private String approver;
	
	/**
	 * 审批人名_字段
	 */
	private String approvername;

	/**
	 * 审批时间_字段
	 */
	private String approvaltime;

	/**
	 * 审批备注_字段
	 */
	private String approvalinfo;
	/**
	 * 提交人名
	 */
	private String reportername;
	
	/**
	 * 所属部门
	 */
	private String department;
	
	/**
	 * 所属部门ID
	 */
	private String departmentid;
	
	

	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "REPORTER", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@Column(name = "REPORTTIME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	/*@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}*/
	
	@Column(name = "REPORTSTATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getReportstatus() {
		return reportstatus;
	}


	public void setReportstatus(String reportstatus) {
		this.reportstatus = reportstatus;
	}

	@Column(name = "APPROVALSTATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getApprovalstatus() {
		return approvalstatus;
	}


	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	
	

	@Column(name = "WORKNUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getWorknumber() {
		return worknumber;
	}


	public void setWorknumber(String worknumber) {
		this.worknumber = worknumber;
	}

	@Column(name = "WORKTAG", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getWorktag() {
		return worktag;
	}

	public void setWorktag(String worktag) {
		this.worktag = worktag;
	}

	@Column(name = "WORKINFO", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getWorkinfo() {
		return workinfo;
	}

	public void setWorkinfo(String workinfo) {
		this.workinfo = workinfo;
	}

	@Column(name = "TAG", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	/*@Column(name = "VIEW", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}*/

	@Column(name = "APPROVER", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	@Column(name = "APPROVALTIME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getApprovaltime() {
		return approvaltime;
	}

	public void setApprovaltime(String approvaltime) {
		this.approvaltime = approvaltime;
	}

	@Column(name = "APPROVALINFO", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getApprovalinfo() {
		return approvalinfo;
	}

	public void setApprovalinfo(String approvalinfo) {
		this.approvalinfo = approvalinfo;
	}


	@Column(name = "CHANGEVIEW", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getChangeview() {
		return changeview;
	}


	public void setChangeview(String changeview) {
		this.changeview = changeview;
	}


	@Column(name = "APPROVERNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getApprovername() {
		return approvername;
	}


	public void setApprovername(String approvername) {
		this.approvername = approvername;
	}


	
	
	@Column(name = "REPORTERNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getReportername() {
		return reportername;
	}


	public void setReportername(String reportername) {
		this.reportername = reportername;
	}

	@Column(name = "DEPARTMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "DEPARTMENTID", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDepartmentid() {
		return departmentid;
	}


	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	
	

	
	
}
