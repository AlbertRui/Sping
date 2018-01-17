package me.spring.aop.helloworld;

/**
 * ������
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) {
		ArithmeticCalculator target = new ArithmeticCalculatorImpl();
		ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(target).getLoggingProxy();
		
		int result = proxy.add(1, 2);
		System.out.println("-->" + result);
		result = proxy.div(2, 3);
		System.out.println("-->" + result);
	}
}
