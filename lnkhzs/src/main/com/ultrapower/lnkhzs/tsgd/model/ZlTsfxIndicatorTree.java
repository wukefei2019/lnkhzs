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
@Table(name = "ZL_TSFX_INDICATOR_TREE")
public class ZlTsfxIndicatorTree implements IOmcsModel{
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
    private String indicatorsql;

    /**
     *null_字段 
     */
    private String ndicatorsqlmonth;

    /**
     *null_字段 
     */
    private String indicatorlevel;

    /**
     *null_字段 
     */
    private String parendid;

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
    @Column(name = "INDICATORSQL", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getIndicatorsql() {
        return indicatorsql;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setIndicatorsql(String indicatorsql) {
        this.indicatorsql = indicatorsql;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "NDICATORSQLMONTH", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getNdicatorsqlmonth() {
        return ndicatorsqlmonth;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setNdicatorsqlmonth(String ndicatorsqlmonth) {
        this.ndicatorsqlmonth = ndicatorsqlmonth;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "INDICATORLEVEL", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getIndicatorlevel() {
        return indicatorlevel;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setIndicatorlevel(String indicatorlevel) {
        this.indicatorlevel = indicatorlevel;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "PARENDID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getParendid() {
        return parendid;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setParendid(String parendid) {
        this.parendid = parendid;
    }

}
