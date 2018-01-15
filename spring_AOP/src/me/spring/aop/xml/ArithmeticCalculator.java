package me.spring.aop.xml;

/**
 * 提供加减乘除计算的接口
 * @author Administrator
 *
 */
public interface ArithmeticCalculator {

	int add(int i, int j);
	int sub(int i, int j);
	int mul(int i, int j);
	int div(int i, int j);
}
