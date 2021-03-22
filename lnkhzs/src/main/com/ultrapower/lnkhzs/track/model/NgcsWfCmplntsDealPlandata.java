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
@Table(name = "NGCS_WF_CMPLNTS_DEAL_PLANDATA")
public class NgcsWfCmplntsDealPlandata implements IOmcsModel{
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
    private String plandepart;

    /**
     *null_字段 
     */
    private String planperson;

    /**
     *null_字段 
     */
    private String plantarget;

    /**
     *null_字段 
     */
    private String plancontent;

    /**
     *null_字段 
     */
    private String plandate;

    /**
     * [null_Get方法]
     */
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
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "assigned")
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
    @Column(name = "PLANDEPART", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getPlandepart() {
        return plandepart;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setPlandepart(String plandepart) {
        this.plandepart = plandepart;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "PLANPERSON", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getPlanperson() {
        return planperson;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setPlanperson(String planperson) {
        this.planperson = planperson;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "PLANTARGET", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getPlantarget() {
        return plantarget;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setPlantarget(String plantarget) {
        this.plantarget = plantarget;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "PLANCONTENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getPlancontent() {
        return plancontent;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setPlancontent(String plancontent) {
        this.plancontent = plancontent;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "PLANDATE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getPlandate() {
        return plandate;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setPlandate(String plandate) {
        this.plandate = plandate;
    }

}
