package me.spring.tx.xml.service.impl;

import me.spring.tx.xml.BookShopDao;
import me.spring.tx.xml.service.BookShopService;

public class BookShopServiceImpl implements BookShopService{

	private BookShopDao bookShopDao;
	
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	@Override
	public void purchase(String username, String isbn) {
		//��ȡ��ĵ���
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//������Ŀ��
		bookShopDao.updateBookStock(isbn);
		//�����û����
		bookShopDao.updateUserAccount(username, price);
	}

}
