package me.spring.tx.xml.service;

import java.util.List;

public interface Cashier {
	
	/**
	 * ����౾��
	 * @param username
	 * @param isbns
	 */
	public void checkout(String username, List<String> isbns);
}
