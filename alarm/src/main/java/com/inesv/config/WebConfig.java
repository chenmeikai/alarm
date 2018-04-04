/**
 * 
 */
package com.inesv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author meikai
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	// 默认首页
	/*@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/html/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}*/
	
	

	// 跨域
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**") // 配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径
				.allowedMethods("*") // 允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等
				.allowedOrigins("*") // 允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，如："http://www.baidu.com"，只有百度可以访问我们的跨域资源
				.allowedHeaders("*"); // 允许所有的请求header访问，可以自定义设置任意请求头信息，如："X-YAUTH-TOKEN"
	}

	// 虚拟路径映射
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 将所有/static/** 访问都映射到classpath:/static/ 目录下
		// registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		// 将所有file/** 访问都映射到file:F:/pictures/ 目录下
		registry.addResourceHandler("/file/**").addResourceLocations("file:F:/pictures/");
	}*/

}