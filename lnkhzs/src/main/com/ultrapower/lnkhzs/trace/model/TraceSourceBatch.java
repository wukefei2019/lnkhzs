package com.ultrapower.lnkhzs.trace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.base.model.IOmcsModel;

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
@Table(name = "ZL_TRACE_SOURCE_BATCH")
public class TraceSourceBatch implements IOmcsModel{
    /**
     *null_字段 
     */
    private String id;

    /**
     *null_字段 
     */
    private String code;

    /**
     *null_字段 
     */
    private String title;

    /**
     *未完成，已完成_字段 
     */
    private String status;

    /**
     *null_字段 
     */
    private String createperson;

    /**
     *null_字段 
     */
    private String createtime;

    /**
     * [null_Get方法]
     */
    @Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=30 )
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
     * [null_Get方法]
     */
    @Column(name = "CODE", unique=false ,nullable=true, insertable=true, updatable=true ,length=30 )
    public String getCode() {
        return code;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "TITLE", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getTitle() {
        return title;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * [未完成，已完成_Get方法]
     */
    @Column(name = "STATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=30 )
    public String getStatus() {
        return status;
    }

    /**
    * [未完成，已完成_Set方法]
    * @param args
    */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "CREATEPERSON", unique=false ,nullable=true, insertable=true, updatable=true ,length=30 )
    public String getCreateperson() {
        return createperson;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setCreateperson(String createperson) {
        this.createperson = createperson;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "CREATETIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=30 )
    public String getCreatetime() {
        return createtime;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

}
