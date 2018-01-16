package me.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 不推荐使用JdbcDaoSupport，推荐直接使用JdbcTemplate作为Dao类的成员变量
 * @author Administrator
 *
 */
@Repository
public class DepartmentDao extends JdbcDaoSupport {

	/**
	 * 调用父类的setDataSource方法
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}
}
