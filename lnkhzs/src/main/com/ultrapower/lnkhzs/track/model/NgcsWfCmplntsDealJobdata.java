package com.ultrapower.lnkhzs.track.model;

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
@Table(name = "NGCS_WF_CMPLNTS_DEAL_JOBDATA")
public class NgcsWfCmplntsDealJobdata implements IOmcsModel{
    /**
     *null_字段 
     */
    private String id;

    /**
     *处理信息主表ID_字段 
     */
    private String pid;

    /**
     *null_字段 
     */
    private String jobdepart;

    /**
     *null_字段 
     */
    private String jobperson;

    /**
     *null_字段 
     */
    private String jobcontent;

    /**
     *null_字段 
     */
    private String jobdate;

    /**
     * [null_Get方法]
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "assigned")
    @Column(name = "ID", unique=false ,nullable=false, insertable=true, updatable=true ,length=50 )
    public String getId() {
        return id;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * [处理信息主表ID_Get方法]
     */
    @Column(name = "PID", unique=true, nullable=false, insertable=true, updatable=true, length=50 )
    public String getPid() {
        return pid;
    }

    /**
    * [处理信息主表ID_Set方法]
    * @param args
    */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "JOBDEPART", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getJobdepart() {
        return jobdepart;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setJobdepart(String jobdepart) {
        this.jobdepart = jobdepart;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "JOBPERSON", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getJobperson() {
        return jobperson;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setJobperson(String jobperson) {
        this.jobperson = jobperson;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "JOBCONTENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getJobcontent() {
        return jobcontent;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setJobcontent(String jobcontent) {
        this.jobcontent = jobcontent;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "JOBDATE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getJobdate() {
        return jobdate;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setJobdate(String jobdate) {
        this.jobdate = jobdate;
    }

}
