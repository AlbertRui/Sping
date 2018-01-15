package me.spring.beans.generic.di;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

	/**
	 * 泛型注入，子类通过User类型依赖
	 * 控制台打印:UserRepository 即BaseRepository的子类
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
