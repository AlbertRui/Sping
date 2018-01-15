package me.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态工厂方法：直接调用一个类的静态方法就可以返回bean实例
 * @author Administrator
 *
 */
public class StaticCarFactory {

	private static Map<String, Car> cars = new HashMap<String, Car>();
	static {
		cars.put("Audi", new Car("Audi", 3000000));
		cars.put("Ford", new Car("Ford", 4000000));
	}
	
	/**
	 * 静态工厂方法
	 * @param carName
	 * @return
	 */
	public static Car getCar(String carName) {
		return cars.get(carName);
	}
}
