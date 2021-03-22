package com.ultrapower.lnkhzs.survey.model;

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
@Entity
@Table(name = "ZL_KHZS_QUESTIONNAIRE_STYLE")
public class KhzsQuestionnaireStyle implements ICommonModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 611616198559617187L;
	

	/**
     *问卷风格id_字段 
     */
    private String id;

    /**
     *问卷id_字段 
     */
    private String questionnaireid;

    /**
     *背景图片_字段 
     */
    private String background;
    
    /**
     *上边框大小_字段 
     */
    private String topsize;

    /**
     *上边框颜色_字段 
     */
    private String topcolor;
    
    /**
     * 下边框大小_字段
     */
    private String bottomsize;
    
    /**
     * 下边框颜色
     */
    private String bottomcolor;
    
    /**
	 * 左边框大小
	 */
	private String leftsize;
	
	/**
	 * 左边框颜色
	 */
	private String leftcolor;
	

	/**
	 * 右边框大小
	 */
	private String rightsize;
	
	/**
	 * 右边框颜色
	 */
	private String rightcolor;
	
	/**
	 * 标题字体
	 */
	private String fontfamily;
	
	/**
	 * 标题大小
	 */
	private String fontsize;
	
	/**
	 * 标题位置
	 */
	private String fontlocation;
	
	/**
	 * 标题是否加粗
	 */
	private String fontweight;
	
	/**
	 * 各题目字体
	 */
	private String fontfamilycom;
	
	/**
	 * 各题目大小
	 */
	private String fontsizecom;
	
	/**
	 * 各题目位置
	 */
	private String fontlocationcom;
	
	/**
	 * 各题目是否加粗
	 */
	private String fontweightcom;
	
	/**
	 * 背景全量json
	 */
	private String backgroundlist;
	
	/**
	 * 上边框样式全量json
	 */
	private String toplist;
	
	/**
	 * 下边框样式全量json
	 */
	private String bottomlist;
	
	/**
	 * 左边框样式全量json
	 */
	private String leftlist;
	
	/**
	 * 右边框样式全量json
	 */
	private String rightlist;
	
	/**
	 * 标题字体全量json
	 */
	private String fontfamilylist;
	
	/**
	 * 标题位置全量json
	 */
	private String fontlocationlist;
	
	/**
	 * 标题加粗全量json
	 */
	private String fontweightlist;
	
	/**
	 * 各题目字体全量json
	 */
	private String fontfamilycomlist;
	
	/**
	 * 各题目位置全量json
	 */
	private String fontlocationcomlist;
	
	/**
	 * 各题目加粗全量json
	 */
	private String fontweightcomlist;
	



    /**
     * [问卷风格id_Get方法]
     * @author:
     */
    @Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getId() {
        return id;
    }
    /**
    * [问卷风格id_Set方法]
    * @author:
    * @param args
    */
    public void setId(String id) {
        this.id = id;
    }
    

    @Column(name = "QUESTIONNAIREID", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getQuestionnaireid() {
		return questionnaireid;
	}
    
	public void setQuestionnaireid(String questionnaireid) {
		this.questionnaireid = questionnaireid;
	}
	
	@Column(name = "BACKGROUND", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getBackground() {
		return background;
	}
	
	public void setBackground(String background) {
		this.background = background;
	}
	
	@Column(name = "TOPSIZE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getTopsize() {
		return topsize;
	}
	
	public void setTopsize(String topsize) {
		this.topsize = topsize;
	}
	
	@Column(name = "TOPCOLOR", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getTopcolor() {
		return topcolor;
	}
	
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	
	@Column(name = "BOTTOMSIZE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getBottomsize() {
		return bottomsize;
	}
	
	public void setBottomsize(String bottomsize) {
		this.bottomsize = bottomsize;
	}
	
	@Column(name = "BOTTOMCOLOR", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getBottomcolor() {
		return bottomcolor;
	}
	
	public void setBottomcolor(String bottomcolor) {
		this.bottomcolor = bottomcolor;
	}
	
	@Column(name = "LEFTSIZE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getLeftsize() {
		return leftsize;
	}
	
	public void setLeftsize(String leftsize) {
		this.leftsize = leftsize;
	}
	
	@Column(name = "LEFTCOLOR", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getLeftcolor() {
		return leftcolor;
	}
	
	public void setLeftcolor(String leftcolor) {
		this.leftcolor = leftcolor;
	}
	
	@Column(name = "RIGHTSIZE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getRightsize() {
		return rightsize;
	}
	
	public void setRightsize(String rightsize) {
		this.rightsize = rightsize;
	}
	
	@Column(name = "RIGHTCOLOR", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getRightcolor() {
		return rightcolor;
	}
	
	public void setRightcolor(String rightcolor) {
		this.rightcolor = rightcolor;
	}
	
	@Column(name = "FONTFAMILY", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFontfamily() {
		return fontfamily;
	}
	
	public void setFontfamily(String fontfamily) {
		this.fontfamily = fontfamily;
	}
	
	@Column(name = "FONTSIZE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFontsize() {
		return fontsize;
	}
	
	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}
	
	@Column(name = "FONTLOCATION", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFontlocation() {
		return fontlocation;
	}
	
	public void setFontlocation(String fontlocation) {
		this.fontlocation = fontlocation;
	}
	
	@Column(name = "FONTWEIGHT", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFontweight() {
		return fontweight;
	}
	
	public void setFontweight(String fontweight) {
		this.fontweight = fontweight;
	}
	
	@Column(name = "FONTFAMILYCOM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFontfamilycom() {
		return fontfamilycom;
	}
	
	public void setFontfamilycom(String fontfamilycom) {
		this.fontfamilycom = fontfamilycom;
	}
	
	@Column(name = "FONTSIZECOM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFontsizecom() {
		return fontsizecom;
	}
	
	public void setFontsizecom(String fontsizecom) {
		this.fontsizecom = fontsizecom;
	}
	
	@Column(name = "FONTLOCATIONCOM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFontlocationcom() {
		return fontlocationcom;
	}
	
	public void setFontlocationcom(String fontlocationcom) {
		this.fontlocationcom = fontlocationcom;
	}
	
	@Column(name = "FONTWEIGHTCOM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFontweightcom() {
		return fontweightcom;
	}
	
	public void setFontweightcom(String fontweightcom) {
		this.fontweightcom = fontweightcom;
	}
	
	@Column(name = "BACKGROUNDLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getBackgroundlist() {
		return backgroundlist;
	}
	
	public void setBackgroundlist(String backgroundlist) {
		this.backgroundlist = backgroundlist;
	}
	
	@Column(name = "TOPLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getToplist() {
		return toplist;
	}
	
	public void setToplist(String toplist) {
		this.toplist = toplist;
	}
	
	@Column(name = "BOTTOMLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getBottomlist() {
		return bottomlist;
	}
	
	public void setBottomlist(String bottomlist) {
		this.bottomlist = bottomlist;
	}
	
	@Column(name = "LEFTLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getLeftlist() {
		return leftlist;
	}
	
	public void setLeftlist(String leftlist) {
		this.leftlist = leftlist;
	}
	
	@Column(name = "RIGHTLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getRightlist() {
		return rightlist;
	}
	
	public void setRightlist(String rightlist) {
		this.rightlist = rightlist;
	}
	
	@Column(name = "FONTFAMILYLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getFontfamilylist() {
		return fontfamilylist;
	}
	
	public void setFontfamilylist(String fontfamilylist) {
		this.fontfamilylist = fontfamilylist;
	}
	
	@Column(name = "FONTLOCATIONLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getFontlocationlist() {
		return fontlocationlist;
	}
	
	public void setFontlocationlist(String fontlocationlist) {
		this.fontlocationlist = fontlocationlist;
	}
	
	@Column(name = "FONTWEIGHTLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getFontweightlist() {
		return fontweightlist;
	}
	
	public void setFontweightlist(String fontweightlist) {
		this.fontweightlist = fontweightlist;
	}
	
	@Column(name = "FONTFAMILYCOMLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getFontfamilycomlist() {
		return fontfamilycomlist;
	}
	
	public void setFontfamilycomlist(String fontfamilycomlist) {
		this.fontfamilycomlist = fontfamilycomlist;
	}
	
	@Column(name = "FONTLOCATIONCOMLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getFontlocationcomlist() {
		return fontlocationcomlist;
	}
	
	public void setFontlocationcomlist(String fontlocationcomlist) {
		this.fontlocationcomlist = fontlocationcomlist;
	}
	
	@Column(name = "FONTWEIGHTCOMLIST", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getFontweightcomlist() {
		return fontweightcomlist;
	}
	
	public void setFontweightcomlist(String fontweightcomlist) {
		this.fontweightcomlist = fontweightcomlist;
	}

    
    

}
