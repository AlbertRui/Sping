package me.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
//		HelloWorld helloWorld = new HelloWorld();
//		helloWorld.setName("Spring");
//		helloWorld.hello();
		
		//1.����Spring��IOC��������
		//ApplicationContext����IOC����
		//ClassPathXmlApplicationContext��ApplicationContext��ʵ���࣬��ʵ�������·���¼��������ļ�
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.��IOC�����л�ȡbeanʵ��
		//����id��λ��IOC�����е�bean
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		//�������ͷ���IOC�����е�bean��������IOC������ֻ����һ�������͵�bean
		//HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		//3.����hello����
		helloWorld.hello();
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		car = (Car) ctx.getBean("car2");
		System.out.println(car);
		Person person = (Person) ctx.getBean("person2");
		System.out.println(person);
	}
	
}
