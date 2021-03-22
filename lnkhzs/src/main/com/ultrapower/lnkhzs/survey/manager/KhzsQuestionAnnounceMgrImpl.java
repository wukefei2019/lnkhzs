package com.ultrapower.lnkhzs.survey.manager;

import com.ultrapower.lnkhzs.khzs.model.KhzsTDepadmin;
import com.ultrapower.lnkhzs.khzs.model.KhzsTMainBak;
import com.ultrapower.lnkhzs.survey.model.BindDicSelectInfo;
import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.model.BindDicselectSymbol;
import com.ultrapower.lnkhzs.survey.model.DwProductUserInfo;
import com.ultrapower.lnkhzs.survey.model.KhzsAnswer;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAll;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAnnounce;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionAnnounceService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireService;
import com.ultrapower.lnkhzs.survey.web.IaSMSSendAction;
import com.ultrapower.omcs.utils.DateUtils;

import jxl.write.DateFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContextEvent;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bmc.thirdparty.org.apache.commons.beanutils.BeanUtils;
import com.sshtools.j2ssh.net.HttpRequest;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.constants.Constants;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.ftpclient.FtpClient;
import com.ultrapower.eoms.ultrasm.model.DepInfo;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [问卷_接口]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public  class KhzsQuestionAnnounceMgrImpl implements IKhzsQuestionAnnounceService {


	private static final KhzsQuestionAnnounce KhzsQuestionAnnounce = null;

	private IDao<KhzsQuestionAnnounce> kzsQuestionAnnounceDao;
	
	private IDao<DepInfo> depAnnounceDao;
	
	
	

	public IDao<DepInfo> getDepAnnounceDao() {
		return depAnnounceDao;
	}

	public void setDepAnnounceDao(IDao<DepInfo> depAnnounceDao) {
		this.depAnnounceDao = depAnnounceDao;
	}
	
	public IDao<KhzsQuestionAnnounce> getKzsQuestionAnnounceDao() {
		return kzsQuestionAnnounceDao;
	}

	public void setKzsQuestionAnnounceDao(IDao<KhzsQuestionAnnounce> kzsQuestionAnnounceDao) {
		this.kzsQuestionAnnounceDao = kzsQuestionAnnounceDao;
	}
	
	
	
	/**
	 * 删除公告
	 */
		@Override
		public void delAnnounce(String id) {
			kzsQuestionAnnounceDao.removeById(id);	
			
		}
		
		
		//获取页面详细数据
				@Override
				public KhzsQuestionAnnounce queryKhzsQuestionAnnounce(String getid) {
					return kzsQuestionAnnounceDao.get(getid);
				}

			
				
				
				
		
		/**
		 * 修改公告信息
		 * @return 
		 */
				@Override
				public void editAnnounce(KhzsQuestionAnnounce khzsQuestionAnnounce) {
					if (StringUtils.isBlank(khzsQuestionAnnounce.getId())) {
						khzsQuestionAnnounce.setId(UUIDGenerator.getUUIDoffSpace());
						kzsQuestionAnnounceDao.save(khzsQuestionAnnounce);
					} else {
						kzsQuestionAnnounceDao.saveOrUpdate(khzsQuestionAnnounce);
					}

				}
			
    		
				



		
	  @Override
	  public boolean save(KhzsQuestionAnnounce khzsQuestionAnnounce) {
		try {
			if(khzsQuestionAnnounce.getId()==null||khzsQuestionAnnounce.getId().isEmpty()){
					//新增
				khzsQuestionAnnounce.setId(UUIDGenerator.getUUIDoffSpace());
				kzsQuestionAnnounceDao.save(khzsQuestionAnnounce);
			}else{
					//修改
				kzsQuestionAnnounceDao.saveOrUpdate(khzsQuestionAnnounce);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void addAnnonce(String id, com.ultrapower.lnkhzs.survey.model.KhzsQuestionAnnounce khzsQuestionAnnounce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExist(KhzsQuestionAnnounce khzsQuestionAnnounce) {
		
		KhzsQuestionAnnounce demo=kzsQuestionAnnounceDao.get(khzsQuestionAnnounce.getId());
			if(demo==null)
				return true;//新增
			else
				return false;//修改
		}

		
	}
	

	
	


		
	
	