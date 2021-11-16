package mtsd.sam3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mtsd.sam3.domainServices.EmployeeServices;
import mtsd.sam3.entities.Employee;
import mtsd.sam3.entities.Role;
import mtsd.sam3.repository.EmployeeRepository;
import mtsd.sam3.restControllers.EmployeeRestController;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class ServiceTest {
	
	@Autowired
    EmployeeRepository employeeRepository;
	
	
	@InjectMocks
	EmployeeRestController employeeRestController;
	
	@BeforeAll
	public void createEmployee( ) {
		MockHttpServletRequest request = new MockHttpServletRequest();
	    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	     
	    @SuppressWarnings("deprecation")
		Employee employee = new Employee("Jhon", "Smith", "072342333", "jhon.smith@gmail.com", new Date(1987, 11, 22));
	    
	    Role role = new Role("Project manager", true);
	    employee.setRole(role);
	     
	    employeeRestController.createEmployee(employee);
	}

	@Test
	void test() {
		assertTrue(EmployeeServices.isTeamLeader(employeeRepository, 1));
	}

}
