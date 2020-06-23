package com.study.demo;

import com.study.demo.circularreference.implementinterface.CircularDependencyA;
import com.study.demo.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		//applicationContext.register(CircularDependencyA.class);
		//applicationContext.refresh();
		//System.out.println(applicationContext.getBeanDefinitionCount());
		//System.out.println(applicationContext.getBeanDefinitionNames());
		//System.out.println(applicationContext.getBeanFactoryPostProcessors());
		//CircularDependencyA circularDependencyA = applicationContext.getBean(CircularDependencyA.class);
		//System.out.println(circularDependencyA.getCircB().getMessage());
	}
}
