package me.spring.beans.generic.di;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

	/**
	 * ����ע�룬����ͨ��User��������
	 * ����̨��ӡ:UserRepository ��BaseRepository������
	 * add..
	 * me.spring.beans.generic.di.UserRepository@6fb0d3ed
	 */
	@Autowired
	protected  BaseRepository<T> baseRepository;
	public void add() {
		System.out.println("add..");
		System.out.println(baseRepository);
	}
}
