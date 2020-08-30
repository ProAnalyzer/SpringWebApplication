package net.codejava.employee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.employee.dao.EmployeeDAO;
import net.codejava.employee.model.Employee;
import net.codejava.employee.model.Login;
//import net.codejava.employee.repository.EmployeeRepository;

@Controller
public class MainController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	//@Autowired
	//private EmployeeRepository empRepo;

	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) {
		List<Employee> listEmployee = employeeDAO.list();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("index");

		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newEmployee(ModelAndView model) {
		Employee newEmployee = new Employee();
		model.addObject("employee", newEmployee);
		model.setViewName("employee_form");

		return model;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
		if (employee.getEmpId() == null) {
			if (result.hasErrors()) {
				ModelAndView model = new ModelAndView("employee_form");
				return model;
			} else {
				employeeDAO.save(employee);
			}
		} else {
			if (result.hasErrors()) {
				ModelAndView model = new ModelAndView("employee_form");
				return model;
			} else {
				employeeDAO.update(employee);
			}
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request) {

		Integer empId = Integer.parseInt(request.getParameter("empId"));
		Employee employee = employeeDAO.get(empId);

		ModelAndView model = new ModelAndView("employee_form");
		model.addObject("employee", employee);

		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@RequestParam Integer empId) {
		employeeDAO.delete(empId);

		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("login");
		model.addObject("login", new Login());
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView model = null;
		Employee employee = employeeDAO.validateEmployee(login);
		if (null != employee) {
			model = new ModelAndView("welcome");
			model.addObject("firstName", employee.getFirstName());
			model.addObject("middleName", employee.getMiddleName());
			model.addObject("lastName", employee.getLastName());
			model.addObject("gender", employee.getGender());
			model.addObject("dob", employee.getDob());
			model.addObject("age", employee.getAge());
			model.addObject("startDate", employee.getStartDate());
			model.addObject("salary", employee.getSalary());
			model.addObject("city", employee.getCity());
			model.addObject("phone", employee.getPhone());
			model.addObject("address", employee.getAddress());
			model.addObject("email", employee.getEmail());
			model.addObject("jobProfile", employee.getJobProfile());
		} else {
			model = new ModelAndView("login");
			model.addObject("message", "Username or Password is wrong!!");
		}
		return model;
	}
}
