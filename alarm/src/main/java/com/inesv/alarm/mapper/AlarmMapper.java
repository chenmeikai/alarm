/**   
 * Copyright © 2018  
 * @Package: com.example.anotation.mapper   
 * @date: 2018年4月3日 下午2:13:25 
 */
package com.inesv.alarm.mapper;
import com.inesv.alarm.model.Alarm;

/**    
 * @Description:TODO 
 * @author: cmk
 * @date:   2018年4月3日 下午2:13:25      
 */
public interface AlarmMapper {
	
	public int save(Alarm alarm);
	
	//获取接口的最后一次告警记录
	public Alarm getByEventLastTime(String event);

}
