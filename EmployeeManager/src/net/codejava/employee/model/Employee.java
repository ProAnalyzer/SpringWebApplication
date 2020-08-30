package net.codejava.employee.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "EmpTable")
public @Data class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;

	@Column(name = "firstName", nullable = false)
	@NotEmpty(message = "Please, Enter your First Name")
	@Size(min = 2, max = 30)
	@Pattern(regexp = "[a-zA-Z]*", message = "Please enter only Letters")
	private String firstName;

	@Column(name = "middleName", nullable = true)
	@Size(min = 2, max = 30)
	@Pattern(regexp = "[a-zA-Z]*", message = "Please enter only Letters")
	private String middleName;

	@Column(name = "lastName", nullable = false)
	@NotEmpty(message = "Please, Enter your Last Name")
	@Size(min = 2, max = 30)
	@Pattern(regexp = "[a-zA-Z]*", message = "Please enter only Letters")
	private String lastName;

	@Column(name = "gender", nullable = false)
	@NotEmpty(message = "Please, Choose your gender")
	private String gender;

	@Column(name = "dob", nullable = false)
	//@Past
	//@NotEmpty(message = "Please, Put your Date Of Birth")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dob;

	@Column(name = "age", nullable = false)
	//@NotEmpty(message = "Please, Enter your Age between 18-70")
	//@Min(18)
	//@Max(70)
	//@Pattern(regexp = "[0-9]*", message = "Please enter only Numbers")
	private Integer age;

	@Column(name = "startDate", nullable = false)
	//@NotEmpty(message = "Please, Enter your Start Date of Journey")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate;

	@Column(name = "salary", nullable = false)
	//@NotEmpty(message = "Please, Enter your Salary above 10000")
	//@Min(10000)
	//@Pattern(regexp = "[0-9]*", message = "Please enter only Numbers")
	private Float salary;

	@Column(name = "city", nullable = false)
	//@NotEmpty(message = "Please, Enter your City")
	//@Size(min = 2, max = 30)
	@Pattern(regexp = "[a-zA-Z]*", message = "Please enter only Letters")
	private String city;

	@Column(name = "phone", nullable = false)
	//@NotEmpty(message = "Please, Enter your Phone Number")
	//@Size(min = 10, max = 10)
	//@Pattern(regexp = "[0-9]*", message = "Please enter only Numbers")
	private Number phone;

	@Column(name = "address", nullable = false)
	//@NotEmpty(message = "Please, Enter your Address")
	//@Size(min = 2, max = 150)
	private String address;

	@Column(name = "email", nullable = false)
	//@NotEmpty(message = "Please, Enter your Email")
	@Email
	//@Pattern(regexp = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}", message = "Please maintain proper Email patterns")
	private String email;

	@Column(name = "jobProfile", nullable = false)
	//@NotEmpty(message = "Please, Enter your Job Profile")
	//@Size(min = 3, max = 20)
	//@Pattern(regexp = "[a-zA-Z]*", message = "Please enter only Letters")
	private String jobProfile;

	@Column(name = "userName", nullable = false)
	@NotEmpty(message = "Please, Enter your User Name")
	@Size(min = 3, max = 10)
	@Pattern(regexp = "[a-zA-Z0-9]*", message = "Please enter the combination of letters and Numbers")
	private String userName;

	@Column(name = "password", nullable = false)
	@Size(min = 6, max = 10, message = "Please, Enter your Password")
	@Pattern(regexp = "^(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*\\d.*\\d)(?=.*\\W.*\\W)[a-zA-Z0-9\\S]{9,}", message = "Please, maintain the above format")
	private String password;

	@Column(name = "confirmPassword", nullable = false)
	@NotEmpty(message = "Please, Repeat your Password")
	@Pattern(regexp = "^(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*\\d.*\\d)(?=.*\\W.*\\W)[a-zA-Z0-9\\S]{9,}", message = "Please, repeat the same Password")
	private String confirmPassword;

	public Employee() {

	}

	public Employee(Integer empId, String firstName, String middleName, String lastName, String gender, Date dob, Integer age,
			Date startDate, Float salary, String city, Number phone, String address, String email,
			String jobProfile, String userName, String password, String confirmPassword) {

		this(firstName, middleName, lastName, gender, dob, age, startDate, salary, city, phone, address, email,
				jobProfile, userName, password, confirmPassword);

		this.empId = empId;
	}

	public Employee(String firstName, String middleName, String lastName, String gender, Date dob, Integer age,
			Date startDate, Float salary, String city, Number phone, String address, String email,
			String jobProfile, String userName, String password, String confirmPassword) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.age = age;
		this.startDate = startDate;
		this.salary = salary;
		this.city = city;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.jobProfile = jobProfile;
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

}
