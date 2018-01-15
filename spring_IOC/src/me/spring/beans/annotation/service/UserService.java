package me.spring.beans.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.spring.beans.annotation.repository.UserRepository;

/**
 * Ä£ÄâÒµÎñ²ã
 * @author Administrator
 *
 */
@Service
public class UserService {

	@Autowired
	@Qualifier("userJdbcRepository")
	private UserRepository userRepository;
	public void add() {
		System.out.println("UserService's add method");
		userRepository.save();
	}
}
