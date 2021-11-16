package mtsd.sam3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mtsd.sam3.entities.Employee;
import mtsd.sam3.restControllers.EmployeeRestController;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRestControllerTest {
	
	@InjectMocks
    EmployeeRestController employeeController;
	

	@Test
	void testGetAllEmployees() {
		MockHttpServletRequest request = new MockHttpServletRequest();
	    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	    
	    employeeController.getAllEmployees();
	    
	    
	}

	@Test
	void testGetUpperManagementEmployees() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateEmployee() {
		MockHttpServletRequest request = new MockHttpServletRequest();
	    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	     
	    @SuppressWarnings("deprecation")
		Employee employee = new Employee("Jhon", "Smith", "072342333", "jhon.smith@gmail.com", new Date(1987, 11, 22));
	     
	    employeeController.createEmployee(employee);
	}

	@Test
	void testGetEmployeeById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeeTeamsById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteEmployee() {
		fail("Not yet implemented");
	}

}
