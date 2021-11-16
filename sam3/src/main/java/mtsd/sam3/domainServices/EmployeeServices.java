package mtsd.sam3.domainServices;

import java.util.List;
import java.util.stream.Collectors;

import mtsd.sam3.entities.Employee;
import mtsd.sam3.entities.Team;
import mtsd.sam3.exeption.ResourceNotFoundException;
import mtsd.sam3.repository.EmployeeRepository;

public class EmployeeServices {
	
	
	public EmployeeServices() {
		super();
	}

	public static List<Employee> getUpperManagementEmployees(EmployeeRepository employeeRepository) {
		List<Employee> upperManagementEmployees = 
				(List<Employee>) employeeRepository
				.findAll()
				.stream()
				.filter(employee -> employee.getRole().isUpperManagement() == true)
				.collect(Collectors.toList());
		return upperManagementEmployees;
	}
	
	public static boolean isTeamLeader(EmployeeRepository employeeRepository, int id) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " doesn't exist"));
		
		return employee.getTeams().stream().anyMatch(team -> team.getTeamLeader().getId() == employee.getId());
		
	}
}
