package com.ultrapower.lnkhzs.tsgd.service;

import java.util.Map;

import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.tsgd.model.SWork;
import com.ultrapower.lnkhzs.tsgd.model.WFresend;

public interface SWorkService {
	
	boolean addSend(SWork sWork);
	
	SWork addSendReturnDemo(SWork sWork);

	SWork getDetailById(SWork sWork);

	void delProcess(String id);


	boolean addInsert(SWork sWork);

	boolean addFilesNew(String fileid, String uploadFilePath);


	
	
	
}
