package com.macu.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.macu.entity.Employee;
import com.macu.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class DemoController {
		
	
	private EmployeeService employeeService;
	
	 public DemoController(EmployeeService employeeService) {
		 this.employeeService = employeeService;
		}
	 
	 @GetMapping("/")
	 public String homePage() {
		 return "home";
	 }
	
	@GetMapping("/index")
	public String showTime(Model model) {
		return "index";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("theEmployee") Employee employee) {
		Employee newEmployee = employee;
		System.out.println("Form Employee:"+employee);
		employeeService.save(newEmployee);

//		List<Employee> employees = employeeService.findAll(); 
//		System.out.println(employees);
//		model.addAttribute("employees",employees);
		return "redirect:/employees/list";
	}
	
	
	@GetMapping("/list")
	public String listEmployees(Model model)	 {
		
		List<Employee> employees = employeeService.findAll();
		Comparator<Employee> comp  = new Employee();
		
		Collections.sort(employees, comp);
		//System.out.println(employees);
		model.addAttribute("employees",employees);
		return "list-employees";
	}
	
	
	@PostMapping("/update")
	public String update(@ModelAttribute("theEmployee") Employee employee) {
		List<Employee> employees ; 
		System.out.println("update reuesting"+employee);
		employeeService.update(employee);
		return "redirect:/employees/list";
	}

	
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theEmployeeId,Model model) {
		employeeService.deleteByID(theEmployeeId);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormFeed")
	public String showFormFeed(Model model) {
		//System.out.println("call rest of show formFeed");
		Employee employee = new Employee();
		model.addAttribute("theEmployee",employee);
		return "employee-form";
	}
	
	@GetMapping("/showFormUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theEmployeeId, Model model) {
		Employee employee = employeeService.findByID(theEmployeeId);
		//System.out.println("updatin form call"+employee);
		model.addAttribute("theEmployee",employee);
		return "update-form";
	}
	
}
