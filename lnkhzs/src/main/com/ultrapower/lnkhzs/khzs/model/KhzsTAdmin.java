package com.ultrapower.lnkhzs.khzs.model;

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
 * <p>Description: [null_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_KHZS_T_ADMIN")
public class KhzsTAdmin implements ICommonModel {
    /**
     *部门编码（或存外系统部门id）_字段 
     */
    private String depcode;

    /**
     *机构全名_字段 
     */
    private String depfullname;

    /**
     *机构pid_字段 
     */
    private String depid;

    /**
     *机构名称_字段 
     */
    private String depname;

    /**
     *用户中文名_字段 
     */
    private String fullname;

    /**
     *登录名唯一_字段 
     */
    private String loginname;

    /**
     *null_字段 
     */
    private String pid;

    /**
     * [部门编码（或存外系统部门id）_Get方法]
     * @author:
     */
    @Column(name = "DEPCODE", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getDepcode() {
        return depcode;
    }
    /**
    * [部门编码（或存外系统部门id）_Set方法]
    * @author:
    * @param args
    */
    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }


    /**
     * [机构全名_Get方法]
     * @author:
     */
    @Column(name = "DEPFULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=500 )
    public String getDepfullname() {
        return depfullname;
    }
    /**
    * [机构全名_Set方法]
    * @author:
    * @param args
    */
    public void setDepfullname(String depfullname) {
        this.depfullname = depfullname;
    }


    /**
     * [机构pid_Get方法]
     * @author:
     */
    @Column(name = "DEPID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getDepid() {
        return depid;
    }
    /**
    * [机构pid_Set方法]
    * @author:
    * @param args
    */
    public void setDepid(String depid) {
        this.depid = depid;
    }


    /**
     * [机构名称_Get方法]
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
     * [用户中文名_Get方法]
     * @author:
     */
    @Column(name = "FULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getFullname() {
        return fullname;
    }
    /**
    * [用户中文名_Set方法]
    * @author:
    * @param args
    */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    /**
     * [登录名唯一_Get方法]
     * @author:
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=60 )
    public String getLoginname() {
        return loginname;
    }
    /**
    * [登录名唯一_Set方法]
    * @author:
    * @param args
    */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }


    /**
     * [null_Get方法]
     * @author:
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
    * @author:
    * @param args
    */
    public void setPid(String pid) {
        this.pid = pid;
    }


}
