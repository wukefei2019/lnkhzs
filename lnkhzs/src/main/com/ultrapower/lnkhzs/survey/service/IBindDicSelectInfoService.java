package com.ultrapower.lnkhzs.survey.service;

import com.ultrapower.lnkhzs.survey.model.BindDicSelectInfo;

public interface IBindDicSelectInfoService {
	
	BindDicSelectInfo getSelectMain(String id);
	void delectDicSelectInfo(String id);

}
