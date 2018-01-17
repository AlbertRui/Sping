package me.spring.aop.impl;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ��֤����,����ʹ��@Orderָ����������ȼ���ֵԽС�����ȼ�Խ��
 * @author Administrator
 *
 */
@Order(1)
@Aspect
@Component
public class ValidationAspect {

	/**
	 * ��֤������Է���
	 * @param joinPoint
	 */
	@Before("execution(public int me.spring.aop.impl.ArithmeticCalculator.*(..) )")
	public void validateArgs(JoinPoint joinPoint) {
		System.out.println("validate:" + Arrays.asList(joinPoint.getArgs()));
	}
}
