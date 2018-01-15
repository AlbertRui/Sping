package me.spring.aop.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArithmeticCalculator atc = (ArithmeticCalculator) ctx.getBean("arithmeticCalculatorImpl");
		int result = atc.add(1, 2);
		System.out.println("result:" + result);
		result = atc.div(4, 2);
		System.out.println("result:" + result);
	}
}
