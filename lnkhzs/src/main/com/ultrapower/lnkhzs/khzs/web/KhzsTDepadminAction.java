package com.ultrapower.lnkhzs.khzs.web;

import com.ultrapower.lnkhzs.base.web.BaseAction;
import com.ultrapower.lnkhzs.khzs.model.KhzsTDepadmin;
import com.ultrapower.lnkhzs.khzs.service.IKhzsTDepadminService;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsTDepadminAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private IKhzsTDepadminService khzsTDepadminService;
    
    public KhzsTDepadmin khzsTDepadmin;
    
    public void setKhzsTDepadminService(IKhzsTDepadminService khzsTDepadminService) {
		this.khzsTDepadminService = khzsTDepadminService;
	}

	public KhzsTDepadmin getKhzsTDepadmin() {
		return khzsTDepadmin;
	}

	public void setKhzsTDepadmin(KhzsTDepadmin khzsTDepadmin) {
		this.khzsTDepadmin = khzsTDepadmin;
	}

	public void saveDepAdmin(){
		try {
			if(khzsTDepadminService.isExist(khzsTDepadmin)){
				khzsTDepadminService.save(khzsTDepadmin);
				this.renderText("success");
			}else{
				this.renderText("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void deleteDepAdmin(){
		khzsTDepadminService.deleteDepAdmin(khzsTDepadmin.getDepid());
		this.renderText("success");
	}
	
}
