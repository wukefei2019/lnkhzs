package com.ultrapower.lnkhzs.quality.model;

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
 * <p>Description: [投诉结单归档量明细_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "NGCS_WF_CMPLNTS_CHECKSHEET")
public class SWfCmplntsCheckSheetDetail implements ICommonModel {
    /**
     *id_字段 
     */
    private String id;

    /**
     *比对日期_字段 
     */
    private String checkdate;

    /**
     *报表名称_字段 
     */
    private String sheetname;

    /**
     *入库条数_字段 
     */
    private String inrownum;

    /**
     *校验文件条数_字段 
     */
    private String chkrownum;

    /**
     *当前状态_字段 
     */
    private String status;

    /**
     *日期_字段 
     */
    private String systime;
    
    /**
     * 对应表名_字段 
     */
    private String tablename;


    
    
    
    @Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getId() {
		return id;
	}
    
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "CHECKDATE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCheckdate() {
		return checkdate;
	}
	
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	
	@Column(name = "SHEETNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getSheetname() {
		return sheetname;
	}
	
	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}
	
	@Column(name = "INROWNUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getInrownum() {
		return inrownum;
	}
	
	public void setInrownum(String inrownum) {
		this.inrownum = inrownum;
	}
	
	@Column(name = "CHKROWNUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getChkrownum() {
		return chkrownum;
	}
	
	public void setChkrownum(String chkrownum) {
		this.chkrownum = chkrownum;
	}
	
	@Column(name = "STATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "SYSTIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getSystime() {
		return systime;
	}
	
	public void setSystime(String systime) {
		this.systime = systime;
	}

	@Column(name = "TABLENAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	

}
