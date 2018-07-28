package com.aerohive.nms.a3.communicator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.aerohive.nms.a3.communicator.interceptor.ConnectionStatusInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
	
	@Autowired
	private ConnectionStatusInterceptor connStatusInterceptor;

	@Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(connStatusInterceptor);
        super.addInterceptors(registry);
    }
}
