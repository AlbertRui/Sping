package me.spring.tx.xml;

import java.util.List;

public interface Cashier {
	
	/**
	 * ¹ºÂò¶à±¾Êé
	 * @param username
	 * @param isbns
	 */
	public void checkout(String username, List<String> isbns);
}
