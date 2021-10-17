package mtsd.sam3.dao;

import java.util.List;

import mtsd.sam3.entities.Employee;

public interface EmployeeDao {
	
	public List<Employee> FindAll();
}
