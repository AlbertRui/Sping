package me.spring.tx;

public interface BookShopDao {

	/**
	 * ������Ż�ȡ��ĵ���
	 * @param Isbn
	 * @return
	 */
	public int findBookPriceByIsbn(String isbn);
	
	/**
	 * ʹ�������Ӧ�Ŀ���һ
	 * @param Isbn
	 */
	public void updateBookStock(String isbn);
	
	/**
	 * �����û����˻���ʹusername��balance - price
	 * @param username
	 * @param price
	 */
	public void updateUserAccount(String username, int price);
}
