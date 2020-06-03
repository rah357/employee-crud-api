package com.macu.service;

import java.util.List;

import com.macu.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findByID(int theID) ;

	public void save(Employee theEmployee);
	
	public void deleteByID(int theID);

	public void update(Employee employee);
	
	
}
