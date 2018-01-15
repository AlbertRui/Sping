package me.spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * �Զ����FactoryBean��Ҫʵ��FactoryBean�ӿ�
 * @author Administrator
 *
 */
public class CarFactoryBean implements FactoryBean<Car> {

	private String brand;

	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * ����bean�Ķ���
	 */
	@Override
	public Car getObject() throws Exception {
		return new Car(brand, 5000000);
	}

	/**
	 * ����bean������
	 */
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	/**
	 * �ǲ��ǵ�����
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
