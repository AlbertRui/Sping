package me.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法:实力工厂的方法，即现需要创建工厂本身，在调用工厂的实例方法返回bean实例
 * @author Administrator
 *
 */
public class InstanceCarFactory {

	private Map<String, Car> cars = null;
	
	public InstanceCarFactory() {
		cars = new HashMap<String, Car>();
		cars.put("Audi", new Car("Audi", 3000000));
		cars.put("Ford", new Car("Ford", 4000000));
	}

	/**
	 * 实例工厂方法
	 * @param brand
	 * @return
	 */
	public Car getCar(String brand) {
		return cars.get(brand);
	}
}
