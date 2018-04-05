/**   
 * Copyright © 2018 
 * @Package: StringUtils.java 
 * @author: Administrator   
 * @date: 2018年4月5日 下午4:22:32 
 */
package com.inesv.alarm.utils;

/**
 * @Description:TODO
 * @author: cmk
 * @date: 2018年4月5日 下午4:22:32
 */
public class StringUtils {

	public static boolean isBlank(String text) {

		boolean flag = false;
		if (text == null || "".equals(text) == true) {
			flag = true;
		}
			
		return flag;
	}

	public static boolean isNotBlank(String text) {

		boolean flag = true;
		if (text == null || "".equals(text) == true) {
			flag = false;
		}
		return flag;
	}

}
