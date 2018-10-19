package com.wy.model.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wy.model.interceptor.LoginInterceptor;
import com.wy.model.interceptor.TimerInterceptor;

/**
 * mvc基本配置
 * 
 * @author Lenovo
 *
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/test").setViewName("/test/index");
		registry.addViewController("/main").setViewName("main");
	}

	/**
	 * 配置fastJson
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);
	}

	/**
	 * 拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 计时拦截器
		registry.addInterceptor(new TimerInterceptor()).addPathPatterns("/**");
		// 登录拦截器
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
			.excludePathPatterns("/","/login","/error","/assets/**","/test/**","/js/**");
	}

}
