package com.study.demo.circularreference.usepostconstructannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: LiuHeYong
 * @create: 2020-04-26
 * @description:
 */

//@Component
public class CircularDependencyA {

	@Autowired
	private CircularDependencyB circB;

	public CircularDependencyB getCircB() {
		return circB;
	}

	@PostConstruct
	public void init() {
		circB.setCircA(this);
	}

	public void setCircB(CircularDependencyB circB) {
		this.circB = circB;
	}

	public String getMessageA() {
		return circB.getMessageB();
	}

}
