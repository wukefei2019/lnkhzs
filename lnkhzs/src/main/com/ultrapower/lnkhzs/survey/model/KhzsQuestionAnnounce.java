package com.ultrapower.lnkhzs.survey.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [问卷_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_KHZS_ANNOUNCE")
public class KhzsQuestionAnnounce implements ICommonModel {
    
	private static final long serialVersionUID = 611616198559617187L;
	
    /**
     *公告id_字段 
     */
    private String id;

    /**
     *公告内容_字段 
     */
    private String  content;
    
    /**
     *有效时间_字段 
     */
    private String time;

    /**
     *状态(草稿 发布)_字段 
     */
    private String status;
    
    
    
    @Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
	public String getId() {
		return id;
	}
    /**
     * [公告ID_Set方法]
     * @author:
     * @param args
     */
	public void setId(String id) {
		this.id = id;
	}
	/**
     * [公告内容_Get方法]
     * @author:
     */
    @Column(name = "CONTENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getContent() {
		return content;
	}
    /**
     * [公告内容_Set方法]
     * @author:
     * @param args
     */
	public void setContent(String content) {
		this.content = content;
	}
	/**
     * [有效时间_Get方法]
     * @author:
     */
    @Column(name = "TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getTime() {
		return time;
	}
    /**
     * [有效时间_Set方法]
     * @author:
     * @param args
     */
	public void setTime(String time) {
		this.time = time;
	}
	/**
     * [公告状态_Get方法]
     * @author:
     */
    @Column(name = "STATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getStatus() {
		return status;
	}
    /**
     * [公告状态_Set方法]
     * @author:
     * @param args
     */
	public void setStatus(String status) {
		this.status = status;
	}

}
