package me.spring.aop.impl;

import org.springframework.stereotype.Component;

/**
 * 加减乘除计算的具体实现
 * @author Administrator
 *
 */
@Component
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

	@Override
	public int add(int i, int j) {
		return i + j;
	}

	@Override
	public int sub(int i, int j) {
		return i - j;
	}

	@Override
	public int mul(int i, int j) {
		return i * j;
	}

	@Override
	public int div(int i, int j) {
		return i / j;
	}

}
