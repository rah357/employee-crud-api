package com.macu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macu.dao.EmployeeRepository;
import com.macu.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl( EmployeeRepository employeeDAO) {
		this.employeeRepository = employeeDAO;
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findByID(int theID) {
		Optional<Employee> result = employeeRepository.findById(theID);
		

		Employee employee = null;
		
		if(result.isPresent()) {	
			employee = result.get();
		}else {
			throw new RuntimeException("Employee id is not Exception");
		}
		
		
		
		return employee;
	}

	@Override	
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteByID(int theID) {
		employeeRepository.deleteById(theID);
	}

	@Override
	@Transactional
	public void update(Employee employee) {
		employeeRepository.setUserInfoById(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
		
		//Employee existEmployee = findByID(employee.getId());
		System.out.println("updating employee dao");
	}

}
