package com.ultrapower.lnkhzs.standards.model;

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
@Table(name = "DW_HOME_IF_BOCE")
public class HomeIfBoce implements ICommonModel {
    /**
     *城市_字段 
     */
    private String city;

    /**
     *入库时间_字段 
     */
    private String createTime;

    /**
     *指标名_字段 
     */
    private String indexName;

    /**
     *指标值_字段 
     */
    private String indexValue;

    /**
     *是否mtd_字段 
     */
    private String isMtd;

    /**
     *统计时间_字段 
     */
    private String statisticalTime;

    /**
     *类别_字段 
     */
    private String type;

    /**
     * [城市_Get方法]
     * @author:
     */
    @Column(name = "CITY", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getCity() {
        return city;
    }
    /**
    * [城市_Set方法]
    * @author:
    * @param args
    */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * [入库时间_Get方法]
     * @author:
     */
    @Column(name = "CREATE_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getCreateTime() {
        return createTime;
    }
    /**
    * [入库时间_Set方法]
    * @author:
    * @param args
    */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    /**
     * [指标名_Get方法]
     * @author:
     */
    @Id
    @Column(name = "INDEX_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getIndexName() {
        return indexName;
    }
    /**
    * [指标名_Set方法]
    * @author:
    * @param args
    */
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }


    /**
     * [指标值_Get方法]
     * @author:
     */
    @Column(name = "INDEX_VALUE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getIndexValue() {
        return indexValue;
    }
    /**
    * [指标值_Set方法]
    * @author:
    * @param args
    */
    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }


    /**
     * [是否mtd_Get方法]
     * @author:
     */
    @Column(name = "IS_MTD", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getIsMtd() {
        return isMtd;
    }
    /**
    * [是否mtd_Set方法]
    * @author:
    * @param args
    */
    public void setIsMtd(String isMtd) {
        this.isMtd = isMtd;
    }


    /**
     * [统计时间_Get方法]
     * @author:
     */
    @Column(name = "STATISTICAL_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getStatisticalTime() {
        return statisticalTime;
    }
    /**
    * [统计时间_Set方法]
    * @author:
    * @param args
    */
    public void setStatisticalTime(String statisticalTime) {
        this.statisticalTime = statisticalTime;
    }


    /**
     * [类别_Get方法]
     * @author:
     */
    @Column(name = "TYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getType() {
        return type;
    }
    /**
    * [类别_Set方法]
    * @author:
    * @param args
    */
    public void setType(String type) {
        this.type = type;
    }


}
