package com.ultrapower.lnkhzs.trace.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [溯源问题清单_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_TRACE_SOURCE_DEPADMIN")
public class TraceManager implements ICommonModel {
    /**
     *id_字段 
     */
    private String pid;

    /**
     *二级部门名称_字段 
     */
    private String deplevel2name;

    /**
     *机构名称_字段 
     */
    private String depname;

    /**
     *登录唯一名_字段 
     */
    private String loginname;

    /**
     *用户中文名_字段 
     */
    private String fullname;

    /**
     *二级部门id_字段 
     */
    private String depevel2id;

    /**
     * [二级部门名称_Get方法]
     * @author:
     */
    @Id
    @Column(name = "DEPLEVEL2NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getDeplevel2name() {
		return deplevel2name;
	}
    /**
     * [二级部门名称_Set方法]
     * @author:
     * @param args
     */
	public    void setDeplevel2name(String deplevel2name) {
		this.deplevel2name = deplevel2name;
	}
	/**
     * [机构名称_Get方法
     * @author:
     */
    @Column(name = "DEPNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getDepname() {
		return depname;
	}
    /**
     * [机构名称_Set方法]
     * @author:
     * @param args
     */
	public void setDepname(String depname) {
		this.depname = depname;
	}
	/**
     * [唯一登录名_Get方法]
     * @author:
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=60 )
	public String getLoginname() {
		return loginname;
	}
    /**
     * [唯一登录名_Set方法]
     * @author:
     * @param args
     */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	/**
     * [用户中文名_Get方法]
     * @author:
     */
    @Column(name = "FULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFullname() {
		return fullname;
	}
    /**
     * [唯一登录名_Set方法]
     * @author:
     * @param args
     */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	/**
     * [二级部门ID_Get方法]
     * @author:
     */
    @Column(name = "DEPLEVEL2ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
	public String getDepevel2id() {
		return depevel2id;
	}
    /**
     * [二级部门ID_Set方法]
     * @author:
     * @param args
     */
	public void setDepevel2id(String depevel2id) {
		this.depevel2id = depevel2id;
	}
	/**
     * [ID_Get方法]
     * @author:
     */
    @Column(name = "PID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
	public String getPid() {
		return pid;
	}
    /**
     * [ID_Set方法]
     * @author:
     * @param args
     */
	public  void setPid(String pid) {
		this.pid = pid;
	}
	
}
