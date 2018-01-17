package me.spring.aop.impl;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 验证切面,可以使用@Order指定切面的优先级，值越小，优先级越高
 * @author Administrator
 *
 */
@Order(1)
@Aspect
@Component
public class ValidationAspect {

	/**
	 * 验证切面测试方法
	 * @param joinPoint
	 */
	@Before("execution(public int me.spring.aop.impl.ArithmeticCalculator.*(..) )")
	public void validateArgs(JoinPoint joinPoint) {
		System.out.println("validate:" + Arrays.asList(joinPoint.getArgs()));
	}
}
