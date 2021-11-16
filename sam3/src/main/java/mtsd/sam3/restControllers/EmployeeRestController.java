package mtsd.sam3.restControllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mtsd.sam3.domainServices.EmployeeServices;
import mtsd.sam3.entities.Employee;
import mtsd.sam3.entities.Team;
import mtsd.sam3.exeption.ResourceNotFoundException;
import mtsd.sam3.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	
	@GetMapping("employees/upperManagement")
	public List<Employee> getUpperManagementEmployees() {
		return EmployeeServices.getUpperManagementEmployees(employeeRepository);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody @Valid Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " doesn't exist"));
		return ResponseEntity.ok(employee);
	}
	
	@GetMapping("employees/{id}/teams")
	public ResponseEntity<List<Team>> getEmployeeTeamsById(@PathVariable int id) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " doesn't exist"));
		
		return ResponseEntity.ok(employee.getTeams());
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody @Valid Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " doesn't exist"));
		
		employee.setId(employeeDetails.getId());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setBirthDate(employeeDetails.getBirthDate());
		employee.setEmail(employeeDetails.getEmail());
		employee.setPhoneNumber(employee.getPhoneNumber());
		employee.setRole(employeeDetails.getRole());
		employee.setTeams(employeeDetails.getTeams());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
		
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " doesn't exist"));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	

}
