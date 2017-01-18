package com.hangaebal.config;

import com.hangaebal.interceptor.MenuInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * Created by hcs on 2017. 1. 11..
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public MenuInterceptor menuInterceptor() {
		return new MenuInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(menuInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
	}
}
