package com.study.demo;

import com.study.demo.aspect.useclass.myclass.IndexDao;
import com.study.demo.aspect.useinterface.IndexDaoImpl;
import com.study.demo.aspect.useinterface.IndexDaoInterface;
import com.study.demo.config.Config;
import com.study.demo.initbean.InitSequenceBean;
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

		//IndexDao dao = (IndexDao) applicationContext.getBean(IndexDao.class);
		//dao.helloSpring();

		//IndexDaoInterface dao = (IndexDaoInterface) applicationContext.getBean(IndexDaoInterface.class);
		//dao.helloSpringInterface();

		applicationContext.getBean(InitSequenceBean.class);

	}
}
