package com.ultrapower.lnkhzs.survey.manager;

import com.ultrapower.lnkhzs.survey.model.KhzsAnswer;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAll;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAllHidelist;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
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
public class KhzsQuestionMgrImpl implements IKhzsQuestionService {

	private IDao<KhzsQuestion> khzsQuestionDao;
	
	private IDao<KhzsQuestionAll> khzsQuestionAllDao;

	private IDao<KhzsRelationship> khzsRelationshipDao;

	private IDao<KhzsQuestionnaire> KhzsQuestionnaireDao;
	
	private IDao<KhzsQuestionAllHidelist> KhzsQuestionAllHidelistDao;
	
	private IDao<KhzsAnswer> khzsAnswerDao;
	
	
	
	
	
	

	public IDao<KhzsAnswer> getKhzsAnswerDao() {
		return khzsAnswerDao;
	}

	public void setKhzsAnswerDao(IDao<KhzsAnswer> khzsAnswerDao) {
		this.khzsAnswerDao = khzsAnswerDao;
	}

	public IDao<KhzsQuestionAllHidelist> getKhzsQuestionAllHidelistDao() {
		return KhzsQuestionAllHidelistDao;
	}

	public void setKhzsQuestionAllHidelistDao(IDao<KhzsQuestionAllHidelist> khzsQuestionAllHidelistDao) {
		KhzsQuestionAllHidelistDao = khzsQuestionAllHidelistDao;
	}

	public void setKhzsQuestionDao(IDao<KhzsQuestion> khzsQuestionDao) {
		this.khzsQuestionDao = khzsQuestionDao;
	}

	public void setKhzsRelationshipDao(IDao<KhzsRelationship> khzsRelationshipDao) {
		this.khzsRelationshipDao = khzsRelationshipDao;
	}

	public void setKhzsQuestionnaireDao(IDao<KhzsQuestionnaire> khzsQuestionnaireDao) {
		KhzsQuestionnaireDao = khzsQuestionnaireDao;
	}
	
	public void setKhzsQuestionAllDao(IDao<KhzsQuestionAll> khzsQuestionAllDao) {
		this.khzsQuestionAllDao = khzsQuestionAllDao;
	}

	/**
	 * 删除题目
	 */
	@Override
	public void delDytk(String id) {
		khzsQuestionDao.removeById(id);
	}

	/**
	 * 修改题目
	 */
	@Override
	public void editDytk(KhzsQuestion khzsQuestion) {
		
		if (StringUtils.isBlank(khzsQuestion.getId())) {
			if (khzsQuestion.getType().equals("单选(其他)") || khzsQuestion.getType().equals("多选(其他)")) {
				khzsQuestion.setRemark("1");
			}else{
				khzsQuestion.setRemark("0");
			}
			
			khzsQuestion.setId(UUIDGenerator.getUUIDoffSpace());
			khzsQuestionDao.save(khzsQuestion);
		} else {
			khzsQuestionDao.saveOrUpdate(khzsQuestion);
		}

	}

	/**
	 * 查询题目
	 */
	@Override
	public List<Map<Object, Object>> queryKhzsQuestion(String id) {
		Map<Object, Object> optionaMap = new HashMap<Object, Object>();
		Map<Object, Object> optionbMap = new HashMap<Object, Object>();
		Map<Object, Object> khzsQuestionMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> list = new ArrayList<Map<Object,Object>>();
		String[] optionaArr = null;
		String[] optionbArr = null;
		
		KhzsQuestion khzsQuestion = khzsQuestionDao.get(id);
		
		if(khzsQuestion.getType().equals("矩阵填空")){
			
			
			String optiona = khzsQuestion.getOptiona();
				if(optiona!=null && !optiona.isEmpty()){
					 optionaArr = optiona.split("#\\*#\\*#\\*");
					 
					 for(int i = 0; i<optionaArr.length;i++){
							String optionas = optionaArr[i];
							optionaMap.put("optionL"+i, optionas);
						}
				}
			
			
			String optionb = khzsQuestion.getOptionb();
			if(optionb!=null && !optionb.isEmpty()){
				 optionbArr = optionb.split("#\\*#\\*#\\*");
				 
				 for(int i = 0; i<optionbArr.length;i++){
						String optionbs = optionbArr[i];
						optionbMap.put("optionC"+i, optionbs);
					}
			}
			
		}
		
		khzsQuestionMap.put("khzsQuestion", khzsQuestion);
		list.add(optionaMap);
		list.add(optionbMap);
		list.add(khzsQuestionMap);
		
		
		return list;
	}

