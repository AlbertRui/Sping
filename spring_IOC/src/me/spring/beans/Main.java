package me.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
//		HelloWorld helloWorld = new HelloWorld();
//		helloWorld.setName("Spring");
//		helloWorld.hello();
		
		//1.创建Spring的IOC容器对象
		//ApplicationContext代表IOC容器
		//ClassPathXmlApplicationContext是ApplicationContext的实现类，该实现类从类路径下加载配置文件
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从IOC容器中获取bean实例
		//利用id定位到IOC容器中的bean
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		//利用类型返回IOC容器中的bean，担忧求IOC容器中只能有一个该类型的bean
		//HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		//3.调用hello方法
		helloWorld.hello();
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		car = (Car) ctx.getBean("car2");
		System.out.println(car);
		Person person = (Person) ctx.getBean("person2");
		System.out.println(person);
	}
	
}
