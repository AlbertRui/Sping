package me.spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义的FactoryBean需要实现FactoryBean接口
 * @author Administrator
 *
 */
public class CarFactoryBean implements FactoryBean<Car> {

	private String brand;

	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * 返回bean的对象
	 */
	@Override
	public Car getObject() throws Exception {
		return new Car(brand, 5000000);
	}

	/**
	 * 返回bean的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	/**
	 * 是不是单例的
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
