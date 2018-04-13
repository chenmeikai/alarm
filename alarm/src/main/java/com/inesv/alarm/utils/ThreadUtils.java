/**   
 * Copyright © 2018 
 * @Package: Thread.java 
 * @author: Administrator   
 * @date: 2018年3月18日 下午5:10:34 
 */
package com.inesv.alarm.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:TODO
 * @author: cmk
 * @date: 2018年3月18日 下午5:10:34
 */
public class ThreadUtils {


    static ScheduledExecutorService scheduledThreadPool;

    
    static int MAX_THREAD=3;
    
    
    
    /**
     * 用来创建一个定长线程池，并且支持定时和周期性的执行任务
     * @param runnable
     * @param delay 延迟执行
     * @param unit  时间单位
     * @return
     */
    public static ExecutorService execute(Runnable runnable, long delay, TimeUnit unit) {
        if (scheduledThreadPool == null)
            scheduledThreadPool = Executors.newScheduledThreadPool(MAX_THREAD);
        scheduledThreadPool.schedule(runnable, delay, unit);
        return scheduledThreadPool;
    }
    
    public final static void scheduledPoolClose() {
    	 if (scheduledThreadPool != null)
    		 scheduledThreadPool.shutdown();
    }


}