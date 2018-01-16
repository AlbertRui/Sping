package me.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * 测试Spring的JdbcTemplate工具
 * @author Administrator
 *
 */
public class JDBCTest {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	private EmployeeDao employeeDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		employeeDao = (EmployeeDao) ctx.getBean("employeeDao");
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");
	}

	/**
	 * 使用具名参数时可以使用update(String sql, SqlParameterSource paramSource) 进行更新操作
	 * 1.SQL语句中的参数名和类的属性一致
	 * 2.使用SqlParameterSource的BeanPropertySqlParameterSource实现类作为参数
	 */
	@Test
	public void testNamedParameterJdbcTemplate2() {
		String sql = "INSERT INTO employees(last_name, email) VALUES(:lastName, :email)";
		Employee employee = new Employee();
		employee.setEmail("Liu@163.com");
		employee.setLastName("Liu");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
	
	/**
	 * 可以为参数起名
	 * 1.优点：若有多个参数，则不用再去对应位置，直接对应参数名，便于维护
	 * 2.缺点：较为麻烦
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "INSERT INTO employees(last_name, email, dept_id) VALUES(:lastName, :email, :deptid)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lastName", "Zhang");
		paramMap.put("email", "Zhang@123.com");
		paramMap.put("deptid", 3);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

	@Test
	public void testEmployeeDao() {
		Employee employee = employeeDao.getEmployee(1);
		System.out.println(employee);
	}

	/**
	 * 执行批量更新：批量的insert，update，delete 最后一个参数是Object[]
	 * 的List类型，应为修改一调记录需要一个object的数组
	 */
	@Test
	public void testBatchUpdate() {
		String sql = "INSERT INTO employees(last_name, email, dept_id) VALUES(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[] { "AA", "AA@123.com", 1 });
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	/**
	 * 执行insert，update，delete
	 */
	@Test
	public void testUpdate() {
		String sql = "UPDATE employees SET last_name = ? WHERE id = ?";
		jdbcTemplate.update(sql, "jack", 5);
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}

	/**
	 * 查询实体类的集合 注意不是用的queryForList方法
	 */
	@Test
	public void testQueryForList() {
		String sql = "SELECT id, last_name lastName, email FROM employees WHERE id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper, 1);
		System.out.println(employees);
	}

	/**
	 * 从数据库获取一条记录，实际得到一个对象 注意：不是调用queryForObject(String sql, Class<Employee>
	 * requiredType, Object... args)这个方法 而需要调用：queryForObject(String sql,
	 * RowMapper<Employee> rowMapper, Object... args)这个方法
	 * 1.其中的RowMapper指定如何去映射结果集的行，常用的实现类为：BeanPropertyRowMapper
	 * 2.使用SQL中列的别名完成列名和类的属性名的映射。例如：last_name lastName
	 * 3.不支持级联属性的映射，JdbcTemplate只是一个jdbc的小工具，而不是ORM框架
	 */
	@Test
	public void testQueryForObject() {
		String sql = "SELECT id, last_name lastName, email, dept_id as \"department.id\" FROM employees WHERE id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
	}

	/**
	 * 获取单个列的值，或作统计查询
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "SELECT count(id) FROM employees";
		Long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
}
