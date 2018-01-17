package me.spring.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * ��̬����ʵ����־
 * @author Administrator
 *
 */
public class ArithmeticCalculatorLoggingProxy {

	//Ҫ����Ķ���
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	
	/**
	 * ��ȡ��־����
	 * @return
	 */
	public ArithmeticCalculator getLoggingProxy() {
		
		ArithmeticCalculator proxy = null;
		
		//����������ĸ���������������
		ClassLoader loader = target.getClass().getClassLoader();
		//�����������ͣ�����������Щ����
		@SuppressWarnings("rawtypes")
		Class[] interfaces = new Class[] {ArithmeticCalculator.class};
		//�����ô���������еķ����Ǹ�ִ�еĴ���
		InvocationHandler h = new InvocationHandler() {
			
			/**
			 * proxy:���ڷ��صĴ������һ������£���invoke�����ж���ʹ�øö���
			 * method:���ڱ����õķ���
			 * args:���÷����Ǵ���Ĳ���
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				String methodName = method.getName();
				//��־
				System.out.println("The method " +  methodName + " begin with " + Arrays.asList(args));
				//ִ�з���
				Object result = null;
				
				try {
					//ǰ��֪ͨ
					result = method.invoke(target, args);
					//����֪ͨ�����Է��ʵ������ķ���ֵ
				} catch (Exception e) {
					e.printStackTrace();
					//�쳣֪ͨ�����Է��ʵ��������ֵ��쳣
				}
				
				//��Ϊ����������	�쳣�����Կ��ܷ��ʲ��������ķ���ֵ
				//��־
				System.out.println("The method " + methodName + " end with " + result);
				return result;
			}
		};
		
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
		
	}
}
