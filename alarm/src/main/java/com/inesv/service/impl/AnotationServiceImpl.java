package com.inesv.service.impl;

import org.springframework.stereotype.Service;

import com.inesv.alarm.anotation.AlarmLog;
import com.inesv.service.AnotationService;

@Service
public class AnotationServiceImpl implements AnotationService {
  
	
	
	@Override
	@AlarmLog(alarmTime=3,isEmail=true)
	public void test(String name,int age) throws Exception {
		String wrong=null;
//		wrong.equals("asdf");
		System.out.println("hello world");
		throw new Exception("haha");
	}

}
