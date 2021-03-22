package com.ultrapower.lnkhzs.khzs.model;

import com.ultrapower.omcs.base.model.IOmcsModel;
import com.ultrapower.omcs.common.model.ICommonModel;

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
@Table(name = "ZL_KHZS_T_MAIN_BAK")
public class KhzsTMainBak implements ICommonModel {
    /**
     *附件_字段 
     */
    private String attachment;

    /**
     *发起时间_字段 
     */
    private String createtime;

    /**
     *发起人机构_字段 
     */
    private String depname;

    /**
     *问题描述_字段 
     */
    private String description;

    /**
     *流程状态_字段 
     */
    private String flowstatus;

    /**
     *发起人全名_字段 
     */
    private String fullname;

    /**
     *序号_字段 
     */
    private String idx;

    /**
     *发起人登入名_字段 
     */
    private String loginname;

    /**
     *处理人机构_字段 
     */
    private String nextdepname;

    /**
     *下一级处理人全名_字段 
     */
    private String nextfullname;

    /**
     *下一级处理人登入名_字段 
     */
    private String nextloginname;

    /**
     *null_字段 
     */
    private String pid;

    /**
     *待解决问题_字段 
     */
    private String question;

    /**
     *问题类型_字段 
     */
    private String questiontype;

    /**
     *备注_字段 
     */
    private String remark;

    /**
     *解决方案_字段 
     */
    private String solution;

    /**
     *问题来源_字段 
     */
    private String source;

    /**
     *主题_字段 
     */
    private String theme;

    /**
     *类型（1:典型投诉案例;2:一线员工建议/意见）_字段 
     */
    private String type;
    
    private String dutydept;
    private String dutydeptid;
    private String dutyuser;
    private String dutyuserid;

    private String example;
    
    private String solution2;
    
    
    
    @Column(name = "EXAMPLE")
    public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	
	@Column(name = "SOLUTION2")
	public String getSolution2() {
		return solution2;
	}
	public void setSolution2(String solution2) {
		this.solution2 = solution2;
	}
	@Column(name = "DUTYDEPT")
    public String getDutydept() {
		return dutydept;
	}
	public void setDutydept(String dutydept) {
		this.dutydept = dutydept;
	}
	@Column(name = "DUTYDEPTID")
	public String getDutydeptid() {
		return dutydeptid;
	}
	public void setDutydeptid(String dutydeptid) {
		this.dutydeptid = dutydeptid;
	}
	
	@Column(name = "DUTYUSER")
	public String getDutyuser() {
		return dutyuser;
	}
	public void setDutyuser(String dutyuser) {
		this.dutyuser = dutyuser;
	}
	
