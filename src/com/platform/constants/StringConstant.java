package com.platform.constants;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.platform.util.LoginBean;


/**
 * 字符串常量
 * 
 * @author MarkerKing
 *
 */
public final class StringConstant {
	private StringConstant(){}
	//系统中的角色
	public static final String ROLE_STUDENT = "STUDENT";
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_SCHOOL = "SCHOOL";
	
	public static final int questionType_car = 0;
	public static final int questionType_bus = 1;
	public static final int questionType_truck = 2;
	public static final int questionType_moto = 3;
	public static final int questionType_3 = 4;//科目三
	public static final int questionType_2 =5;//科目二视频
	
	public static final String SECTION_TYPE_1 = "1";//科目1
	public static final String SECTION_TYPE_3 = "3";//科目3
	
	//图片存储目录
	public static final String PATH_IMAGE_SUB1 = "sub1Image";//科目附件片目录
	public static final String PATH_IMAGE_SUB2 = "sub2Image";//科目二附件目录
	public static final String PATH_IMAGE_SUB3 = "sub3Image";//科目三附件目录
	public static final String PATH_IMAGE_ADVER = "adverImage";//广告附件目录
	
	//权限菜单
	public static final String NAV_INDEX = "index";
	//科目一
	public static final String NAV_SUB1 = "sub1";
	public static final String NAV_SUB1_EXERCISE = "sub1_exercise";
	public static final String NAV_SUB1_SIMULATION = "sub1_simulation";
	public static final String NAV_SUB1_ORDER = "sub1_order";
	public static final String NAV_SUB1_MARK = "sub1_mark";
	public static final String NAV_SUB1_WRONG = "sub1_wrong";
	//科目二
	public static final String NAV_SUB2 = "sub2";
	public static final String NAV_SUB2_ANIMATION = "sub2_animation";
	//科目三
	public static final String NAV_SUB3 = "sub3";
	public static final String NAV_SUB3_EXERCISE = "sub3_exercise";
	public static final String NAV_SUB3_SIMULATION = "sub3_simulation";
	public static final String NAV_SUB3_ORDER = "sub3_order";
	public static final String NAV_SUB3_MARK = "sub3_mark";
	public static final String NAV_SUB3_WRONG = "sub3_wrong";
	//个人中心
	public static final String NAV_USER = "user";
	public static final String NAV_USER_INFO = "user_info";
	public static final String NAV_USER_PASS = "user_pass";
	public static final String NAV_USER_MONEY = "user_money";
	//驾校
	public static final String NAV_SCHOOL = "school";
	public static final String NAV_SCHOOL_ANNOUNCE = "school_announce";
	public static final String NAV_SCHOOL_ANNOUNCE_ADMIN = "school_announce_admin";
	public static final String NAV_SCHOOL_SCORE1 = "school_score1";
	public static final String NAV_SCHOOL_SCORE3 = "school_score3";
	//题库管理
	public static final String NAV_QUESTION = "question";
	public static final String NAV_QUESTION_ADMIN = "question_admin";
	
	//广告管理
	public static final String NAV_ADS = "ads";
	
	public static Map<String,Integer> questionType = new Hashtable<String,Integer>();
	public static Map<String,String> operate = new HashMap<String,String>();
	public static Map<String,String> loginInfo = new HashMap<String,String>();
	
	//广告相关
	public static Map<String,String> advPage = new HashMap<String,String>();
	public static Map<String,String> advPosition = new HashMap<String,String>();
	
	public static final String NO_LOGIN = "nologin";
	