	/**
	 * 调查问卷
	 */
	@Override
	public List<KhzsQuestionBean> getQuestionnaire(String id,String nDate) {
		List<KhzsQuestionBean> returnList = new ArrayList<KhzsQuestionBean>();
		
		
		List<Map<Object, Object>> list = null;
		Map<Object, Object> map = null;
		
		QueryAdapter q = new QueryAdapter();
		String sql = " SELECT zkq.*,zkr.ORDERS,zkr.id as relationid,zkr.QUESTIONID as questionid FROM ZL_KHZS_QUESTION_ALL zkq , ZL_KHZS_RELATIONSHIP zkr "
				+ "WHERE zkq.ID = zkr.QUESTIONID and  zkr.QUESTIONNAIREID =  '" + id + "' order by zkr.ORDERS ASC  ";
		
		
		DataTable datatable = q.executeQuery(sql);
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			
			KhzsQuestionBean questionBean = new KhzsQuestionBean();
			questionBean.setId(datatable.getRowList().get(i).getString("id"));
			
			List<KhzsQuestionAllHidelist> find = KhzsQuestionAllHidelistDao.find("from KhzsQuestionAllHidelist where questionid = ? and questionnaireid = ? ", questionBean.getId(),id);
			list = new ArrayList<Map<Object,Object>>();
			
			for(int j = 0; j<find.size();j++){
				String options = find.get(j).getOptions();//选项ABCDEFGHIJKLMNOP
				String hidequestionid = find.get(j).getHidequestionid();//需要隐藏的题号			
				String[] optionsArr=options.split("\\.");
				for(int mm=0;mm<optionsArr.length;mm++){
					map = new HashMap<Object, Object>();
					if(optionsArr[mm].equals("A")){
						map.put("A", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("B")){
						map.put("B", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("C")){
						map.put("C", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("D")){
						map.put("D", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("E")){
						map.put("E", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("F")){
						map.put("F", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("G")){
						map.put("G", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("H")){
						map.put("H", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("I")){
						map.put("I", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("J")){
						map.put("J", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("K")){
						map.put("K", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("L")){
						map.put("L", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("M")){
						map.put("M", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("N")){
						map.put("N", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("O")){
						map.put("O", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
					if(optionsArr[mm].equals("P")){
						map.put("P", hidequestionid);
						list.add(map);
						questionBean.setList(list);
						continue;
					}
				}
				
			}
			
			
			//String questionid = datatable.getRowList().get(i).getString("questionid");
			String questionid = datatable.getRowList().get(i).getString("relationid");
			String newDate = nDate+"%";
			if( nDate!="" && nDate!=null && !(nDate.isEmpty())){ 
				/*List<KhzsAnswer> answerList = khzsAnswerDao.find(" from KhzsAnswer where answertime like ? and ANSWER is not null and RELATIONID=? ",newDate,questionid );
				*/
				List<KhzsAnswer> answerList = khzsAnswerDao.find(" from KhzsAnswer where answertime like ? and ANSWER is not null and RELATIONID=? ",newDate,questionid );
				
				if(answerList!=null && answerList.size()!=0){
					
					questionBean.setCategory(datatable.getRowList().get(i).getString("CATEGORY"));
					questionBean.setQuestion(datatable.getRowList().get(i).getString("Question"));
					questionBean.setType(datatable.getRowList().get(i).getString("Type"));
					questionBean.setOptionA(datatable.getRowList().get(i).getString("OptionA"));
					questionBean.setOptionB(datatable.getRowList().get(i).getString("OptionB"));
					questionBean.setOptionC(datatable.getRowList().get(i).getString("OptionC"));
					questionBean.setOptionD(datatable.getRowList().get(i).getString("OptionD"));
					questionBean.setOptionE(datatable.getRowList().get(i).getString("OptionE"));
					questionBean.setOptionF(datatable.getRowList().get(i).getString("OptionF"));
					questionBean.setOptionG(datatable.getRowList().get(i).getString("OptionG"));
					questionBean.setOptionH(datatable.getRowList().get(i).getString("OptionH"));
					
					questionBean.setOptionI(datatable.getRowList().get(i).getString("OptionI"));
					questionBean.setOptionJ(datatable.getRowList().get(i).getString("OptionJ"));
					questionBean.setOptionK(datatable.getRowList().get(i).getString("OptionK"));
					questionBean.setOptionL(datatable.getRowList().get(i).getString("OptionL"));
					questionBean.setOptionM(datatable.getRowList().get(i).getString("OptionM"));
					questionBean.setOptionN(datatable.getRowList().get(i).getString("OptionN"));
					questionBean.setOptionO(datatable.getRowList().get(i).getString("OptionO"));
					questionBean.setOptionP(datatable.getRowList().get(i).getString("OptionP"));
					
					questionBean.setRemark(datatable.getRowList().get(i).getString("Remark"));
					questionBean.setOrders(datatable.getRowList().get(i).getString("Orders"));
					questionBean.setIsanswer(datatable.getRowList().get(i).getString("Isanswer"));
					returnList.add(questionBean);
					
				}
			}else{
				questionBean.setCategory(datatable.getRowList().get(i).getString("CATEGORY"));
				questionBean.setQuestion(datatable.getRowList().get(i).getString("Question"));
				questionBean.setType(datatable.getRowList().get(i).getString("Type"));
				questionBean.setOptionA(datatable.getRowList().get(i).getString("OptionA"));
				questionBean.setOptionB(datatable.getRowList().get(i).getString("OptionB"));
				questionBean.setOptionC(datatable.getRowList().get(i).getString("OptionC"));
				questionBean.setOptionD(datatable.getRowList().get(i).getString("OptionD"));
				questionBean.setOptionE(datatable.getRowList().get(i).getString("OptionE"));
				questionBean.setOptionF(datatable.getRowList().get(i).getString("OptionF"));
				questionBean.setOptionG(datatable.getRowList().get(i).getString("OptionG"));
				questionBean.setOptionH(datatable.getRowList().get(i).getString("OptionH"));
				
				questionBean.setOptionI(datatable.getRowList().get(i).getString("OptionI"));
				questionBean.setOptionJ(datatable.getRowList().get(i).getString("OptionJ"));
				questionBean.setOptionK(datatable.getRowList().get(i).getString("OptionK"));
				questionBean.setOptionL(datatable.getRowList().get(i).getString("OptionL"));
				questionBean.setOptionM(datatable.getRowList().get(i).getString("OptionM"));
				questionBean.setOptionN(datatable.getRowList().get(i).getString("OptionN"));
				questionBean.setOptionO(datatable.getRowList().get(i).getString("OptionO"));
				questionBean.setOptionP(datatable.getRowList().get(i).getString("OptionP"));
				
				questionBean.setRemark(datatable.getRowList().get(i).getString("Remark"));
				questionBean.setOrders(datatable.getRowList().get(i).getString("Orders"));
				questionBean.setIsanswer(datatable.getRowList().get(i).getString("Isanswer"));
				returnList.add(questionBean);
			}
			
		}
		return returnList;
	}

	@Override
	public void dealDanXuan(String t_danxuan, String i_danxuan) {
		String[] title = t_danxuan.split("||");
		String[] info = i_danxuan.split("||");
		KhzsQuestion khzsQuestion = null;
		for (int i = 0; i < title.length; i++) {
			khzsQuestion = new KhzsQuestion();
			khzsQuestion.setId(UUIDGenerator.getUUIDoffSpace());
			khzsQuestion.setCategory("");
			khzsQuestionDao.save(khzsQuestion);
		}
	}

	@Override
	public void dealDuoXuan(String t_duoxuan, String i_duoxuan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dealJianDa(String t_jianda, String i_jianda) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dealShiFei(String t_shifei, String i_shifei) {
		// TODO Auto-generated method stub

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
			for (int i = 0; i < mylist.size(); i++) {
				String name = "";// 类型：danxuan,duoxuan,shifei,jianda,danxuanqita,duoxuanqita
				String content = "";// 问题
				String optiona = "";// 答案a
				String optionb = "";// 答案b
				String optionc = "";// 答案c
				String optiond = "";// 答案d
				String optione = "";// 答案e
				String optionf = "";// 答案f
				String optiong = "";// 答案g
				String optionh = "";// 答案h
				map = mylist.get(i);
				name = map.get("name").toString();
				content = map.get("content").toString();
				JSONArray ja = (JSONArray) map.get("options");
				for (int j = 0; j <= ja.size(); j++) {

					if (j == 0) {
						if ((name.equals("单选(其他)") || name.equals("多选(其他)")) && j == ja.size()) {
							optiona = "其他";
						} else if (j == ja.size()) {
							;
						} else {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optiona = options;
						}
					}

					if (j == 1) {
						if ((name.equals("单选(其他)") || name.equals("多选(其他)")) && j == ja.size()) {
							optionb = "其他";
						} else if (j == ja.size()) {
							;
						} else {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionb = options;
						}
					}

					if (j == 2) {
						if ((name.equals("单选(其他)") || name.equals("多选(其他)")) && j == ja.size()) {
							optionc = "其他";
						} else if (j == ja.size()) {
							;
						} else {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionc = options;
						}
					}

					if (j == 3) {
						if ((name.equals("单选(其他)") || name.equals("多选(其他)")) && j == ja.size()) {
							optiond = "其他";
						} else if (j == ja.size()) {
							;
						} else {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optiond = options;
						}
					}

					if (j == 4) {
						if ((name.equals("单选(其他)") || name.equals("多选(其他)")) && j == ja.size()) {
							optione = "其他";
						} else if (j == ja.size()) {
							;
						} else {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optione = options;
						}
					}

					if (j == 5) {
						if ((name.equals("单选(其他)") || name.equals("多选(其他)")) && j == ja.size()) {
							optionf = "其他";
						} else if (j == ja.size()) {
							;
						} else {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionf = options;
						}
					}

					if (j == 6) {
						if ((name.equals("单选(其他)") || name.equals("多选(其他)")) && j == ja.size()) {
							optiong = "其他";
						} else if (j == ja.size()) {
							;
						} else {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optiong = options;
						}
					}

					if (j == 7) {
						if ((name.equals("单选(其他)") || name.equals("多选(其他)")) && j == ja.size()) {
							optionh = "其他";
						} else if (j == ja.size()) {
							;
						} else {
							String options = ja.get(j).toString();
							options = options.substring(options.indexOf("\":\"") + 3, options.indexOf("\"}"));
							optionh = options;
						}
					}
				}
				KhzsQuestion khzsQuestion = new KhzsQuestion();
				khzsQuestion.setOptiona(optiona);
				khzsQuestion.setOptionb(optionb);
				khzsQuestion.setOptionc(optionc);
				khzsQuestion.setOptiond(optiond);
				khzsQuestion.setOptione(optione);
				khzsQuestion.setOptionf(optionf);
				khzsQuestion.setOptiong(optiong);
				khzsQuestion.setOptionh(optionh);
				khzsQuestion.setQuestion(content);
				khzsQuestion.setType(name);
				if (name.equals("单选(其他)")) {
					khzsQuestion.setRemark("1");
				}
				if (name.equals("多选(其他)")) {
					khzsQuestion.setRemark("1");
				}

				// khzsQuestionService.editDytk(khzsQuestion);
				String uuid = UUIDGenerator.getUUIDoffSpace();
				khzsQuestion.setId(uuid);
				khzsQuestionDao.save(khzsQuestion);

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
		// TODO Auto-generated method stub
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
			List<KhzsQuestion> find2 = khzsQuestionDao.find("from KhzsQuestion where id = ?", id2);
			int size2 = find2.size();
			for (int j = 0; j < find2.size(); j++) {
				KhzsQuestion khzsQuestion = find2.get(j);
				String question = khzsQuestion.getQuestion();// 获取题目
				String type = khzsQuestion.getType();// 获取题型
				String optiona = khzsQuestion.getOptiona();
				String optionb = khzsQuestion.getOptionb();
				String optionc = khzsQuestion.getOptionc();
				String optiond = khzsQuestion.getOptiond();
				String optione = khzsQuestion.getOptione();
				String optionf = khzsQuestion.getOptionf();
				String optiong = khzsQuestion.getOptiong();
				String optionh = khzsQuestion.getOptionh();
				List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
				Map<Object, Object> map2 = null;
				for (int myi = 1; myi < 2; myi++) {
					if (optiona != null) {
						if (optiona.equals("其他"))
							continue;
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optiona);
						list1.add(map2);
					}
					if (optionb != null) {
						if (optionb.equals("其他"))
							continue;
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optionb);
						list1.add(map2);
					}
					if (optionc != null) {
						if (optionc.equals("其他"))
							continue;
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optionc);
						list1.add(map2);
					}
					if (optiond != null) {
						if (optiond.equals("其他"))
							continue;
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optiond);
						list1.add(map2);
					}
					if (optione != null) {
						if (optione.equals("其他"))
							continue;
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optione);
						list1.add(map2);
					}
					if (optionf != null) {
						if (optionf.equals("其他"))
							continue;
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optionf);
						list1.add(map2);
					}
					if (optiong != null) {
						if (optiong.equals("其他"))
							continue;
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optiong);
						list1.add(map2);
					}
					if (optionh != null) {
						if (optionh.equals("其他"))
							continue;
						map2 = new HashMap<Object, Object>();
						map2.put("optText", optionh);
						list1.add(map2);
					}
				}

				map.put("options", list1);

				map.put("name", type);
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
				} else if (type.equals("多选(其他)")) {
					map.put("type", "duoxuanqita");
					map.put("comp", "duoxuanqita");
				} else if (type.equals("单选(其他)")) {
					map.put("type", "danxuanqita");
					map.put("comp", "danxuanqita");
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
				List<KhzsQuestion> find2 = khzsQuestionDao.find("from KhzsQuestion where id = ?", arrid[i]);
				for (int j = 0; j < find2.size(); j++) {
					KhzsQuestion khzsQuestion = find2.get(j);
					String question = khzsQuestion.getQuestion();// 获取题目
					String type = khzsQuestion.getType();// 获取题型
					String optiona = khzsQuestion.getOptiona();
					String optionb = khzsQuestion.getOptionb();
					String optionc = khzsQuestion.getOptionc();
					String optiond = khzsQuestion.getOptiond();
					String optione = khzsQuestion.getOptione();
					String optionf = khzsQuestion.getOptionf();
					String optiong = khzsQuestion.getOptiong();
					String optionh = khzsQuestion.getOptionh();
					String optioni = khzsQuestion.getOptioni();
					String optionj = khzsQuestion.getOptionj();
					String optionk = khzsQuestion.getOptionk();
					String optionl = khzsQuestion.getOptionl();
					String optionm = khzsQuestion.getOptionm();
					String optionn = khzsQuestion.getOptionn();
					String optiono = khzsQuestion.getOptiono();
					String optionp = khzsQuestion.getOptionp();
					String isanswer = khzsQuestion.getIsanswer();
					String remark = khzsQuestion.getRemark();
					String ps=khzsQuestion.getPs();
					String minnumber=khzsQuestion.getMinnumber();
					String maxnumber=khzsQuestion.getMaxnumber();
					List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
					List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
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
					}if (optioni != null) {
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
					map.put("content", question);
					map.put("isanswer", isanswer);
					map.put("remark", remark);
					map.put("id", i);
					map.put("ps", ps==null?"":ps);
					if (type.equals("单选")) {
						map.put("type", "danxuan");
						map.put("comp", "danxuan");
						map.put("hideitems", list2);
						map.put("hidenum", list2);
					} else if (type.equals("多选")) {
						map.put("type", "duoxuan");
						map.put("comp", "duoxuan");
						map.put("hideitems", list2);
						map.put("hidenum", list2);
					} else if (type.equals("判断")) {
						map.put("type", "shifei");
						map.put("comp", "shifei");
						map.put("hideitems", list2);
						map.put("hidenum", list2);
					} else if (type.equals("简答")) {
						map.put("type", "wenda");
						map.put("comp", "wenda");
						map.put("minnumber", minnumber);
						map.put("maxnumber", maxnumber);
						map.put("options", optiona);
					} else if (type.equals("单项打分")) {
						map.put("type", "danxiangdafen");
						map.put("comp", "danxiangdafen");
						map.put("hideitems", list2);
						map.put("hidenum", list2);
						map.put("mintext", minnumber);
						map.put("maxtext", maxnumber);
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
	public void addManyDytk(String ids, KhzsQuestion khzsQuestion) {
		String[] arrids=ids.split(",,");
		KhzsQuestionAll khzsQuestionAll = null;
		for(int i=0;i<arrids.length;i++){
			khzsQuestionAll=khzsQuestionAllDao.get(arrids[i]);
			khzsQuestion.setCategory(khzsQuestionAll.getCategory());
			khzsQuestion.setId(UUIDGenerator.getUUIDoffSpace());
			khzsQuestion.setIsanswer(khzsQuestionAll.getIsanswer());
			khzsQuestion.setOptiona(khzsQuestionAll.getOptiona());
			khzsQuestion.setOptionb(khzsQuestionAll.getOptionb());
			khzsQuestion.setOptionc(khzsQuestionAll.getOptionc());
			khzsQuestion.setOptiond(khzsQuestionAll.getOptiond());
			khzsQuestion.setOptione(khzsQuestionAll.getOptione());
			khzsQuestion.setOptionf(khzsQuestionAll.getOptionf());
			khzsQuestion.setOptiong(khzsQuestionAll.getOptiong());
			khzsQuestion.setOptionh(khzsQuestionAll.getOptionh());
			khzsQuestion.setOptioni(khzsQuestionAll.getOptioni());
			khzsQuestion.setOptionj(khzsQuestionAll.getOptionj());
			khzsQuestion.setOptionk(khzsQuestionAll.getOptionk());
			khzsQuestion.setOptionl(khzsQuestionAll.getOptionl());
			khzsQuestion.setOptionm(khzsQuestionAll.getOptionm());
			khzsQuestion.setOptionn(khzsQuestionAll.getOptionn());
			khzsQuestion.setOptiono(khzsQuestionAll.getOptiono());
			khzsQuestion.setOptionp(khzsQuestionAll.getOptionp());
			khzsQuestion.setQuestion(khzsQuestionAll.getQuestion());
			khzsQuestion.setRemark(khzsQuestionAll.getRemark());
			khzsQuestion.setType(khzsQuestionAll.getType());
			khzsQuestionDao.save(khzsQuestion);
		}
	}
	
	
	
	/**
	 * 调查问卷
	 */
	@Override
	public String ajaxGetYCT(String id) {
		
		QueryAdapter q = new QueryAdapter();
		String sql = " SELECT zkq.*,zkr.ORDERS,zkr.id as relationid FROM ZL_KHZS_QUESTION_ALL zkq , ZL_KHZS_RELATIONSHIP zkr "
				+ "WHERE zkq.ID = zkr.QUESTIONID and  zkr.QUESTIONNAIREID =  '" + id + "' order by zkr.ORDERS ASC  ";
		
		String YCquestion = "";
		DataTable datatable = q.executeQuery(sql);
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			KhzsQuestionBean questionBean = new KhzsQuestionBean();
			questionBean.setId(datatable.getRowList().get(i).getString("id"));
			
			List<KhzsQuestionAllHidelist> find = KhzsQuestionAllHidelistDao.find("from KhzsQuestionAllHidelist where questionid = ? and questionnaireid = ? ", questionBean.getId(),id);
			
			 List<KhzsAnswer> KhzsAnswerList = khzsAnswerDao.find("from KhzsAnswer where relationid = ?  ", questionBean.getId());
			
			/* for(int z = 0;z<KhzsAnswerList.size();z++){
				 
				 if(find.get(0).getQuestionid().equals(KhzsAnswerList.get(z).getRelationid())){
				 
			 }*/
			 
			for(int j = 0; j<find.size();j++){
				
				for(int z = 0;z<KhzsAnswerList.size();z++){
					
					if(find.get(j).getOptions().equals(KhzsAnswerList.get(z).getAnswer())){
						
						if(find.get(j).getHidequestionid()!=null){
							YCquestion += find.get(j).getHidequestionid()+",";
						}
						
					}
					
				}
				
			}
		}
		return YCquestion;
	}

}
