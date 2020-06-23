package com.study.demo.circularreference.constructorinject;

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

	//@Autowired
	public CircularDependencyB(CircularDependencyA circA) {
		this.circA = circA;
	}

}
