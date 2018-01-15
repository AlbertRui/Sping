package me.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * ʵ����������:ʵ�������ķ�����������Ҫ�������������ڵ��ù�����ʵ����������beanʵ��
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
	 * ʵ����������
	 * @param brand
	 * @return
	 */
	public Car getCar(String brand) {
		return cars.get(brand);
	}
}
