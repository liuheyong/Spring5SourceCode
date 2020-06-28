package com.study.demo.aspect.useinterface;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: liuheyong
 * @create: 2019-12-30
 * @description:
 */
//@Component
//@Aspect
public class SpringAspect {

	//*可以使用在任何地方，标识一个到多个字符。..用在包名和参数列表中，标识当前包和其子包以及任意多个参数。+用在类名和接口名后面标识当前类及其子类以及当前接口及其子接口
	@Pointcut("execution(* com.study.demo..helloSpringInterface(..))")
	private void methodPointcut() {
	}

	@Before("methodPointcut()")
	public void myAdvice() {
		System.out.println("IndexDaoInterface.helloSpringInterface ======> 开始before前置通知");
	}

}