	@Column(name = "DUTYUSERID")
	public String getDutyuserid() {
		return dutyuserid;
	}
	public void setDutyuserid(String dutyuserid) {
		this.dutyuserid = dutyuserid;
	}
	/**
     * [附件_Get方法]
     * @author:
     */
    @Column(name = "ATTACHMENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getAttachment() {
        return attachment;
    }
    /**
    * [附件_Set方法]
    * @author:
    * @param args
    */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }


    /**
     * [发起时间_Get方法]
     * @author:
     */
    @Column(name = "CREATETIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getCreatetime() {
        return createtime;
    }
    /**
    * [发起时间_Set方法]
    * @author:
    * @param args
    */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    /**
     * [发起人机构_Get方法]
     * @author:
     */
    @Column(name = "DEPNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getDepname() {
        return depname;
    }
    /**
    * [发起人机构_Set方法]
    * @author:
    * @param args
    */
    public void setDepname(String depname) {
        this.depname = depname;
    }


    /**
     * [问题描述_Get方法]
     * @author:
     */
    @Column(name = "DESCRIPTION", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getDescription() {
        return description;
    }
    /**
    * [问题描述_Set方法]
    * @author:
    * @param args
    */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * [流程状态_Get方法]
     * @author:
     */
    @Column(name = "FLOWSTATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getFlowstatus() {
        return flowstatus;
    }
    /**
    * [流程状态_Set方法]
    * @author:
    * @param args
    */
    public void setFlowstatus(String flowstatus) {
        this.flowstatus = flowstatus;
    }


    /**
     * [发起人全名_Get方法]
     * @author:
     */
    @Column(name = "FULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getFullname() {
        return fullname;
    }
    /**
    * [发起人全名_Set方法]
    * @author:
    * @param args
    */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    /**
     * [序号_Get方法]
     * @author:
     */
    @Column(name = "IDX", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getIdx() {
        return idx;
    }
    /**
    * [序号_Set方法]
    * @author:
    * @param args
    */
    public void setIdx(String idx) {
        this.idx = idx;
    }


    /**
     * [发起人登入名_Get方法]
     * @author:
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getLoginname() {
        return loginname;
    }
    /**
    * [发起人登入名_Set方法]
    * @author:
    * @param args
    */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }


    /**
     * [处理人机构_Get方法]
     * @author:
     */
    @Column(name = "NEXTDEPNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getNextdepname() {
        return nextdepname;
    }
    /**
    * [处理人机构_Set方法]
    * @author:
    * @param args
    */
    public void setNextdepname(String nextdepname) {
        this.nextdepname = nextdepname;
    }


    /**
     * [下一级处理人全名_Get方法]
     * @author:
     */
    @Column(name = "NEXTFULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getNextfullname() {
        return nextfullname;
    }
    /**
    * [下一级处理人全名_Set方法]
    * @author:
    * @param args
    */
    public void setNextfullname(String nextfullname) {
        this.nextfullname = nextfullname;
    }


    /**
     * [下一级处理人登入名_Get方法]
     * @author:
     */
    @Column(name = "NEXTLOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getNextloginname() {
        return nextloginname;
    }
    /**
    * [下一级处理人登入名_Set方法]
    * @author:
    * @param args
    */
    public void setNextloginname(String nextloginname) {
        this.nextloginname = nextloginname;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "assigned")
    @Column(name = "PID", unique=true, nullable=false, insertable=true, updatable=true, length=50 )
    public String getPid() {
        return pid;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setPid(String pid) {
        this.pid = pid;
    }


    /**
     * [待解决问题_Get方法]
     * @author:
     */
    @Column(name = "QUESTION", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getQuestion() {
        return question;
    }
    /**
    * [待解决问题_Set方法]
    * @author:
    * @param args
    */
    public void setQuestion(String question) {
        this.question = question;
    }


    /**
     * [问题类型_Get方法]
     * @author:
     */
    @Column(name = "QUESTIONTYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getQuestiontype() {
        return questiontype;
    }
    /**
    * [问题类型_Set方法]
    * @author:
    * @param args
    */
    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype;
    }


    /**
     * [备注_Get方法]
     * @author:
     */
    @Column(name = "REMARK", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getRemark() {
        return remark;
    }
    /**
    * [备注_Set方法]
    * @author:
    * @param args
    */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * [解决方案_Get方法]
     * @author:
     */
    @Column(name = "SOLUTION", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getSolution() {
        return solution;
    }
    /**
    * [解决方案_Set方法]
    * @author:
    * @param args
    */
    public void setSolution(String solution) {
        this.solution = solution;
    }


    /**
     * [问题来源_Get方法]
     * @author:
     */
    @Column(name = "SOURCE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getSource() {
        return source;
    }
    /**
    * [问题来源_Set方法]
    * @author:
    * @param args
    */
    public void setSource(String source) {
        this.source = source;
    }


    /**
     * [主题_Get方法]
     * @author:
     */
    @Column(name = "THEME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getTheme() {
        return theme;
    }
    /**
    * [主题_Set方法]
    * @author:
    * @param args
    */
    public void setTheme(String theme) {
        this.theme = theme;
    }


    /**
     * [类型（1:典型投诉案例;2:一线员工建议/意见）_Get方法]
     * @author:
     */
    @Column(name = "TYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getType() {
        return type;
    }
    /**
    * [类型（1:典型投诉案例;2:一线员工建议/意见）_Set方法]
    * @author:
    * @param args
    */
    public void setType(String type) {
        this.type = type;
    }


}
