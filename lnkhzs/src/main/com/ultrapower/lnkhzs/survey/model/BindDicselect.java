package com.ultrapower.lnkhzs.survey.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [问卷题库关系表_实体]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "BS_T_SM_DICSELECT")
public class BindDicselect implements ICommonModel {
	/**
	 * id_字段
	 */
	private String id;

	/**
	 * 用户编码_字段
	 */
	private String user_id;

	/**
	 * 地市_字段
	 */
	private String city_id;
	
	/**
	 * 区县_字段
	 */
	private String county_id;

	/**
	 * 年龄_字段
	 */
	private String age;

	/**
	 * 入网时间_字段
	 */
	private String user_opentime;

	/**
	 * 区域（城镇/农村）_字段
	 */
	private String region_id1;

	/**
	 * 星级_字段
	 */
	private String star_level;

	/**
	 * 是否双卡客户_字段
	 */
	private String is_2card;

	/**
	 * 是否主副卡客户_字段
	 */
	private String is_zhufu_card;

	/**
	 * 是否国内漫游客户_字段
	 */
	private String rom_in_chi_mark;

	/**
	 * 是否国际漫游客户_字段
	 */
	private String rom_in_earth_mark;

	/**
	 * 是否校园客户_字段
	 */
	private String school_flag;

	/**
	 * 是否全球通客户_字段
	 */
	private String is_world;

	/**
	 * 是否开通VOLTE功能_字段
	 */
	private String is_offer;

	/**
	 * 手机终端品牌_字段
	 */
	private String provider_name;

	/**
	 * 手机终端型号_字段
	 */
	private String term_name;

	/**
	 * 近三个月月均ARPU（元）_字段
	 */
	private String arpu_3month;

	/**
	 * 近三个月月均DOU（M）_字段
	 */
	private String dou_3month;

	/**
	 * 近三个月月均MOU（分钟）_字段
	 */
	private String mou_3month;

	/**
	 * 近三个月月均VOLTE通话时长_字段
	 */
	private String volte_duration_3month;

	/**
	 * 资费套餐名称_字段
	 */
	private String plan_id;

	/**
	 * 是否融合套餐_字段
	 */
	private String planchng_3month;

	/**
	 * 近三个月流量限速次数_字段
	 */
	private String gprs_xs_3month;

	/**
	 * 当月套餐内流量饱和值_字段
	 */
	private String gprs_res_baohe;

	/**
	 * 当月套餐内语音通话饱和值_字段
	 */
	private String call_res_baohe;

	/**
	 * 近三个月套外流量（M）_字段
	 */
	private String over_flow_3month;

	/**
	 * 近三个月套外语音费用（元）_字段
	 */
	private String call_duration_fee_3momth;

	/**
	 * 欠费标识_字段
	 */
	private String owe_mark;

	/**
	 * 近三个月营业厅办理次数_字段
	 */
	private String yyt_banli_3month;

	/**
	 * 近三个月登陆网厅次数_字段
	 */
	private String www_action_3month;

	/**
	 * 近三个月登陆手机营业厅次数_字段
	 */
	private String login_3month;

	/**
	 * 是否宽带客户_字段
	 */
	private String is_kd;

	/**
	 * 宽带套餐名称_字段
	 */
	private String offer_name;

	/**
	 * 是否融合套餐_字段
	 */
	private String is_fuse;

	/**
	 * 宽带带宽_字段
	 */
	private String prod_bandwidth;

	/**
	 * 开通时间_字段
	 */
	private String kd_create_date;

	/**
	 * 宽带到期时间_字段
	 */
	private String max_expire_date;

	/**
	 * 是否宽带电视客户_字段
	 */
	private String is_kdds;

	/**
	 * 近三个月宽带电视消费金额_字段
	 */
	private String mbh_should_fee;

	/**
	 * 是否集团客户成员_字段
	 */
	private String is_group;

	/**
	 * 是否集团客户关键人_字段
	 */
	private String is_group_keyper;

	/**
	 * 是否集团客户联系人_字段
	 */
	private String is_group_linker;

	/**
	 * 是否集团产品使用人_字段
	 */
	private String is_group_order;

