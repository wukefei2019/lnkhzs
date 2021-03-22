package com.ultrapower.lnkhzs.tsgd.service;

import java.util.List;
import java.util.Map;

import com.ultrapower.lnkhzs.tsgd.model.WFresend;

public interface WFresendService {
	
	boolean addSend(WFresend wFresend);

	WFresend getDetailById(WFresend wFresend);

	WFresend getDetailByLABELCATEGORY(WFresend wFresend);
	
	Map<String, String> getInfo(String A12);

}
