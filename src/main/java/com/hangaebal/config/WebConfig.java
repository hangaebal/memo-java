package com.hangaebal.config;

import com.hangaebal.interceptor.MenuInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * Created by hcs on 2017. 1. 11..
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Value("${upload.directory}")
	String UPLOAD_DERECTORY;

	@Bean
	public MenuInterceptor menuInterceptor() {
		return new MenuInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(menuInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:" + UPLOAD_DERECTORY);
	}
}
