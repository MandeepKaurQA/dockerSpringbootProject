package com.qait.rest.controller;

import com.qait.beans.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {
	@Autowired
	private EmployeeDOA employeeDOA;
	private static final String welcomemsg = "Welcome Mr. %s!";

	@GetMapping("/welcome/user")
	@ResponseBody
	public Welcome welcomeUser(
			@RequestParam(name = "name", required = false, defaultValue = "Java Fan") String name) {
		return new Welcome(String.format(welcomemsg, name));
	}

	@GetMapping("/getEmployees")
	@ResponseBody
	public List<Employee> getEmployees() {
		List<Employee> list = employeeDOA.getAllEmployees();
		return list;
	}

	@GetMapping("/employee/{empNo}")
	@ResponseBody
	public Employee getEmployee(@PathVariable("empNo") String empNo) {
		return employeeDOA.getEmployee(empNo);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register/employee")
	@ResponseBody
	public Employee addEmployee(@RequestBody Employee emp) {

		System.out.println("(Service Side) Creating employee: "
				+ emp.getEmpNo());

		return employeeDOA.addEmployee(emp);
	}

	
	@RequestMapping(value = "update/employee", //
	method = RequestMethod.PUT, //
	produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee emp) {

		System.out
				.println("(Service Side) Editing employee: " + emp.getEmpNo());

		return employeeDOA.updateEmployee(emp);
	}
	
	 @RequestMapping(value = "delete/employee/{empNo}", //
	            method = RequestMethod.DELETE, //
	            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public void deleteEmployee(@PathVariable("empNo") String empNo) {
	 
	        System.out.println("(Service Side) Deleting employee: " + empNo);
	 
	        employeeDOA.deleteEmployee(empNo);
	    }
}
