package com.ultrapower.lnkhzs.survey.manager;

import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAll;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAllHidelist;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionAllService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionService;
import com.ultrapower.omcs.utils.DateUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionBean;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [题库_接口]
 * </p>
 * 
 * @author : lxd
 * @date : 2019/7/23
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsQuestionAllMgrImpl implements IKhzsQuestionAllService {

	private IDao<KhzsQuestionAll> khzsQuestionAllDao;

	private IDao<KhzsQuestionAllHidelist> khzsQuestionAllHidelistDao;

	private IDao<KhzsRelationship> khzsRelationshipDao;

	private IDao<KhzsQuestionnaire> KhzsQuestionnaireDao;

	public void setKhzsQuestionDao(IDao<KhzsQuestionAll> khzsQuestionAllDao) {
		this.khzsQuestionAllDao = khzsQuestionAllDao;
	}

	public void setKhzsRelationshipDao(IDao<KhzsRelationship> khzsRelationshipDao) {
		this.khzsRelationshipDao = khzsRelationshipDao;
	}

	public void setKhzsQuestionnaireDao(IDao<KhzsQuestionnaire> khzsQuestionnaireDao) {
		KhzsQuestionnaireDao = khzsQuestionnaireDao;
	}
	
	public void setKhzsQuestionAllHidelistDao(IDao<KhzsQuestionAllHidelist> khzsQuestionAllHidelistDao) {
		this.khzsQuestionAllHidelistDao = khzsQuestionAllHidelistDao;
	}

	@Override
	public void addQuestionAndShip(String myquestion, String myAsk) {
		try {
			myquestion = myquestion.replace("{", "{\"");
			myquestion = myquestion.replace(":", "\":\"");
			myquestion = myquestion.replace(",", "\",\"");
			myquestion = myquestion.replace("}", "\"}");
			myquestion = myquestion.replace("\"[", "[");
			myquestion = myquestion.replace("]\"", "]");
			myquestion = myquestion.replace("}\",\"{", "},{");
			List<Map<String, Object>> mylist = (List<Map<String, Object>>) JSONArray.parse(myquestion);
			Map<String, Object> map = new HashMap<String, Object>();

			List<Map<String, Object>> myoptions = null;// 答案
			System.out.println(mylist);
			int executeUpdate = khzsRelationshipDao
					.executeUpdate("delete from KhzsRelationship where questionnaireid =?", myAsk);
			executeUpdate = khzsQuestionAllHidelistDao
					.executeUpdate("delete from KhzsQuestionAllHidelist where questionnaireid =?", myAsk);
			for (int i = 0; i < mylist.size(); i++) {
				String name = "";// 类型：danxuan,duoxuan,shifei,jianda,danxiangdafen,tiankongjianda,juzhentiankong
				String content = "";// 问题
				String optiona = "";// 答案a
				String optionb = "";// 答案b
				String optionc = "";// 答案c
				String optiond = "";// 答案d
				String optione = "";// 答案e
				String optionf = "";// 答案f
				String optiong = "";// 答案g
				String optionh = "";// 答案h
				String optioni = "";// 答案i
				String optionj = "";// 答案g
				String optionk = "";// 答案k
				String optionl = "";// 答案l
				String optionm = "";// 答案m
				String optionn = "";// 答案n
				String optiono = "";// 答案o
				String optionp = "";// 答案p
				String isanswer = "";// 必答,非必答
				String remark = "";// 是否存在其他选项
				String ps = "";//备注
				map = mylist.get(i);
				name = map.get("name").toString();
				content = map.get("content").toString();
				isanswer = map.get("isanswer").toString();
				remark = map.get("remark").toString();
				ps = map.get("ps").toString();
				
				if(name.equals("矩阵填空")){
					JSONArray jrow = (JSONArray) map.get("optionsrow");
					JSONArray jcol = (JSONArray) map.get("optionscol");
					for(int j=0;j<jrow.size();j++){
						String options = jrow.get(j).toString();
						options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
						if(optiona.isEmpty()){
							optiona=options;
						}else{
							optiona=optiona+"#*#*#*"+options;
						}
					}
					for(int j=0;j<jcol.size();j++){
						String options = jcol.get(j).toString();
						options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
						if(optionb.isEmpty()){
							optionb=options;
						}else{
							optionb=optionb+"#*#*#*"+options;
						}
					}
				}else if(name.equals("简答")||name.equals("位置")){
					optiona=map.get("options").toString();
					if(optiona==null||optiona.equals("null")){
						optiona="";
					}
				}
				else{
					JSONArray ja = (JSONArray) map.get("options");
					for (int j = 0; j < ja.size(); j++) {
						if (j == 0) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optiona = options;
							continue;
						}
						if (j == 1) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionb = options;
							continue;
						}
						if (j == 2) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionc = options;
							continue;
						}
						if (j == 3) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optiond = options;
							continue;
						}
						if (j == 4) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optione = options;
							continue;
						}
						if (j == 5) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionf = options;
							continue;
						}
						if (j == 6) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optiong = options;
							continue;
						}
						if (j == 7) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionh = options;
							continue;
						}
						if (j == 8) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optioni = options;
							continue;
						}
						if (j == 9) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionj = options;
							continue;
						}
						if (j == 10) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionk = options;
							continue;
						}
						if (j == 11) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionl = options;
							continue;
						}
						if (j == 12) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionm = options;
							continue;
						}
						if (j == 13) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionn = options;
							continue;
						}
						if (j == 14) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optiono = options;
							continue;
						}if (j == 15) {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionp = options;
							continue;
						}
					}
				}
				
				KhzsQuestionAll khzsQuestionAll = new KhzsQuestionAll();
				khzsQuestionAll.setOptiona(optiona);
				khzsQuestionAll.setOptionb(optionb);
				khzsQuestionAll.setOptionc(optionc);
				khzsQuestionAll.setOptiond(optiond);
				khzsQuestionAll.setOptione(optione);
				khzsQuestionAll.setOptionf(optionf);
				khzsQuestionAll.setOptiong(optiong);
				khzsQuestionAll.setOptionh(optionh);
				khzsQuestionAll.setOptioni(optioni);
				khzsQuestionAll.setOptionj(optionj);
				khzsQuestionAll.setOptionk(optionk);
				khzsQuestionAll.setOptionl(optionl);
				khzsQuestionAll.setOptionm(optionm);
				khzsQuestionAll.setOptionn(optionn);
				khzsQuestionAll.setOptiono(optiono);
				khzsQuestionAll.setOptionp(optionp);
				khzsQuestionAll.setQuestion(content);
				khzsQuestionAll.setType(name);
				khzsQuestionAll.setIsanswer(isanswer);
				khzsQuestionAll.setPs(ps);
				if (remark.equals("1")) {
					khzsQuestionAll.setRemark("1");
				} else {
					khzsQuestionAll.setRemark("0");
				}
				if(name.equals("简答")){
					khzsQuestionAll.setMinnumber(map.get("minnumber").toString());
					khzsQuestionAll.setMaxnumber(map.get("maxnumber").toString());
				}
				if(name.equals("单项打分")){
					khzsQuestionAll.setMinnumber(map.get("mintext").toString());
					khzsQuestionAll.setMaxnumber(map.get("maxtext").toString());
				}
				// khzsQuestionService.editDytk(khzsQuestionAll);
				String uuid = UUIDGenerator.getUUIDoffSpace();
				khzsQuestionAll.setId(uuid);
				khzsQuestionAllDao.save(khzsQuestionAll);
				if(name.equals("单选")||name.equals("单项打分")||name.equals("判断")||name.equals("多选")){
					/*int aa = khzsQuestionAllHidelistDao
							.executeUpdate("delete from KhzsQuestionAllHidelist where questionnaireid =?", myAsk);*/
					JSONArray jahideitems = (JSONArray) map.get("hideitems");
					JSONArray jahidenum = (JSONArray) map.get("hidenum");
					for (int j = 0; j < jahideitems.size(); j++) {
						KhzsQuestionAllHidelist khzsQuestionAllHidelist = new KhzsQuestionAllHidelist();
						khzsQuestionAllHidelist.setId(UUIDGenerator.getUUIDoffSpace());
						String options = jahideitems.get(j).toString();
						String num = jahidenum.get(j).toString();//{"optitem":"3"}
						//options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
						num = num.substring(num.indexOf("\":\"") + 3, num.indexOf("\"}"));
						khzsQuestionAllHidelist.setHidequestionid(num);
						khzsQuestionAllHidelist.setQuestionid(uuid);
						khzsQuestionAllHidelist.setQuestionnaireid(myAsk);
						options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
						String[] optionsArr=options.split("\\.");
						String optionstr="";
						for(int mm=0;mm<optionsArr.length;mm++){
							switch (optionsArr[mm]) {
							case "1":
								optionstr+="A.";
								break;
							case "2":
								optionstr+="B.";
								break;
							case "3":
								optionstr+="C.";
								break;
							case "4":
								optionstr+="D.";
								break;
							case "5":
								optionstr+="E.";
								break;
							case "6":
								optionstr+="F.";
								break;
							case "7":
								optionstr+="G.";
								break;
							case "8":
								optionstr+="H.";
								break;
							case "9":
								optionstr+="I.";
								break;
							case "10":
								optionstr+="J.";
								break;
							case "11":
								optionstr+="K.";
								break;
							case "12":
								optionstr+="L.";
								break;
							case "13":
								optionstr+="M.";
								break;
							case "14":
								optionstr+="N.";
								break;
							case "15":
								optionstr+="O.";
								break;
							case "16":
								optionstr+="P.";
								break;
							default:
								break;
							}
						}
						khzsQuestionAllHidelist.setOptions(optionstr);
						khzsQuestionAllHidelistDao.save(khzsQuestionAllHidelist);
						// khzsQuestionAllHidelistDao
					}
				}
				KhzsRelationship entity = new KhzsRelationship();
				List<KhzsQuestionnaire> find = KhzsQuestionnaireDao.find("from KhzsQuestionnaire where id = ?", myAsk);
				for (int j = 0; j < find.size(); j++) {
					KhzsQuestionnaire khzsQuestionnaire = find.get(j);
					String name2 = khzsQuestionnaire.getName();// 获取问卷名
					entity.setName(name2);
				}
				entity.setCreattime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
				entity.setId(UUIDGenerator.getUUIDoffSpace());
				entity.setQuestionid(uuid);
				entity.setQuestionnaireid(myAsk);
				entity.setOrders(i + 1.0);
				khzsRelationshipDao.save(entity);// 添加到关系表
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<Object, Object>> seleByid(String id) {
		Map<Object, Object> map = null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		List<KhzsRelationship> find = khzsRelationshipDao
				.find("from KhzsRelationship where QUESTIONNAIREID = ? order by ORDERS ASC", id);
		int size = find.size();
		for (int i = 0; i < find.size(); i++) {
			map = new HashMap<Object, Object>();
			KhzsRelationship khzsRelationship = find.get(i);
			String id2 = khzsRelationship.getQuestionid();// 获取题库id
			Double orders = khzsRelationship.getOrders();// 获取关系表排序号

			map.put("id", orders);
			// 获取相关题库信息
			List<KhzsQuestionAll> find2 = khzsQuestionAllDao.find("from KhzsQuestionAll where id = ?", id2);
			int size2 = find2.size();
			for (int j = 0; j < find2.size(); j++) {
				KhzsQuestionAll khzsQuestionAll = find2.get(j);
				String question = khzsQuestionAll.getQuestion();// 获取题目
				String type = khzsQuestionAll.getType();// 获取题型
				String isanswer = khzsQuestionAll.getIsanswer();
				String remark = khzsQuestionAll.getRemark();
				String ps=khzsQuestionAll.getPs()==null?"":khzsQuestionAll.getPs();
				
				
				String optiona = khzsQuestionAll.getOptiona();
				String optionb = khzsQuestionAll.getOptionb();
				String optionc = khzsQuestionAll.getOptionc();
				String optiond = khzsQuestionAll.getOptiond();
				String optione = khzsQuestionAll.getOptione();
				String optionf = khzsQuestionAll.getOptionf();
				String optiong = khzsQuestionAll.getOptiong();
				String optionh = khzsQuestionAll.getOptionh();
				String optioni = khzsQuestionAll.getOptioni();
				String optionj = khzsQuestionAll.getOptionj();
				String optionk = khzsQuestionAll.getOptionk();
				String optionl = khzsQuestionAll.getOptionl();
				String optionm = khzsQuestionAll.getOptionm();
				String optionn = khzsQuestionAll.getOptionn();
				String optiono = khzsQuestionAll.getOptiono();
				String optionp = khzsQuestionAll.getOptionp();
				
				if (type.equals("简答")||type.equals("位置")) {
					map.put("options", optiona==null?"":optiona);
				}else if(type.equals("矩阵填空")){
					String[] arrrow=optiona.split("#\\*#\\*#\\*");
					String[] arrcol=optionb.split("#\\*#\\*#\\*");
					List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
					Map<Object, Object> map2 = null;
					for(int rowi=0;rowi<arrrow.length;rowi++){
						map2 = new HashMap<Object, Object>();
						map2.put("optText", arrrow[rowi]);
						list1.add(map2);
					}
					map.put("optionsrow", list1);
					
					list1 = new ArrayList<Map<Object, Object>>();
					for(int coli=0;coli<arrcol.length;coli++){
						map2 = new HashMap<Object, Object>();
						map2.put("optText", arrcol[coli]);
						list1.add(map2);
					}
					map.put("optionscol", list1);
				}else {
					List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
					Map<Object, Object> map2 = null;
					map2 = new HashMap<Object, Object>();
					if (optiona != null) {
						if (optiona.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optiona);
							list1.add(map2);
						}

					}
					if (optionb != null) {
						if (optionb.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionb);
							list1.add(map2);
						}

					}
					if (optionc != null) {
						if (optionc.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionc);
							list1.add(map2);
						}

					}
					if (optiond != null) {
						if (optiond.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optiond);
							list1.add(map2);
						}

					}
					if (optione != null) {
						if (optione.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optione);
							list1.add(map2);
						}

					}
					if (optionf != null) {
						if (optionf.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionf);
							list1.add(map2);
						}

					}
					if (optiong != null) {
						if (optiong.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optiong);
							list1.add(map2);
						}

					}
					if (optionh != null) {
						if (optionh.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionh);
							list1.add(map2);
						}

					}
					if (optioni != null) {
						if (optioni.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optioni);
							list1.add(map2);
						}

					}
					if (optionj != null) {
						if (optionj.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionj);
							list1.add(map2);
						}

					}
					if (optionk != null) {
						if (optionk.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionk);
							list1.add(map2);
						}

					}
					if (optionl != null) {
						if (optionl.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionl);
							list1.add(map2);
						}
					}
					if (optionm != null) {
						if (optionm.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionm);
							list1.add(map2);
						}

					}
					if (optionn != null) {
						if (optionn.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionn);
							list1.add(map2);
						}

					}
					if (optiono != null) {
						if (optiono.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optiono);
							list1.add(map2);
						}

					}
					if (optionp != null) {
						if (optionp.equals("其他"))
							;
						else {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionp);
							list1.add(map2);
						}

					}
					map.put("options", list1);
				}
				map.put("name", type);
				map.put("content", question);
				map.put("isanswer", isanswer);
				map.put("remark", remark);
				map.put("id", i);
				map.put("ps", ps);
				if (type.equals("单选")||type.equals("单项打分")||type.equals("判断")||type.equals("多选")) {
					if(type.equals("单选")){
						map.put("type", "danxuan");
						map.put("comp", "danxuan");
					}
					if(type.equals("单项打分")){
						map.put("type", "danxiangdafen");
						map.put("comp", "danxiangdafen");
						map.put("mintext",khzsQuestionAll.getMinnumber());
						map.put("maxtext",khzsQuestionAll.getMaxnumber());
					}
					if(type.equals("判断")){
						map.put("type", "shifei");
						map.put("comp", "shifei");
					}
					if(type.equals("多选")) {
						map.put("type", "duoxuan");
						map.put("comp", "duoxuan");
					}
					List<KhzsQuestionAllHidelist> find3 = khzsQuestionAllHidelistDao.find("from KhzsQuestionAllHidelist where questionnaireid = ? AND questionid = ? ORDER BY OPTIONS asc", id,id2);
					List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
					List<Map<Object, Object>> list3 = new ArrayList<Map<Object, Object>>();
					Map<Object, Object> map3 = null;
					Map<Object, Object> map4 = null;
					for(int mm=0;mm<find3.size();mm++){
						//选项
						map3 = new HashMap<Object, Object>();
						String myoption=find3.get(mm).getOptions();
						String[] myoptionArr=myoption.split("\\.");
						String myoptionstr="";
						for(int nn=0;nn<myoptionArr.length;nn++){
							switch (myoptionArr[nn]) {
							case "A":
								myoptionstr+="1.";
								break;
							case "B":
								myoptionstr+="2.";
								break;
							case "C":
								myoptionstr+="3.";
								break;
							case "D":
								myoptionstr+="4.";
								break;
							case "E":
								myoptionstr+="5.";
								break;
							case "F":
								myoptionstr+="6.";
								break;
							case "G":
								myoptionstr+="7.";
								break;
							case "H":
								myoptionstr+="8.";
								break;
							case "I":
								myoptionstr+="9.";
								break;
							case "J":
								myoptionstr+="10.";
								break;
							case "K":
								myoptionstr+="11.";
								break;
							case "L":
								myoptionstr+="12.";
								break;
							case "M":
								myoptionstr+="13.";
								break;
							case "N":
								myoptionstr+="14.";
								break;
							case "O":
								myoptionstr+="15.";
								break;
							case "P":
								myoptionstr+="16.";
								break;
							default:
								break;
							}
						}
						map3.put("optitem", myoptionstr);
						
						list2.add(map3);
						//隐藏题号
						map4 = new HashMap<Object, Object>();
						map4.put("optitem", find3.get(mm).getHidequestionid()==null?"":find3.get(mm).getHidequestionid());
						list3.add(map4);
					}
					map.put("hideitems", list2);
					map.put("hidenum", list3);
				}  else if (type.equals("简答")) {
					map.put("type", "wenda");
					map.put("comp", "wenda");
					map.put("minnumber",khzsQuestionAll.getMinnumber());
					map.put("maxnumber",khzsQuestionAll.getMaxnumber());
				} else if(type.equals("填空简答")){
					map.put("type", "tiankongjianda");
					map.put("comp", "tiankongjianda");
				}else if(type.equals("矩阵填空")){
					map.put("type", "juzhentiankong");
					map.put("comp", "juzhentiankong");
				}else if (type.equals("位置")) {
					map.put("type", "weizhi");
					map.put("comp", "weizhi");
				}
			}
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<Object, Object>> selectQuestions(String cheID) {
		try {
			List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
			String[] arrid = cheID.split(",,");
			Map<Object, Object> map = null;
			for (int i = 0; i < arrid.length; i++) {
				map = new HashMap<Object, Object>();
				List<KhzsQuestionAll> find2 = khzsQuestionAllDao.find("from KhzsQuestionAll where id = ?", arrid[i]);
				for (int j = 0; j < find2.size(); j++) {
					KhzsQuestionAll khzsQuestionAll = find2.get(j);
					String question = khzsQuestionAll.getQuestion();// 获取题目
					String type = khzsQuestionAll.getType();// 获取题型
					String optiona = khzsQuestionAll.getOptiona();
					String optionb = khzsQuestionAll.getOptionb();
					String optionc = khzsQuestionAll.getOptionc();
					String optiond = khzsQuestionAll.getOptiond();
					String optione = khzsQuestionAll.getOptione();
					String optionf = khzsQuestionAll.getOptionf();
					String optiong = khzsQuestionAll.getOptiong();
					String optionh = khzsQuestionAll.getOptionh();
					String isanswer = khzsQuestionAll.getIsanswer();
					String remark = khzsQuestionAll.getRemark();
					List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
					Map<Object, Object> map2 = null;
					if (optiona != null) {
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optiona);
						list1.add(map2);
					}
					if (optionb != null) {
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optionb);
						list1.add(map2);
					}
					if (optionc != null) {
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optionc);
						list1.add(map2);
					}
					if (optiond != null) {
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optiond);
						list1.add(map2);
					}
					if (optione != null) {
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optione);
						list1.add(map2);
					}
					if (optionf != null) {
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optionf);
						list1.add(map2);
					}
					if (optiong != null) {
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optiong);
						list1.add(map2);
					}
					if (optionh != null) {
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optionh);
						list1.add(map2);
					}
					map.put("options", list1);
					map.put("name", type);
					map.put("isanswer", isanswer);
					map.put("remark", remark);
					/* map.put("id", 2); */
					map.put("content", question);
					if (type.equals("单选")) {
						map.put("type", "danxuan");
						map.put("comp", "danxuan");
					} else if (type.equals("多选")) {
						map.put("type", "duoxuan");
						map.put("comp", "duoxuan");
					} else if (type.equals("判断")) {
						map.put("type", "shifei");
						map.put("comp", "shifei");
					} else if (type.equals("简答")) {
						map.put("type", "wenda");
						map.put("comp", "wenda");
					} else if (type.equals("单项打分")) {
						map.put("type", "danxiangdafen");
						map.put("comp", "danxiangdafen");
					}
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<Object, Object>> selectQuestionsByWJ(String cheID) {
		try {
			List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
			String[] arrid = cheID.split(",,");
			Map<Object, Object> map = null;
			for (int i = 0; i < arrid.length; i++) {
				List<KhzsRelationship> find1 = khzsRelationshipDao
						.find("from KhzsRelationship where questionnaireid = ? order by ORDERS ASC", arrid[i]);
				for (int n = 0; n < find1.size(); n++) {
					map = new HashMap<Object, Object>();
					List<KhzsQuestionAll> find2 = khzsQuestionAllDao.find("from KhzsQuestionAll where id = ? ",
							find1.get(n).getQuestionid());
					for (int j = 0; j < find2.size(); j++) {
						KhzsQuestionAll khzsQuestionAll = find2.get(j);
						String question = khzsQuestionAll.getQuestion();// 获取题目
						String type = khzsQuestionAll.getType();// 获取题型
						String optiona = khzsQuestionAll.getOptiona();
						String optionb = khzsQuestionAll.getOptionb();
						String optionc = khzsQuestionAll.getOptionc();
						String optiond = khzsQuestionAll.getOptiond();
						String optione = khzsQuestionAll.getOptione();
						String optionf = khzsQuestionAll.getOptionf();
						String optiong = khzsQuestionAll.getOptiong();
						String optionh = khzsQuestionAll.getOptionh();
						String optioni = khzsQuestionAll.getOptioni();
						String optionj = khzsQuestionAll.getOptionj();
						String optionk = khzsQuestionAll.getOptionk();
						String optionl = khzsQuestionAll.getOptionl();
						String optionm = khzsQuestionAll.getOptionm();
						String optionn = khzsQuestionAll.getOptionn();
						String optiono = khzsQuestionAll.getOptiono();
						String optionp = khzsQuestionAll.getOptionp();
						String isanswer = khzsQuestionAll.getIsanswer();
						String remark = khzsQuestionAll.getRemark();
						String ps=khzsQuestionAll.getPs();
						String minnumber=khzsQuestionAll.getMinnumber();
						String maxnumber=khzsQuestionAll.getMaxnumber();
						List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
						Map<Object, Object> map2 = null;
						if (optiona != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optiona);
							list1.add(map2);
						}
						if (optionb != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionb);
							list1.add(map2);
						}
						if (optionc != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionc);
							list1.add(map2);
						}
						if (optiond != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optiond);
							list1.add(map2);
						}
						if (optione != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optione);
							list1.add(map2);
						}
						if (optionf != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionf);
							list1.add(map2);
						}
						if (optiong != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optiong);
							list1.add(map2);
						}
						if (optionh != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionh);
							list1.add(map2);
						}
						if (optioni != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optioni);
							list1.add(map2);
						}
						if (optionj != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionj);
							list1.add(map2);
						}
						if (optionk != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionk);
							list1.add(map2);
						}
						if (optionl != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionl);
							list1.add(map2);
						}
						if (optionm != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionm);
							list1.add(map2);
						}
						if (optionn != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionn);
							list1.add(map2);
						}
						if (optiono != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optiono);
							list1.add(map2);
						}
						if (optionp != null) {
							map2 = new HashMap<Object, Object>();
							map2.put("optText", optionp);
							list1.add(map2);
						}
						map.put("options", list1);
						map.put("name", type);
						map.put("isanswer", isanswer);
						map.put("remark", remark);
						/* map.put("id", 2); */
						map.put("content", question);
						map.put("id", n*(i+1));
						map.put("ps", ps==null?"":ps);
						if (type.equals("单选")||type.equals("多选")||type.equals("判断")||type.equals("单项打分")) {
							if(type.equals("单选")){
								map.put("type", "danxuan");
								map.put("comp", "danxuan");
							}
							if(type.equals("判断")){
								map.put("type", "shifei");
								map.put("comp", "shifei");
							}
							if(type.equals("单项打分")){
								map.put("type", "danxiangdafen");
								map.put("comp", "danxiangdafen");
								map.put("mintext", minnumber);
								map.put("maxtext", maxnumber);
							}
							if (type.equals("多选")) {
								map.put("type", "duoxuan");
								map.put("comp", "duoxuan");
							}
							
							/*List<KhzsQuestionAllHidelist> find3 = khzsQuestionAllHidelistDao.find("from KhzsQuestionAllHidelist where questionnaireid = ? AND questionid = ? ORDER BY OPTIONS asc", arrid[i],find1.get(n).getQuestionid());
							List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
							Map<Object, Object> map3 = null;
							for(int mm=0;mm<find3.size();mm++){
								map3 = new HashMap<Object, Object>();
								map3.put("optitem", find3.get(mm).getHidequestionid()==null?"":find3.get(mm).getHidequestionid());
								list2.add(map3);
							}
							map.put("hideitems", list2);*/
							List<KhzsQuestionAllHidelist> find3 = khzsQuestionAllHidelistDao.find("from KhzsQuestionAllHidelist where questionnaireid = ? AND questionid = ? ORDER BY OPTIONS asc", arrid[i],find1.get(n).getQuestionid());
							List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
							List<Map<Object, Object>> list3 = new ArrayList<Map<Object, Object>>();
							Map<Object, Object> map3 = null;
							Map<Object, Object> map4 = null;
							for(int mm=0;mm<find3.size();mm++){
								//选项
								map3 = new HashMap<Object, Object>();
								String myoption=find3.get(mm).getOptions();
								String[] myoptionArr = {};
								if(myoption!=null&&!myoption.isEmpty()){
									myoptionArr=myoption.split("\\.");
								}
								String myoptionstr="";
								for(int nn=0;nn<myoptionArr.length;nn++){
									switch (myoptionArr[nn]) {
									case "A":
										myoptionstr+="1.";
										break;
									case "B":
										myoptionstr+="2.";
										break;
									case "C":
										myoptionstr+="3.";
										break;
									case "D":
										myoptionstr+="4.";
										break;
									case "E":
										myoptionstr+="5.";
										break;
									case "F":
										myoptionstr+="6.";
										break;
									case "G":
										myoptionstr+="7.";
										break;
									case "H":
										myoptionstr+="8.";
										break;
									case "I":
										myoptionstr+="9.";
										break;
									case "J":
										myoptionstr+="10.";
										break;
									case "K":
										myoptionstr+="11.";
										break;
									case "L":
										myoptionstr+="12.";
										break;
									case "M":
										myoptionstr+="13.";
										break;
									case "N":
										myoptionstr+="14.";
										break;
									case "O":
										myoptionstr+="15.";
										break;
									case "P":
										myoptionstr+="16.";
										break;
									default:
										break;
									}
								}
								map3.put("optitem", myoptionstr);
								//map3.put("optitem", find3.get(mm).getHidequestionid()==null?"":find3.get(mm).getHidequestionid());
								list2.add(map3);
								//隐藏题号
								map4 = new HashMap<Object, Object>();
								map4.put("optitem", find3.get(mm).getHidequestionid()==null?"":find3.get(mm).getHidequestionid());
								list3.add(map4);
							}
							map.put("hideitems", list2);
							map.put("hidenum", list3);
						} else if (type.equals("多选")) {
							map.put("type", "duoxuan");
							map.put("comp", "duoxuan");
						}else if (type.equals("填空简答")) {
							map.put("type", "tiankongjianda");
							map.put("comp", "tiankongjianda");
						}else if (type.equals("矩阵填空")) {
							map.put("type", "juzhentiankong");
							map.put("comp", "juzhentiankong");
							String[] arrrow=optiona.split("#\\*#\\*#\\*");
							String[] arrcol=optionb.split("#\\*#\\*#\\*");
							List<Map<Object, Object>> listjuzhen = new ArrayList<Map<Object, Object>>();
							Map<Object, Object> mapjuzhen = null;
							for(int rowi=0;rowi<arrrow.length;rowi++){
								mapjuzhen = new HashMap<Object, Object>();
								mapjuzhen.put("optText", arrrow[rowi]);
								listjuzhen.add(mapjuzhen);
							}
							map.put("optionsrow", listjuzhen);
							listjuzhen = new ArrayList<Map<Object, Object>>();
							for(int coli=0;coli<arrcol.length;coli++){
								mapjuzhen = new HashMap<Object, Object>();
								mapjuzhen.put("optText", arrcol[coli]);
								listjuzhen.add(mapjuzhen);
							}
							map.put("optionscol", listjuzhen);
						} else if (type.equals("判断")) {
							map.put("type", "shifei");
							map.put("comp", "shifei");
							List<KhzsQuestionAllHidelist> find3 = khzsQuestionAllHidelistDao.find("from KhzsQuestionAllHidelist where questionnaireid = ? AND questionid = ? ORDER BY OPTIONS asc", arrid[i],find1.get(n).getQuestionid());
							List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
							List<Map<Object, Object>> list3 = new ArrayList<Map<Object, Object>>();
							Map<Object, Object> map3 = null;
							Map<Object, Object> map4 = null;
							for(int mm=0;mm<find3.size();mm++){
								//选项
								map3 = new HashMap<Object, Object>();
								String myoption=find3.get(mm).getOptions();
								String[] myoptionArr=myoption.split("\\.");
								String myoptionstr="";
								for(int nn=0;nn<myoptionArr.length;nn++){
									switch (myoptionArr[nn]) {
									case "A":
										myoptionstr+="1.";
										break;
									case "B":
										myoptionstr+="2.";
										break;
									case "C":
										myoptionstr+="3.";
										break;
									case "D":
										myoptionstr+="4.";
										break;
									case "E":
										myoptionstr+="5.";
										break;
									case "F":
										myoptionstr+="6.";
										break;
									case "G":
										myoptionstr+="7.";
										break;
									case "H":
										myoptionstr+="8.";
										break;
									case "I":
										myoptionstr+="9.";
										break;
									case "J":
										myoptionstr+="10.";
										break;
									case "K":
										myoptionstr+="11.";
										break;
									case "L":
										myoptionstr+="12.";
										break;
									case "M":
										myoptionstr+="13.";
										break;
									case "N":
										myoptionstr+="14.";
										break;
									case "O":
										myoptionstr+="15.";
										break;
									case "P":
										myoptionstr+="16.";
										break;
									default:
										break;
									}
								}
								map3.put("optitem", myoptionstr);
								list2.add(map3);
								//隐藏题号
								map4 = new HashMap<Object, Object>();
								map4.put("optitem", find3.get(mm).getHidequestionid()==null?"":find3.get(mm).getHidequestionid());
								list3.add(map4);
							}
							map.put("hideitems", list2);
							map.put("hidenum", list3);
						} else if (type.equals("简答")) {
							map.put("type", "wenda");
							map.put("comp", "wenda");
							map.put("minnumber", minnumber);
							map.put("maxnumber", maxnumber);
							map.put("options", optiona);
						}else if (type.equals("位置")) {
							map.put("type", "weizhi");
							map.put("comp", "weizhi");
							map.put("options", optiona);
						} else if (type.equals("单项打分")) {
							map.put("type", "danxiangdafen");
							map.put("comp", "danxiangdafen");
							map.put("mintext", minnumber);
							map.put("maxtext", maxnumber);
							List<KhzsQuestionAllHidelist> find3 = khzsQuestionAllHidelistDao.find("from KhzsQuestionAllHidelist where questionnaireid = ? AND questionid = ? ORDER BY OPTIONS asc", arrid[i],find1.get(n).getQuestionid());
							List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
							List<Map<Object, Object>> list3 = new ArrayList<Map<Object, Object>>();
							Map<Object, Object> map3 = null;
							Map<Object, Object> map4 = null;
							for(int mm=0;mm<find3.size();mm++){
								//选项
								map3 = new HashMap<Object, Object>();
								String myoption=find3.get(mm).getOptions();
								String[] myoptionArr=myoption.split("\\.");
								String myoptionstr="";
								for(int nn=0;nn<myoptionArr.length;nn++){
									switch (myoptionArr[nn]) {
									case "A":
										myoptionstr+="1.";
										break;
									case "B":
										myoptionstr+="2.";
										break;
									case "C":
										myoptionstr+="3.";
										break;
									case "D":
										myoptionstr+="4.";
										break;
									case "E":
										myoptionstr+="5.";
										break;
									case "F":
										myoptionstr+="6.";
										break;
									case "G":
										myoptionstr+="7.";
										break;
									case "H":
										myoptionstr+="8.";
										break;
									case "I":
										myoptionstr+="9.";
										break;
									case "J":
										myoptionstr+="10.";
										break;
									case "K":
										myoptionstr+="11.";
										break;
									case "L":
										myoptionstr+="12.";
										break;
									case "M":
										myoptionstr+="13.";
										break;
									case "N":
										myoptionstr+="14.";
										break;
									case "O":
										myoptionstr+="15.";
										break;
									case "P":
										myoptionstr+="16.";
										break;
									default:
										break;
									}
								}
								map3.put("optitem", myoptionstr);
								list2.add(map3);
								//隐藏题号
								map4 = new HashMap<Object, Object>();
								map4.put("optitem", find3.get(mm).getHidequestionid()==null?"":find3.get(mm).getHidequestionid());
								list3.add(map4);
							}
							map.put("hideitems", list2);
							map.put("hidenum", list3);
						}
					}
					list.add(map);
				}
			}
			System.out.println(list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<Object, Object>> seleDetailByid(String myAsk) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		List<KhzsQuestionnaire> find = KhzsQuestionnaireDao.find("from KhzsQuestionnaire where id = ?", myAsk);
		for (int j = 0; j < find.size(); j++) {
			KhzsQuestionnaire khzsQuestionnaire = find.get(j);
			map.put("myName", khzsQuestionnaire.getName());
			map.put("myNameSub", khzsQuestionnaire.getNamesub());
			map.put("ispublic", khzsQuestionnaire.getIspublic());
			map.put("isreward", khzsQuestionnaire.getIsreward());
		}
		list.add(map);
		return list;
	}
	
	

}
