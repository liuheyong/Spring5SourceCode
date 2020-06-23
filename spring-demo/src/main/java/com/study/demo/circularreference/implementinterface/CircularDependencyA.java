package com.study.demo.circularreference.implementinterface;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: LiuHeYong
 * @create: 2020-04-26
 * @description:
 */

//@Component
public class CircularDependencyA implements ApplicationContextAware, InitializingBean {

	private CircularDependencyB circB;

	private ApplicationContext context;

	public CircularDependencyB getCircB() {
		return circB;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		circB = context.getBean(CircularDependencyB.class);
	}

	@Override
	public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
		context = ctx;
	}

}
