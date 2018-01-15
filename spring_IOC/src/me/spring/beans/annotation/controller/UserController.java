package me.spring.beans.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import me.spring.beans.annotation.service.UserService;

/**
 * Ä£Äâ±íÏÖ²ã
 * @author Administrator
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	public void execute() {
		System.out.println("UserController's execute method");
		userService.add();
	}
}
