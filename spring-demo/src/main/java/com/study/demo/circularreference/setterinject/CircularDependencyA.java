package com.study.demo.circularreference.setterinject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: LiuHeYong
 * @create: 2020-04-26
 * @description:
 */

//@Component
public class CircularDependencyA {

	//@Autowired 用在字段上和用在setter方法上效果是一样的
	private CircularDependencyB circB;

	public CircularDependencyB getCircB() {
		return circB;
	}

	//@Autowired
	public void setCircB(CircularDependencyB circB) {
		this.circB = circB;
	}

	public String getMessageA() {
		return circB.getMessageB();
	}

}
