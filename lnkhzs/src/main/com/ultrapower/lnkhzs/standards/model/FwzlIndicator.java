package com.ultrapower.lnkhzs.standards.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

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
@Table(name = "ZL_FWZL_INDICATOR")
public class FwzlIndicator implements ICommonModel {
    /**
     *null_字段 
     */
    private String childid;

    /**
     *id_字段 
     */
    private String id;

    /**
     *指标名称_字段 
     */
    private String name;

    /**
     *sql_字段 
     */
    private String sql;

    /**
     *时长/率/次数_字段 
     */
    private String type;

    /**
     *显示顺序_字段 
     */
    private String vieworder;

    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "CHILDID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getChildid() {
        return childid;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setChildid(String childid) {
        this.childid = childid;
    }


    /**
     * [id_Get方法]
     * @author:
     */
    @Id
    @Column(name = "ID", unique=false ,nullable=false, insertable=true, updatable=true ,length=50 )
    public String getId() {
        return id;
    }
    /**
    * [id_Set方法]
    * @author:
    * @param args
    */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * [指标名称_Get方法]
     * @author:
     */
    @Column(name = "NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName() {
        return name;
    }
    /**
    * [指标名称_Set方法]
    * @author:
    * @param args
    */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * [sql_Get方法]
     * @author:
     */
    @Column(name = "SQL", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getSql() {
        return sql;
    }
    /**
    * [sql_Set方法]
    * @author:
    * @param args
    */
    public void setSql(String sql) {
        this.sql = sql;
    }


    /**
     * [时长/率/次数_Get方法]
     * @author:
     */
    @Column(name = "TYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getType() {
        return type;
    }
    /**
    * [时长/率/次数_Set方法]
    * @author:
    * @param args
    */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * [显示顺序_Get方法]
     * @author:
     */
    @Column(name = "VIEWORDER", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getVieworder() {
        return vieworder;
    }
    /**
    * [显示顺序_Set方法]
    * @author:
    * @param args
    */
    public void setVieworder(String vieworder) {
        this.vieworder = vieworder;
    }


}
