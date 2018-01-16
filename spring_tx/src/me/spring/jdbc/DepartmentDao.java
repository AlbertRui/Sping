package me.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * ���Ƽ�ʹ��JdbcDaoSupport���Ƽ�ֱ��ʹ��JdbcTemplate��ΪDao��ĳ�Ա����
 * @author Administrator
 *
 */
@Repository
public class DepartmentDao extends JdbcDaoSupport {

	/**
	 * ���ø����setDataSource����
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}
}
