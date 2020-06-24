package com.study.demo.aspect.useinterface;

import org.springframework.stereotype.Component;

@Component
public class IndexDaoImpl implements IndexDaoInterface{

	public void helloSpringInterface() {
		System.out.println("IndexDao ==========> Hello Spring");
	}

}
