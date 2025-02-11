package com.telus.csm.ewlnsc.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.framework.config.spring.PlaceholderConfigurer;

@Configuration
@EnableAspectJAutoProxy
@ImportResource("classpath:/com/telus/csm/ewlnsc/adapter/util/wireline-sales-ejb-config.xml")
public class EnterpriseWlnSalesServiceConfig {
	
	@Autowired
	BeanFactory beanFactory;
	
	@Bean
	 public static PlaceholderConfigurer placeHolderConfigurer() {
		PlaceholderConfigurer placeholderConfigurer = new PlaceholderConfigurer();
		placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return  placeholderConfigurer;
		 
	 }	
	@Bean
	 public static ApplicationProperties applicationProperties() {
		ApplicationProperties applicationProperties = new ApplicationProperties();
		return  applicationProperties;
	 }	
	
}
	
	
//	<bean id="OrderManager" class="com.telus.csm.ewlnss.adapter.common.EjbClientFactory">
//	<property name="businessInterface"
//		value="com.telus.ucss.wirelinesales.ejb.OrderManager" />
//	<property name="jndiName"
//		value="om#com.telus.ucss.wirelinesales.ejb.OrderManager" />
//	<property name="url"
//		value="${connections/ejbServices/wirelineSalesEjbSvr}" />

