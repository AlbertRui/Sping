package me.spring.tx;

public interface BookShopDao {

	/**
	 * 根据书号获取书的单价
	 * @param Isbn
	 * @return
	 */
	public int findBookPriceByIsbn(String isbn);
	
	/**
	 * 使书号所对应的库存减一
	 * @param Isbn
	 */
	public void updateBookStock(String isbn);
	
	/**
	 * 更新用户的账户余额，使username的balance - price
	 * @param username
	 * @param price
	 */
	public void updateUserAccount(String username, int price);
}