	/**
	 * 所属集团客户入网时间_字段
	 */
	private String create_date;

	/**
	 * 行业类型_字段
	 */
	private String group_industry_id;

	/**
	 * 集团客户等级_字段
	 */
	private String group_level_id;

	/**
	 * 是否集团专线客户_字段
	 */
	private String is_group_zx;

	/**
	 * 是否企业宽带客户_字段
	 */
	private String is_qykd;

	/**
	 * 是否集团V网客户_字段
	 */
	private String is_group_vpmn;

	/**
	 * 是否IDC客户_字段
	 */
	private String is_idc;

	/**
	 * 是否IMS电话客户_字段
	 */
	private String is_ims;

	/**
	 * 近三个月10086投诉次数_字段
	 */
	private String complain_10086_counts;

	/**
	 * 其中：移动业务投诉次数_字段
	 */
	private String complain_ydyw_counts;

	/**
	 * 移动网络质量投诉次数_字段
	 */
	private String complain_yywl_counts;

	/**
	 * 家庭业务投诉次数_字段
	 */
	private String complain_jtyw_counts;

	/**
	 * 家庭宽带投诉次数_字段
	 */
	private String complain_jtkd_counts;

	/**
	 * 宽带电视投诉次数_字段
	 */
	private String complain_jtds_counts;

	/**
	 * 家庭宽带网络质量投诉次数_字段
	 */
	private String complain_jtwl_counts;

	/**
	 * 集团业务投诉次数_字段
	 */
	private String complain_jt_counts;

	/**
	 * 增值业务投诉次数_字段
	 */
	private String complain_zz_counts;

	/**
	 * 是否开通VOLTE_字段
	 */
	private String is_ofer1;

	/**
	 * 近三月是否有VOLTE通信_字段
	 */
	private String is_volte_3month;
	
	/**
	 * 短信内容_字段
	 */
	private String message_info;
	
	
	/**
	 * 地市_字段中文
	 *//*
	private String city_idCh;
	
	*//**
	 * 区县中文_字段
	 *//*
	private String county_idCh;
	*//**
	 * 区域（城镇/农村）中文_字段
	 *//*
	private String region_id1Ch;
	*//**
	 * 星级中文_字段
	 *//*
	private String star_levelCh;
	*//**
	 * 手机终端品牌中文_字段
	 *//*
	private String provider_nameCh;
	*//**
	 * 手机终端型号中文_字段
	 *//*
	private String term_nameCh;
	*//**
	 * 资费套餐名称中文_字段
	 *//*
	private String plan_idCh;
	*//**
	 * 宽带套餐名称中文_字段
	 *//*
	private String offer_nameCh;
	*//**
	 * 宽带带宽中文_字段
	 *//*
	private String prod_bandwidthCh;
	*//**
	 * 行业类型中文_字段
	 *//*
	private String group_industry_idCh;
	*//**
	 * 集团客户等级中文_字段
	 *//*
	private String group_level_idCh;*/
	
	
	
	
	
	
	
	
	

