package com.ultrapower.lnkhzs.survey.web;



import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireService;
import com.ultrapower.lnkhzs.survey.service.IKhzsRelationshipService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [问卷题库关系表_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsRelationshipAction extends BaseAction {

    private static final long serialVersionUID = -1L;
    
    //题库id 加,,
    private String cheID;
    
    //问卷id
    private String wjId;
    
    private KhzsRelationship khzsRelationship;
    
    private IKhzsRelationshipService khzsRelationshipService;
    

	public KhzsRelationship getKhzsRelationship() {
		return khzsRelationship;
	}


	public void setKhzsRelationship(KhzsRelationship khzsRelationship) {
		this.khzsRelationship = khzsRelationship;
	}


    public void setKhzsRelationshipService(IKhzsRelationshipService khzsRelationshipService) {
        this.khzsRelationshipService = khzsRelationshipService;
    }
    

    
    public String getWjId() {
		return wjId;
	}
	public void setWjId(String wjId) {
		this.wjId = wjId;
	}


	public String getCheID() {
		return cheID;
	}


	public void setCheID(String cheID) {
		this.cheID = cheID;
	}


	//关系表删除
    public void deleteDywj(){
    	khzsRelationshipService.deleteDywj(khzsRelationship.getId());
    	this.renderText("success");
    }
    
    
  //关系表新增数据
    public void addGxtk(){
    	khzsRelationshipService.saveList(cheID,wjId);
    	renderText("success");
    }


}
