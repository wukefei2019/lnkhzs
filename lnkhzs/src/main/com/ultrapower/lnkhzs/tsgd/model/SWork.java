package com.ultrapower.lnkhzs.tsgd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.ultrapower.omcs.base.model.IOmcsModel;

@Entity
@Table(name = "ZL_SUPERVISE_WORK_ORDERS")
public class SWork implements IOmcsModel {
	/**
	 * ID，主键
	 */
	private String id;
	/**
	 * 督办事项
	 */
	private String supervision_matters;
	/**
	 * 派单部门
	 */
	private String department;
	/**
	 * 派单人
	 */
	private String dispatch;
	/**
	 * 派单人电话
	 */

	private String dispatch_phone;
	/**
	 * 起草时间
	 */
	private String createtime;
	/**
	 * 督办时限
	 */
	private String time_limit;
	/**
	 * 督办类别
	 */
	private String type;
	/**
	 * 主责部门
	 */
	private String maindepart;
	
	/**
	 * 主责部门id
	 */
	private String maindepartid;
	
	/**
	 * 主责部门全量名
	 */
	private String maindepartfull;
	
	/**
	 * 督办问题描述
	 */
	private String description;
	/**
	 * 督办问题来源
	 */
	private String source;
	
	/**
	 * 督办目标
	 */
	private String target;
	
	
	/**
	 * 父ID
	 */
	private String pid;
	
	/**
	 * 当前流程状态
	 */
	private String status;
	
	/**
	 * 编号
	 */
	private String serial_number;
	
	/**
	 * 审批人
	 */
	private String approvor;
	
	/**
	 * 抄送至
	 */
	private String sendto;
	
	/**
	 * 审批人登录名
	 */
	private String approvorlogin;
	
	/**
	 * 抄送至登录名
	 */
	private String sendtologin;
	
	
	/**
	 * 流程返回内容
	 */
	private String resultstr;
	
	private String backguid;
	
	private String backhandlingtime;
	
	private String backhandlingperson;
	
	private String backhandlingpersonlogin;
	
	private String backresolution;
	
	/**
	 * 上传文件名
	 */
	private String appendix_name;
	
	/**
	 * 上传文件地址
	 */
	private String appendix_address;
	
	
	private String appendix_pid;

	@Column(name = "PID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "SUPERVISION_MATTERS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSupervision_matters() {
		return supervision_matters;
	}

	public void setSupervision_matters(String supervision_matters) {
		this.supervision_matters = supervision_matters;
	}

	@Column(name = "DEPARTMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "DISPATCH", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}

	@Column(name = "DISPATCH_PHONE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDispatch_phone() {
		return dispatch_phone;
	}

	public void setDispatch_phone(String dispatch_phone) {
		this.dispatch_phone = dispatch_phone;
	}

	@Column(name = "CREATETIME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "TIME_LIMIT", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(String time_limit) {
		this.time_limit = time_limit;
	}

	@Column(name = "TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "MAINDEPART", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getMaindepart() {
		return maindepart;
	}

	public void setMaindepart(String maindepart) {
		this.maindepart = maindepart;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SOURCE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "TARGET", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SERIAL_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	@Column(name = "APPROVOR", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getApprovor() {
		return approvor;
	}

	public void setApprovor(String approvor) {
		this.approvor = approvor;
	}

	@Column(name = "SENDTO", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSendto() {
		return sendto;
	}

	public void setSendto(String sendto) {
		this.sendto = sendto;
	}

	@Column(name = "APPROVORLOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getApprovorlogin() {
		return approvorlogin;
	}

	public void setApprovorlogin(String approvorlogin) {
		this.approvorlogin = approvorlogin;
	}

	@Column(name = "SENDTOLOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSendtologin() {
		return sendtologin;
	}

	public void setSendtologin(String sendtologin) {
		this.sendtologin = sendtologin;
	}

	@Column(name = "MAINDEPARTID", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getMaindepartid() {
		return maindepartid;
	}

	public void setMaindepartid(String maindepartid) {
		this.maindepartid = maindepartid;
	}

	@Column(name = "MAINDEPARTFULL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getMaindepartfull() {
		return maindepartfull;
	}

	public void setMaindepartfull(String maindepartfull) {
		this.maindepartfull = maindepartfull;
	}

	@Column(name = "RESULTSTR", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getResultstr() {
		return resultstr;
	}

	public void setResultstr(String resultstr) {
		this.resultstr = resultstr;
	}

	@Column(name = "BACKGUID", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackguid() {
		return backguid;
	}

	public void setBackguid(String backguid) {
		this.backguid = backguid;
	}

	@Column(name = "BACKHANDLINGTIME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackhandlingtime() {
		return backhandlingtime;
	}

	public void setBackhandlingtime(String backhandlingtime) {
		this.backhandlingtime = backhandlingtime;
	}

	@Column(name = "BACKHANDLINGPERSON", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackhandlingperson() {
		return backhandlingperson;
	}

	public void setBackhandlingperson(String backhandlingperson) {
		this.backhandlingperson = backhandlingperson;
	}

	@Column(name = "BACKHANDLINGPERSONLOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackhandlingpersonlogin() {
		return backhandlingpersonlogin;
	}

	public void setBackhandlingpersonlogin(String backhandlingpersonlogin) {
		this.backhandlingpersonlogin = backhandlingpersonlogin;
	}

	@Column(name = "BACKRESOLUTION", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackresolution() {
		return backresolution;
	}

	public void setBackresolution(String backresolution) {
		this.backresolution = backresolution;
	}
	
	@Column(name = "APPENDIX_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAppendix_name() {
		return appendix_name;
	}

	public void setAppendix_name(String getAppendix_name) {
		this.pid = appendix_name;
	}

	
	@Column(name = "APPENDIX_ADDRESS", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAppendix_address() {
		return appendix_address;
	}

	public void setAppendix_address(String appendix_address) {
		this.appendix_address = appendix_address;
	}

	@Column(name = "APPENDIX_PID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAppendix_pid() {
		return appendix_pid;
	}

	public void setAppendix_pid(String appendix_pid) {
		this.appendix_pid = appendix_pid;
	}

	
	

}
