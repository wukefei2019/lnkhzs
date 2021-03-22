package com.ultrapower.lnkhzs.survey.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaireStyle;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireStyleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
public class KhzsQuestionnaireStyleMgrImpl implements IKhzsQuestionnaireStyleService {

	private IDao<KhzsQuestionnaireStyle> khzsQuestionnaireStyleDao;

	public IDao<KhzsQuestionnaireStyle> getKhzsQuestionnaireStyleDao() {
		return khzsQuestionnaireStyleDao;
	}

	public void setKhzsQuestionnaireStyleDao(IDao<KhzsQuestionnaireStyle> khzsQuestionnaireStyleDao) {
		this.khzsQuestionnaireStyleDao = khzsQuestionnaireStyleDao;
	}

	@Override
	public Boolean SaveOtherSet(String myid, String backgroundList, String mtop, String mbottom, String mleft,
			String mright, String fontFamily, String fontSize, String fontLocation, String fontWeight,
			String fontFamily_com, String fontSize_com, String fontLocation_com, String fontWeight_com, Boolean saveOrUpdate) {

		
		int executeUpdate = khzsQuestionnaireStyleDao
				.executeUpdate("delete from KhzsQuestionnaireStyle where questionnaireid =?", myid);
		
		JSONArray jaBackgroundList = JSONArray.fromObject(backgroundList);
		JSONObject joMtop = JSONObject.fromObject(mtop);
		JSONObject joMbottom = JSONObject.fromObject(mbottom);
		JSONObject joMleft = JSONObject.fromObject(mleft);
		JSONObject joMright = JSONObject.fromObject(mright);
		JSONArray jaFontFamily = JSONArray.fromObject(fontFamily);
		JSONArray jaFontLocation = JSONArray.fromObject(fontLocation);
		JSONArray jaFontWeight = JSONArray.fromObject(fontWeight);
		JSONArray jaFontFamily_com = JSONArray.fromObject(fontFamily_com);
		JSONArray jaFontLocation_com = JSONArray.fromObject(fontLocation_com);
		JSONArray jaFontWeight_com = JSONArray.fromObject(fontWeight_com);

		KhzsQuestionnaireStyle khzsQuestionnaireStyle = new KhzsQuestionnaireStyle();
		String uuid = UUIDGenerator.getUUIDoffSpace();// id
		String tbackground = "";
		for (int i = 0; i < jaBackgroundList.size(); i++) {
			if (!jaBackgroundList.getJSONObject(i).getString("style1").isEmpty()) {
				tbackground = jaBackgroundList.getJSONObject(i).get("src1").toString();
				break;
			} else if (!jaBackgroundList.getJSONObject(i).getString("style2").isEmpty()) {
				tbackground = jaBackgroundList.getJSONObject(i).getString("src2");
				break;
			}
		}
		String ttopsize = joMtop.getString("number");
		String ttopcolor = joMtop.getString("color");
		String tbottomsize = joMbottom.getString("number");
		String tbottomcolor = joMbottom.getString("color");
		String tleftsize = joMleft.getString("number");
		String tleftcolor = joMleft.getString("color");
		String trightsize = joMright.getString("number");
		String trightcolor = joMright.getString("color");
		String tfontfamily = "";
		for (int i = 0; i < jaFontFamily.size(); i++) {
			if ((boolean) jaFontFamily.getJSONObject(i).get("pick")) {
				tfontfamily = jaFontFamily.getJSONObject(i).getString("name");
				break;
			}
		}
		String tfontsize = fontSize;
		String tfontlocation = "";
		for (int i = 0; i < jaFontLocation.size(); i++) {
			if ((boolean) jaFontLocation.getJSONObject(i).get("pick")) {
				tfontlocation = jaFontLocation.getJSONObject(i).getString("inname");
				break;
			}
		}
		String tfontweight = "";
		for (int i = 0; i < jaFontWeight.size(); i++) {
			if ((boolean) jaFontWeight.getJSONObject(i).get("pick")) {
				tfontweight = jaFontWeight.getJSONObject(i).getString("inname");
				break;
			}
		}
		String tfontfamilycom = "";
		for (int i = 0; i < jaFontFamily_com.size(); i++) {
			if ((boolean) jaFontFamily_com.getJSONObject(i).get("pick")) {
				tfontfamilycom = jaFontFamily_com.getJSONObject(i).getString("name");
				break;
			}
		}
		String tfontsizecom = fontSize_com;
		String tfontlocationcom = "";
		for (int i = 0; i < jaFontLocation_com.size(); i++) {
			if ((boolean) jaFontLocation_com.getJSONObject(i).get("pick")) {
				tfontlocationcom = jaFontLocation_com.getJSONObject(i).getString("inname");
				break;
			}
		}
		String tfontweightcom = "";
		for (int i = 0; i < jaFontWeight_com.size(); i++) {
			if ((boolean) jaFontWeight_com.getJSONObject(i).get("pick")) {
				tfontweightcom = jaFontWeight_com.getJSONObject(i).getString("inname");
				break;
			}
		}

		khzsQuestionnaireStyle.setId(uuid);
		khzsQuestionnaireStyle.setQuestionnaireid(myid);
		khzsQuestionnaireStyle.setBackground(tbackground);
		khzsQuestionnaireStyle.setTopsize(ttopsize);
		khzsQuestionnaireStyle.setTopcolor(ttopcolor);
		khzsQuestionnaireStyle.setBottomsize(tbottomsize);
		khzsQuestionnaireStyle.setBottomcolor(tbottomcolor);
		khzsQuestionnaireStyle.setLeftsize(tleftsize);
		khzsQuestionnaireStyle.setLeftcolor(tleftcolor);
		khzsQuestionnaireStyle.setRightsize(trightsize);
		khzsQuestionnaireStyle.setRightcolor(trightcolor);
		khzsQuestionnaireStyle.setFontfamily(tfontfamily);
		khzsQuestionnaireStyle.setFontsize(tfontsize);
		khzsQuestionnaireStyle.setFontlocation(tfontlocation);
		khzsQuestionnaireStyle.setFontweight(tfontweight);
		khzsQuestionnaireStyle.setFontfamilycom(tfontfamilycom);
		khzsQuestionnaireStyle.setFontsizecom(tfontsizecom);
		khzsQuestionnaireStyle.setFontlocationcom(tfontlocationcom);
		khzsQuestionnaireStyle.setFontweightcom(tfontweightcom);
		khzsQuestionnaireStyle.setBackgroundlist(backgroundList);
		khzsQuestionnaireStyle.setToplist(mtop);
		khzsQuestionnaireStyle.setBottomlist(mbottom);
		khzsQuestionnaireStyle.setLeftlist(mleft);
		khzsQuestionnaireStyle.setRightlist(mright);
		khzsQuestionnaireStyle.setFontfamilylist(fontFamily);
		khzsQuestionnaireStyle.setFontlocationlist(fontLocation);
		khzsQuestionnaireStyle.setFontweightlist(fontWeight);
		khzsQuestionnaireStyle.setFontfamilycomlist(fontFamily_com);
		khzsQuestionnaireStyle.setFontlocationcomlist(fontLocation_com);
		khzsQuestionnaireStyle.setFontweightcomlist(fontWeight_com);

		khzsQuestionnaireStyleDao.save(khzsQuestionnaireStyle);
		
		/*if(saveOrUpdate)
			khzsQuestionnaireStyleDao.save(khzsQuestionnaireStyle);
		else
			khzsQuestionnaireStyleDao.saveOrUpdate(khzsQuestionnaireStyle);*/

		return true;
	}

