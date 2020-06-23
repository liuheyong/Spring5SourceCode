package com.study.demo.circularreference.constructormethod1;

import org.springframework.context.annotation.Lazy;

/**
 * @author: LiuHeYong
 * @create: 2020-04-26
 * @description:
 */

//@Component
public class CircularDependencyA {

	private CircularDependencyB circB;

	//@Autowired
	public CircularDependencyA(@Lazy CircularDependencyB circB) {
		this.circB = circB;
	}

}
