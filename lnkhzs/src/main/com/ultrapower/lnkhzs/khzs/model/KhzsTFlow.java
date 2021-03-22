package com.ultrapower.lnkhzs.khzs.model;

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
@Table(name = "ZL_KHZS_T_FLOW")
public class KhzsTFlow implements IOmcsModel{
    /**
     *操作人机构_字段 
     */
    private String depname;
    
    //责任机构
    private String deptname;
    
    private String status;
    
    private String laststatus;

    /**
     *下一级处理人全名_字段 
     */
    private String nextfullname;

    /**
     *下一级处理人登入名_字段 
     */
    private String nextloginname;

    /**
     *处理人机构_字段 
     */
    private String nextdepname;

    /**
     *操作记录_字段 
     */
    private String remark;

    /**
     *解决措施_字段 
     */
    private String remark2;

    /**
     *null_字段 
     */
    private String pid;

    /**
     *客户之声pid_字段 
     */
    private String vocpid;

    /**
     *时间_字段 
     */
    private String createtime;

    /**
     *审核意见_字段 
     */
    private String opinion;

    /**
     *操作人全名_字段 
     */
    private String fullname;

    /**
     *操作人登入名_字段 
     */
    private String loginname;

    /**
     * [操作人机构_Get方法]
     */
    @Column(name = "DEPNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getDepname() {
        return depname;
    }

    /**
    * [操作人机构_Set方法]
    * @param args
    */
    public void setDepname(String depname) {
        this.depname = depname;
    }
    
    

    @Column(name = "DEPTNAME")
    public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

    
    
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }


	

	/**
    * [操作人机构_Set方法]
    * @param args
    */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    @Column(name = "LASTSTATUS")
    public String getLaststatus() {
		return laststatus;
	}

	public void setLaststatus(String laststatus) {
		this.laststatus = laststatus;
	}

    /**
     * [下一级处理人全名_Get方法]
     */
    @Column(name = "NEXTFULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getNextfullname() {
        return nextfullname;
    }

    /**
    * [下一级处理人全名_Set方法]
    * @param args
    */
    public void setNextfullname(String nextfullname) {
        this.nextfullname = nextfullname;
    }

    /**
     * [下一级处理人登入名_Get方法]
     */
    @Column(name = "NEXTLOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getNextloginname() {
        return nextloginname;
    }

    /**
    * [下一级处理人登入名_Set方法]
    * @param args
    */
    public void setNextloginname(String nextloginname) {
        this.nextloginname = nextloginname;
    }

    /**
     * [处理人机构_Get方法]
     */
    @Column(name = "NEXTDEPNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getNextdepname() {
        return nextdepname;
    }

    /**
    * [处理人机构_Set方法]
    * @param args
    */
    public void setNextdepname(String nextdepname) {
        this.nextdepname = nextdepname;
    }

    /**
     * [操作记录_Get方法]
     */
    @Column(name = "REMARK", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getRemark() {
        return remark;
    }

    /**
    * [操作记录_Set方法]
    * @param args
    */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * [解决措施_Get方法]
     */
    @Column(name = "REMARK2", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getRemark2() {
        return remark2;
    }

    /**
    * [解决措施_Set方法]
    * @param args
    */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

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
     * [客户之声pid_Get方法]
     */
    @Column(name = "VOCPID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getVocpid() {
        return vocpid;
    }

    /**
    * [客户之声pid_Set方法]
    * @param args
    */
    public void setVocpid(String vocpid) {
        this.vocpid = vocpid;
    }

    /**
     * [时间_Get方法]
     */
    @Column(name = "CREATETIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getCreatetime() {
        return createtime;
    }

    /**
    * [时间_Set方法]
    * @param args
    */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * [审核意见_Get方法]
     */
    @Column(name = "OPINION", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getOpinion() {
        return opinion;
    }

    /**
    * [审核意见_Set方法]
    * @param args
    */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * [操作人全名_Get方法]
     */
    @Column(name = "FULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getFullname() {
        return fullname;
    }

    /**
    * [操作人全名_Set方法]
    * @param args
    */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * [操作人登入名_Get方法]
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getLoginname() {
        return loginname;
    }

    /**
    * [操作人登入名_Set方法]
    * @param args
    */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

}
