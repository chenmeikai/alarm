/**   
 * Copyright © 2018  
 * @Package: com.example.anotation.model   
 * @date: 2018年4月3日 下午2:08:02 
 */
package com.inesv.alarm.model;

import java.util.Date;

/**    
 * @Description:告警表 
 * @author: cmk
 * @date:   2018年4月3日 下午2:08:02      
 */
public class Alarm {
	
	private Long id;
	
	//告警类型
	private Integer type;
	
	//紧急等级
	private Integer grade;
	
	//触发告警事件（接口）
	private String event;
	
	//触发告警事件（接口）
	private String detail;
	
	//发生时间
	private Date createTime;
	
	//耗时情况
	private double useTime;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public double getUseTime() {
		return useTime;
	}
	public void setUseTime(double useTime) {
		this.useTime = useTime;
	}
	
	

}
