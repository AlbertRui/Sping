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
	 * 1.ʹ��propagationָ������Ĵ�����Ϊ������ǰ�����﷽��������һ�����﷽������ʱ
	 * ���ʹ�����Ĭ��ֵΪ��REQUIRED,��ʹ�õ��÷���������
	 * REQUIRED_NEW��ʹ���Լ���������õ�������������ﱻ����
	 * 2.ʹ��isolationָ������ĸ��뼶����õ��ǣ�READ_COMMITTED
	 * 3.Ĭ�������Spring������ʽ�������������ʱ�쳣���лع���Ҳ����ͨ����Ӧ�����Խ������ã�ͨ�������ȡĬ��ֵ����
	 * 4.ʹ��readOnlyָ�������Ƿ�ɶ�����ʾ�������ֻ��ȡ���ݵ����������ݣ�Ĭ�������ֵΪfalse
	 * �������԰������ݿ������Ż�����������Ҫ����ֻ������Ӧ��Ϊ��readOnly=true
	 * 5.ʹ��timeoutָ��ǿ�ƻع�����֮ǰ�������ռ�õ�ʱ�䣬��λΪ��
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
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
