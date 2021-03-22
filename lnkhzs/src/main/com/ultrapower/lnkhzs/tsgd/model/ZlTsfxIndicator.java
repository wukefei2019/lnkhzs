package com.ultrapower.lnkhzs.tsgd.model;

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
@Table(name = "ZL_TSFX_INDICATOR")
public class ZlTsfxIndicator implements IOmcsModel{
    /**
     *null_字段 
     */
    private String id;

    /**
     *null_字段 
     */
    private String name;

    /**
     *null_字段 
     */
    private String sql;

    /**
     *null_字段 
     */
    private String childid;

    /**
     *null_字段 
     */
    private String type;

    /**
     *null_字段 
     */
    private String vieworder;

    /**
     * [null_Get方法]
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "assigned")
    @Column(name = "ID", unique=true, nullable=false, insertable=true, updatable=true, length=50 )
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
    @Column(name = "NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName() {
        return name;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "SQL", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getSql() {
        return sql;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "CHILDID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getChildid() {
        return childid;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setChildid(String childid) {
        this.childid = childid;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "TYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getType() {
        return type;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "VIEWORDER", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getVieworder() {
        return vieworder;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setVieworder(String vieworder) {
        this.vieworder = vieworder;
    }

}
