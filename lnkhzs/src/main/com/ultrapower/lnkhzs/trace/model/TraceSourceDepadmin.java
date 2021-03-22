package com.ultrapower.lnkhzs.trace.model;

import com.ultrapower.omcs.base.model.IOmcsModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2018年1月4日
 * <p>Title      : 辽宁移动质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_实体]</p>
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_TRACE_SOURCE_DEPADMIN")
public class TraceSourceDepadmin implements IOmcsModel{
    /**
     *null_字段 
     */
    private String pid;

    /**
     *null_字段 
     */
    private String deplevel2name;

    /**
     *null_字段 
     */
    private String depname;

    /**
     *null_字段 
     */
    private String loginname;

    /**
     *null_字段 
     */
    private String fullname;

    /**
     * [null_Get方法]
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "assigned")
    @Column(name = "PID", unique=true, nullable=false, insertable=true, updatable=true, length=50 )
    public String getPid() {
        return pid;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "DEPLEVEL2NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getDeplevel2name() {
        return deplevel2name;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setDeplevel2name(String deplevel2name) {
        this.deplevel2name = deplevel2name;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "DEPNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getDepname() {
        return depname;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setDepname(String depname) {
        this.depname = depname;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=60 )
    public String getLoginname() {
        return loginname;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "FULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getFullname() {
        return fullname;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
