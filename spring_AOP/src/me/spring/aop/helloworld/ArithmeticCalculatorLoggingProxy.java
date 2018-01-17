package me.spring.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理实现日志
 * @author Administrator
 *
 */
public class ArithmeticCalculatorLoggingProxy {

	//要代理的对象
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	
	/**
	 * 获取日志代理
	 * @return
	 */
	public ArithmeticCalculator getLoggingProxy() {
		
		ArithmeticCalculator proxy = null;
		
		//代理对象由哪个类加载器负责加载
		ClassLoader loader = target.getClass().getClassLoader();
		//代理对象的类型，即其中有哪些方法
		@SuppressWarnings("rawtypes")
		Class[] interfaces = new Class[] {ArithmeticCalculator.class};
		//当调用代理对象其中的方法是该执行的代码
		InvocationHandler h = new InvocationHandler() {
			
			/**
			 * proxy:正在返回的代理对象，一般情况下，在invoke方法中都不使用该对象
			 * method:正在被调用的方法
			 * args:调用方法是传入的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				String methodName = method.getName();
				//日志
				System.out.println("The method " +  methodName + " begin with " + Arrays.asList(args));
				//执行方法
				Object result = null;
				
				try {
					//前置通知
					result = method.invoke(target, args);
					//返回通知，可以访问到方法的返回值
				} catch (Exception e) {
					e.printStackTrace();
					//异常通知，可以访问到方法出现的异常
				}
				
				//因为方法可能抛	异常，所以可能访问不到方法的返回值
				//日志
				System.out.println("The method " + methodName + " end with " + result);
				return result;
			}
		};
		
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
		
	}
}
