package net.codejava.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.codejava.employee.model.Employee;
import net.codejava.employee.model.Login;

public class EmployeeDAOImpl implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public EmployeeDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Employee e) {
		String sql = "INSERT INTO \"EmpTable\" (firstName, middleName, lastName, gender, DOB, age, startDate, salary, city, phone, address, email, jobProfile, userName, password, confirmPassword) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, e.getFirstName(), e.getMiddleName(), e.getLastName(), e.getGender(),
				e.getDob(), e.getAge(), e.getStartDate(), e.getSalary(), e.getCity(), e.getPhone(), e.getAddress(),
				e.getEmail(), e.getJobProfile(), e.getUserName(), e.getPassword(), e.getConfirmPassword());
	}

	@Override
	public int update(Employee e) {
		String sql = "UPDATE \"EmpTable\" SET firstName=?, middleName=?, lastName=?, gender=?, DOB=?, age=?, startDate=?, salary=?, city=?, phone=?, address=?, email=?, jobProfile=? WHERE empId=?";
		return jdbcTemplate.update(sql, e.getFirstName(), e.getMiddleName(), e.getLastName(), e.getGender(),
				e.getDob(), e.getAge(), e.getStartDate(), e.getSalary(), e.getCity(), e.getPhone(), e.getAddress(),
				e.getEmail(), e.getJobProfile(), e.getEmpId());
	}

	@Override
	public Employee get(Integer empId) {
		String sql = "SELECT * FROM \"EmpTable\" WHERE empId=" + empId;

		ResultSetExtractor<Employee> extractor = new ResultSetExtractor<Employee>() {

			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String firstName = rs.getString("firstName");
					String middleName = rs.getString("middleName");
					String lastName = rs.getString("lastName");
					String gender = rs.getString("gender");
					Date dob = rs.getDate("dob");
					Integer age = rs.getInt("age");
					Date startDate = rs.getDate("startDate");
					Float salary = rs.getFloat("salary");
					String city = rs.getString("city");
					Number phone = rs.getBigDecimal("phone");
					String address = rs.getString("address");
					String email = rs.getString("email");
					String jobProfile = rs.getString("jobProfile");
					String userName = rs.getString("userName");
					String password = rs.getString("password");
					String confirmPassword = rs.getString("confirmPassword");

					return new Employee(empId, firstName, middleName, lastName, gender, dob, age, startDate,
							salary, city, phone, address, email, jobProfile, userName, password, confirmPassword);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer empId) {
		String sql = "DELETE FROM \"EmpTable\" WHERE empId=" + empId;
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Employee> list() {
		String sql = "SELECT * FROM \"EmpTable\"";

		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer empId = rs.getInt("empId");
				String firstName = rs.getString("firstName");
				String middleName = rs.getString("middleName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				Integer age = rs.getInt("age");
				Date startDate = rs.getDate("startDate");
				Float salary = rs.getFloat("salary");
				String city = rs.getString("city");
				Number phone = rs.getBigDecimal("phone");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String jobProfile = rs.getString("jobProfile");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String confirmPassword = rs.getString("confirmPassword");

				return new Employee(empId, firstName, middleName, lastName, gender, dob, age, startDate, salary,
						city, phone, address, email, jobProfile, userName, password, confirmPassword);
			}
		};

		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Employee validateEmployee(Login login) {
		String sql = "SELECT * FROM \"EmpTable\" WHERE userName='" + login.getUserName() + "' AND password='"
				+ login.getPassword() + "'";
		List<Employee> employees = jdbcTemplate.query(sql, new EmployeeMapper());
		return employees.size() > 0 ? employees.get(0) : null;
	}

	class EmployeeMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();

			employee.setFirstName(rs.getString("firstName"));
			employee.setMiddleName(rs.getString("middleName"));
			employee.setLastName(rs.getString("lastName") );
			employee.setGender(rs.getString("gender"));
			employee.setDob(rs.getDate("dob"));
			employee.setAge(rs.getInt("age"));
			employee.setStartDate(rs.getDate("startDate"));
			employee.setSalary(rs.getFloat("salary"));
			employee.setCity(rs.getString("city"));
			employee.setPhone(rs.getBigDecimal("phone"));
			employee.setAddress(rs.getString("address"));
			employee.setEmail(rs.getString("email"));
			employee.setJobProfile(rs.getString("jobProfile"));
			employee.setUserName(rs.getString("userName"));
			employee.setPassword(rs.getString("password"));

			return employee;
		}
	}

}
