package net.codejava.employee.dao;

import java.util.List;

import net.codejava.employee.model.Employee;
import net.codejava.employee.model.Login;

public interface EmployeeDAO {

	public int save(Employee employee);

	public int update(Employee employee);

	public Employee get(Integer emp_id);

	public int delete(Integer emp_id);

	public List<Employee> list();

	public Employee validateEmployee(Login login);

}
