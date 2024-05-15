package com.pm.cr.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<PredefinedHeaderFilter> customPredefinedHeaderFilter() {
		FilterRegistrationBean<PredefinedHeaderFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new PredefinedHeaderFilter());
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}
