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
 * 把这个类声明为一个切面
 * 先把该类放入IOC容器中，再声明为一个切面
 * @author Administrator
 *
 */
@Aspect
@Component
public class LoggingAspect {

	/**
	 * 环绕通知需要携带ProceedingJoinPoint类型的参数
	 * 环绕通知类似于动态代理的全过程，ProceedingJoinPoint类型的参数可以决定是否执行目标类型的方法
	 * 且环绕通知必须有返回值，返回值为目标方法的返回值
	 * @param pjd
	 */
	@Around("execution(* me.spring.aop.impl.ArithmeticCalculator.*(int, int))")
	public Object aroundMethod(ProceedingJoinPoint pjd) {
		Object result = null;
		
		String methodName = pjd.getSignature().getName();
		//执行目标方法
		try {
			//前置通知
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
			result = pjd.proceed();
			//返回通知
			System.out.println("The method " + methodName + " ends with " + result);
		} catch (Throwable e) {
			//异常通知
			System.out.println("The method " + methodName + " occurs exception: " + e);
			throw new RuntimeException(e);
		}
		//后置通知
		System.out.println("The method " + methodName + " ends");
		return result;
	}
	
	/**
	 * 声明切入点表达式，一般的，该方法中不需要加入其他代码
	 * 使用PointCut声明切入点表达式
	 * 后面的其他通知直接使用方法名来引用切入点表达式
	 */
	@Pointcut("execution(* me.spring.aop.impl.ArithmeticCalculator.*(..))")
	public void declareJoinPointExpression() {
		
	}
	
	/**
	 * 在me.spring.aop.impl.ArithmeticCalculator接口的实现类的每一个	方法执行之前执行一段代码
	 * 声明该方法是一个前置通知
	 */
	@Before("declareJoinPointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method " + methodName + " begin with " + args);
	}
	
	/**
	 * 后置通知：在目标方法执行后（无论是否发生异常）执行的通知
	 * 不能访问目标方法执行的结果
	 */
	@After("execution(* me.spring.aop.impl.ArithmeticCalculator.*(int, int))")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends");
	}
	
	/**
	 * 在方法正常结束后执行的代码
	 * 返回通知是可以访问到方法名的
	 * @param joinPoint
	 */
	@AfterReturning(value="execution(* me.spring.aop.impl.ArithmeticCalculator.*(int, int))", returning="result")
	public void arterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end with " + result);
	}
	
	/**
	 * 在目标方法出现异常时执行的代码
	 * 可以访问到异常对象，且可以指定在出现特定异常时在执行通知的代码
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(value="execution(* me.spring.aop.impl.ArithmeticCalculator.*(int, int))", throwing="exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception: " + exception);
	}
	
}
