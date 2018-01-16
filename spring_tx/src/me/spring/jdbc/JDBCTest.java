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
 * ����Spring��JdbcTemplate����
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
	 * ʹ�þ�������ʱ����ʹ��update(String sql, SqlParameterSource paramSource) ���и��²���
	 * 1.SQL����еĲ��������������һ��
	 * 2.ʹ��SqlParameterSource��BeanPropertySqlParameterSourceʵ������Ϊ����
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
	 * ����Ϊ��������
	 * 1.�ŵ㣺���ж��������������ȥ��Ӧλ�ã�ֱ�Ӷ�Ӧ������������ά��
	 * 2.ȱ�㣺��Ϊ�鷳
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
	 * ִ���������£�������insert��update��delete ���һ��������Object[]
	 * ��List���ͣ�ӦΪ�޸�һ����¼��Ҫһ��object������
	 */
	@Test
	public void testBatchUpdate() {
		String sql = "INSERT INTO employees(last_name, email, dept_id) VALUES(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[] { "AA", "AA@123.com", 1 });
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	/**
	 * ִ��insert��update��delete
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
	 * ��ѯʵ����ļ��� ע�ⲻ���õ�queryForList����
	 */
	@Test
	public void testQueryForList() {
		String sql = "SELECT id, last_name lastName, email FROM employees WHERE id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper, 1);
		System.out.println(employees);
	}

	/**
	 * �����ݿ��ȡһ����¼��ʵ�ʵõ�һ������ ע�⣺���ǵ���queryForObject(String sql, Class<Employee>
	 * requiredType, Object... args)������� ����Ҫ���ã�queryForObject(String sql,
	 * RowMapper<Employee> rowMapper, Object... args)�������
	 * 1.���е�RowMapperָ�����ȥӳ���������У����õ�ʵ����Ϊ��BeanPropertyRowMapper
	 * 2.ʹ��SQL���еı�����������������������ӳ�䡣���磺last_name lastName
	 * 3.��֧�ּ������Ե�ӳ�䣬JdbcTemplateֻ��һ��jdbc��С���ߣ�������ORM���
	 */
	@Test
	public void testQueryForObject() {
		String sql = "SELECT id, last_name lastName, email, dept_id as \"department.id\" FROM employees WHERE id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
	}

	/**
	 * ��ȡ�����е�ֵ������ͳ�Ʋ�ѯ
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "SELECT count(id) FROM employees";
		Long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
}
