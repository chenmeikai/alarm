/**   
 * Copyright © 2018  
 * @Package: com.example.anotation   
 * @date: 2018年4月3日 上午8:56:01 
 */
package com.inesv.alarm.anotation;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.inesv.alarm.mapper.AlarmMapper;
import com.inesv.alarm.model.Alarm;
import com.inesv.alarm.utils.PropertiesUtil;
import com.inesv.alarm.utils.SendEmail;
import com.inesv.alarm.utils.StringUtils;
import com.inesv.alarm.utils.ThreadUtils;

/**
 * @Description:告警注解
 * @author: cmk
 * @date: 2018年4月3日 上午8:56:01
 */
@Aspect // AOP 切面
@Component
public class AlarmAspect {

	@Autowired
	private AlarmMapper alarmMapper;
	String host;
	String sender;
	String password;
	String receiver;

	// 声明个切面，切到 com.inesv.service.* 这个目录下，以save开头的方法，方法参数(..)和返回类型(*)不限
	@Pointcut("execution(* com.*.service.*.*.*(..))")
	private void pointcut() {
	}

	/**
	 * 在方法执行前后 环绕式
	 * @param point
	 * @param myLog
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "(execution(* *.*.*..*(..))) && @annotation(alarmLog)")
	public Object around(ProceedingJoinPoint joinPoint, AlarmLog alarmLog) throws Throwable {

		long alarmTime = alarmLog.alarmTime();

		boolean isEmail = alarmLog.isEmail();

		// 开始时间
		Date startDate = new Date();

		// 目标组件的类名
		String className = joinPoint.getTarget().getClass().getName();
		// 调用的方法名
		String method = joinPoint.getSignature().getName();
		// 访问目标方法的参数
		Object[] args = joinPoint.getArgs();
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			sb.append("-" + arg + "-");
		}
		String params = sb.toString();

		// 执行程序
		Object obj = joinPoint.proceed();

		/**
		 * 在调用目标组件业务方法后业务处理
		 */

		// 结束时间
		Date endDate = new Date();
		long useTime = endDate.getTime() - startDate.getTime();

		// 耗时达到告警时间,则记录
		if (useTime >= alarmTime * 1000) {
			String detail = "(超时（类名:" + className + ")--(方法:" + method + ")--(参数:" + params + ")";
			Alarm alarm = new Alarm();
			alarm.setType(0);
			alarm.setGrade(0);
			alarm.setDetail(detail);
			alarm.setEvent(className + "." + method);
			alarm.setUseTime(useTime);
			alarm.setCreateTime(startDate);

			// 发送邮件
			if (isEmail == true) {
				// 查询上一次该接口发生告警的时间，判断是否已过冷却时间(30分钟)，是则发送短信
				Date date = alarmMapper.getByEventLastTime(className + "." + method);
				Long coolTime = 30 * 60 * 1000L;
				if (date != null) {
					Date lastDate =date;
					coolTime = startDate.getTime() - lastDate.getTime();
				}
				if (coolTime >= 30 * 60 * 1000L) {
					// 异步发送邮件
					try {
						ThreadUtils.execute(new Runnable() {
							@Override
							public void run() {
								try {
									// 获取邮箱相关参数，并发送邮件
									try {
										host = PropertiesUtil.getProperty("alarm.email.host");
										sender = PropertiesUtil.getProperty("alarm.email.sender");
										password = PropertiesUtil.getProperty("alarm.email.password");
										receiver = PropertiesUtil.getProperty("alarm.email.receiver");
										if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(sender)
												&& StringUtils.isNotBlank(password)
												&& StringUtils.isNotBlank(receiver)) {
											SendEmail.sendAlarmEmail(host, sender, password, receiver, detail);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}, 2, TimeUnit.MILLISECONDS);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			alarmMapper.save(alarm);
		}
		return obj;
	}

	
	/**
	 * 方法执行后 并抛出异常
	 *
	 * @param joinPoint
	 * @param myLog
	 * @param ex
	 */
	@AfterThrowing(value = "(execution(* *.*.*..*(..))) && @annotation(alarmLog)", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, AlarmLog alarmLog, Exception ex) {

		boolean isEmail = alarmLog.isEmail();

		// 开始时间
		Date startDate = new Date();
		// 目标组件的类名
		String className = joinPoint.getTarget().getClass().getName();
		// 调用的方法名
		String method = joinPoint.getSignature().getName();
		// 访问目标方法的参数
		Object[] args = joinPoint.getArgs();
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			sb.append("-" + arg + "-");
		}
		String params = sb.toString();
		String detail = "(异常(类名:" + className + ")--(方法:" + method + ")--(参数:" + params + ")";
		Alarm alarm = new Alarm();
		alarm.setType(1);
		alarm.setGrade(0);
		alarm.setDetail(detail);
		alarm.setEvent(className + "." + method);
		alarm.setUseTime(0);
		alarm.setCreateTime(startDate);

		// 发送邮件
		if (isEmail == true) {
			// 查询上一次该接口发生告警的时间，判断是否已过冷却时间(30分钟)，是则发送短信
			Date date = alarmMapper.getByEventLastTime(className + "." + method);
			Long coolTime = 30 * 60 * 1000L;
			if (date != null) {
				Date lastDate =date;
				coolTime = startDate.getTime() - lastDate.getTime();
			}
			if (coolTime >= 30 * 60 * 1000L) {
				// 异步发送短信
				try {
					ThreadUtils.execute(new Runnable() {
						@Override
						public void run() {
							try {
								// 获取邮箱相关参数，并发送邮件
								try {
									host = PropertiesUtil.getProperty("alarm.email.host");
									sender = PropertiesUtil.getProperty("alarm.email.sender");
									password = PropertiesUtil.getProperty("alarm.email.password");
									receiver = PropertiesUtil.getProperty("alarm.email.receiver");
									if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(sender)
											&& StringUtils.isNotBlank(password) && StringUtils.isNotBlank(receiver)) {
										SendEmail.sendAlarmEmail(host, sender, password, receiver, detail);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}, 2, TimeUnit.MILLISECONDS);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// 添加记录
		alarmMapper.save(alarm);
	}

}
