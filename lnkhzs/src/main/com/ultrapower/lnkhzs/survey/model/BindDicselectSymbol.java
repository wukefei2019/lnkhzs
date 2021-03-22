package com.ultrapower.lnkhzs.survey.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [问卷题库关系表_实体]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "BS_T_SM_DICSELECT_SYMBOL")
public class BindDicselectSymbol implements ICommonModel {
	/**
	 * id_字段
	 */
	private String id;

	/**
	 * 筛选条件ID_字段
	 */
	private String dicselectid;
	
	/**
	 * 筛选条件字段名_字段
	 */
	private String selectname;
	
	/**
	 * 0 >  ;1 =   ;2 <_字段
	 */
	private String symbol;
	
	
	

	/**
	 * [dicselectid_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "DICSELECTID", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getDicselectid() {
		return dicselectid;
	}

	/**
	 * [dicselectid_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setDicselectid(String dicselectid) {
		this.dicselectid = dicselectid;
	}

	/**
	 * [selectname_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "SELECTNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getSelectname() {
		return selectname;
	}

	/**
	 * [selectname_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	/**
	 * [symbol_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "SYMBOL", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getSymbol() {
		return symbol;
	}

	/**
	 * [symbol_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * [id_Get方法]
	 * 
	 * @author:
	 */
	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	/**
	 * [id_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setId(String id) {
		this.id = id;
	}

}
