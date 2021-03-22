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
 * <p>Description: [null_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_TRACE_SOURCE_SEND_FLOW")
public class TraceSourceSendFlow implements ICommonModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *null_字段 
     */
    private String createtime;

    /**
     *null_字段 
     */
    private String pid;

    /**
     *null_字段 
     */
    private String sendmsg;

    /**
     *null_字段 
     */
    private String sendname;

    /**
     *null_字段 
     */
    private String sendphone;

    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "CREATETIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getCreatetime() {
        return createtime;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "SENDMSG", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getSendmsg() {
        return sendmsg;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setSendmsg(String sendmsg) {
        this.sendmsg = sendmsg;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "SENDNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getSendname() {
        return sendname;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setSendname(String sendname) {
        this.sendname = sendname;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "SENDPHONE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getSendphone() {
        return sendphone;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setSendphone(String sendphone) {
        this.sendphone = sendphone;
    }


}
