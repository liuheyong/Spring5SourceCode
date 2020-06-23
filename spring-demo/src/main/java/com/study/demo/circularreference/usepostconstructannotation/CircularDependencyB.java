package com.study.demo.circularreference.usepostconstructannotation;

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

	private String message = "Hi CircularDependencyB !";

	public CircularDependencyA getCircA() {
		return circA;
	}

	public void setCircA(CircularDependencyA circA) {
		this.circA = circA;
	}

	public String getMessageB() {
		return message;
	}

}
