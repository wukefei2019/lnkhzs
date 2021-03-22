package com.ultrapower.wfinterface.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import javax.persistence.Id;
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

public class KhzsQuestionnaire  {

	KhzsQuestionnaire model;

	/**
     *问卷时间_字段 
     */
    private String creattime;

    /**
     *问卷号_字段 
     */
    private String id;

    /**
     *问卷名_字段 
     */
    private String name;
    
    /**
     *副标题_字段 
     */
    private String namesub;

    /**
     *状态(草稿 生效 已发布)_字段 
     */
    private String status;
    
    
    private String bsdicselectid;
    
    /**
     * 共有私有
     */
    private String ispublic;
    
    /**
	 * 创建人
	 */
	private String createby;
	
	/**
	 * 创建人 id
	 */
	private String createbyname;
	

	/**
	 * 问卷号绑定的短信、时长等表ID
	 */
	private String bsdicseleinfoid;
	
	/**
	 * 群发短信返回内容ID
	 */
	private String projectid;
	
    
	/**
     * [问卷时间_Get方法]
     * @author:
     */
    @Column(name = "CREATTIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCreattime() {
        return creattime;
    }
    /**
    * [问卷时间_Set方法]
    * @author:
    * @param args
    */
    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }


    /**
     * [问卷号_Get方法]
     * @author:
     */
    @Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getId() {
        return id;
    }
    /**
    * [问卷号_Set方法]
    * @author:
    * @param args
    */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * [问卷名_Get方法]
     * @author:
     */
    @Column(name = "NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName() {
        return name;
    }
    /**
    * [问卷名_Set方法]
    * @author:
    * @param args
    */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * [副标题_Get方法]
     * @author:
     */
    @Column(name = "NAMESUB", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getNamesub() {
		return namesub;
	}
    /**
     * [副标题_Set方法]
     * @author:
     * @param args
     */
	public void setNamesub(String namesub) {
		this.namesub = namesub;
	}
	/**
     * [状态(草稿 生效 已发布)_Get方法]
     * @author:
     */
    @Column(name = "STATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getStatus() {
        return status;
    }
    /**
    * [状态(草稿 生效 已发布)_Set方法]
    * @author:
    * @param args
    */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * [问卷号绑定的相关问题ID_Get方法]
     * @author:
     */
    @Column(name = "BSDICSELECTID", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getBsdicselectid() {
		return bsdicselectid;
	}
	
    /**
     * [问卷号绑定的相关问题ID_Set方法]
     * @author:
     * @param args
     */
	public void setBsdicselectid(String bsdicselectid) {
		this.bsdicselectid = bsdicselectid;
	}
	
	
	 /**
     * [公有私有_Get方法]
     * @author:
     */
    @Column(name = "ISPUBLIC", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getIspublic() {
		return ispublic;
	}
    
    /**
     * [公有私有D_Set方法]
     * @author:
     * @param args
     */
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
    
	@Column(name = "CREATEBY", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	@Column(name = "CREATEBYNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCreatebyname() {
		return createbyname;
	}

	public void setCreatebyname(String createbyname) {
		this.createbyname = createbyname;
	}
	
	 /**
     * [问卷号绑定的短信、时长等表ID_Get方法]
     * @author:
     */
    @Column(name = "BSDICSELEINFOID", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getBsdicseleinfoid() { 
		return bsdicseleinfoid;
	}
	
    /**
     * [问卷号绑定的短信、时长等表ID_Set方法]
     * @author:
     * @param args
     */
	public void setBsdicseleinfoid(String bsdicseleinfoid) {
		this.bsdicseleinfoid = bsdicseleinfoid;
	}
	
	
	/**
     * [群发短信返回内容ID_Get方法]
     * @author:
     */
    @Column(name = "PROJECTID", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getProjectid() {
		return projectid;
	}
	
	
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public KhzsQuestionnaire getModel() {
		return model;
	}
	public void setModel(KhzsQuestionnaire model) {
		this.model = model;
	}
	
	
	
	
	
    


}
