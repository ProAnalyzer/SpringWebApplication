package net.codejava.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.employee.model.Employee;

/**
 * @author Pronay Ghosh
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
