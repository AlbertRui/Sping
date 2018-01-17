package me.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-xml.xml");
		ArithmeticCalculator atc = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");
		int result = atc.add(1, 2);
		System.out.println("result:" + result);
		result = atc.div(4, 2);
		System.out.println("result:" + result);
	}
}
