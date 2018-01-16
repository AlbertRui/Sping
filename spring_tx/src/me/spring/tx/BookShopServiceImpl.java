package me.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService{

	@Autowired
	private BookShopDao bookShopDao;
	
	/**
	 * @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, noRollbackFor={UserAccountException.class})
	 * 1.使用propagation指定事物的传播行为，及当前的事物方法被另外一个事物方法调用时
	 * 如何使用事物，默认值为：REQUIRED,及使用调用方法的事物
	 * REQUIRED_NEW：使用自己的事物，调用的事物，方法的事物被挂起
	 * 2.使用isolation指定事务的隔离级别，最常用的是：READ_COMMITTED
	 * 3.默认情况下Spring的声明式事物对所有运行时异常进行回滚，也可以通过对应的属性进行设置，通常情况下取默认值即可
	 * 4.使用readOnly指定事物是否可读，表示这个事物只读取数据但不更新数据，默认情况下值为false
	 * 这样可以帮助数据库引擎优化事物，若真的需要设置只读，则应该为：readOnly=true
	 * 5.使用timeout指定强制回滚事务之前事物可以占用的时间，单位为秒
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	@Override
	public void purchase(String username, String isbn) {
		//获取书的单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//更新书的库存
		bookShopDao.updateBookStock(isbn);
		//更新用户余额
		bookShopDao.updateUserAccount(username, price);
	}

}
