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
 * <p>Description: [评论表_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_KHZS_T_COMMENT")
public class KhzsTComment implements ICommonModel {
    /**
     *评论内容_字段 
     */
    private String content;

    /**
     *时间_字段 
     */
    private String createtime;

    /**
     *评论人全名_字段 
     */
    private String fullname;

    /**
     *评论人登入名_字段 
     */
    private String loginname;

    /**
     *pid_字段 
     */
    private String pid;

    /**
     *客户之声pid_字段 
     */
    private String vocpid;

    /**
     * [评论内容_Get方法]
     * @author:
     */
    @Column(name = "CONTENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getContent() {
        return content;
    }
    /**
    * [评论内容_Set方法]
    * @author:
    * @param args
    */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * [时间_Get方法]
     * @author:
     */
    @Column(name = "CREATETIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getCreatetime() {
        return createtime;
    }
    /**
    * [时间_Set方法]
    * @author:
    * @param args
    */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    /**
     * [评论人全名_Get方法]
     * @author:
     */
    @Column(name = "FULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getFullname() {
        return fullname;
    }
    /**
    * [评论人全名_Set方法]
    * @author:
    * @param args
    */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    /**
     * [评论人登入名_Get方法]
     * @author:
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getLoginname() {
        return loginname;
    }
    /**
    * [评论人登入名_Set方法]
    * @author:
    * @param args
    */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }


    /**
     * [pid_Get方法]
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
    * [pid_Set方法]
    * @author:
    * @param args
    */
    public void setPid(String pid) {
        this.pid = pid;
    }


    /**
     * [客户之声pid_Get方法]
     * @author:
     */
    @Column(name = "VOCPID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getVocpid() {
        return vocpid;
    }
    /**
    * [客户之声pid_Set方法]
    * @author:
    * @param args
    */
    public void setVocpid(String vocpid) {
        this.vocpid = vocpid;
    }


}
