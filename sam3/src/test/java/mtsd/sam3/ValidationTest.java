package mtsd.sam3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import mtsd.sam3.entities.Employee;

class ValidationTest {

	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	@BeforeAll
	public static void createValidator() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@AfterAll
	public static void close() {
		validatorFactory.close();
	}

	@Test
	void shouldHaveNoViolations() {

		Employee employee = new Employee();
		employee.setFirstName("Michael");
		employee.setLastName("Scott");
		employee.setPhoneNumber("07234234322");
		employee.setEmail("michaelscott@gmail.com");
		
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		
		 assertEquals(violations.size(), 0);
		
		    
		    
		//assertEquals("First name is mandatory", violation.getMessage());
	}
	
	@Test
	void blankFirstName() {
		Employee employee = new Employee();
		employee.setFirstName("");
		employee.setLastName("scott");
		employee.setPhoneNumber("07234234322");
		employee.setEmail("mscott@gmail.com");
		
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Employee> violation = violations.iterator().next();
		assertEquals("First name is mandatory", violation.getMessage());
		assertEquals("firstName", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
		
	}
	
	@Test
	void wrongEmailPattern() {
		Employee employee = new Employee();
		employee.setFirstName("Michael");
		employee.setLastName("scott");
		employee.setPhoneNumber("07234234322");
		employee.setEmail("mscott.com");
		
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Employee> violation = violations.iterator().next();
		assertEquals("The format of email is invalid", violation.getMessage());
		assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("mscott.com", violation.getInvalidValue());
		
	}
	
	@Test
	void getsFutureDate() {
		Employee employee = new Employee();
		employee.setFirstName("Michael");
		employee.setLastName("scott");
		employee.setPhoneNumber("07234234322");
		employee.setEmail("mscott@gmail.com");
		employee.setBirthDate(new Date(System.currentTimeMillis() + 100000) );
		
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Employee> violation = violations.iterator().next();
		assertEquals("must be a past date", violation.getMessage());
		assertEquals("birthDate", violation.getPropertyPath().toString());
        
		
	}
	
	@Test
	void sizeExceedsLimit() {
		Employee employee = new Employee();
		employee.setFirstName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		employee.setLastName("scott");
		employee.setPhoneNumber("07234234322");
		employee.setEmail("mscott@gmail.com");
		employee.setBirthDate(new Date(System.currentTimeMillis() - 100000) );
		
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Employee> violation = violations.iterator().next();
		assertEquals("size must be between 0 and 100", violation.getMessage());
		assertEquals("firstName", violation.getPropertyPath().toString());
		assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", violation.getInvalidValue());
		
	}
	
	

}
