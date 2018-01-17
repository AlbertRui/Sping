package me.spring.aop.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class ValidationAspect {

	/**
	 * 验证切面测试的方法
	 * @param joinPoint
	 */
	public void validateArgs(JoinPoint joinPoint) {
		System.out.println("validate:" + Arrays.asList(joinPoint.getArgs()));
	}
}
