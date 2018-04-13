/**   
 * Copyright © 2018  
 * @Package: com.example.anotation.mapper   
 * @date: 2018年4月3日 下午2:13:25 
 */
package com.inesv.alarm.mapper;
import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.inesv.alarm.model.Alarm;

/**    
 * @Description:TODO 
 * @author: cmk
 * @date:   2018年4月3日 下午2:13:25      
 */
public interface AlarmMapper {
	
	@Insert("insert into t_alarm(type,grade,event,detail,create_time,use_time)values(#{type},#{grade},#{event},#{detail},now(),#{useTime})")
	public int save(Alarm alarm);
	
	//获取接口的最后一次告警记录
	@Select("select create_time from t_alarm WHERE event=#{event,jdbcType=VARCHAR} order by id desc limit 1")    
	public Date getByEventLastTime(String event);

}
