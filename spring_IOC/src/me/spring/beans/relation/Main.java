package me.spring.beans.relation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.spring.beans.autowire.Address;
import me.spring.beans.autowire.Person;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relations.xml");
		Address address = (Address) ctx.getBean("address2");
		Person person = (Person) ctx.getBean("person");
		System.out.println(address);
		System.out.println(person);
	}
}
