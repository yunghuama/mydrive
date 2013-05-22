package com.platform.constants;

import java.util.HashMap;
import java.util.Map;


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
	public static final int questionType_3 = 4;
	
	public static final String SECTION_TYPE_1 = "1";//科目1
	public static final String SECTION_TYPE_3 = "3";//科目3
	
	public static Map<String,Integer> questionType = new HashMap<String,Integer>();
	
	public static final String NO_LOGIN = "nologin";
	
	static {
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
	}
}