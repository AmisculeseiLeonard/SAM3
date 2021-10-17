package mtsd.sam3.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mtsd.sam3.dao.EmployeeDao;
import mtsd.sam3.entities.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeDao.FindAll();
	}

}