	/**
	 * [用户编码_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getUser_id() {
		return user_id;
	}

	/**
	 * [用户编码_Set方法]
	 * 
	 * @author:
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * [地市_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getCity_id() {
		return city_id;
	}

	/**
	 * [地市_Set方法]
	 * 
	 * @author:
	 */
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	
	/**
	 * [区县_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getCounty_id() {
		return county_id;
	}

	/**
	 * [区县_Set方法]
	 * 
	 * @author:
	 */
	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}

	/**
	 * [年龄_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "AGE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getAge() {
		return age;
	}

	/**
	 * [年龄_Set方法]
	 * 
	 * @author:
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * [入网时间_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "USER_OPENTIME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getUser_opentime() {
		return user_opentime;
	}

	/**
	 * [入网时间_Set方法]
	 * 
	 * @author:
	 */
	public void setUser_opentime(String user_opentime) {
		this.user_opentime = user_opentime;
	}

	/**
	 * [区域（城镇/农村）_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "REGION_ID1", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getRegion_id1() {
		return region_id1;
	}

	/**
	 * [区域（城镇/农村）_Set方法]
	 * 
	 * @author:
	 */
	public void setRegion_id1(String region_id1) {
		this.region_id1 = region_id1;
	}

	/**
	 * [星级_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "STAR_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getStar_level() {
		return star_level;
	}

	/**
	 * [星级_Set方法]
	 * 
	 * @author:
	 */
	public void setStar_level(String star_level) {
		this.star_level = star_level;
	}

	/**
	 * [是否双卡客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_2CARD", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_2card() {
		return is_2card;
	}

	/**
	 * [是否双卡客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_2card(String is_2card) {
		this.is_2card = is_2card;
	}

	/**
	 * [是否主副卡客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_ZHUFU_CARD", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_zhufu_card() {
		return is_zhufu_card;
	}

	/**
	 * [是否主副卡客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_zhufu_card(String is_zhufu_card) {
		this.is_zhufu_card = is_zhufu_card;
	}

	/**
	 * [是否国内漫游客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "ROM_IN_CHI_MARK", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getRom_in_chi_mark() {
		return rom_in_chi_mark;
	}

	/**
	 * [是否国内漫游客户_Set方法]
	 * 
	 * @author:
	 */
	public void setRom_in_chi_mark(String rom_in_chi_mark) {
		this.rom_in_chi_mark = rom_in_chi_mark;
	}

	/**
	 * [是否国际漫游客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "ROM_IN_EARTH_MARK", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getRom_in_earth_mark() {
		return rom_in_earth_mark;
	}

	/**
	 * [是否国际漫游客户_Set方法]
	 * 
	 * @author:
	 */
	public void setRom_in_earth_mark(String rom_in_earth_mark) {
		this.rom_in_earth_mark = rom_in_earth_mark;
	}

	/**
	 * [是否校园客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "SCHOOL_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getSchool_flag() {
		return school_flag;
	}

	/**
	 * [是否校园客户_Set方法]
	 * 
	 * @author:
	 */
	public void setSchool_flag(String school_flag) {
		this.school_flag = school_flag;
	}

	/**
	 * [是否全球通客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_WORLD", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_world() {
		return is_world;
	}

	/**
	 * [是否全球通客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_world(String is_world) {
		this.is_world = is_world;
	}

	/**
	 * [是否开通VOLTE功能_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_OFFER", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_offer() {
		return is_offer;
	}

	/**
	 * [是否开通VOLTE功能_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_offer(String is_offer) {
		this.is_offer = is_offer;
	}

	/**
	 * [手机终端品牌_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "PROVIDER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getProvider_name() {
		return provider_name;
	}

	/**
	 * [手机终端品牌_Set方法]
	 * 
	 * @author:
	 */
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}

	/**
	 * [手机终端型号_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "TERM_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getTerm_name() {
		return term_name;
	}

	/**
	 * [手机终端型号_Set方法]
	 * 
	 * @author:
	 */
	public void setTerm_name(String term_name) {
		this.term_name = term_name;
	}

	/**
	 * [近三个月月均ARPU（元）_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "ARPU_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getArpu_3month() {
		return arpu_3month;
	}

	/**
	 * [近三个月月均ARPU（元）_Set方法]
	 * 
	 * @author:
	 */
	public void setArpu_3month(String arpu_3month) {
		this.arpu_3month = arpu_3month;
	}

	/**
	 * [近三个月月均DOU（M）_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "DOU_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDou_3month() {
		return dou_3month;
	}

	/**
	 * [近三个月月均DOU（M）_Set方法]
	 * 
	 * @author:
	 */
	public void setDou_3month(String dou_3month) {
		this.dou_3month = dou_3month;
	}

	/**
	 * [近三个月月均MOU（分钟）_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "MOU_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getMou_3month() {
		return mou_3month;
	}

	/**
	 * [近三个月月均MOU（分钟）_Set方法]
	 * 
	 * @author:
	 */
	public void setMou_3month(String mou_3month) {
		this.mou_3month = mou_3month;
	}

	/**
	 * [近三个月月均VOLTE通话时长_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "VOLTE_DURATION_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getVolte_duration_3month() {
		return volte_duration_3month;
	}

	/**
	 * [近三个月月均VOLTE通话时长_Set方法]
	 * 
	 * @author:
	 */
	public void setVolte_duration_3month(String volte_duration_3month) {
		this.volte_duration_3month = volte_duration_3month;
	}

	/**
	 * [资费套餐名称_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "PLAN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getPlan_id() {
		return plan_id;
	}

	/**
	 * [资费套餐名称_Set方法]
	 * 
	 * @author:
	 */
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}


	/**
	 * [近三个月套餐变更次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "PLANCHNG_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getPlanchng_3month() {
		return planchng_3month;
	}

	/**
	 * [近三个月套餐变更次数_Set方法]
	 * 
	 * @author:
	 */
	public void setPlanchng_3month(String planchng_3month) {
		this.planchng_3month = planchng_3month;
	}


	/**
	 * [近三个月流量限速次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "GPRS_XS_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getGprs_xs_3month() {
		return gprs_xs_3month;
	}

	/**
	 * [近三个月流量限速次数_Set方法]
	 * 
	 * @author:
	 */
	public void setGprs_xs_3month(String gprs_xs_3month) {
		this.gprs_xs_3month = gprs_xs_3month;
	}


	/**
	 * [当月套餐内流量饱和值_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "GPRS_RES_BAOHE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getGprs_res_baohe() {
		return gprs_res_baohe;
	}

	/**
	 * [当月套餐内流量饱和值_Set方法]
	 * 
	 * @author:
	 */
	public void setGprs_res_baohe(String gprs_res_baohe) {
		this.gprs_res_baohe = gprs_res_baohe;
	}


	/**
	 * [当月套餐内语音通话饱和值_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "CALL_RES_BAOHE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getCall_res_baohe() {
		return call_res_baohe;
	}

	/**
	 * [当月套餐内语音通话饱和值_Set方法]
	 * 
	 * @author:
	 */
	public void setCall_res_baohe(String call_res_baohe) {
		this.call_res_baohe = call_res_baohe;
	}


	/**
	 * [近三个月套外流量（M）_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "OVER_FLOW_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getOver_flow_3month() {
		return over_flow_3month;
	}

	/**
	 * [近三个月套外流量（M）_Set方法]
	 * 
	 * @author:
	 */
	public void setOver_flow_3month(String over_flow_3month) {
		this.over_flow_3month = over_flow_3month;
	}


	/**
	 * [近三个月套外语音费用（元）_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "CALL_DURATION_FEE_3MOMTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getCall_duration_fee_3momth() {
		return call_duration_fee_3momth;
	}

	/**
	 * [近三个月套外语音费用（元）_Set方法]
	 * 
	 * @author:
	 */
	public void setCall_duration_fee_3momth(String call_duration_fee_3momth) {
		this.call_duration_fee_3momth = call_duration_fee_3momth;
	}


	/**
	 * [欠费标识_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "OWE_MARK", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getOwe_mark() {
		return owe_mark;
	}

	/**
	 * [欠费标识_Set方法]
	 * 
	 * @author:
	 */
	public void setOwe_mark(String owe_mark) {
		this.owe_mark = owe_mark;
	}


	/**
	 * [近三个月营业厅办理次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "YYT_BANLI_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getYyt_banli_3month() {
		return yyt_banli_3month;
	}

	/**
	 * [近三个月营业厅办理次数_Set方法]
	 * 
	 * @author:
	 */
	public void setYyt_banli_3month(String yyt_banli_3month) {
		this.yyt_banli_3month = yyt_banli_3month;
	}


	/**
	 * [近三个月登陆网厅次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "WWW_ACTION_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getWww_action_3month() {
		return www_action_3month;
	}

	/**
	 * [近三个月登陆网厅次数_Set方法]
	 * 
	 * @author:
	 */
	public void setWww_action_3month(String www_action_3month) {
		this.www_action_3month = www_action_3month;
	}


	/**
	 * [近三个月登陆手机营业厅次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "LOGIN_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getLogin_3month() {
		return login_3month;
	}

	/**
	 * [近三个月登陆手机营业厅次数_Set方法]
	 * 
	 * @author:
	 */
	public void setLogin_3month(String login_3month) {
		this.login_3month = login_3month;
	}


	/**
	 * [是否宽带客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_KD", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_kd() {
		return is_kd;
	}

	/**
	 * [是否宽带客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_kd(String is_kd) {
		this.is_kd = is_kd;
	}


	/**
	 * [宽带套餐名称_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "OFFER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getOffer_name() {
		return offer_name;
	}

	/**
	 * [宽带套餐名称_Set方法]
	 * 
	 * @author:
	 */
	public void setOffer_name(String offer_name) {
		this.offer_name = offer_name;
	}


	/**
	 * [是否融合套餐_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_FUSE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_fuse() {
		return is_fuse;
	}

	/**
	 * [是否融合套餐_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_fuse(String is_fuse) {
		this.is_fuse = is_fuse;
	}


	/**
	 * [宽带带宽_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "PROD_BANDWIDTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getProd_bandwidth() {
		return prod_bandwidth;
	}

	/**
	 * [宽带带宽_Set方法]
	 * 
	 * @author:
	 */
	public void setProd_bandwidth(String prod_bandwidth) {
		this.prod_bandwidth = prod_bandwidth;
	}


	/**
	 * [开通时间_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "KD_CREATE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getKd_create_date() {
		return kd_create_date;
	}

	/**
	 * [开通时间_Set方法]
	 * 
	 * @author:
	 */
	public void setKd_create_date(String kd_create_date) {
		this.kd_create_date = kd_create_date;
	}


	/**
	 * [宽带到期时间_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "MAX_EXPIRE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getMax_expire_date() {
		return max_expire_date;
	}

	/**
	 * [宽带到期时间_Set方法]
	 * 
	 * @author:
	 */
	public void setMax_expire_date(String max_expire_date) {
		this.max_expire_date = max_expire_date;
	}


	/**
	 * [是否宽带电视客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_KDDS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_kdds() {
		return is_kdds;
	}

	/**
	 * [是否宽带电视客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_kdds(String is_kdds) {
		this.is_kdds = is_kdds;
	}


	/**
	 * [近三个月宽带电视消费金额_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "MBH_SHOULD_FEE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getMbh_should_fee() {
		return mbh_should_fee;
	}

	/**
	 * [近三个月宽带电视消费金额_Set方法]
	 * 
	 * @author:
	 */
	public void setMbh_should_fee(String mbh_should_fee) {
		this.mbh_should_fee = mbh_should_fee;
	}


	/**
	 * [是否集团客户成员_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_GROUP", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_group() {
		return is_group;
	}

	/**
	 * [是否集团客户成员_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_group(String is_group) {
		this.is_group = is_group;
	}


	/**
	 * [是否集团客户关键人_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_GROUP_KEYPER", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_group_keyper() {
		return is_group_keyper;
	}

	/**
	 * [是否集团客户关键人_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_group_keyper(String is_group_keyper) {
		this.is_group_keyper = is_group_keyper;
	}


	/**
	 * [是否集团客户联系人_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_GROUP_LINKER", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_group_linker() {
		return is_group_linker;
	}

	/**
	 * [是否集团客户联系人_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_group_linker(String is_group_linker) {
		this.is_group_linker = is_group_linker;
	}

	/**
	 * [是否集团产品使用人_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_GROUP_ORDER", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_group_order() {
		return is_group_order;
	}

	/**
	 * [是否集团产品使用人_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_group_order(String is_group_order) {
		this.is_group_order = is_group_order;
	}


	/**
	 * [所属集团客户入网时间_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "CREATE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getCreate_date() {
		return create_date;
	}

	/**
	 * [所属集团客户入网时间_Set方法]
	 * 
	 * @author:
	 */
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}


	/**
	 * [行业类型_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "GROUP_INDUSTRY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getGroup_industry_id() {
		return group_industry_id;
	}

	/**
	 * [行业类型_Set方法]
	 * 
	 * @author:
	 */
	public void setGroup_industry_id(String group_industry_id) {
		this.group_industry_id = group_industry_id;
	}


	/**
	 * [集团客户等级_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "GROUP_LEVEL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getGroup_level_id() {
		return group_level_id;
	}

	/**
	 * [集团客户等级_Set方法]
	 * 
	 * @author:
	 */
	public void setGroup_level_id(String group_level_id) {
		this.group_level_id = group_level_id;
	}


	/**
	 * [是否集团专线客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_GROUP_ZX", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_group_zx() {
		return is_group_zx;
	}

	/**
	 * [是否集团专线客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_group_zx(String is_group_zx) {
		this.is_group_zx = is_group_zx;
	}


	/**
	 * [是否企业宽带客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_QYKD", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_qykd() {
		return is_qykd;
	}

	/**
	 * [是否企业宽带客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_qykd(String is_qykd) {
		this.is_qykd = is_qykd;
	}


	/**
	 * [是否集团V网客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_GROUP_VPMN", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_group_vpmn() {
		return is_group_vpmn;
	}

	/**
	 * [是否集团V网客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_group_vpmn(String is_group_vpmn) {
		this.is_group_vpmn = is_group_vpmn;
	}


	/**
	 * [是否IDC客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_IDC", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_idc() {
		return is_idc;
	}

	/**
	 * [是否IDC客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_idc(String is_idc) {
		this.is_idc = is_idc;
	}


	/**
	 * [是否IMS电话客户_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_IMS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_ims() {
		return is_ims;
	}

	/**
	 * [是否IMS电话客户_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_ims(String is_ims) {
		this.is_ims = is_ims;
	}


	/**
	 * [近三个月10086投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_10086_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_10086_counts() {
		return complain_10086_counts;
	}

	/**
	 * [近三个月10086投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_10086_counts(String complain_10086_counts) {
		this.complain_10086_counts = complain_10086_counts;
	}


	/**
	 * [其中：移动业务投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_YDYW_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_ydyw_counts() {
		return complain_ydyw_counts;
	}

	/**
	 * [其中：移动业务投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_ydyw_counts(String complain_ydyw_counts) {
		this.complain_ydyw_counts = complain_ydyw_counts;
	}


	/**
	 * [移动网络质量投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_YYWL_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_yywl_counts() {
		return complain_yywl_counts;
	}

	/**
	 * [移动网络质量投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_yywl_counts(String complain_yywl_counts) {
		this.complain_yywl_counts = complain_yywl_counts;
	}


	/**
	 * [家庭业务投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_JTYW_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_jtyw_counts() {
		return complain_jtyw_counts;
	}

	/**
	 * [家庭业务投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_jtyw_counts(String complain_jtyw_counts) {
		this.complain_jtyw_counts = complain_jtyw_counts;
	}


	/**
	 * [家庭宽带投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_JTKD_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_jtkd_counts() {
		return complain_jtkd_counts;
	}

	/**
	 * [家庭宽带投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_jtkd_counts(String complain_jtkd_counts) {
		this.complain_jtkd_counts = complain_jtkd_counts;
	}


	/**
	 * [宽带电视投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_JTDS_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_jtds_counts() {
		return complain_jtds_counts;
	}

	/**
	 * [宽带电视投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_jtds_counts(String complain_jtds_counts) {
		this.complain_jtds_counts = complain_jtds_counts;
	}


	/**
	 * [家庭宽带网络质量投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_JTWL_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_jtwl_counts() {
		return complain_jtwl_counts;
	}

	/**
	 * [家庭宽带网络质量投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_jtwl_counts(String complain_jtwl_counts) {
		this.complain_jtwl_counts = complain_jtwl_counts;
	}


	/**
	 * [集团业务投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_JT_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_jt_counts() {
		return complain_jt_counts;
	}

	/**
	 * [集团业务投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_jt_counts(String complain_jt_counts) {
		this.complain_jt_counts = complain_jt_counts;
	}


	/**
	 * [增值业务投诉次数_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "COMPLAIN_ZZ_COUNTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComplain_zz_counts() {
		return complain_zz_counts;
	}

	/**
	 * [增值业务投诉次数_Set方法]
	 * 
	 * @author:
	 */
	public void setComplain_zz_counts(String complain_zz_counts) {
		this.complain_zz_counts = complain_zz_counts;
	}


	/**
	 * [是否开通VOLTE_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_OFER1", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_ofer1() {
		return is_ofer1;
	}

	/**
	 * [是否开通VOLTE_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_ofer1(String is_ofer1) {
		this.is_ofer1 = is_ofer1;
	}


	/**
	 * [近三月是否有VOLTE通信_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "IS_VOLTE_3MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIs_volte_3month() {
		return is_volte_3month;
	}

	/**
	 * [近三月是否有VOLTE通信_Set方法]
	 * 
	 * @author:
	 */
	public void setIs_volte_3month(String is_volte_3month) {
		this.is_volte_3month = is_volte_3month;
	}
	
	/**
	 * [短信内容_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "MESSAGE_INFO", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getMessage_info() {
		return message_info;
	}

	/**
	 * [短信内容_Set方法]
	 * 
	 * @author:
	 */
	public void setMessage_info(String message_info) {
		this.message_info = message_info;
	}

	/**
	 * [id_Get方法]
	 * 
	 * @author:
	 */
	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	/**
	 * [id_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	/**
	 * [地市_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getCity_idCh() {
		return city_idCh;
	}
	*//**
	 * [地市_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setCity_idCh(String city_idCh) {
		this.city_idCh = city_idCh;
	}
	
	
	*//**
	 * [区县_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getCounty_idCh() {
		return county_idCh;
	}
	*//**
	 * [区县_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setCounty_idCh(String county_idCh) {
		this.county_idCh = county_idCh;
	}
	
	
	*//**
	 * [区域（城镇/农村_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getRegion_id1Ch() {
		return region_id1Ch;
	}
	*//**
	 * [区域（城镇/农村_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setRegion_id1Ch(String region_id1Ch) {
		this.region_id1Ch = region_id1Ch;
	}

	*//**
	 * [星级_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getStar_levelCh() {
		return star_levelCh;
	}
	*//**
	 * [星级_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setStar_levelCh(String star_levelCh) {
		this.star_levelCh = star_levelCh;
	}
	
	
	*//**
	 * [手机终端品牌_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getProvider_nameCh() {
		return provider_nameCh;
	}
	*//**
	 * [手机终端品牌_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setProvider_nameCh(String provider_nameCh) {
		this.provider_nameCh = provider_nameCh;
	}
	
	
	*//**
	 * [手机终端型号_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getTerm_nameCh() {
		return term_nameCh;
	}
	*//**
	 * [手机终端型号_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setTerm_nameCh(String term_nameCh) {
		this.term_nameCh = term_nameCh;
	}
	
	
	*//**
	 * [资费套餐名称_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getPlan_idCh() {
		return plan_idCh;
	}
	*//**
	 * [资费套餐名称_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setPlan_idCh(String plan_idCh) {
		this.plan_idCh = plan_idCh;
	}
	
	
	*//**
	 * [宽带套餐名称_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getOffer_nameCh() {
		return offer_nameCh;
	}
	*//**
	 * [宽带套餐名称_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setOffer_nameCh(String offer_nameCh) {
		this.offer_nameCh = offer_nameCh;
	}
	
	*//**
	 * [宽带带宽_中文Get方法]
	 * 
	 * @author:
	 *//*

	public String getProd_bandwidthCh() {
		return prod_bandwidthCh;
	}
	*//**
	 * [宽带带宽_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setProd_bandwidthCh(String prod_bandwidthCh) {
		this.prod_bandwidthCh = prod_bandwidthCh;
	}
	
	
	*//**
	 * [行业类型_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getGroup_industry_idCh() {
		return group_industry_idCh;
	}
	*//**
	 * [行业类型_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setGroup_industry_idCh(String group_industry_idCh) {
		this.group_industry_idCh = group_industry_idCh;
	}

	
	*//**
	 * [集团客户等级_中文Get方法]
	 * 
	 * @author:
	 *//*
	public String getGroup_level_idCh() {
		return group_level_idCh;
	}
	*//**
	 * [集团客户等级_中文Set方法]
	 * 
	 * @author:
	 *//*
	public void setGroup_level_idCh(String group_level_idCh) {
		this.group_level_idCh = group_level_idCh;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
