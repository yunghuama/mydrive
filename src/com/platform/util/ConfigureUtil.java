package com.platform.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 *    说明:  配置文件工具
 * </p>
 *
 * 作者:马永华
 * 时间:2012-8-28 下午03:08:32
 * 版本:V1.0
 */
public class ConfigureUtil {

	private static Properties properties;
	
	//获得Properties
	private  Properties getProperties(){
		if(properties==null){
			properties = new Properties();
			InputStream in = ConfigureUtil.this.getClass().getClassLoader().getResourceAsStream("/DriverConfig.properties"); 
			try {
				properties.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	//根据名字
	public  String get(String name){
		return getProperties().getProperty(name);
	}
}
