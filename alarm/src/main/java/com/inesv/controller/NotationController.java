/**   
 * Copyright © 2018  
 * @Package: com.inesv.controller   
 * @date: 2018年4月4日 上午9:05:08 
 */
package com.inesv.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inesv.service.AnotationService;

/**    
 * @Description:TODO 
 * @author: cmk
 * @date:   2018年4月4日 上午9:05:08      
 */
@RestController
public class NotationController {
	
	@Resource
	private AnotationService anotationService;
	
	@RequestMapping("anotation")
	public String test() {
		String name="宏强";
		int age =2;
		try {
			anotationService.test(name,age);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "hello";
	}

}