	static {
		//设置问题类型
		questionType.put("c1", questionType_car);
		questionType.put("c2", questionType_car);
		questionType.put("c3", questionType_car);
		questionType.put("c4", questionType_car);
		questionType.put("c5", questionType_car);
		questionType.put("a1", questionType_bus);
		questionType.put("a3", questionType_bus);
		questionType.put("b1", questionType_bus);
		questionType.put("a2", questionType_truck);
		questionType.put("b2", questionType_truck);
		questionType.put("d", questionType_moto);
		questionType.put("e", questionType_moto);
		questionType.put("f", questionType_moto);
		
		//设置学员菜单
		operate.put(ROLE_STUDENT+NAV_INDEX, "T");
		operate.put(ROLE_STUDENT+NAV_SUB1, "T");
		operate.put(ROLE_STUDENT+NAV_SUB1_EXERCISE, "T");
		operate.put(ROLE_STUDENT+NAV_SUB1_SIMULATION, "T");
		operate.put(ROLE_STUDENT+NAV_SUB1_ORDER, "T");
		operate.put(ROLE_STUDENT+NAV_SUB1_MARK, "T");
		operate.put(ROLE_STUDENT+NAV_SUB1_WRONG, "T");
		operate.put(ROLE_STUDENT+NAV_SUB2, "T");
		operate.put(ROLE_STUDENT+NAV_SUB2_ANIMATION, "T");
		operate.put(ROLE_STUDENT+NAV_SUB3, "T");
		operate.put(ROLE_STUDENT+NAV_SUB3_EXERCISE, "T");
		operate.put(ROLE_STUDENT+NAV_SUB3_SIMULATION, "T");
		operate.put(ROLE_STUDENT+NAV_SUB3_ORDER, "T");
		operate.put(ROLE_STUDENT+NAV_SUB3_MARK, "T");
		operate.put(ROLE_STUDENT+NAV_SUB3_WRONG, "T");
		operate.put(ROLE_STUDENT+NAV_USER, "T");
		operate.put(ROLE_STUDENT+NAV_USER_INFO, "T");
		operate.put(ROLE_STUDENT+NAV_USER_PASS, "T");
		operate.put(ROLE_STUDENT+NAV_USER_MONEY, "T");
		operate.put(ROLE_STUDENT+NAV_SCHOOL, "T");
		operate.put(ROLE_STUDENT+NAV_SCHOOL_ANNOUNCE, "T");
		
		//设置驾校菜单
		operate.put(ROLE_SCHOOL+NAV_INDEX, "T");
		operate.put(ROLE_SCHOOL+NAV_USER, "T");
		operate.put(ROLE_SCHOOL+NAV_USER_PASS, "T");
		operate.put(ROLE_SCHOOL+NAV_SCHOOL, "T");
		operate.put(ROLE_SCHOOL+NAV_SCHOOL_ANNOUNCE_ADMIN, "T");
		operate.put(ROLE_SCHOOL+NAV_SCHOOL_SCORE1, "T");
		operate.put(ROLE_SCHOOL+NAV_SCHOOL_SCORE3, "T");
		
		//设置管理员
		operate.put(ROLE_ADMIN+NAV_INDEX, "T");
		operate.put(ROLE_ADMIN+NAV_USER, "T");
		operate.put(ROLE_ADMIN+NAV_USER_PASS, "T");
		operate.put(ROLE_ADMIN+NAV_QUESTION, "T");
		operate.put(ROLE_ADMIN+NAV_QUESTION_ADMIN, "T");
		operate.put(ROLE_ADMIN+NAV_ADS, "T");
		
		advPage.put("0", "登录页面");
		advPage.put("1", "工作台页面");
		advPosition.put("0","左边");
		advPosition.put("1","右边");
	}
	
	public static String getAdvPage(String type){
		if(type==null||"".equals(type))
			return "";
		return advPage.get(type);
	}
	
	public static String getPosition(String type){
		if(type==null||"".equals(type))
			return "";
		return advPosition.get(type);
	}
	
	//判断操作权限
	public static String getOperate(String operateId){
		String op = "F";
		try{
			LoginBean loginBean = LoginBean.getLoginBean();
			if(loginBean==null) return op;
			op = operate.get(loginBean.getUser().getRole()+operateId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return op;
	}
}