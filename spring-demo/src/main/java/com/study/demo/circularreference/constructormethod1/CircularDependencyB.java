package com.study.demo.circularreference.constructormethod1;

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
