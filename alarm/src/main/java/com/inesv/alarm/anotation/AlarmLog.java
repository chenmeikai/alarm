package com.inesv.alarm.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
@Documented 
public @interface AlarmLog {  
    
	long  alarmTime() default 3;
	
	boolean isEmail() default false ;
}  