	@Override
	public List<Map<Object, Object>> getOtherSet(String id) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		List<KhzsQuestionnaireStyle> find = khzsQuestionnaireStyleDao
				.find("from KhzsQuestionnaireStyle where QUESTIONNAIREID = ? ", id);
		if (find.size() == 0) {
			find = khzsQuestionnaireStyleDao.find("from KhzsQuestionnaireStyle where ID = ? ",
					"2020040100000001");
		}
		JSONArray backgroundList = JSONArray.fromObject(find.get(0).getBackgroundlist());
		JSONObject mtop = JSONObject.fromObject(find.get(0).getToplist());
		JSONObject mbottom = JSONObject.fromObject(find.get(0).getBottomlist());
		JSONObject mleft = JSONObject.fromObject(find.get(0).getLeftlist());
		JSONObject mright = JSONObject.fromObject(find.get(0).getRightlist());
		JSONArray fontFamily = JSONArray.fromObject(find.get(0).getFontfamilylist());
		int fontSize = Integer.parseInt(find.get(0).getFontsize());
		JSONArray fontLocation = JSONArray.fromObject(find.get(0).getFontlocationlist());
		JSONArray fontWeight = JSONArray.fromObject(find.get(0).getFontweightlist());
		JSONArray fontFamily_com = JSONArray.fromObject(find.get(0).getFontfamilycomlist());
		int fontSize_com = Integer.parseInt(find.get(0).getFontsizecom());
		JSONArray fontLocation_com = JSONArray.fromObject(find.get(0).getFontlocationcomlist());
		JSONArray fontWeight_com = JSONArray.fromObject(find.get(0).getFontweightcomlist());
		map.put("backgroundList", backgroundList);
		map.put("mtop", mtop);
		map.put("mbottom", mbottom);
		map.put("mright", mright);
		map.put("mleft", mleft);
		map.put("fontFamily", fontFamily);
		map.put("fontSize", fontSize);
		map.put("fontLocation", fontLocation);
		map.put("fontWeight", fontWeight);
		map.put("fontFamily_com", fontFamily_com);
		map.put("fontSize_com", fontSize_com);
		map.put("fontLocation_com", fontLocation_com);
		map.put("fontWeight_com", fontWeight_com);
		list.add(map);

		return list;
	}

	@Override
	public Map<Object, Object> getOtherSetToShow(String id) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<KhzsQuestionnaireStyle> find = khzsQuestionnaireStyleDao
				.find("from KhzsQuestionnaireStyle where QUESTIONNAIREID = ? ", id);
		if (find.size() == 0) {
			find = khzsQuestionnaireStyleDao.find("from KhzsQuestionnaireStyle where ID = ? ",
					"2020040100000001");
		}
		map.put("backgroundList", find.get(0).getBackground());
		map.put("topsize", find.get(0).getTopsize());
		map.put("topcolor", find.get(0).getTopcolor());
		map.put("bottomsize", find.get(0).getBottomsize());
		map.put("bottomcolor", find.get(0).getBottomcolor());
		map.put("leftsize", find.get(0).getLeftsize());
		map.put("leftcolor", find.get(0).getLeftcolor());
		map.put("rightsize", find.get(0).getRightsize());
		map.put("rightcolor", find.get(0).getRightcolor());
		map.put("fontfamily", find.get(0).getFontfamily());
		map.put("fontsize", find.get(0).getFontsize());
		map.put("fontlocation", find.get(0).getFontlocation());
		map.put("fontweight", find.get(0).getFontweight());
		map.put("fontfamilycom", find.get(0).getFontfamilycom());
		map.put("fontsizecom", find.get(0).getFontsizecom());
		map.put("fontlocationcom", find.get(0).getFontlocationcom());
		map.put("fontweightcom", find.get(0).getFontweightcom());
		
		
		return map;
	}

}
