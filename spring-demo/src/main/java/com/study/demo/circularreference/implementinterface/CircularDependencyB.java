package com.study.demo.circularreference.implementinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: LiuHeYong
 * @create: 2020-04-26
 * @description:
 */
//@Component
public class CircularDependencyB {

	private CircularDependencyA circA;

	private String message = "Hi!";

	@Autowired
	public void setCircA(CircularDependencyA circA) {
		this.circA = circA;
	}

	public String getMessage() {
		return message;
	}


}
