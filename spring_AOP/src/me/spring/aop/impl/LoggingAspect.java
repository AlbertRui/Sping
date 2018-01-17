package me.spring.aop.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * �����������Ϊһ������
 * �ȰѸ������IOC�����У�������Ϊһ������
 * @author Administrator
 *
 */
@Aspect
@Component
public class LoggingAspect {

	/**
	 * ����֪ͨ��ҪЯ��ProceedingJoinPoint���͵Ĳ���
	 * ����֪ͨ�����ڶ�̬�����ȫ���̣�ProceedingJoinPoint���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�����͵ķ���
	 * �һ���֪ͨ�����з���ֵ������ֵΪĿ�귽���ķ���ֵ
	 * @param pjd
	 */
	@Around("execution(* me.spring.aop.impl.ArithmeticCalculator.*(int, int))")
	public Object aroundMethod(ProceedingJoinPoint pjd) {
		Object result = null;
		
		String methodName = pjd.getSignature().getName();
		//ִ��Ŀ�귽��
		try {
			//ǰ��֪ͨ
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
			result = pjd.proceed();
			//����֪ͨ
			System.out.println("The method " + methodName + " ends with " + result);
		} catch (Throwable e) {
			//�쳣֪ͨ
			System.out.println("The method " + methodName + " occurs exception: " + e);
			throw new RuntimeException(e);
		}
		//����֪ͨ
		System.out.println("The method " + methodName + " ends");
		return result;
	}
	
	/**
	 * �����������ʽ��һ��ģ��÷����в���Ҫ������������
	 * ʹ��PointCut�����������ʽ
	 * ���������ֱ֪ͨ��ʹ�÷������������������ʽ
	 */
	@Pointcut("execution(* me.spring.aop.impl.ArithmeticCalculator.*(..))")
	public void declareJoinPointExpression() {
		
	}
	
	/**
	 * ��me.spring.aop.impl.ArithmeticCalculator�ӿڵ�ʵ�����ÿһ��	����ִ��֮ǰִ��һ�δ���
	 * �����÷�����һ��ǰ��֪ͨ
	 */
	@Before("declareJoinPointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method " + methodName + " begin with " + args);
	}
	
	/**
	 * ����֪ͨ����Ŀ�귽��ִ�к������Ƿ����쳣��ִ�е�֪ͨ
	 * ���ܷ���Ŀ�귽��ִ�еĽ��
	 */
	@After("execution(* me.spring.aop.impl.ArithmeticCalculator.*(int, int))")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends");
	}
	
	/**
	 * �ڷ�������������ִ�еĴ���
	 * ����֪ͨ�ǿ��Է��ʵ���������
	 * @param joinPoint
	 */
	@AfterReturning(value="execution(* me.spring.aop.impl.ArithmeticCalculator.*(int, int))", returning="result")
	public void arterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end with " + result);
	}
	
	/**
	 * ��Ŀ�귽�������쳣ʱִ�еĴ���
	 * ���Է��ʵ��쳣�����ҿ���ָ���ڳ����ض��쳣ʱ��ִ��֪ͨ�Ĵ���
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(value="execution(* me.spring.aop.impl.ArithmeticCalculator.*(int, int))", throwing="exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception: " + exception);
	}
	
}
