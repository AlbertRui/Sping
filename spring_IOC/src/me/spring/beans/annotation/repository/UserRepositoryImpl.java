package me.spring.beans.annotation.repository;

import org.springframework.stereotype.Repository;

/**
 * ģ��־û���
 * @author Administrator
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

	@Override
	public void save() {

		System.out.println("UserRepository's save method");
	}

}
